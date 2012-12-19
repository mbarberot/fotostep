package business.model.databaseManager.pictureManager;

import business.model.database.Album;
import business.model.database.FormatEnum;
import business.model.database.Picture;
import java.awt.Point;
import java.nio.Buffer;
import javax.ejb.Local;

/**
 * Interface locale du bean ImageManager
 *
 * @author Mathieu Barberot
 */
@Local
public interface PictureManagerLocal
{
    public Picture addImage(Buffer buffer, Album album, String path, String description, int width, int height, FormatEnum format);
    
    public Picture addImage(Buffer buffer, Album album, String path, String description, int width, int height, FormatEnum format, Point point);
    
    public void editImage(Picture picture, Album album, String description, Point point);

    public void removeImage(Picture picture);
}