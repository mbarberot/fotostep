package business.model.databaseManager.likeManager;

import business.model.database.Album;
import business.model.database.Picture;
import business.model.database.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Bean de manipulation de l'entité User dans la base de données.
 *
 * @author Mathieu Barberot
 */
@Stateless
public class LikeManager implements LikeManagerLocal
{

    @PersistenceContext
    EntityManager em;

    public void like(User user, Album album)
    {
        user.addLikedAlbum(album);
        album.addLiker(user);
    }

    public void like(User user, Picture picture)
    {
        user.addLikedPicture(picture);
        picture.addLiker(user);
    }

    public void dislike(User user, Album album)
    {
        user.removeLikedAlbum(album);
        album.removeLiker(user);
    }

    public void dislike(User user, Picture picture)
    {
        user.removeLikedPicture(picture);
        user.removeLikedAlbum(null);
    }
}
