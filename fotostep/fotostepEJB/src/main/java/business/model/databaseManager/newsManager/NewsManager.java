package business.model.databaseManager.newsManager;

import business.model.database.*;
import business.util.exceptions.UserNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
    List<AuthorizationEnum> rights = Arrays.asList(AuthorizationEnum.FRIENDS, AuthorizationEnum.PUBLIC);

    public List<News> getNewsFor(User user) throws UserNotFoundException
    {
        ArrayList<News> news = new ArrayList<News>();
        
        if (!user.getFriends().isEmpty())
        {
            

            addCommentAlbumNews(user, news);
            addCommentPictureNews(user, news);
            addLikeAlbumNews(user, news);
            addLikePictureNews(user, news);
            addNewPhotoNews(user, news);
            addCreateAlbumNews(user, news);
            addNewFriendNews(user, news);
            addUpdateInfoNews(user, news);

            // Trier
            Collections.sort(news);
            // Récupérer les 10 dernières news
            int limit = (news.size() < 10 ? news.size() : 10);

            return news.subList(0, limit);
        }
        else
        {
            return news;
        }
    }
    
    private void addUpdateInfoNews(User user, List<News> news)
    {
        Query query = em.createQuery(
                    "SELECT u "
                    + "FROM User u "
                    + "WHERE u IN(:friends) AND u.updatedate > u.registerdate "
                    + "ORDER BY u.updatedate DESC");

            query.setParameter("friends", user.getFriends());
            query.setMaxResults(10);

            List<User> list = query.getResultList();

            if (list != null && !list.isEmpty())
            {
                for (User e : list)
                {
                    news.add(new News(e, NewsEnum.UPDATEINFO, e.getUpdatedate(), e));
                }
            }
    }

    private void addNewFriendNews(User user, List<News> news)
    {
        // In dev
        Query query = em.createQuery(
                "SELECT f "
                + "FROM Friendship f "
                + "WHERE f.user IN(:friends) AND f.user IN (SELECT f2.friend FROM Friendship f2 WHERE f2.user = f.friend) "
                + "ORDER BY f.date DESC");

        query.setParameter("friends", user.getFriends());
        query.setMaxResults(10);

        List<Friendship> list = query.getResultList();

        if (list != null && !list.isEmpty())
        {
            for (Friendship e : list)
            {
                news.add(new News(e.getUser(), NewsEnum.NEWFRIEND, e.getDate(), e));
            }
        }
    }

    private void addCreateAlbumNews(User user, List<News> news)
    {
        Query query = em.createQuery(
                "SELECT a "
                + "FROM Album a "
                + "WHERE a.authorization IN(:rights) AND a.user IN(:friends) "
                + "ORDER BY a.date DESC");

        query.setParameter("friends", user.getFriends());
        query.setParameter("rights", rights);
        query.setMaxResults(10);

        List<Album> list = query.getResultList();

        if (list != null && !list.isEmpty())
        {
            for (Album e : list)
            {
                news.add(new News(e.getUser(), NewsEnum.CREATEALBUM, e.getDate(), e));
            }
        }
    }

    private void addNewPhotoNews(User user, List<News> news)
    {
        Query query = em.createQuery(
                "SELECT p "
                + "FROM Picture p "
                + "JOIN p.album a "
                + "WHERE a.authorization IN(:rights) AND a.user IN(:friends) "
                + "ORDER BY p.date DESC");

        query.setParameter("friends", user.getFriends());
        query.setParameter("rights", rights);
        query.setMaxResults(10);

        List<Picture> list = query.getResultList();

        if (list != null && !list.isEmpty())
        {
            for (Picture e : list)
            {
                news.add(new News(e.getAlbum().getUser(), NewsEnum.ADDPHOTO, e.getDate(), e));
            }
        }
    }

    private void addLikeAlbumNews(User user, List<News> news)
    {
        Query query = em.createQuery(
                "SELECT la "
                + "FROM Likealbum la "
                + "JOIN la.album a "
                + "WHERE la.liker IN(:friends) AND a.authorization IN(:rights) "
                + "ORDER BY la.date DESC");

        query.setParameter("friends", user.getFriends());
        query.setParameter("rights", rights);
        query.setMaxResults(10);

        List<Likealbum> likeAlbum = query.getResultList();

        if (likeAlbum != null && !likeAlbum.isEmpty())
        {
            for (Likealbum la : likeAlbum)
            {
                news.add(new News(la.getLiker(), NewsEnum.LIKEALBUM, la.getDate(), la));
            }
        }
    }

    private void addLikePictureNews(User user, List<News> news)
    {
        Query query = em.createQuery(
                "SELECT lp "
                + "FROM Likepicture lp "
                + "JOIN lp.picture p "
                + "JOIN p.album a "
                + "WHERE a.authorization IN(:rights) AND lp.liker IN(:friends) "
                + "ORDER BY lp.date DESC");

        query.setParameter("friends", user.getFriends());
        query.setParameter("rights", rights);
        query.setMaxResults(10);

        List<Likepicture> likePicture = query.getResultList();

        if (likePicture != null && !likePicture.isEmpty())
        {
            for (Likepicture lp : likePicture)
            {
                news.add(new News(lp.getLiker(), NewsEnum.LIKEPICTURE, lp.getDate(), lp));
            }
        }
    }

    private void addCommentAlbumNews(User user, List<News> news)
    {
        Query query = em.createQuery(
                "SELECT ca "
                + "FROM Commentalbum ca "
                + "JOIN ca.album a "
                + "WHERE a.authorization IN(:rights) AND ca.author IN(:friends) "
                + "ORDER BY ca.date DESC");

        query.setParameter("friends", user.getFriends());
        query.setParameter("rights", rights);
        query.setMaxResults(10);

        List<Commentalbum> commentAlbum = query.getResultList();

        if (commentAlbum != null && !commentAlbum.isEmpty())
        {
            for (Commentalbum ca : commentAlbum)
            {
                news.add(new News(ca.getAuthor(), NewsEnum.COMMENTALBUM, ca.getDate(), ca));
            }
        }
    }

    private void addCommentPictureNews(User user, List<News> news)
    {
        Query query = em.createQuery("SELECT cp FROM Commentpicture cp JOIN cp.picture p JOIN p.album a WHERE a.authorization IN(:rights) AND cp.author IN(:friends) ORDER BY cp.date DESC");
        query.setParameter("friends", user.getFriends());
        query.setParameter("rights", rights);
        query.setMaxResults(10);

        List<Commentpicture> commentpicture = query.getResultList();

        if (commentpicture != null && !commentpicture.isEmpty())
        {
            for (Commentpicture cp : commentpicture)
            {
                news.add(new News(cp.getAuthor(), NewsEnum.COMMENTPICTURE, cp.getDate(), cp));
            }
        }
    }
}
