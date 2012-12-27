package jsf;

import business.UserSessionLocal;
import business.model.database.User;

import javax.ejb.EJB;

/**
@author Joan Racenet
 */
public class UserSessionController {

    @EJB
    UserSessionLocal userSession;

    private String userFirstName;
    private String userLastName;

    public UserSessionController()
    {
        super();
    }

    public void createUserSession(User u)
    {
        this.userSession.setUser(u);
        this.userFirstName = userSession.getUserFirstName();
        this.userLastName = userSession.getUserLastName();
    }

    public String getUserFirstName()
    {
        return this.userFirstName;
    }

    public String getUserLastName()
    {
        return this.userLastName;
    }

}
