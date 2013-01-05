package jsf.album;

import business.model.database.*;
import business.model.databaseManager.commentManager.CommentManagerLocal;
import business.model.databaseManager.userManager.UserManagerLocal;
import org.postgis.binary.ByteGetter;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
* @author Joan Racenet
 */
public class ViewAlbumController {

    @EJB
    UserManagerLocal um;
    @EJB
    CommentManagerLocal cm;

    /* Infos de controle */
    private boolean isAuthorized = false;
    private boolean isLikedByMe;
    private boolean isMine = false;

    /* Infos à afficher */
    private String titre = "Album inacessible";
    //private User owner;
    private String description = "Aucune description pour cet album";
    private String creationDate = "Non renseigné";
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
            if(viewedAlbum.getAuthorization() == AuthorizationEnum.PRIVATE)
            {
                authorization = "Privé";
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
        titre = viewedAlbum.getName();
        description = viewedAlbum.getDescription();
        creationDate = viewedAlbum.getDate().toString();
        pictures = viewedAlbum.getPictures();
        likers = viewedAlbum.getLikers();
        isLikedByMe = likers.contains(myUser);
        comments = viewedAlbum.getComments();
    }

    public String like()
    {
        if(!isLikedByMe)
        {
            // Like
        }else
        {
            // Unlike
        }

        return "LIKE_OK";
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
}
