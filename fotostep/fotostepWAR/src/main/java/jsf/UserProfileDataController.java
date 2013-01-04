package jsf;

import business.model.database.Album;
import business.model.database.GenderEnum;
import business.model.database.Picture;
import business.model.database.User;
import business.model.databaseManager.userManager.UserManagerLocal;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Bean contenant les informations d'un utilisateur
 * @author Joan Racenet
 */
public class UserProfileDataController {

    private String firstName = "Non renseigné";
    private String lastName  = "Non renseigné";
    private String birthDate  = "Non renseigné";
    private String userPlace  = "Non renseigné";
    private String gender  = "Non renseigné";
    private String registerDate;
    private String mail  = "Non renseigné";
    private String idTwitter  = "Non renseigné";
    private String idFb = "Non renseigné";
    private String avatarPath  = "";
    private List<Album> albums = new ArrayList<Album>();
    private List<User> friends = new ArrayList<User>();

    private List<Album> localizedAlbums = new ArrayList<Album>();
    private boolean isAFriend;


    @EJB
    private UserManagerLocal um;

    public UserProfileDataController()
    {
    }

    /**
     * Recherche tous les informations de l'utilisateur idUser
     */
    @PostConstruct
    public void init()
    {

        int idUser = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("UserId"));
        User myUser = um.getUserById(idUser);
        firstName = myUser.getFirstname();
        lastName = myUser.getLastname();

        Date uBirthdate = myUser.getBirthdate();
        if(uBirthdate != null)
        {
          birthDate = uBirthdate.toString();
        }

        String place = myUser.getPlace();
        if(place != null)
        {
            userPlace = place;
        }

        gender = (myUser.getGender().equals(GenderEnum.m))? "Homme" : "Femme";
        registerDate = myUser.getRegisterdate().toString();
        mail = myUser.getLogin();

        String userTwitter = myUser.getTwitterid();
        if(userTwitter != null)
        {
            idTwitter = "#"+userTwitter;
        }

        String userFb = myUser.getFbid();
        if(userFb != null)
        {
            idFb = "facebook.com/"+userFb;
        }
        albums = myUser.getAlbums();
        friends = myUser.getFriends();

        // Récupère les albums géolocalisés pour la map view
        for(Album alb : albums)
        {
            Picture cover = alb.getCoverPicture();
            if(cover != null && cover.getCoord() != null)
            {
                localizedAlbums.add(alb);
            }
        }
        // IsAFriend => permet d'afficher ou non le bouton d'ajout
        isAFriend = false;
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        Integer myId = (Integer)httpSession.getAttribute("userId");


        if(myId == idUser)
        {
            isAFriend = true;
        }
        else
        {
            for(User friend : friends)
            {
                  if(friend.getIduser() == myId)
                  {
                      isAFriend = true;
                      return;
                  }
            }
        }
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

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public boolean getIsAFriend() {
        return isAFriend;
    }

    public void setIsAFriend(boolean AFriend) {
        isAFriend = AFriend;
    }

    public List<Album> getLocalizedAlbums() {
        return localizedAlbums;
    }

    public void setLocalizedAlbums(List<Album> localizedAlbums) {
        this.localizedAlbums = localizedAlbums;
    }
}
