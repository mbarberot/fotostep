package jsf.friends;

import business.model.database.User;
import business.model.databaseManager.userManager.UserManagerLocal;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mathieu Barberot
 */
public class RequestFriendsController
{
    @EJB
    private UserManagerLocal userManager;
    
    private List<User> requesting = new ArrayList<User>();
    private boolean hasRequest = false;
    
    public RequestFriendsController()
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
        
        requesting = user.getRequestingFriends();
        hasRequest = (!requesting.isEmpty());
    }
    
    private void wannaMoreFriends(int howmany)
    {
        // TODO remove
        Date stamp = new Date();
        Random r = new Random(stamp.getTime());
        
        for(int i = 0; i < howmany; i++)
        {
            User u = new User();
            u.setIduser(r.nextInt(30));
            u.setFirstname("Joan");
            u.setLastname("Racenet");
            u.setAvatar("holder.js/64x64");
            requesting.add(u);
        }
    }

    public List<User> getRequesting()
    {
        init();
        return requesting;
    }

    public void setRequesting(List<User> requesting)
    {
        this.requesting = requesting;
    }

    public boolean isHasRequest()
    {
        init();
        return hasRequest;
    }

    public void setHasRequest(boolean hasRequest)
    {
        this.hasRequest = hasRequest;
    }
    
    public String acceptRequest()
    {
        // Récupération de l'iduser placé dans la session http
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) ctx.getExternalContext().getRequest();
        HttpSession session = req.getSession(false);
        Integer iduser = (Integer) session.getAttribute("userId");

        // Récupération de l'idfriend placé dans la requete Ajax
        ctx = FacesContext.getCurrentInstance();
        Map<String,String> requestMap = ctx.getExternalContext().getRequestParameterMap();
        String value = requestMap.get("accept-request");
        Integer idFriend = new Integer(value);
        
        
        // Récupération des utilisateurs
        User user = userManager.getUserById(iduser);
        User friend = userManager.getUserById(idFriend);
        
        // Ajout de l'amitié et suppression de la demande
        userManager.acceptFriendship(user, friend);
        
        return "ACCEPT_FRIEND_OK";

    }
    
    public String rejectRequest()
    {
        // Récupération de l'iduser placé dans la session http
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) ctx.getExternalContext().getRequest();
        HttpSession session = req.getSession(false);
        Integer iduser = (Integer) session.getAttribute("userId");

        // Récupération de l'idfriend placé dans la requete Ajax
        ctx = FacesContext.getCurrentInstance();
        Map<String,String> requestMap = ctx.getExternalContext().getRequestParameterMap();
        String value = requestMap.get("reject-request");
        Integer idFriend = new Integer(value);
        
        
        // Récupération des utilisateurs
        User user = userManager.getUserById(iduser);
        User friend = userManager.getUserById(idFriend);
        
        // Suppression de l'amitié
        userManager.rejectFriendship(user, friend);
        
        return "REJECT_FRIEND_OK";
    }

    
    
    
}
