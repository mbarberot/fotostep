package business.importbusiness;

import business.model.database.Album;
import business.model.database.FormatEnum;
import business.model.database.Picture;
import business.model.databaseManager.pictureManager.PictureManagerLocal;
import java.nio.Buffer;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 * Bean gérant l'import des images
 * 
 * @author Mathieu Barberot
 */
@Stateful
public class ImageImporter implements ImageImporterLocal
{ 
    @EJB
    private PictureManagerLocal pictureManager;

    public Picture addImage(Buffer buffer, Album album, String path, String description, String tag, int width, int height, FormatEnum format, Date date) {
        Picture picture = pictureManager.addImage(buffer, album, path, description, tag, width, height, format, date);
        return picture;
    }
}
