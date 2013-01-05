package business.model.databaseManager.albumManager;

import business.model.database.Album;
import business.model.database.AuthorizationEnum;
import business.model.database.Picture;
import business.model.database.User;
import business.util.exceptions.AlbumNotFoundException;
import business.util.exceptions.PictureNotFoundException;
import business.util.exceptions.UserNotFoundException;
import javax.ejb.Local;

/**
 * Interface locale du bean AlbumManager
 *
 * @author Mathieu Barberot
 */
@Local
public interface AlbumManagerLocal
{
    
    public Album createAlbum(User user, String name, String description, AuthorizationEnum authorization) throws UserNotFoundException;

    public void deleteAlbum(Album album) throws AlbumNotFoundException;

    public void updateAlbum(Album album, String name, String description, AuthorizationEnum authorization, Picture picture) throws AlbumNotFoundException, PictureNotFoundException;
    
    public Album getDefaultAlbum(User user);

}
