package jsf;

import business.model.database.Album;
import business.model.database.User;
import business.model.databaseManager.userManager.UserManagerLocal;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Contient la liste des albums à afficher pour l'utilisateur loggé
 * @author Joan Racenet
 */
public class ViewAlbumsController {

    private List<Album> albums = new ArrayList<Album>();
    @EJB
    UserManagerLocal um;

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
        return "";
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
