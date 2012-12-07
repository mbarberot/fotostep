package business.model.databaseManager.imageManager;

import java.nio.Buffer;

import javax.ejb.Local;

import business.model.database.Image;
import business.model.database.User;

/**
 * Interface locale du bean ImageManager
 *
 * @author Mathieu Barberot
 */
@Local
public interface ImageManagerLocal
{
	public Image addImage(User user, String name, Buffer buffer);
	
	public void removeImage(Image image);
}
