package business.model.databaseManager.pictureManager;

import business.model.database.Album;
import business.model.database.FormatEnum;
import java.nio.Buffer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import business.model.database.Picture;
import business.model.database.User;
import java.awt.Point;

/**
 * Bean de manipulation de l'entité Image dans la base de données.
 *
 * @author Mathieu Barberot
 */
@Stateless
public class PictureManager implements PictureManagerLocal
{
    @PersistenceContext
    EntityManager em;

    public Picture addImage(Buffer buffer, Album album, String path, String description, int width, int height, FormatEnum format)
    {
        Picture picture = new Picture();

        picture.setAlbum(album);
        picture.setDescription(description);
        picture.setFormat(format);
        picture.setHeight(height);
        picture.setPath(path);
        picture.setWidth(width);

        em.persist(picture);
        
        saveImage(buffer, path);
        
        return picture;

    }

    public Picture addImage(Buffer buffer, Album album, String path, String description, int width, int height, FormatEnum format, Point point)
    {
        Picture picture = new Picture();

        picture.setAlbum(album);
        picture.setCoord(point);
        picture.setDescription(description);
        picture.setFormat(format);
        picture.setHeight(height);
        picture.setPath(path);
        picture.setWidth(width);

        em.persist(picture);
        
        saveImage(buffer, path);
        
        return picture;
    }

    public void editImage(Picture picture, Album album, String description, Point point)
    {
        picture.setAlbum(album);
        picture.setCoord(point);
        picture.setDescription(description);
    }

    public void removeImage(Picture picture)
    {
        em.remove(picture);
    }
    
    private void saveImage (Buffer buffer, String path)
    {
        // TODO : Ecrire l'image sur le disque
    }
}
