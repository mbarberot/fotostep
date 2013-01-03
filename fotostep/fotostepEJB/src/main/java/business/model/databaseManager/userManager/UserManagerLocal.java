package business.model.databaseManager.userManager;

import javax.ejb.Local;

import business.model.database.User;
import business.util.exceptions.UserNotFoundException;

@Local
public interface UserManagerLocal {
	
	public User registerNewUser(String mail, String password, String firstName,String lastName, String gender);
    public User getUserById(int id);
	public User getUserByLogin(String mail);
    public User authenticate(String mail, String password) throws UserNotFoundException;
}
