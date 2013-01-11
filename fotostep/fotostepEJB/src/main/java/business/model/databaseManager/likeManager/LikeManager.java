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
		em.persist(user);
	}

	public void like(User user, Picture picture)
	{
		user.addLikedPicture(picture);
		em.persist(user);
	}

	public void dislike(User user, Album album)
	{
		user.removeLikedAlbum(album);
		em.persist(user);
	}

	public void dislike(User user, Picture picture)
	{
		user.removeLikedPicture(picture);
		em.persist(user);
	}
}
