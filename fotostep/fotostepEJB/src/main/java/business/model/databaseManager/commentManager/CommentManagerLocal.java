package business.model.databaseManager.commentManager;

import javax.ejb.Local;

import business.model.database.Comment;
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
    
	public Comment addComment(Image image, User user, String title, String text);
	public void editComment(Comment comment, String text);
	public void deleteComment(Comment comment);
	
	
}
