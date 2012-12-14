package business.model.databaseManager.albumManager;

import business.model.database.Album;
import business.model.database.Image;
import business.model.database.User;
import business.util.Permission;
import business.util.exceptions.AlbumNotFoundException;
import business.util.exceptions.ImageNotFoundException;
import business.util.exceptions.UserNotFoundException;
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

    public Album createAlbum(User user, String name, String description, Permission permission) throws UserNotFoundException{
        Album album = new Album();
        album.setIdUser(user);
        album.setName(name);
        album.setDescription(description);
        album.setPerm(permission.getPermission());
        album.setDate(System.currentTimeMillis());
        em.persist(album);
        return album;
    }

    public void deleteAlbum(Album album) throws AlbumNotFoundException{
        em.remove(album);
    }

    public void updateAlbum(Album album, String name, String description, Permission permission, Image image) throws AlbumNotFoundException, ImageNotFoundException{
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
