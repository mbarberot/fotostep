package business.model.databaseManager.userManager;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import business.model.database.*;
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
		
		if(gender.equals("m"))
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

        // Création de l'album par défaut
        Album defaultA = new Album();
        defaultA.setUser(newUser);
        defaultA.setAuthorization(AuthorizationEnum.PRIVATE);
        defaultA.setIsdefault(1);
        defaultA.setName("Album par défaut");
        defaultA.setDate(new Date());
        defaultA.setDescription("Album par défaut où sont stockées vos photos");
        em.persist(defaultA);

		return newUser;
	}

    public User getUserById(int id)
    {
        try
        {
            User res = em.find(User.class, id);
            return res;
        }
        catch(NoResultException e)
        {
            return null;
        }
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
	
    public void askFriend(User user, User friend){
    	if(friend.getFriends().contains(user))
    		throw new IllegalStateException("L'autre utilisateur vous a déjà fait une demande");
    	
    	if(user.getFriends().contains(friend))
    		throw new IllegalStateException("Cet utilisateur a déjà lancé un demande");
    		
    	user.getFriends().add(friend);
    	em.persist(user);
    	
    	if(friend.getFriends().remove(user))
        	em.persist(friend);
    }   
    
    public void removeFriend(User user, User friend){
    	if(!user.getFriends().contains(friend))
    		throw new IllegalStateException("Cet utilisateur n'a pas cet ami");

    	user.getFriends().remove(friend);
    	em.persist(user);
    }
    
    public void acceptFriend(User user, User friend){
    	if(!friend.getFriends().contains(user))
    		throw new IllegalStateException("L'autre utilisateur n'a pas fait de demande");
    	
    	if(user.getFriends().contains(friend))
    		throw new IllegalStateException("Cet utilisateur est déjà ami avec cet utilisateur");
    		
    	user.getFriends().add(friend);
    	em.persist(user);
    }
    
}
