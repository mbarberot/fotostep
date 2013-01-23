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
    
    private List<User> requestingFrom = new ArrayList<User>();
    private List<User> requestingTo = new ArrayList<User>();
    private boolean hasRequestFrom = false;
    private boolean hasRequestTo = false;
    private String sizeFrom = "";
    private String sizeTo = "";
    
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
        
        requestingFrom = user.getRequestingFrom();
        hasRequestFrom = (!requestingFrom.isEmpty());
        sizeFrom = ""+requestingFrom.size();
        
        requestingTo = user.getRequestingTo();
        hasRequestTo = (!requestingTo.isEmpty());
        sizeTo = ""+requestingTo.size();
    }
    
    private void loadFrom()
    {
        // Récupération de l'iduser placé dans la session http
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) ctx.getExternalContext().getRequest();
        HttpSession session = req.getSession(false);
        Integer iduser = (Integer) session.getAttribute("userId");
        
        // Récupération de l'utilisateur
        User user = userManager.getUserById(iduser);
        
        requestingFrom = user.getRequestingFrom();
        hasRequestFrom = (!requestingFrom.isEmpty());
        sizeFrom = ""+requestingFrom.size();
    }
    
    private void loadTo()
    {
        // Récupération de l'iduser placé dans la session http
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) ctx.getExternalContext().getRequest();
        HttpSession session = req.getSession(false);
        Integer iduser = (Integer) session.getAttribute("userId");
        
        // Récupération de l'utilisateur
        User user = userManager.getUserById(iduser);
        
        requestingTo = user.getRequestingTo();
        hasRequestTo = (!requestingTo.isEmpty());
        sizeTo = ""+requestingTo.size();
    }

    public boolean isHasRequestFrom()
    {
        loadFrom();
        return hasRequestFrom;
    }

    public void setHasRequestFrom(boolean hasRequestFrom)
    {
        this.hasRequestFrom = hasRequestFrom;
    }

    public boolean isHasRequestTo()
    {
        loadTo();
        return hasRequestTo;
    }

    public void setHasRequestTo(boolean hasRequestTo)
    {
        this.hasRequestTo = hasRequestTo;
    }

    public List<User> getRequestingFrom()
    {
        loadFrom();
        return requestingFrom;
    }

    public void setRequestingFrom(List<User> requestingFrom)
    {
        this.requestingFrom = requestingFrom;
    }

    public List<User> getRequestingTo()
    {
        loadTo();
        return requestingTo;
    }

    public void setRequestingTo(List<User> requestingTo)
    {
        this.requestingTo = requestingTo;
    }

    public String getSizeFrom()
    {
        loadFrom();
        return sizeFrom;
    }

    public void setSizeFrom(String sizeFrom)
    {
        this.sizeFrom = sizeFrom;
    }

    public String getSizeTo()
    {
        loadTo();
        return sizeTo;
    }

    public void setSizeTo(String sizeTo)
    {
        this.sizeTo = sizeTo;
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
    
    public String cancelRequest()
    {
        // Récupération de l'iduser placé dans la session http
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) ctx.getExternalContext().getRequest();
        HttpSession session = req.getSession(false);
        Integer iduser = (Integer) session.getAttribute("userId");

        // Récupération de l'idfriend placé dans la requete Ajax
        ctx = FacesContext.getCurrentInstance();
        Map<String,String> requestMap = ctx.getExternalContext().getRequestParameterMap();
        String value = requestMap.get("cancel-request");
        Integer idFriend = new Integer(value);
        
        // Récupération des utilisateurs
        User user = userManager.getUserById(iduser);
        User friend = userManager.getUserById(idFriend);
        
        // Suppression de l'amitié
        userManager.cancelFriendship(user, friend);
        
        return "CANCEL_FRIEND_OK";
    }
    
}
