package business.model.databaseManager.pictureManager;

import business.model.database.Album;
import business.model.database.FormatEnum;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Date;

import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.vividsolutions.jts.geom.Point;

import business.model.database.Picture;

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
        
        generateTumb(picture, 600, 400);
        
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
        
        generateTumb(picture, 600, 400);
        
        return picture;
    }

    public void editImage(Picture picture, Album album, String description, String tags, Point point)
    {
        picture.setAlbum(album);
        picture.setCoord(point);
        picture.setDescription(description);
        picture.setTags(tags);
    }    
    
    public Picture findPictureById(int id) {        
        try {
            Picture pic = em.find(Picture.class, id);
            return pic;
        } catch (NoResultException e) {
            return null;
        }
    }

    public void removeImage(Picture picture)
    {
        em.remove(em.find(Picture.class, picture.getIdpicture()));
    }
    
    public void generateTumb(Picture picture, int width, int height)
    {
    	File file = new File(System.getProperty("user.home") + picture.getPath());
    	
    	if(!file.exists() || !file.isFile())
    		throw new IllegalStateException("The file " + picture.getPath() + " don't exist");
    	
    	try {
    		BufferedImage original = ImageIO.read(file);
    		
    		BufferedImage resizedImage = new BufferedImage(width, height, original.getType());
    		Graphics2D g = resizedImage.createGraphics();
    		g.drawImage(original, 0, 0, width, height, null);
    		g.dispose();
    		
    		File newFile = new File(System.getProperty("user.home") + picture.getPath() + "_" + width + "_" + height);
    		newFile.createNewFile();
    		ImageIO.write(resizedImage, picture.getFormat().name(), newFile);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

}
