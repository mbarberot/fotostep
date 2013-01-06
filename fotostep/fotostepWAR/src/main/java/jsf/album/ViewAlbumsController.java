package jsf.album;

import business.model.database.Album;
import business.model.database.User;
import business.model.databaseManager.albumManager.AlbumManagerLocal;
import business.model.databaseManager.userManager.UserManagerLocal;
import business.util.exceptions.AlbumNotFoundException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Contient la liste des albums à afficher pour l'utilisateur loggé
 * @author Joan Racenet
 */
public class ViewAlbumsController {

    private List<Album> albums = new ArrayList<Album>();
    @EJB
    UserManagerLocal um;
    @EJB
    AlbumManagerLocal am;
    private int handledAlbum;
    public ViewAlbumsController()
    {
    }

    @PostConstruct
    public void init()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        Integer myId = (Integer)httpSession.getAttribute("userId");
        User u = um.getUserById(myId);

        albums = u.getAlbums();
    }

    public String deleteAlbum()
    {
        // Récupération de l'utilisateur
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        Integer myId = (Integer)httpSession.getAttribute("userId");
        User u = um.getUserById(myId);

        // Récupération de l'id album
        Map requestMap = context.getExternalContext().getRequestParameterMap();
        String value = (String)requestMap.get("deletedalb");
        int idToDelete = Integer.parseInt(value);

        Album todelete = am.findAlbumById(idToDelete);
        try {
            am.deleteAlbum(todelete);
        } catch (AlbumNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return "DELETE-OK";
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public int getHandledAlbum() {
        return handledAlbum;
    }

    public void setHandledAlbum(int handledAlbum) {
        this.handledAlbum = handledAlbum;
    }
}
