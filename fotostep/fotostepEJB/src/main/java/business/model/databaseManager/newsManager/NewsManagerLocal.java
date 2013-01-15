package business.model.databaseManager.newsManager;

import business.model.database.News;
import business.model.database.User;
import business.util.exceptions.UserNotFoundException;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface locale du bean NewsManager
 * 
 * @author Mathieu Barberot
 */
@Local
public interface NewsManagerLocal
{
    public List<News> getNewsFor(User user) throws UserNotFoundException;
}
