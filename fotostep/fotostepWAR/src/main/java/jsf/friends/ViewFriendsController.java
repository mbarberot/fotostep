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
        wannaMoreFriends(11);
        
        hasFriend = !friends.isEmpty();
    }
    
    
    private void wannaMoreFriends(int howmany)
    {
        Date stamp = new Date();
        Random r = new Random(stamp.getTime());
        
        for(int i = 0; i < howmany; i++)
        {
            User u = new User();
            u.setIduser(r.nextInt(50));
            u.setFirstname("Joan");
            u.setLastname("Racenet");
            u.setAvatar("holder.js/64x64");
            friends.add(u);
        }
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

        ctx = FacesContext.getCurrentInstance();
        Map<String,String> requestMap = ctx.getExternalContext().getRequestParameterMap();
        String value = requestMap.get("remove-user");
        Integer idFriend = new Integer(value);
        
        System.out.println("trying to remove friend nb "+ idFriend);

        
        // Récupération des utilisateurs
        User user = userManager.getUserById(iduser);
        User friend = userManager.getUserById(idFriend);
        
        System.out.println("User = " + user + " friend = " + friend);

        // Suppression de l'amitié
        userManager.removeFriend(user, friend);
        
        
        User u2 = userManager.getUserById(iduser);
        System.out.println("still have the removed friend = " + u2.getFriends().contains(friend));

        return "REMOVE_FRIEND_OK";

    }
}
