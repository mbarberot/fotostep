package jsf.album;

import business.model.database.*;
import business.model.databaseManager.albumManager.AlbumManagerLocal;
import business.model.databaseManager.commentManager.CommentManagerLocal;
import business.model.databaseManager.likeManager.LikeManagerLocal;
import business.model.databaseManager.userManager.UserManagerLocal;
import business.util.exceptions.AlbumNotFoundException;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.postgis.binary.ByteGetter;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* @author Joan Racenet
 */
public class ViewAlbumController {

    @EJB
    UserManagerLocal um;
    @EJB
    CommentManagerLocal cm;
    @EJB
    AlbumManagerLocal am;
    @EJB
    LikeManagerLocal lm;

    /* Infos de controle */
    private boolean isAuthorized = false;
    private boolean isLikedByMe;
    private boolean isMine = false;
    private boolean isDefault;
    private Album currentAlbum;

    private int albId;

    /* Infos à afficher */
    private String titre = "Album inacessible";
    //private User owner;
    private String description = "Aucune description pour cet album";
    private String creationDate = "Non renseignée";
    private String authorization;
    private List<Picture> pictures = new ArrayList<Picture>();
    private List<User> likers = new ArrayList<User>();
    private List<Commentalbum> comments = new ArrayList<Commentalbum>();

    /* Formulaire d'ajout d'un commentaire */
    private String commentText;


    public ViewAlbumController(){}

    @PostConstruct
    public void init()
    {

        // Récupération des paramètres de requête
        Integer idUser = null, idAlbum = null;
        try
        {
            idUser = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("UserId"));
            idAlbum = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("AlbumId"));
        }catch(Exception e) {}

        if(idUser == null || idAlbum == null)
        {
            isAuthorized = false;
            return;
        }
        // Récupération de l'utilisateur de la session
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        Integer myId = (Integer)httpSession.getAttribute("userId");

        User myUser = um.getUserById(myId);

        // Récupération du propriétaire de l'album
        User owner = um.getUserById(idUser);
        if(owner == null)
        {
            isAuthorized = false;
            return;
        }

        // Récupération de l'album
        Album viewedAlbum = null;
        for(Album alb : owner.getAlbums())
        {
            if(alb.getIdalbum() == idAlbum)
            {
                viewedAlbum = alb;
                break;
            }
        }
        if(viewedAlbum == null)
        {
            isAuthorized = false;
            return;
        }

        // Vérifier si l'utilisateur connecté a le droit de voir l'album
        if(myId.equals(idUser))
        {
            isAuthorized = true;
            isMine = true;
            switch(viewedAlbum.getAuthorization())
            {
                case PUBLIC:
                    authorization = "Publique";
                    break;
                case PRIVATE:
                    authorization = "Privé";
                    break;
                case FRIENDS:
                    authorization = "Amis";
                    break;
            }

        }
        else
        {
            switch(viewedAlbum.getAuthorization())
            {
                case PUBLIC:
                    isAuthorized = true;
                    authorization = "Public";
                    break;
                case FRIENDS:
                    if(owner.getFriends().contains(myUser))
                    {
                        isAuthorized = true;
                        authorization = "Amis";
                    }
                    else
                    {
                        isAuthorized = false;
                        return;
                    }
                    break;
                case PRIVATE:
                    isAuthorized = false;
                    return;

            }
        }

        // L'album existe bien et l'utilisateur connecté peut le consulter
        currentAlbum = viewedAlbum;
        albId = viewedAlbum.getIdalbum();
        titre = viewedAlbum.getName();
        creationDate = viewedAlbum.getDate().toString();
        pictures = viewedAlbum.getPictures();
        likers = viewedAlbum.getLikers();
        isLikedByMe = false;
        for(User liker : likers)
        {
            if(liker.getIduser() == myUser.getIduser())
            {
                isLikedByMe = true;
                break;
            }
        }
        comments = viewedAlbum.getComments();
        isDefault = viewedAlbum.getIsdefault()== 1;
    }

    public String like()
    {
        // Récupération de l'utilisateur de la session
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        Integer myId = (Integer)httpSession.getAttribute("userId");
        User myUser = new User();
        myUser.setIduser(myId);

        if(!isLikedByMe)
        {
            // Like

            // Màj de la vue
            likers.add(myUser);

            // Màj du modèle
            lm.like(myUser, currentAlbum);
            setIsLikedByMe(true);
        }else
        {
            // Unlike
            System.out.println("Unlike");
            likers.remove(myUser);
            int i;

            for(i = 0 ; i < likers.size() ; i++)
            {
                if(likers.get(i).getIduser() == myUser.getIduser())
                {
                    likers.remove(i);
                    i = likers.size();
                }
            }
            lm.dislike(myUser, currentAlbum);

            setIsLikedByMe(false);
        }
        return "LIKE_OK";
    }

    public String deleteAlbum()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        Map requestMap = context.getExternalContext().getRequestParameterMap();
        String value = (String)requestMap.get("deletedalb");
        int idToDelete = Integer.parseInt(value);

        Album todelete = am.findAlbumById(idToDelete);
        if(todelete != null)
        {
            try {
                am.deleteAlbum(todelete);
            } catch (AlbumNotFoundException e) {
                e.printStackTrace();
            }
        }
        return "DELETE_OK";
    }

    public String postComment()
    {
        return "COMMENT_OK" ;
    }


    public boolean getIsAuthorized() {
        return isAuthorized;
    }

    public void setIsAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }

    public boolean getIsLikedByMe() {
        return isLikedByMe;
    }

    public void setIsLikedByMe(boolean likedByMe) {
        isLikedByMe = likedByMe;
    }

    public boolean getIsMine() {
        return isMine;
    }

    public void setIsMine(boolean mine) {
        isMine = mine;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public List<User> getLikers() {
        return likers;
    }

    public void setLikers(List<User> likers) {
        this.likers = likers;
    }

    public List<Commentalbum> getComments() {
        return comments;
    }

    public void setComments(List<Commentalbum> comments) {
        this.comments = comments;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public int getAlbId() {
        return albId;
    }

    public void setAlbId(int albId) {
        this.albId = albId;
    }

    public boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}
