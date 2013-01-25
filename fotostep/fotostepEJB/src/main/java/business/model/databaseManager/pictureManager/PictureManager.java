package business.model.databaseManager.pictureManager;

import business.model.database.*;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

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

    private static Point []thumbsFormat = new Point[]{new Point(64,64),new Point(100, 100),new Point(250, 200),new Point(800,600)};
    
    
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

        for(Point f : thumbsFormat){
            generateThumb(picture, f.x, f.y);
        }
        
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

        for(Point f : thumbsFormat){
            generateThumb(picture, f.x, f.y);
        }
        
        return picture;
    }

    public void changeAlbum(Picture picture, Album album)
    {
        Picture toUpdate = findPictureById(picture.getIdpicture());
        Album alb = em.find(Album.class, album.getIdalbum());
        toUpdate.setAlbum(alb);

        em.persist(toUpdate);
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

        Picture todel = em.find(Picture.class, picture.getIdpicture());
        todel.getLikers().clear();
        em.persist(todel);
        em.flush();

        em.clear();

        Picture toRedel = em.find(Picture.class, todel.getIdpicture());

        em.remove(toRedel);

        Path path = Paths.get(System.getProperty("user.home") + picture.getPath());
        File file = new File(path.toUri());
        if(file.exists()){
        	file.delete();
        }

        for(Point f : thumbsFormat){
        	path = Paths.get(System.getProperty("user.home") + picture.getPath() + "_" + f.x + "_" + f.y);
        	file = new File(path.toUri());
        	if(file.exists()){
        		file.delete();
        	}

        }
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
