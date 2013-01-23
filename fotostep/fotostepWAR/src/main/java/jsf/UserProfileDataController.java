package jsf;

import business.model.database.*;
import business.model.databaseManager.userManager.UserManagerLocal;
import business.utilities.JSONUtilityLocal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Bean contenant les informations d'un utilisateur
 * @author Joan Racenet
 */
public class UserProfileDataController {
    private boolean visible = true;
    private String firstName = "Non renseigné";
    private String lastName  = "Non renseigné";
    private String birthDate  = "Non renseigné";
    private String userPlace  = "Non renseigné";
    private String gender  = "Non renseigné";
    private String registerDate;
    private String updateDate;
    private String mail  = "Non renseigné";
    private String idTwitter  = "Non renseigné";
    private String idFb = "Non renseigné";
    private Picture avatar = null;
    private List<Album> albums = new ArrayList<Album>();
    private List<User> friends = new ArrayList<User>();
    private List<Picture> localizedPicture = new ArrayList<Picture>();
    private String jsonPictures;
    private boolean isAFriend;
    private boolean profileOfMine;

    @EJB
    private UserManagerLocal um;
    @EJB
    private JSONUtilityLocal jsonutil;

    public UserProfileDataController()
    {
    }

    /**
     * Recherche tous les informations de l'utilisateur idUser
     */
    @PostConstruct
    public void init()
    {

        Integer idUser = null;
        try
        {
             idUser = Integer.parseInt(FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("UserId"));
        }
        catch(Exception e) {
            return;
        }

        if(idUser == null)
        {
            visible = false;
            return;
        }
        User viewedUser = um.getUserById(idUser);
        if(viewedUser == null)
        {
            visible = false;
            return;
        }
        firstName = viewedUser.getFirstname();
        lastName = viewedUser.getLastname();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date uBirthdate = viewedUser.getBirthdate();
        if(uBirthdate != null)
        {
          birthDate = sdf.format(uBirthdate);
        }

        String place = viewedUser.getPlace();
        if(place != null)
        {
            userPlace = place;
        }

        gender = (viewedUser.getGender().equals(GenderEnum.m))? "Homme" : "Femme";
        registerDate = sdf.format(viewedUser.getRegisterdate());
        updateDate = sdf.format(viewedUser.getUpdatedate());
        mail = viewedUser.getLogin();
        avatar = viewedUser.getAvatar();

        String userTwitter = viewedUser.getTwitterid();
        if(userTwitter != null)
        {
            idTwitter = "#"+userTwitter;
        }

        String userFb = viewedUser.getFbid();
        if(userFb != null)
        {
            idFb = "facebook.com/"+userFb;
        }

        // IsAFriend => permet d'afficher ou non le bouton d'ajout
        isAFriend = false;
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        Integer myId = (Integer)httpSession.getAttribute("userId");
        User myUser = um.getUserById(myId);


        friends = viewedUser.getFriends();



        // Récupère les albums visibles
        if(myId.equals(idUser))
        {
            isAFriend = false;
            albums = viewedUser.getAlbums();
            System.out.println("Is Profile of mine = Ok");
            profileOfMine = true;
        }
        else
        {
            isAFriend = friends.contains(myUser);
            albums = um.getAuthorizedAlbums(myUser, viewedUser);
        }

        // Récupère les photos géolocalisés pour la map view
        for(Album alb : albums)
        {
            for(Picture pic : alb.getPictures())
            {

                if(pic.getLat() != 0.0 && pic.getLgt() != 0.0)
                {
                    localizedPicture.add(pic);
                }
            }
        }
        this.jsonPictures = jsonutil.picturesToJSONString(localizedPicture);

    }

    /**
     * Getters & setters
     */
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate.toString();
    }

    public String getUserPlace() {
        return userPlace;
    }

    public void setUserPlace(String userPlace) {
        this.userPlace = userPlace;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate.toString();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getIdTwitter() {
        return idTwitter;
    }

    public void setIdTwitter(String idTwitter) {
        this.idTwitter = idTwitter;
    }

    public String getIdFb() {
        return idFb;
    }

    public void setIdFb(String idFb) {
        this.idFb = idFb;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public Picture getAvatar() {
        return avatar;
    }

    public void setAvatarPath(Picture avatar) {
        this.avatar = avatar;
    }

    public boolean getIsAFriend() {
        return isAFriend;
    }

    public void setIsAFriend(boolean AFriend) {
        isAFriend = AFriend;
    }

    public List<Picture> getLocalizedPicture() {
        return localizedPicture;
    }

    public void setLocalizedPicture(List<Picture> localizedPicture) {
        this.localizedPicture = localizedPicture;
    }

    public String getJsonPictures() {
        return jsonPictures;
    }

    public void setJsonPictures(String jsonPictures) {
        this.jsonPictures = jsonPictures;
    }

    public boolean getProfileOfMine() {
        return profileOfMine;
    }

    public void setProfileOfMine(boolean profileOfMine) {
        this.profileOfMine = profileOfMine;
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
