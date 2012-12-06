package business.model.databaseManager.userManager;

import javax.ejb.Local;

import business.model.database.User;
import business.model.database.Userdata;

/**
 * Interface locale du bean UserManager
 *
 * @author Mathieu Barberot
 */
@Local
public interface UserManagerLocal
{
	
	
	public User addUser(String login, String password, String nickname);	

	public Userdata getUserData(User user);

	public void setUserData(Userdata data);
	
	public void enableUser(int id);
	
	public void disableUser(int id);
	
	public User getUser(int id);
	
	public User searchUserByNickname(String nickame);
}
