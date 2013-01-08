package jsf;

import business.model.database.News;
import java.util.ArrayList;
import java.util.List;

/**
 * Contrôleur pour les news
 * 
 * TODO : Récupérer les news
 * 
 * @author Mathieu Barberot
 */
public class NewsController
{

    List<News> news = new ArrayList<News>();
    
    public NewsController()
    {
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
