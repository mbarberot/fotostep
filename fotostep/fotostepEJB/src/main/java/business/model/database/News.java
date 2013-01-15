package business.model.database;

import java.util.Date;

/**
 * News
 *
 * @author Mathieu Barberot
 */
public class News implements Comparable<News>
{
    /** Type de la news (cf NewsEnum) */
    private NewsEnum type;
    /** User sur lequel porte la news */
    private User user;
    /** Date de l'évenement */
    private Date date;
    /** Objet lié à la news (Picture, Album, ...) */
    private Object data;
    
    
    public News(User user, NewsEnum type, Date date, Object data)
    {
        this.user = user;
        this.date = date;
        this.type = type;
        this.data = data;
    }

    public int compareTo(News t)
    {
        if(this.date.before(t.date))
        {
            return 1;
        } 
        else if(this.date.after(t.date))
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }


    
    
    public User getUser()
    {
        return user;
    }

    public NewsEnum getType()
    {
        return type;
    }

    public Object getData()
    {
        return data;
    }
    
    
    
    
    
    
            
}