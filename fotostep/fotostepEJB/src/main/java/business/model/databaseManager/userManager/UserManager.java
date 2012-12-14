package business.model.databaseManager.userManager;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import business.model.database.Album;
import business.model.database.Albumlike;
import business.model.database.Image;
import business.model.database.Imagelike;
import business.model.database.User;
import business.model.database.Userdata;
import business.utilities.HashingUtilityLocal;

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

	@EJB
	HashingUtilityLocal hashTool;
	/** Ajoute un utilisateur dans la base avec ses informations de base */
	public User addUser(String login, String password) {
		User user = new User();		
		user.setLogin(login);
		user.setPassword(hashTool.md5Hash(password));
		user.setDate(System.currentTimeMillis());
		
		em.persist(user);
		
		return user;
	}

	public Userdata createUserRegisterData(User user, String firstName, String lastName,
			byte gender, Date birthDate) {
		
		Userdata data = new Userdata();
		data.setFirstname(firstName);
		data.setForeName(lastName);
		data.setGender(gender);
		data.setBirthDate(birthDate);
		data.setIdUser(user.getIdUser());
		em.persist(data);
		
		return data;
	}
	
	/** Récupère les infos d'un utilisateur */
	public Userdata getUserData(User user) {		
		return em.find(Userdata.class, user.getIdUser());
	}

	
	public void enableUser(int id) {
		User user = searchUserById(id);
		user.setEnabled(true);
		em.persist(user);
	}

	public void disableUser(int id) {
		User user = searchUserById(id);
		user.setEnabled(false);
		em.persist(user);
	}	
	
	public User searchUserById(int id){
		Query query = em.createNativeQuery("User.findByIdUser", User.class);
		query.setParameter("idUser", id);
		Object result = query.getSingleResult();
		return result == null?null:(User)result;
	}
	
	public User searchUserByNickname(String nickame){
		Query query = em.createNativeQuery("User.findByNickname", User.class);
		query.setParameter("login", nickame);
		Object result = query.getSingleResult();
		return result == null?null:(User)result;
	}
	
	public User getUser(int id) {
		return em.find(User.class, id);
	}

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
