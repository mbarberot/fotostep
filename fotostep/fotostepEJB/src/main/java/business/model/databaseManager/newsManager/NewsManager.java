package business.model.databaseManager.newsManager;

import business.model.database.Commentalbum;
import business.model.database.News;
import business.model.database.NewsEnum;
import business.model.database.User;
import business.util.exceptions.UserNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Bean permettant d'acquérir les news.
 *
 * @author Barberot Mathieu
 */
@Stateless
public class NewsManager implements NewsManagerLocal
{

    @PersistenceContext
    EntityManager em;

    public List<News> getNewsFor(User user) throws UserNotFoundException
    {
        List<News> news = new ArrayList<News>();


        Query query;

        query = em.createQuery("SELECT ca FROM Commentalbum ca JOIN ca.author u WHERE u IN(:friends) ORDER BY ca.date DESC");
        query.setParameter("friends", user.getFriends());
        query.setMaxResults(10);
        
        List<Commentalbum> commentAlbum = query.getResultList();
        
        if(commentAlbum != null && !commentAlbum.isEmpty())
        {
            for(Commentalbum ca : commentAlbum)
            {   
                news.add(new News(ca.getAuthor(), NewsEnum.COMMENTALBUM, ca.getDate(), ca));
            }
        }
        
        return news;
    }
}
