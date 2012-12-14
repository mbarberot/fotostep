package business.model.databaseManager.likeManager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import business.model.database.Album;
import business.model.database.Albumlike;
import business.model.database.Image;
import business.model.database.Imagelike;
import business.model.database.User;

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
		Albumlike like = new Albumlike();
		like.setUser(user);
		like.setAlbum(album);
		like.setDate(System.currentTimeMillis());
		em.persist(like);
	}

	public void like(User user, Image image) {
		Imagelike like = new Imagelike();
		like.setUser(user);
		like.setImage(image);
		like.setDate(System.currentTimeMillis());
		em.persist(like);
	}

	public void dislike(User user, Album album) {
		for(Albumlike like : user.getAlbumlikes())
			if(like.getAlbum().equals(album))
				em.remove(like);
	}

	public void dislike(User user, Image image) {
		for(Imagelike like : user.getImagelikes())
			if(like.getImage().equals(image))
				em.remove(like);
	}

}
