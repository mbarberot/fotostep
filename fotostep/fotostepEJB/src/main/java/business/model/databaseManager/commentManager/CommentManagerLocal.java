package business.model.databaseManager.commentManager;

import javax.ejb.Local;

import business.model.database.Album;
import business.model.database.Commentalbum;
import business.model.database.Commentimage;
import business.model.database.Image;
import business.model.database.User;

/**
 * Interface locale du bean CommentManager
 *
 * @author Mathieu Barberot
 */
@Local
public interface CommentManagerLocal
{
    
	public Commentalbum addComment(Album album, User user, String title, String text);
	public void editComment(Commentalbum comment, String text);
	public void deleteComment(Commentalbum comment);
	
	public Commentimage addComment(Image image, User user, String title, String text);
	public void editComment(Commentimage comment, String text);
	public void deleteComment(Commentimage comment);
	
	
}
