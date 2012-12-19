package business.model.databaseManager.commentManager;

import javax.ejb.Local;

import business.model.database.Album;
import business.model.database.Commentalbum;
import business.model.database.Commentpicture;
import business.model.database.Picture;
import business.model.database.User;

/**
 * Interface locale du bean CommentManager
 *
 * @author Mathieu Barberot
 */
@Local
public interface CommentManagerLocal
{
    public Commentalbum addComment(Album album, User user, String text);

    public void editComment(Commentalbum comment, String text);

    public void deleteComment(Commentalbum comment);

    public Commentpicture addComment(Picture picture, User user, String text);

    public void editComment(Commentpicture comment, String text);

    public void deleteComment(Commentpicture comment);
}
