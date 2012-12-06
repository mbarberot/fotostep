package business.model.databaseManager.albumManager;

import business.model.database.Album;
import business.model.database.Image;
import business.model.database.User;
import business.util.Permission;
import business.util.exceptions.AlbumNotFoundException;
import business.util.exceptions.ImageNotFoundException;
import business.util.exceptions.UserNotFoundException;
import java.util.Date;
import javax.ejb.Local;

/**
 * Interface locale du bean AlbumManager
 *
 * @author Mathieu Barberot
 */
@Local
public interface AlbumManagerLocal
{
    
    public void createAlbum(User user, String name, String description, Permission permission) throws UserNotFoundException;

    public void deleteAlbum(Album album) throws AlbumNotFoundException;

    public void updateAlbum(Album album, String name, String description, Permission permission, Image image) throws AlbumNotFoundException, ImageNotFoundException;

    public void likeAlbum(Album album, User user) throws AlbumNotFoundException, UserNotFoundException;

    public void commentAlbum(Album album, User user, String title, Date date, String body) throws AlbumNotFoundException, UserNotFoundException;
}
