package business.model.databaseManager.likeManager;

import business.model.database.*;
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

	public void like(User user, Album album) {
		Albumlikes like = new Albumlikes();
		like.setUser(user);
		like.setAlbum(album);
		like.setDate(System.currentTimeMillis());
		em.persist(like);
	}

	public void like(User user, Image image) {
		Imagelikes like = new Imagelikes();
		like.setUser(user);
		like.setImage(image);
		like.setDate(System.currentTimeMillis());
		em.persist(like);
	}

	public void dislike(User user, Album album) {
		for(Albumlikes like : user.getAlbumlikes())
			if(like.getAlbum().equals(album))
				em.remove(like);
	}

	public void dislike(User user, Image image) {
		for(Imagelikes like : user.getImagelikes())
			if(like.getImage().equals(image))
				em.remove(like);
	}

}
