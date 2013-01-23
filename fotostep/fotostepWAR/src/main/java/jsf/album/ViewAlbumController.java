package jsf.album;

import business.model.database.*;
import business.model.databaseManager.albumManager.AlbumManagerLocal;
import business.model.databaseManager.commentManager.CommentManagerLocal;
import business.model.databaseManager.likeManager.LikeManagerLocal;
import business.model.databaseManager.pictureManager.PictureManagerLocal;
import business.model.databaseManager.userManager.UserManagerLocal;
import business.util.exceptions.AlbumNotFoundException;
import business.utilities.JSONUtilityLocal;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Joan Racenet
 */

public class ViewAlbumController{


    @EJB
    AlbumManagerLocal am;
    @EJB
    UserManagerLocal um;
    @EJB
    LikeManagerLocal lm;
    @EJB
    CommentManagerLocal cm;
    @EJB
    JSONUtilityLocal jsonutil;
    @EJB
    PictureManagerLocal pm;

    /* Infos de controle */
    private boolean isAuthorized;
    private boolean isMine;
    private boolean isDefault;
    private boolean isLikedByMe;
    private int albId;

    /* Infos à afficher */
    private String titre = "Album inacessible";
    private User owner;
    private String description = "Aucune description pour cet album";
    private String creationDate = "Non renseignée";
    private String authorization;
    private List<Picture> pictures = new ArrayList<Picture>();
    private List<User> likers = new ArrayList<User>();
    private List<Commentalbum> comments = new ArrayList<Commentalbum>();

    /* Formulaire d'ajout d'un commentaire */
    private String commentText;
    private String picsJson;    

    public ViewAlbumController(){}

    @PostConstruct
    public void init()
    {
        Integer idUser = null, idAlbum = null;
        // Récupération des paramètres de requête
        try
        {
            idUser = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("UserId"));
            idAlbum = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("AlbumId"));
        }catch(Exception e) {System.out.println("Recupere GET : \n"); e.printStackTrace();}

        reload(idUser, idAlbum);
    }
    public void reload(Integer idUser, Integer idAlbum)
    {
        System.out.println("Voir l'album id=" + idAlbum + " de id=" + idUser);
        if(idUser == null || idAlbum == null)
        {
            isAuthorized = false;
            System.out.println("idUser ou idAlbum vaut null");
            return;
        }
        // Récupération de l'utilisateur de la session
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        Integer myId = (Integer)httpSession.getAttribute("userId");

        User myUser = um.getUserById(myId);
        System.out.println("My Id User = " + myUser.getIduser());

        // Récupération du propriétaire de l'album
        this.owner = um.getUserById(idUser);
        if(owner == null)
        {
            isAuthorized = false;
            System.out.println("Owner vaut null");
            return;
        }

        // Récupération de l'album
        Album viewedAlbum = null;
        for(Album alb : owner.getAlbums())
        {
            if(alb.getIdalbum() == idAlbum.intValue())
            {
                viewedAlbum = alb;
                break;
            }
        }
        if(viewedAlbum == null)
        {
            isAuthorized = false;
            System.out.println("ViewedAlbum vaut null");
            return;
        }

        // Vérifier si l'utilisateur connecté a le droit de voir l'album
        isMine = false;
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
                        System.out.println("Pas un ami");
                        return;
                    }
                    break;
                case PRIVATE:
                    isAuthorized = false;
                    return;

            }
        }

        // L'album existe bien et l'utilisateur connecté peut le consulter
        albId = viewedAlbum.getIdalbum();
        titre = viewedAlbum.getName();
        creationDate = viewedAlbum.getDate().toString();
        pictures = viewedAlbum.getPictures();
        likers = viewedAlbum.getLikers();
        this.isLikedByMe = false;
        for(User liker : likers)
        {
            if(liker.getIduser() == myId.intValue())
            {
                this.isLikedByMe = true;
            }
        }
        comments = viewedAlbum.getComments();
        isDefault = viewedAlbum.getIsdefault()== 1;
        picsJson = jsonutil.picturesToJSONString(this.pictures);
    }

    public String unlike()
    {

        // Récupération de l'utilisateur de la session
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        Integer myId = (Integer)httpSession.getAttribute("userId");
        User myUser = new User();
        myUser.setIduser(myId);

        context = FacesContext.getCurrentInstance();
        Map requestMap = context.getExternalContext().getRequestParameterMap();
        String value = (String)requestMap.get("unlikealbum");
        int albumtounlike = Integer.parseInt(value);

        Album currentAlbum = new Album();
        currentAlbum.setIdalbum(albumtounlike);

        // Unlike
        System.out.println("Unlike");
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
        isLikedByMe = false;

        return "UNLIKE_OK";

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

        context = FacesContext.getCurrentInstance();
        Map requestMap = context.getExternalContext().getRequestParameterMap();
        String value = (String)requestMap.get("likealbum");
        int albumtolike = Integer.parseInt(value);

        Album currentAlbum = new Album();
        currentAlbum.setIdalbum(albumtolike);

        // Like
        System.out.println("Like");
        // Màj de la vue
        likers.add(myUser);

        // Màj du modèle
        lm.like(myUser, currentAlbum);
        isLikedByMe = true;

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

        //TODO : vérifier si le commentaire est vide

        // Récupère l'utilisateur connecté
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        Integer myId = (Integer)httpSession.getAttribute("userId");
        User myUser = um.getUserById(myId);

        // Récupère l'album
        Album toComment = am.findAlbumById(this.albId);

        // Création du commentaire
        Commentalbum comm = cm.addComment(toComment,myUser,this.getCommentText());

        // Màj de la vue
        comments.add(comm);
        commentText = "";
        return "COMMENT_OK" ;
    }
    
    public String deletePicture() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        Integer myId = (Integer) httpSession.getAttribute("UserId");
        Integer albumId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("AlbumId"));
        Integer pictureId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("PictureId"));

        System.out.println("Suppression de l'image " + pictureId + " de l'album " + albumId + " sur le compte " + myId);
        Picture pic = pm.findPictureById(pictureId);
        pm.removeImage(pic);

        return "DELETE_OK";
    }
    

    public String getPicsJson() {
        return picsJson;
    }

    public void setPicsJson(String picsJson) {
        this.picsJson = picsJson;
    }

    public boolean getIsAuthorized() {
        return isAuthorized;
    }

    public void setIsAuthorized(boolean authorized) {
        isAuthorized = authorized;
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

    public boolean getIsLikedByMe() {
        return isLikedByMe;
    }

    public void setIsLikedByMe(boolean likedByMe) {
        isLikedByMe = likedByMe;
    }


    public AlbumManagerLocal getAm() {
        return am;
    }

    public void setAm(AlbumManagerLocal am) {
        this.am = am;
    }

    public LikeManagerLocal getLm() {
        return lm;
    }

    public void setLm(LikeManagerLocal lm) {
        this.lm = lm;
    }

    public int getNumberOfPictures()
    {
        return this.pictures.size();
    }

    public int getNumberOfComments()
    {
        return this.comments.size();
    }
}
