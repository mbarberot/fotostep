package jsf;


import business.importbusiness.ImageImporterLocal;
import business.model.database.Album;
import business.model.database.FormatEnum;
import business.model.database.User;
import business.model.databaseManager.albumManager.AlbumManagerLocal;
import business.model.databaseManager.userManager.UserManagerLocal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
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
    private String fileName;

    public void submit() throws ValidatorException, IOException {
        
        //String path = System.getProperty("user.home");  
        //String newFileName = path + "/" + getFileName();
        //System.out.println(path);
            
        if (uploadedFile.getSize() > 2097152) {
            
            //FacesContext context = FacesContext.getCurrentInstance();
            //context.addMessage("Erreur taille image", new FacesMessage("L'image est trop volumineuse (2mo max)"));
            System.out.println("Image trop grande");
            
//        } else if (!(uploadedFile.getContentType().equals("typeimage/jpeg") || uploadedFile.getContentType().equals("typeimage/png"))) {
//            
//            //FacesContext context = FacesContext.getCurrentInstance();
//            //context.addMessage("Erreur format image", new FacesMessage("L'image n'est pas au bon format (JPG ou PNG uniquement)"));
//            System.out.println("Image au mauvais format");
//            
       } else {
            
            Buffer buf = ByteBuffer.wrap(uploadedFile.getBytes());
            FormatEnum format = FormatEnum.jpg;
            if (uploadedFile.getContentType().equals("typeimage/jpeg"))
                format = FormatEnum.jpg;
            else
                format = FormatEnum.png;
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            HttpSession httpSession = request.getSession(false);
            Integer myId = (Integer) httpSession.getAttribute("userId");
            User u = userManager.getUserById(myId);
            Album defaultAlbum = albumManager.getDefaultAlbum(u);
            imageImporter.addImage(buf, defaultAlbum, uploadedFile.getName(), "No description", 0, 0, format);
            
            // Prepare filename prefix and suffix for an unique filename in upload folder.
            String prefix = FilenameUtils.getBaseName(uploadedFile.getName());
            String suffix = FilenameUtils.getExtension(uploadedFile.getName());

            // Prepare file and outputstream.
            File file = null;
            OutputStream output = null;

            // Create file with unique name in upload folder and write to it.
            file = File.createTempFile(prefix + "_", "." + suffix, new File(System.getProperty("user.home").toString()));
            output = new FileOutputStream(file);
            IOUtils.copy(uploadedFile.getInputStream(), output);
            fileName = file.getName();

        }
    }


    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public String getFileName() {
        return fileName;
    }


    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
}
