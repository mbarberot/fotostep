package business.model.databaseManager.albumManager;

import business.model.database.Album;
import business.model.database.AuthorizationEnum;
import business.model.database.Picture;
import business.model.database.User;
import business.util.exceptions.AlbumNotFoundException;
import business.util.exceptions.PictureNotFoundException;
import business.util.exceptions.UserNotFoundException;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    public Album createAlbum(User user, String name, String description, AuthorizationEnum authorization) throws UserNotFoundException{
        Album album = new Album();
        
        album.setAuthorization(authorization);
        album.setDate(new Date());
        album.setDescription(description);
        album.setName(name);
        album.setUser(user);
        
        em.persist(album);
        return album;
    }

    public void deleteAlbum(Album album) throws AlbumNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateAlbum(Album album, String name, String description, AuthorizationEnum authorization, Picture picture) throws AlbumNotFoundException, PictureNotFoundException{
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
