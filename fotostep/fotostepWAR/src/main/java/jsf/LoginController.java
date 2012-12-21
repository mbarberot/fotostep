package jsf;

import business.model.databaseManager.userManager.UserManagerLocal;
import javax.ejb.EJB;
import javax.faces.validator.ValidatorException;

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
        return "login.success";
    }
    
    public String doLogout() throws ValidatorException
    {
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
