package business.model.databaseManager.newsManager;

import business.model.database.*;
import business.util.exceptions.UserNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
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
        ArrayList<News> news = new ArrayList<News>();

        addCommentAlbumNews(user, news);
        addCommentPictureNews(user, news);
        
        // In dev
        
        
        // Trier
        Collections.sort(news);
        // Récupérer les 10 dernières news
        int limit = (news.size() < 10 ? news.size() : 10);
        
        return news.subList(0, limit) ;
    }
    
    private void addCommentAlbumNews(User user, List<News> news)
    {
        Query query = em.createQuery("SELECT ca FROM Commentalbum ca JOIN ca.author u WHERE u IN(:friends) ORDER BY ca.date DESC");
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
    }
    
    private void addCommentPictureNews(User user, List<News> news)
    {
        Query query = em.createQuery("SELECT cp FROM Commentpicture cp JOIN cp.author u WHERE u IN(:friends) ORDER BY cp.date DESC");
        query.setParameter("friends", user.getFriends());
        query.setMaxResults(10);
        
        List<Commentpicture> commentpicture = query.getResultList();
        
        if(commentpicture != null && !commentpicture.isEmpty())
        {
            for(Commentpicture cp : commentpicture)
            {   
                news.add(new News(cp.getAuthor(), NewsEnum.COMMENTPICTURE, cp.getDate(), cp));
            }
        }
    }
}
