package jsf;

import business.model.database.User;
import business.model.databaseManager.userManager.UserManagerLocal;
import business.util.exceptions.UserNotFoundException;
import business.utilities.HashingUtilityLocal;

import javax.ejb.EJB;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
    @EJB
    HashingUtilityLocal hashTool;
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
            String hashPass = hashTool.md5Hash(password);
            User u = userManager.authenticate(login, hashPass);

            // Création de la session HTTP
            FacesContext ctx = FacesContext.getCurrentInstance();
            HttpServletRequest req = (HttpServletRequest)ctx.getExternalContext().getRequest();
            HttpSession session=req.getSession();
            session.setAttribute("userId", u.getIduser());


            // Création du bean de gestion de session
            Application application = ctx.getApplication();

            ExpressionFactory exf = application.getExpressionFactory();
            ELContext elc = ctx.getELContext();
            ValueExpression expr = exf.createValueExpression(elc,"#{userSessionController}",UserSessionController.class);
            UserSessionController usc = (UserSessionController)expr.getValue(elc);

            usc.createUserSession(u);


            return "login.success";
        }
        catch (UserNotFoundException ex)
        {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage("Echec de l'identification", new FacesMessage("Le mail ou le mot de passe est incorrect"));
        }

        return "login.fail";
    }

    public String doLogout() throws ValidatorException
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpSession session = ((HttpSession) ec.getSession(false));
        session.invalidate();
        try {
            ec.redirect("fotostep/");
        } catch (IOException e) {
        }
        return "logout.success";
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
