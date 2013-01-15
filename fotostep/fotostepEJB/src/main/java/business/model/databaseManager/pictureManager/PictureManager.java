package business.model.databaseManager.pictureManager;

import business.model.database.Album;
import business.model.database.FormatEnum;
import java.nio.Buffer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.vividsolutions.jts.geom.Point;

import business.model.database.Picture;
import business.model.database.User;
import java.util.Date;

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

    public Picture addImage(Buffer buffer, Album album, String path, String description, String tags, int width, int height, FormatEnum format, Date date)
    {
        Picture picture = new Picture();

        picture.setAlbum(album);
        picture.setDescription(description);
        picture.setTags(tags);
        picture.setFormat(format);
        picture.setHeight(height);
        picture.setPath(path);
        picture.setWidth(width);
        picture.setDate(date);

        em.persist(picture);
        
        saveImage(buffer, path);
        
        return picture;

    }

    public Picture addImage(Buffer buffer, Album album, String path, String description, String tags,
    		int width, int height, FormatEnum format, Point point)
    {
        Picture picture = new Picture();

        picture.setAlbum(album);
        picture.setCoord(point);
        picture.setDescription(description);
        picture.setTags(tags);
        picture.setFormat(format);
        picture.setHeight(height);
        picture.setPath(path);
        picture.setWidth(width);

        em.persist(picture);
        
        saveImage(buffer, path);
        
        return picture;
    }

    public void editImage(Picture picture, Album album, String description, String tags, Point point)
    {
        picture.setAlbum(album);
        picture.setCoord(point);
        picture.setDescription(description);
        picture.setTags(tags);
    }

    public void removeImage(Picture picture)
    {
        em.remove(em.find(Picture.class, picture.getIdpicture()));
        
        //TODO : Supprimer l'image du disque
    }
    
    private void saveImage (Buffer buffer, String path)
    {
        // TODO : Ecrire l'image sur le disque
    }
}
