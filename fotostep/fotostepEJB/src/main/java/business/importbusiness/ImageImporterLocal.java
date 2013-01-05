package business.importbusiness;

import business.model.database.Album;
import business.model.database.FormatEnum;
import business.model.database.Picture;
import java.nio.Buffer;
import javax.ejb.Local;

/**
 * Interface locale de manipulation du bean ImageImporter
 * 
 * @author Mathieu Barberot
 */
@Local
public interface ImageImporterLocal
{
    public Picture addImage(Buffer buffer, Album album, String path, String description, int width, int height, FormatEnum format);
}
