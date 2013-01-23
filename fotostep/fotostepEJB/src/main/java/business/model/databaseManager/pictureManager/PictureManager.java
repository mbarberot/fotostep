package business.model.databaseManager.pictureManager;

import business.model.database.Album;
import business.model.database.FormatEnum;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

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


    public Picture addImage(Buffer buffer, Album album, String path, String description, String tags,
                            int width, int height, FormatEnum format, Date date)
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

        // Miniature pour les albums
        generateThumb(picture, 250, 200);

        // Miniature pour la visualisation de la photo dans la lightbox
        generateThumb(picture, 800, 600);

        // Miniature pour la visualisation de la photo comme miniature d'avatar
        generateThumb(picture, 64, 64);

        // Avatar
        generateThumb(picture, 100, 100);
        
        return picture;

    }

    public Picture addImage(Buffer buffer, Album album, String path, String description, String tags,
    		int width, int height, FormatEnum format, double lgt, double lat)
    {
        Picture picture = new Picture();

        picture.setAlbum(album);
        picture.setLgt(lgt);
        picture.setLat(lat);
        picture.setDescription(description);
        picture.setTags(tags);
        picture.setFormat(format);
        picture.setHeight(height);
        picture.setPath(path);
        picture.setWidth(width);

        em.persist(picture);

        // Miniature pour les albums
        generateThumb(picture, 250, 200);

        // Miniature pour la visualisation de la photo dans la lightbox
        generateThumb(picture, 800, 600);

        // Minitature pour la visualisation de la photo comme avatar
        generateThumb(picture, 64, 64);

        // Avatar
        generateThumb(picture, 100, 100);
        
        return picture;
    }

    public void editImage(Picture picture, Album album, String description, String tags, double lgt, double lat)
    {
        Picture toUpdate = findPictureById(picture.getIdpicture());
        Album alb = em.find(Album.class, album.getIdalbum());
        toUpdate.setAlbum(alb);
        toUpdate.setLgt(lgt);
        toUpdate.setLat(lat);
        toUpdate.setDescription(description);
        toUpdate.setTags(tags);

        em.persist(toUpdate);
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

    public void generateThumb(Picture picture, int width, int height)
    {

    	Path picPath = Paths.get(System.getProperty("user.home") + picture.getPath());
    	File file = new File(picPath.toString());

    	if(!file.exists() || !file.isFile())
    		throw new IllegalStateException("The file " + picPath.toString() + " don't exist");

    	try {
    		BufferedImage original = ImageIO.read(file);
    		BufferedImage resizedImage = null;

    		if(original.getWidth() < width && original.getHeight() < height){
    			resizedImage = original;
    		}else{
    			int w = width;
    			int h = height;
    			if(original.getHeight() > original.getWidth()){
    				w = (int)((float)original.getWidth() / original.getHeight() * height);
    			}else{
    				h = (int)((float)original.getHeight() / original.getWidth() * width);
    			}

    			resizedImage = new BufferedImage(w, h, original.getType());
    			Graphics2D g = resizedImage.createGraphics();
    			g.drawImage(original, 0, 0, w, h, null);
    			g.dispose();
    		}

    		Path resizedPicPath = Paths.get(System.getProperty("user.home") + picture.getPath() + "_" + width + "_" + height);
    		File newFile = new File(resizedPicPath.toUri());
    		newFile.createNewFile();
			ImageIO.write(resizedImage, picture.getFormat().name(), newFile);
			
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

}
