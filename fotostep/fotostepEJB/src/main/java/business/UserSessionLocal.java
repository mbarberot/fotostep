package business;

import business.model.database.User;

import javax.ejb.Local;

/**
 * Interface locale de manipulation du bean UserSession
 * 
 * @author Mathieu Barberot
 */
@Local
public interface UserSessionLocal
{
    public void setUser(User u);
    public String getUserFirstName();
    public String getUserLastName();
    public boolean uploadPhoto(byte[] pic, int idAlbum);
}
