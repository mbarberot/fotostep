package business.model.databaseManager.pictureManager;

import business.model.database.Album;
import business.model.database.FormatEnum;
import business.model.database.Picture;
import java.nio.Buffer;
import java.util.Date;
import javax.ejb.Local;

/**
 * Interface locale du bean ImageManager
 *
 * @author Mathieu Barberot
 */
@Local
public interface PictureManagerLocal
{
    public Picture addImage(Buffer buffer, Album album, String path, String description, String tag, int width, int height, FormatEnum format, Date date);
    
    public Picture addImage(Buffer buffer, Album album, String path, String description, String tag, int width, int height, FormatEnum format, double lgt, double lat);
    
    public void editImage(Picture picture, Album album, String description, String tag, double lgt, double lat);
    
    public Picture findPictureById(int id);

    public void removeImage(Picture picture);   

}
