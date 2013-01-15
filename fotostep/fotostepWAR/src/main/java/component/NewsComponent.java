package component;

import business.model.database.*;

/**
 * Composant des News pour l'affichage
 * @author Mathieu Barberot
 */
public class NewsComponent 
{
    protected String title;
    protected String content;
    protected String userpage;
    protected String avatar;
    
    public NewsComponent(News news, int idUser)
    {
        title = "";
        content = "";
        userpage = "";
        avatar = "";
             
        
        initTitleAndContent(news,idUser);
        initUserpageAndAvatar(news.getUser()); 
        
        System.out.println("[DEBUG]" + title);
    }
    
    private void initTitleAndContent(News news, int idUser)
    {
        // Génération du nom + prénom
        User user = news.getUser();
        title += user.getFirstname() + " " + user.getLastname() + " ";
        
        // Génération de l'action
        NewsEnum type = news.getType();
        
        Picture p;
        Album a;
        User u;
        
        switch(type)
        {
            case ADDPHOTO :
                p = (Picture) news.getData();
                a = p.getAlbum();
                title += "a ajouté une "
                        + "<a href=\"" + p.getPath() + "\">image</a> "
                        + "à son album "
                        + "<a href=\"view-album.jsf?UserId=" + idUser + "&AlbumId=" + a.getIdalbum() + "\">"
                        + "&quot;" + a.getName() + "&quot;"
                        + "</a>.";
                content = "&quot;" + p.getDescription() + "&quot;";
                break;
                
            case CREATEALBUM :
                a = (Album) news.getData();
                title += "a créé un nouvel album : "
                        + "<a href=\"view-album.jsf?UserId=" + idUser + "&AlbumId=" + a.getIdalbum() + "\">"
                        + "&quot;" + a.getName() + "&quot;"
                        + "</a>.";
                content = "&quot;" + a.getDescription() + "&quot;";
                break;

            case COMMENTPICTURE :
                Commentpicture cp = (Commentpicture) news.getData();
                p = cp.getPicture();
                a = p.getAlbum();
                title += "a commenté une "
                        + "<a href=\"" + p.getPath() + "\">"
                        + "image"
                        + "</a> "
                        + "de l'album "
                        + "<a href=\"view-album.jsf?UserId=" + idUser + "&AlbumId=" + a.getIdalbum() + "\">"
                        + "&quot;" + a.getName() + "&quot;"
                        + "</a>.";
                content = "&quot;" + cp.getBody() + "&quot;";
                break;

            case COMMENTALBUM:
                Commentalbum ca = (Commentalbum) news.getData();
                a = ca.getAlbum();
                title += "a commenté l'album "
                        + "<a href=\"view-album.jsf?UserId=" + idUser + "&AlbumId=" + a.getIdalbum() + "\">"
                        + "&quot;" + a.getName() + "&quot;"
                        + "</a>.";
                content = "&quot;" + ca.getBody() + "&quot;";
                break;
                
            case LIKEPICTURE :
                Likepicture lp = (Likepicture) news.getData();
                p = lp.getPicture();
                a = p.getAlbum();
                title += "aime l'"
                        + "<a href=\"" + p.getPath() + "\">"
                        + "image"
                        + "</a> "
                        + "de l'album "
                        + "<a href=\"view-album.jsf?UserId=" + idUser + "&AlbumId=" + a.getIdalbum() + "\">"
                        + "&quot;" + a.getName() + "&quot;"
                        + "</a>.";
                break;
                
            case LIKEALBUM :
                Likealbum la = (Likealbum) news.getData();
                a = la.getAlbum();
                title += "a aime l'album "
                        + "<a href=\"view-album.jsf?UserId=" + idUser + "&AlbumId=" + a.getIdalbum() + "\">"
                        + "&quot;" + a.getName() + "&quot;"
                        + "</a>.";
                break;
                
            case NEWFRIEND :
                Userfriendship uf = (Userfriendship) news.getData();
                u = uf.getUser2();
                title += "est ami avec "
                        + "<a href=\"view-profile.jsf?UserId=" + u.getIduser() + "\">"
                        + u.getFirstname() + " " + u.getLastname()
                        + "</a>.";
                break;
                
            case UPDATEINFO :
                title += "a mis à jour ses informations personnelles.";
                break;
        }
        
    }

    
    private void initUserpageAndAvatar(User user)
    {
        userpage = "view-profile.jsf?UserId=" + user.getIduser();
        avatar = user.getAvatar();
        
        if(avatar == null || avatar.isEmpty())
        {
            avatar = "holder.js/64x64";
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

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getUserpage()
    {
        return userpage;
    }

    public void setUserpage(String userpage)
    {
        this.userpage = userpage;
    }
}
