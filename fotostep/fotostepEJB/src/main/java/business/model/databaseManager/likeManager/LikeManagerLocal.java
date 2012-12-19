package business.model.databaseManager.likeManager;

import business.model.database.Album;
import business.model.database.Picture;
import business.model.database.User;
import javax.ejb.Local;

/**
 * Interface locale du bean UserManager
 *
 * @author Mathieu Barberot
 */
@Local
public interface LikeManagerLocal
{
	
	public void like(User user, Album album);
	
	public void like(User user, Picture picture);
	
	public void dislike(User user, Album album);
	
	public void dislike(User user, Picture picture);
}
