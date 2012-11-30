package business.model.databaseManager.userManager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import business.model.database.User;

/**
 * Bean de manipulation de l'entité User dans la base de données.
 *
 * @author Mathieu Barberot
 */
@Stateless
public class UserManager implements UserManagerLocal
{
	@PersistenceContext
	EntityManager em;

	@Override
	public User addUser(String login, String password, String nickname) {
		User user = new User(-1, login, password, nickname);
		em.persist(user);
		return user;
	}

	@Override
	public void enableUser(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disableUser(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUser(int id) {
		return em.find(User.class, id);
	}
}
