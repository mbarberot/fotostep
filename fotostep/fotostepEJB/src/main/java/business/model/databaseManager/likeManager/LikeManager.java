package business.model.databaseManager.likeManager;

import business.model.database.*;
import business.model.databaseManager.albumManager.AlbumManagerLocal;
import business.model.databaseManager.pictureManager.PictureManagerLocal;
import business.model.databaseManager.userManager.UserManagerLocal;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    @EJB
    UserManagerLocal um;
    @EJB
    AlbumManagerLocal am;
    @EJB
    PictureManagerLocal pm;

    public void like(User user, Album album)
    {
        User userLiker = um.getUserById(user.getIduser());
        Album likedAlbum = am.findAlbumById(album.getIdalbum());

        Likealbum likealbum = new Likealbum();
        likealbum.setDate(new Date());
        likealbum.setAlbum(likedAlbum);
        likealbum.setLiker(userLiker);
        em.persist(likealbum);

        /*
        user.addLikedAlbum(likealbum);
        em.persist(user); */
    }

    public void like(User user, Picture picture)
    {

        User userLiker = um.getUserById(user.getIduser());
        Picture likedPicture = pm.findPictureById(picture.getIdpicture());

        Likepicture likepicture = new Likepicture();
        likepicture.setDate(new Date());
        likepicture.setPicture(likedPicture);
        likepicture.setLiker(userLiker);

        em.persist(likepicture);


    }

    public void dislike(User user, Album album)
    {
        User userLiker = um.getUserById(user.getIduser());
        Album likedAlbum = am.findAlbumById(album.getIdalbum());

        Query query = em.createQuery("SELECT lp FROM Likealbum lp WHERE lp.liker = :liker and lp.album = :album");
        query.setParameter("liker", userLiker);
        query.setParameter("album", likedAlbum);

        try
        {
            Likealbum likealbum = (Likealbum) query.getSingleResult();
            em.remove(likealbum);
            /*
            user.removeLikedAlbum(likealbum);
            em.persist(user);  */
        }
        catch (NoResultException ex)
        {
            ex.printStackTrace();
        }
    }

    public void dislike(User user, Picture picture)
    {
        Query query = em.createQuery("SELECT lp FROM Likepicture lp WHERE lp.liker = :user and lp.picture = :picture");
        query.setParameter("user", user);
        query.setParameter("picture", picture);

        try
        {
            Likepicture likepicture = (Likepicture) query.getSingleResult();
            em.remove(likepicture);
        }
        catch (NoResultException ex)
        {
            ex.printStackTrace();
        }


    }
}
