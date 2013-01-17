package jsf;

import business.model.database.*;
import business.model.databaseManager.newsManager.NewsManagerLocal;
import business.model.databaseManager.userManager.UserManagerLocal;
import business.util.exceptions.UserNotFoundException;
import component.NewsComponent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Contrôleur pour les news
 *
 * TODO : Récupérer les news
 *
 * @author Mathieu Barberot
 */
public class NewsController
{
    
    @EJB
    UserManagerLocal userManager;
    
    @EJB
    NewsManagerLocal newsManager;
            
    private List<NewsComponent> news = new ArrayList<NewsComponent>();
    private List<News> rawNews = new ArrayList<News>();

    public NewsController()
    {
    }

    @PostConstruct
    public void init()
    {
        // Récupération de l'iduser placé dans la session http
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest)ctx.getExternalContext().getRequest();
        HttpSession session=req.getSession(false);
        Integer idUser = (Integer)session.getAttribute("userId");
       
        // Récupération de l'utilisateur
        User user = userManager.getUserById(idUser);

        if(!user.getFriends().isEmpty())
        {

            try
            {
                rawNews = newsManager.getNewsFor(user);

                List<NewsComponent> printedNews = new ArrayList<NewsComponent>();
                for(News n : rawNews)
                {
                    try
                    {
                        printedNews.add(new NewsComponent(n, idUser));
                    }
                    catch(Exception ex) { ex.printStackTrace(); }
                }
                news = printedNews;

                System.out.println("[DEBUG] Nb News : " + rawNews.size());
            }
            catch (UserNotFoundException ex)
            {
                ex.printStackTrace();
            }
            catch(NoResultException ex)
            {
                ex.printStackTrace();
            }
            System.out.println("[DEBUG] Nb News : " + news.size());
        }
    }

    public List<NewsComponent> getNews()
    {
        return news;
    }

    public void setNews(List<NewsComponent> news)
    {
        this.news = news;
    }
}