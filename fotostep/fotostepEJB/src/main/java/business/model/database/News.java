package business.model.database;

import java.util.Date;

/**
 * News
 *
 * @author Mathieu Barberot
 */
public class News implements Comparable<News>
{

    /*
     * Contenu nécessaire :
     *
     * - l'utilisateur 
     * - la date 
     * - le type 
     * - la donnée : Object (sera castée grâce au type)
     *
     * Types : 
     * - commentalbum -> user, commentalbum 
     * - commentpicture -> user, commentpicture
     * - likealbum -> user, album 
     * - likepicture -> user, picture 
     * - newfriend -> user, user 
     * - addphoto -> user, picture 
     * - createalbum -> user, album 
     * - updateinfo -> user
     *
     *
     * TODO : - Construire la classe - Implémenter une méthode de comparaison
     *
     */
    private String title;
    private String content;
    private NewsEnum type;
    private User user;
    private Date date;
    private Object data;
    
    public News(User user, NewsEnum type, Date date, Object data)
    {
        this.user = user;
        this.date = date;
        this.type = type;
        this.data = data;
        
        makeTitleAndContent();
    }
    
    private void makeTitleAndContent()
    {
        title = user.getFirstname() + " " + user.getLastname() + " ";
        content = "";
        
        Album alb;
        Picture pic;
        
        switch(type)
        {
            case COMMENTALBUM :
                Commentalbum commAlb = (Commentalbum) data;
                Album album = commAlb.getAlbum();
                title += "a commenté l'album " + album.getName();
                content = commAlb.getBody();
                break;
            case COMMENTPICTURE :
                Commentpicture commPic = (Commentpicture) data;
                pic = commPic.getPicture();
                title += "a commenté l'image " + pic.getPath() + " de l'album " + pic.getAlbum().getName();
                content = commPic.getBody();
                break;    
            case LIKEPICTURE :
                Likepicture likePic = (Likepicture) data;
                pic = likePic.getPicture();
                title += "aime l'image " + pic.getPath() + " de l'album " + pic.getAlbum().getName();
                break;
            
                
            // TODO : Les autres type
                    
        }
        
        title += ".";
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

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
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