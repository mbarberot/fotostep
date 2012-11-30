package business.model.databaseManager.imageManager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import business.model.database.Image;

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
	

	@Override
	public int registerImage() {
		Image image = new Image();
		em.persist(image);
		return image.getId();
	}


	@Override
	public String getName(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getDescription(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getWidth(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getHeight(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getThumb(int id) {
		// TODO Auto-generated method stub
		return 0;
	}
}
