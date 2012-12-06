package business.model.databaseManager.albumManager;

import business.model.database.Album;
import business.model.database.Image;
import business.model.database.User;
import business.util.Permission;
import business.util.exceptions.AlbumNotFoundException;
import business.util.exceptions.ImageNotFoundException;
import business.util.exceptions.UserNotFoundException;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Bean de manipulation de l'entité Album dans la base de données.
 *
 * @author Mathieu Barberot
 */
@Stateless
public class AlbumManager implements AlbumManagerLocal
{
    @PersistenceContext
    EntityManager em;

    public void createAlbum(User user, String name, String description, Permission permission) throws UserNotFoundException
    {
        Album album = new Album();
        album.setIdUser(user);
        album.setName(name);
        album.setDescription(description);
        album.setPerm(permission.getPermission());
        em.persist(em);
    }

    public void deleteAlbum(Album album) throws AlbumNotFoundException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateAlbum(Album album, String name, String description, Permission permission, Image image) throws AlbumNotFoundException, ImageNotFoundException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void likeAlbum(Album album, User user) throws AlbumNotFoundException, UserNotFoundException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void commentAlbum(Album album, User user, String title, Date date, String body) throws AlbumNotFoundException, UserNotFoundException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
