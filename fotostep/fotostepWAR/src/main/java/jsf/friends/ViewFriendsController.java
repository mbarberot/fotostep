package jsf.friends;

import business.model.database.User;
import business.model.databaseManager.userManager.UserManagerLocal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Controlleur pour l'affichage des amis.
 * 
 * @author Mathieu Barberot
 */
public class ViewFriendsController
{
    @EJB
    private UserManagerLocal userManager;
    
    private List<User> friends = new ArrayList<User>();
    private boolean hasFriend = false;
    
    /**
     * Creates a new instance of ViewFriendsController
     */
    public ViewFriendsController()
    {
    }
    
    @PostConstruct
    public void init()
    {
        // Récupération de l'iduser placé dans la session http
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) ctx.getExternalContext().getRequest();
        HttpSession session = req.getSession(false);
        Integer iduser = (Integer) session.getAttribute("userId");
        
        // Récupération de l'utilisateur
        User user = userManager.getUserById(iduser);
        
        friends = user.getFriends();
        hasFriend = !friends.isEmpty();
    }

    public List<User> getFriends()
    {
        init();
        return friends;
    }

    public void setFriends(List<User> friends)
    {
        this.friends = friends;
    }

    public boolean isHasFriend()
    {
        init();
        return hasFriend;
    }

    public void setHasFriend(boolean hasFriend)
    {
        this.hasFriend = hasFriend;
    }
    
    public String removeFriend()
    {
        // Récupération de l'iduser placé dans la session http
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) ctx.getExternalContext().getRequest();
        HttpSession session = req.getSession(false);
        Integer iduser = (Integer) session.getAttribute("userId");

        // Récupération de l'idfriend placé dans la requete Ajax
        ctx = FacesContext.getCurrentInstance();
        Map<String,String> requestMap = ctx.getExternalContext().getRequestParameterMap();
        String value = requestMap.get("remove-user");
        Integer idFriend = new Integer(value);
        
        
        // Récupération des utilisateurs
        User user = userManager.getUserById(iduser);
        User friend = userManager.getUserById(idFriend);
        
        // Suppression de l'amitié
        userManager.removeFriend(user, friend);
        
        return "REMOVE_FRIEND_OK";

    }
}
