package business.model.databaseManager.likeManager;

import business.model.database.*;
import java.util.Date;
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

    public void like(User user, Album album)
    {
        Likealbum likealbum = new Likealbum();
        likealbum.setDate(new Date());
        likealbum.setAlbum(album);
        likealbum.setLiker(user);

        user.addLikedAlbum(likealbum);
        em.persist(user);
    }

    public void like(User user, Picture picture)
    {

        Likepicture likepicture = new Likepicture();
        likepicture.setDate(new Date());
        likepicture.setPicture(picture);
        likepicture.setLiker(user);

        user.addLikedPicture(likepicture);

        em.persist(user);
    }

    public void dislike(User user, Album album)
    {
        Query query = em.createQuery("SELECT lp FROM Likealbum lp WHERE lp.liker = :liker and lp.album = :album");
        query.setParameter("liker", user);
        query.setParameter("album", album);

        try
        {
            Likealbum likealbum = (Likealbum) query.getSingleResult();
            user.removeLikedAlbum(likealbum);
            em.persist(user);
        }
        catch (NoResultException ex)
        {
            ex.printStackTrace();
        }
    }

    public void dislike(User user, Picture picture)
    {
        Query query = em.createQuery("SELECT lp FROM LikePicture lp WHERE lp.user = :user and lp.picture = :picture");
        query.setParameter("user", user);
        query.setParameter("picture", picture);

        try
        {
            Likepicture likepicture = (Likepicture) query.getSingleResult();
            user.removeLikedPicture(likepicture);
            em.persist(user);
        }
        catch (NoResultException ex)
        {
            ex.printStackTrace();
        }


    }
}
