package business.model.databaseManager.imageManager;

import javax.ejb.Local;

/**
 * Interface locale du bean ImageManager
 *
 * @author Mathieu Barberot
 */
@Local
public interface ImageManagerLocal
{
    
	/*
	 * Register a image
	 * return id
	 */
	public int registerImage();
	
	public String getName(int id);
	
	public String getDescription(int id);
	
	public String getWidth(int id);
	
	public String getHeight(int id);
	
	public int getThumb(int id);
}
