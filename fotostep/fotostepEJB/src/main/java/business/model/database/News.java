package business.model.database;

/**
 * Cette classe est temporaire.
 * 
 * TODO : Créer la vraie entité
 * 
 * @author Mathieu Barberot
 */
public class News
{
    private String title, body;

    public News(String title, String body)
    {
        this.title = title;
        this.body = body;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    
}
