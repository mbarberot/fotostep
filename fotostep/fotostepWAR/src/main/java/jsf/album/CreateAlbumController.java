package jsf.album;

import business.model.database.Album;
import business.model.database.AuthorizationEnum;
import business.model.database.User;
import business.model.databaseManager.albumManager.AlbumManagerLocal;
import business.model.databaseManager.userManager.UserManagerLocal;
import business.util.exceptions.UserNotFoundException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author Joan Racenet
 */
public class CreateAlbumController {
    private String title;
    private String description;
    private String authorization;

    @EJB
    AlbumManagerLocal am;
    @EJB
    UserManagerLocal em;
    public CreateAlbumController() {
    }

    public String doCreate()
    {
        // Récupérer l'utilisateur en session
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        Integer myId = (Integer)httpSession.getAttribute("userId");

        User myUser = em.getUserById(myId);
        if(myUser == null)
        {
            context.addMessage("Erreur", new FacesMessage("Pas d'utilisateur en session"));
        }
        else
        {
            Album alb = am.createAlbum(myUser, title, description, AuthorizationEnum.valueOf(authorization));

            if(alb == null)
            {
                context.addMessage("Erreur", new FacesMessage("Pas d'album crée"));
            }
            return "CREATE_ALBUM_OK";
        }

        return "CREATE_ALBUM_KO";
    }

    public void validateTitle(FacesContext context, UIComponent component,
                                 Object value) throws ValidatorException
    {
        String iTitle = (String) value;
        if (iTitle.length() < 1 || iTitle.length() >= 45) {
            throw new ValidatorException(new FacesMessage(
                    "Le titre doit faire entre 1 et 45 caractères"));
        }
    }

    public void validateDescription(FacesContext context, UIComponent component,
                              Object value) throws ValidatorException
    {
        String iDescription = (String) value;
        if (iDescription.length() > 65535) {
            throw new ValidatorException(new FacesMessage(
                    "La description est limitée à 65535 caractères"));
        }
    }

    public void validateAuthorization(FacesContext context, UIComponent component,
                              Object value) throws ValidatorException
    {
         // Public ou privé ou ami
        String iAuthor = (String) value;

        if(!(iAuthor.equals("FRIENDS") || iAuthor.equals("PRIVATE") || iAuthor.equals("PUBLIC") ))
        {
            throw new ValidatorException(new FacesMessage(
                    "La confidentialité peut être définie à 'Public', 'Privé' ou 'Amis'"));
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
