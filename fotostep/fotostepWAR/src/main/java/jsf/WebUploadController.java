package jsf;

import business.importbusiness.ImageImporterLocal;
import business.model.database.Album;
import business.model.database.FormatEnum;
import business.model.database.User;
import business.model.databaseManager.albumManager.AlbumManagerLocal;
import business.model.databaseManager.userManager.UserManagerLocal;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;

public class WebUploadController {

    @EJB
    private ImageImporterLocal imageImporter;
    @EJB
    private AlbumManagerLocal albumManager;
    @EJB
    private UserManagerLocal userManager;
    private UploadedFile uploadedFile;
    private String description;
    private String tags;    
    private String albumName;

    // Constructeur
    public WebUploadController() {
    }

    // Validate
    public void validateUploadedFile(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {
        UploadedFile file = (UploadedFile) value;
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage error = new FacesMessage();
        error.setSeverity(FacesMessage.SEVERITY_ERROR);

        if (!(file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png"))) {
            error.setDetail("Le fichier n'a pas une extension valide (jpg ou png)");
            ctx.addMessage("msgs", error);
        } else if (file.getSize() > 2097152) {
            error.setDetail("Le fichier est trop volumineux (supérieur à 2 Mo)");
            ctx.addMessage("msgs", error);
        } else if (file.getSize() == 0) {
            error.setDetail("Le fichier est vide");
            ctx.addMessage("msgs", error);
        }


    }

    // Getters + Setters
    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
    
        
    public String getAlbumName() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        Integer myId = (Integer) httpSession.getAttribute("userId");
        User user = userManager.getUserById(myId);        
        String albumId = null;
        Album album = null;
        try {
            albumId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("AlbumId");
            album = albumManager.findAlbumById(Integer.parseInt(albumId));
        } catch (Exception e) {
            // Vous n'avez quand même pas cru que j'allais gérer les exceptions ? @Thomas
            album = albumManager.getDefaultAlbum(user);
        }
        return album.getName();
    }
    
    private void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    // Méthode pour enregistrer une picture
    public void submit() throws ValidatorException, IOException {

        if(uploadedFile == null)
        {
            FacesMessage err = new FacesMessage();
            err.setSummary("Vous n'avez pas choisi de fichier");
            err.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage("uploadForm", err);
            return;
        }
        Buffer buffer = ByteBuffer.wrap(uploadedFile.getBytes());
        FormatEnum format = null;
        int albumId = 0;

        if (uploadedFile.getContentType().equals("image/jpeg")) {
            format = FormatEnum.jpg;
        } else if (uploadedFile.getContentType().equals("image/png")) {
            format = FormatEnum.png;
        }

        Date date = new Date(System.currentTimeMillis());

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        Integer myId = (Integer) httpSession.getAttribute("userId");
        
        User user = userManager.getUserById(myId);
        Album album;
        try {
            albumId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("AlbumId"));
            album = albumManager.findAlbumById(albumId);
        } catch (Exception e) {
            // Vous n'avez quand même pas cru que j'allais gérer les exceptions ? @Thomas
            album = albumManager.getDefaultAlbum(user);
        }        

        InputStream in = new ByteArrayInputStream(uploadedFile.getBytes());
        BufferedImage bimg = ImageIO.read(in);

        if (description.isEmpty()) {
            description = "Pas de description";
        }

        String prefix = FilenameUtils.getBaseName(uploadedFile.getName());
        String suffix = FilenameUtils.getExtension(uploadedFile.getName());

        /* Solution 1 : les fichiers sont stockés sur le serveur 
        
         ServletContext theApplicationsServletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
         String path = theApplicationsServletContext.getRealPath("resources/images/") + "/user" + user.getIduser();*/

        /* Solution 2 : les fichiers sont stockés hors du serveur */
        Path systemPath = Paths.get(System.getProperty("user.home"));
        Path userDirPath = Paths.get("/fotosteppictures/user" + user.getIduser());

        Path dirPath = Paths.get(systemPath.toString() + userDirPath.toString());
        File repertory = new File(dirPath.toUri());
        repertory.mkdirs();

        //File file = File.createTempFile(prefix + "_", "." + suffix, new File(path));
        long serverTimeStamp = System.currentTimeMillis();
        Path picPath = Paths.get(dirPath.toString() + "/" + prefix + "_" + serverTimeStamp);
        File file = new File(picPath.toString());
        file.createNewFile();
        OutputStream output = new FileOutputStream(file);
        IOUtils.copy(uploadedFile.getInputStream(), output);

        output.close();
        in.close();

        Path dbPicPath = Paths.get(userDirPath.toString() + "/" + prefix + "_" + serverTimeStamp);
        imageImporter.addImage(buffer, album, dbPicPath.toString(), description, tags, bimg.getWidth(), bimg.getHeight(), format, date);

        // Affichage message de succes
        FacesMessage msg = new FacesMessage("La photo a bien été uploadée");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        context.addMessage("msgs", msg);
    }

}
