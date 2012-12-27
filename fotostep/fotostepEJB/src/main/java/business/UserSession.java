package business;

import business.model.database.User;

import javax.ejb.Stateful;

/**
 * Bean de gestion de la session d'un utilisateur.
 * 
 * @author Mathieu Barberot
 */
@Stateful
public class UserSession implements UserSessionLocal
{
    private User user;

    public void setUser(User u) {
        this.user = u;
    }

    public String getUserFirstName() {
        return user.getFirstname();
    }

    public String getUserLastName() {
        return user.getLastname();
    }

    public boolean uploadPhoto(byte[] pic, int idAlbum) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
