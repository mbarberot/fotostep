package jsf;

import business.model.database.User;
import business.model.databaseManager.userManager.UserManagerLocal;
import business.util.exceptions.UserNotFoundException;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mathieu Barberot
 */
public class LoginController
{

    private String login = "E-mail";
    private String password = "password";
    @EJB
    UserManagerLocal userManager;

    /**
     * Creates a new instance of LoginController
     */
    public LoginController()
    {
    }

    public String doLogin() throws ValidatorException
    {
        try
        {
            User u = userManager.authenticate(login, password);
        }
        catch (UserNotFoundException ex)
        {
            throw new ValidatorException(new FacesMessage("User not found : " + login));
        }

        return "login.success";
    }

    public String doLogout() throws ValidatorException
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpSession session = ((HttpSession) ec.getSession(false));
        session.invalidate();
        
        return "logout.success";
    }

    public boolean isConnected()
    {
        return false;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
