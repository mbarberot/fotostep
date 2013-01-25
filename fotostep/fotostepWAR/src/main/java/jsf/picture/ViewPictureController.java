package jsf.picture;

import business.model.database.*;
import business.model.databaseManager.albumManager.AlbumManagerLocal;
import business.model.databaseManager.commentManager.CommentManagerLocal;
import business.model.databaseManager.likeManager.LikeManagerLocal;
import business.model.databaseManager.pictureManager.PictureManagerLocal;
import business.model.databaseManager.userManager.UserManagerLocal;
import jsf.album.ViewAlbumController;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Joan Racenet
 * */

 public class ViewPictureController {

    // Infos d'acheminement des données
    private int idPicture;

    @EJB
    PictureManagerLocal pm;
    @EJB
    LikeManagerLocal lm;
    @EJB
    CommentManagerLocal cm;
    @EJB
    UserManagerLocal um;
    @EJB
    AlbumManagerLocal am;

    // Informations affichées
    private String path;
    private String description;
    private String[] tags;
    private int width;
    private int height;
    private double lat;
    private double lgt;
    private Date postDate;
    private List<Commentpicture> comments = new ArrayList<Commentpicture>();
    private List<User> likers = new ArrayList<User>();
    private int albumInd;

    private Picture nextPic = null;
    private Picture prevPic = null;

    private boolean likedByMe;

    private String commentText;

    private Album myAlbum;
    private Picture myPicture;

    private boolean isMine;
    public ViewPictureController() {
    }

    @PostConstruct
    public void init()
    {
        // Récupération de l'id de l'image
        Integer idPicture = null;

        try
        {
            idPicture = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("PictureId"));
        }catch (Exception e) {System.out.println("Recupere GET : \n"); e.printStackTrace();}

        reload(idPicture);
    }

    public void reload(Integer idPicture)
    {

        Picture viewedPicture = pm.findPictureById(idPicture.intValue());

        this.idPicture = idPicture.intValue();
        path = viewedPicture.getPath();
        description = viewedPicture.getDescription();
        width = viewedPicture.getWidth();
        height = viewedPicture.getHeight();

        lgt = viewedPicture.getLgt();
        lat = viewedPicture.getLat() ;

        postDate = viewedPicture.getDate();

        comments = viewedPicture.getComments();
        likers = viewedPicture.getLikers();

        tags = viewedPicture.getTags().split(";");

        FacesContext ctx = FacesContext.getCurrentInstance();
        Application application = ctx.getApplication();

        ExpressionFactory exf = application.getExpressionFactory();
        ELContext elc = ctx.getELContext();
        ValueExpression expr = exf.createValueExpression(elc,"#{viewAlbum}",ViewAlbumController.class);
        ViewAlbumController vac = (ViewAlbumController)expr.getValue(elc);

        this.albumInd =  vac.getPictures().indexOf(viewedPicture) + 1;

        try
        {
            this.nextPic = vac.getPictures().get(this.albumInd);
        }catch (IndexOutOfBoundsException e){}

        try
        {
            this.prevPic = vac.getPictures().get(this.albumInd - 2);
        }catch (IndexOutOfBoundsException e){}

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        Integer myId = (Integer)httpSession.getAttribute("userId");
        User myUser = new User();
        myUser.setIduser(myId);

        myAlbum = viewedPicture.getAlbum();
        likedByMe = likers.contains(myUser);
        myPicture = viewedPicture;
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

        Picture currentPicture = new Picture();
        currentPicture.setIdpicture(this.idPicture);

        // Like
        System.out.println("Like");
        // Màj de la vue
        likers.add(myUser);

        // Màj du modèle
        lm.like(myUser,currentPicture);
        likedByMe = true;

        return "LIKE_OK";
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

        Picture currentPicture = new Picture();
        currentPicture.setIdpicture(this.idPicture);

        // Like
        System.out.println("Like");

        // Màj de la vue
        likers.remove(myUser);

        // Màj du modèle
        lm.dislike(myUser, currentPicture);
        likedByMe = false;

        return "UNLIKE_OK";
    }

    public String postComment()
    {
        // Récupère l'utilisateur connecté
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        Integer myId = (Integer)httpSession.getAttribute("userId");
        User myUser = um.getUserById(myId);

        // Récupère l'album
        Picture toComment = pm.findPictureById(this.idPicture);

        // Création du commentaire
        Commentpicture comm = cm.addComment(toComment, myUser, this.getCommentText());

        // Màj de la vue
        comments.add(comm);
        commentText = "";
        return "COMMENT_OK" ;
    }

    public String chooseAsAvatar()
    {
        // Récupère l'utilisateur connecté
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        Integer myId = (Integer)httpSession.getAttribute("userId");

        User myUser = new User();
        myUser.setIduser(myId);
        Picture newAv = new Picture();
        newAv.setIdpicture(this.idPicture);
        um.setAvatar(myUser,newAv);

        return "SET_AV_OK";
    }


    public String chooseAsCoverImage()
    {

        am.setCoverImage(this.myAlbum, this.myPicture);
        return "SET_COVER_IMAGE_OK";
    }

    public String validateLoc()
    {
        System.out.println("Lgt = " + this.lgt + "Lat = " + this.lat);
        pm.editImage(this.myPicture, this.myAlbum, this.description, this.myPicture.getDescription(),lgt, lat);

        return null;
    }

    public String moveInDefaultAlbum()
    {
        return null;
    }

    public int getIdPicture() {
        return idPicture;
    }

    public void setIdPicture(int idPicture) {
        this.idPicture = idPicture;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLgt() {
        return lgt;
    }

    public void setLgt(double lgt) {
        this.lgt = lgt;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public List<Commentpicture> getComments() {
        return comments;
    }

    public void setComments(List<Commentpicture> comments) {
        this.comments = comments;
    }

    public List<User> getLikers() {
        return likers;
    }

    public void setLikers(List<User> likers) {
        this.likers = likers;
    }

    public int getAlbumInd() {
        return albumInd;
    }

    public void setAlbumInd(int albumInd) {
        this.albumInd = albumInd;
    }

    public Picture getPrevPic() {
        return prevPic;
    }

    public void setPrevPic(Picture prevPic) {
        this.prevPic = prevPic;
    }

    public Picture getNextPic() {
        return nextPic;
    }

    public void setNextPic(Picture nextPic) {
        this.nextPic = nextPic;
    }


    public boolean isLikedByMe() {
        return likedByMe;
    }

    public void setLikedByMe(boolean likedByMe) {
        this.likedByMe = likedByMe;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }


    public int getNumberOfComments()
    {
        return this.comments.size();
    }

}
