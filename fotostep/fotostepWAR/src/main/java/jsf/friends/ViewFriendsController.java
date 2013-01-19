package jsf.friends;

import business.model.database.User;
import business.model.databaseManager.userManager.UserManagerLocal;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    UserManagerLocal userManager;
    
    List<User> friends = new ArrayList<User>();
    
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
        Integer idUser = (Integer) session.getAttribute("userId");

        // Récupération de l'utilisateur
        User user = userManager.getUserById(idUser);
        
        friends = user.getFriends();
        wannaMoreFriends(10);
        
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
        return friends;
    }

    public void setFriends(List<User> friends)
    {
        this.friends = friends;
    }
    
    
}
