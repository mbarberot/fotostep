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
		user.addLikedAlbum(album);
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
		user.removeLikedAlbum(album);
		em.persist(user);
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
                catch(NoResultException ex) { ex.printStackTrace(); }
		
		
	}
}
