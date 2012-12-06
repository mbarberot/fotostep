package business.model.databaseManager.userManager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import business.model.database.User;
import business.model.database.Userdata;

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

	/** Ajoute un utilisateur dans la base avec ses informations de base */
	public User addUser(String login, String password, String nickname) {
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		user.setNickname(nickname);
		em.persist(user);
		return user;
	}

	/** Récupère les infos d'un utilisateur */
	public Userdata getUserData(User user) {		
		return em.find(Userdata.class, user.getIdUser());
	}

	public void setUserData(Userdata data) {
	}

	
	public void enableUser(int id) {
		// TODO Auto-generated method stub
		
	}

	public void disableUser(int id) {
		// TODO Auto-generated method stub
		
	}
	
	
	public User searchUserByNickname(String nickame)
	{
		//TODO 
		return null;
	}
	public User getUser(int id) {
		return em.find(User.class, id);
	}



}
