package jsf;

import business.model.database.*;
import business.model.databaseManager.newsManager.NewsManagerLocal;
import business.model.databaseManager.userManager.UserManagerLocal;
import business.util.exceptions.UserNotFoundException;
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
            
    private List<News> news = new ArrayList<News>();

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
        
        try
        {
            //news.addAll(newsManager.getNewsFor(user));
            news = newsManager.getNewsFor(user);
        }
        catch (UserNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch(NoResultException ex)
        {
            ex.printStackTrace();
        }
    }

    public List<News> getNews()
    {
        return news;
    }

    public void setNews(List<News> news)
    {
        this.news = news;
    }
}