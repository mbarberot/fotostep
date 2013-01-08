package jsf;

import business.model.database.News;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

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
    
    @PostConstruct
    public void init()
    {
        news.add(new News("Demande d'amitié", "Jane veut devenir votre ami(e) !"));
        news.add(new News("Nouvel ami(e) !", "Jane fait désormais partie de vos ami(e)s."));
        news.add(new News("Jane aime l'album \"Dora chez les ours\"", ""));
        news.add(new News("Jane a créé l'album : \"Martine et le grizzli\"", ""));
        news.add(new News("Jane a commenté l'album \"J'aime les poneys\"", "Waai les poney s'trop cool !"));
        news.add(new News("Vous aimez l'image \"Grandeur et décadence du lapin des cavernes\"", ""));
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
