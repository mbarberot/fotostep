package business.model.databaseManager.userManager;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import business.model.database.GenderEnum;
import business.model.database.User;

@Stateless
public class UserManager implements UserManagerLocal{

	@PersistenceContext
	EntityManager em;
	
	public User registerNewUser(String mail, String password, String firstName,
			String lastName, String gender) {
		
		User newUser = new User();
		newUser.setLogin(mail);
		newUser.setPassword(password);
		newUser.setFirstname(firstName);
		newUser.setLastname(lastName);
		
		if(gender.equals("h"))
		{
			newUser.setGender(GenderEnum.m);
		}
		else
		{
			newUser.setGender(GenderEnum.f);
		}
		
		newUser.setRegisterdate(new Date());
		
		em.persist(newUser);
		return newUser;
	}
	
}
