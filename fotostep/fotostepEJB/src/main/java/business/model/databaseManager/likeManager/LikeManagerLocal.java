package business.model.databaseManager.likeManager;

import javax.ejb.Local;

import business.model.database.Album;
import business.model.database.Image;
import business.model.database.User;

/**
 * Interface locale du bean UserManager
 *
 * @author Mathieu Barberot
 */
@Local
public interface LikeManagerLocal
{
	
	public void like(User user, Album album);
	
	public void like(User user, Image image);
	
	public void dislike(User user, Album album);
	
	public void dislike(User user, Image image);
}
