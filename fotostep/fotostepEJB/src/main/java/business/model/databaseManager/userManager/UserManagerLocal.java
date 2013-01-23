package business.model.databaseManager.userManager;

import javax.ejb.Local;

import business.model.database.Album;
import business.model.database.User;
import business.util.exceptions.UserNotFoundException;
import java.util.List;

@Local
public interface UserManagerLocal
{

    public User registerNewUser(String mail, String password, String firstName, String lastName, String gender);

    public User getUserById(int id);

    public User getUserByLogin(String mail);

    public User authenticate(String mail, String password) throws UserNotFoundException;

    public List<Album> getAuthorizedAlbums(User connected, User viewed);
    
    public void removeFriend(User user, User friend);
    
    public void acceptFriendship(User user, User friend);
    
    public void rejectFriendship(User user, User friend);

    public void requestFriendship(User user, User friend);
    
    public void cancelFriendship(User user, User friend);
    
    public List<User> searchUser(String keyword);
}
