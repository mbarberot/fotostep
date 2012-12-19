package business.model.databaseManager.userManager;

import javax.ejb.Local;

import business.model.database.User;

@Local
public interface UserManagerLocal {
	
	public User registerNewUser(String mail, String password, String firstName,String lastName, String gender);

}
