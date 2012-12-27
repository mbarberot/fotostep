package business.model.databaseManager.userManager;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import business.model.database.EnabledEnum;
import business.model.database.GenderEnum;
import business.model.database.User;
import business.util.exceptions.UserNotFoundException;

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
		newUser.setEnabled(EnabledEnum.accepted);
		em.persist(newUser);
		return newUser;
	}

	public User getUserByLogin(String mail) {
		Query query = em.createQuery("SELECT u FROM User u WHERE login = :mail");
		query.setParameter("mail", mail);
		
		try
		{
			User res = (User) query.getSingleResult();
			return res;
		}
		catch(NoResultException e)
		{
			return null;
		}
	}

    public User authenticate(String mail, String password) throws UserNotFoundException
    {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.login = :mail AND u.password = :password");
        query.setParameter("mail", mail);
        query.setParameter("password", password);
        
        
        User user = null;
        try
        {
            user = (User) query.getSingleResult();
        }
        catch(NoResultException ex)
        {
            ex.printStackTrace();
            throw new UserNotFoundException("User not found : " + mail);
        }
        
        return user;
    }
	
}
