package business.model.databaseManager.albumManager;

import business.model.database.Album;
import business.model.database.AuthorizationEnum;
import business.model.database.Picture;
import business.model.database.User;
import business.util.exceptions.AlbumNotFoundException;
import business.util.exceptions.PictureNotFoundException;
import business.util.exceptions.UserNotFoundException;
import java.util.Date;
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

    public Album createAlbum(User user, String name, String description, AuthorizationEnum authorization){
        Album album = new Album();
        
        album.setAuthorization(authorization);
        album.setDate(new Date());

        if(!description.isEmpty())
        {
            album.setDescription(description);
        }
        album.setName(name);
        album.setUser(user);
        album.setIsdefault(0);
        em.persist(album);
        return album;
    }

    public Album findAlbumById(int id) {
        try
        {
            Album alb = em.find(Album.class, id);
            return alb;
        }
        catch(NoResultException e)
        {
            return null;
        }
    }

    public void deleteAlbum(Album album) throws AlbumNotFoundException {
        em.remove(em.find(Album.class, album.getIdalbum()));
    }

    public void updateAlbum(Album album, String name, String description, AuthorizationEnum authorization, Picture picture) throws AlbumNotFoundException, PictureNotFoundException{
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Album getDefaultAlbum(User user) {
        Query query = em.createQuery("SELECT a FROM Album a WHERE iduser = :id AND isdefault = :default");
        query.setParameter("id", user.getIduser());
        query.setParameter("default", 1);

        try {
            Album res = (Album) query.getSingleResult();
            return res;
        } catch (NoResultException e) {
            return null;
        }
    }


}
