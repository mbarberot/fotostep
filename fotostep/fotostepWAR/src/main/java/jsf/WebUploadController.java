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
    
    // Constructeur
    
    public WebUploadController() {}
    
    // Validate
    
    public void validateUploadedFile(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
        UploadedFile file = (UploadedFile) value;

        if (!(file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png"))) {
            throw new ValidatorException(new FacesMessage("Le fichier n'est pas au bon format (JPEG ou PNG)"));
        } else if (file.getSize() > 2097152) {
            throw new ValidatorException(new FacesMessage("Le fichier est trop volumineux (supérieur à 2 Mo)"));
        } else if (file.getSize() == 0) {
            throw new ValidatorException(new FacesMessage("Le fichier est vide"));
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
    
    // Méthode pour enregistrer une picture
    
    public void submit() throws ValidatorException, IOException {
        
        Buffer buffer = ByteBuffer.wrap(uploadedFile.getBytes());
        FormatEnum format = null;
        
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
        Album defaultAlbum = albumManager.getDefaultAlbum(user);
        
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
        
        String path = "/fotosteppictures/user" + user.getIduser();
        
        File repertory = new File(path);
        repertory.mkdirs();
        
        //File file = File.createTempFile(prefix + "_", "." + suffix, new File(path));
        File file = new File(path + "/" + prefix + "_" + System.currentTimeMillis() + "." + suffix);
        file.createNewFile();
        OutputStream output = new FileOutputStream(file);
        IOUtils.copy(uploadedFile.getInputStream(), output);
        
        output.close();
        in.close();
        
        imageImporter.addImage(buffer, defaultAlbum, path + "/" + file.getName(), description, tags, bimg.getWidth(), bimg.getHeight(), format, date);

    }
 
}
