package jsf.album;

import business.model.database.Album;
import business.model.database.User;
import business.model.databaseManager.albumManager.AlbumManagerLocal;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LikeAlbumController {

    private boolean isLikedByMe;
    private int myId = -1;
    private int albumId = -1;

    @EJB
    private AlbumManagerLocal am;

    public LikeAlbumController(){}

    public boolean getIsLikedByMe() {
        System.out.println("Execute getIsLikedByMe");
        if(myId == -1)
        {
            // Récupération de l'utilisateur de la session
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
            HttpSession httpSession = request.getSession(false);
            Integer myId = (Integer)httpSession.getAttribute("userId");
        }

        // Récupération de l'id de l'album
        int idAlbum = Integer.parseInt(FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap().get("AlbumId"));

        if(idAlbum != this.albumId)
        {

            Album alb = am.findAlbumById(idAlbum);
            isLikedByMe = false;
            for(User u : alb.getLikers())
            {
                if(u.getIduser() == myId)
                {
                    isLikedByMe = true;
                    return true;
                }
            }


            return isLikedByMe;
        }
        else
        {
            return isLikedByMe;
        }
    }

    public void setIsLikedByMe(boolean likedByMe) {
        isLikedByMe = likedByMe;
    }
}
