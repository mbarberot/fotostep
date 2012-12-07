package business.model.databaseManager.imageManager;

import java.nio.Buffer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import business.model.database.Image;
import business.model.database.User;

/**
 * Bean de manipulation de l'entité Image dans la base de données.
 *
 * @author Mathieu Barberot
 */
@Stateless
public class ImageManager implements ImageManagerLocal
{
	@PersistenceContext
	EntityManager em;

	public Image addImage(User user, String name, Buffer buffer) {
		Image image = new Image();
		//TODO : Mettre l'auteur de l'image
		//TODO : Stocker l'image
		em.persist(image);
		return image;
	}

	public void removeImage(Image image) {
		em.remove(image);
	}
}
