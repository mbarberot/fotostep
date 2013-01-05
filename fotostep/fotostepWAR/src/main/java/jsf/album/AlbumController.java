package jsf.album;

import business.model.databaseManager.albumManager.AlbumManager;
import business.model.databaseManager.albumManager.AlbumManagerLocal;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 * Managed Bean pour la création d'un album
 * @author Mathieu Barberot
 */
public class AlbumController
{
    @EJB
    private AlbumManagerLocal albumManager;
    
    private String user = "";
    private String name = "";
    private String description = "";
    private String permission = "";
    
    public AlbumController()
    {
    }
    
    public void validateName(FacesContext context, UIComponent component, Object value) throws ValidatorException
    {
        String formName = (String)value;
        String error = null;
        if(formName.length() <= 0)
        {
            error = "Vous devez impérativement donner un nom à votre album";
        }
        else if(formName.length() > 50)
        {
            error = "Le nom de l'album ne doit pas excéder 50 caractères.";
        }
        else if(!formName.matches("[a-z|A-Z|0-9| ]*"))
        {
            error = "Le nom de l'album contient des caractères spéciaux";
        }
        if(error != null)
        {
            throw new ValidatorException(new FacesMessage(error));
        }
    }
    
    public String createAlbum() throws ValidatorException
    {
        // TODO : Implémenter !
        
        System.out.println("Piou !");
        return "PIOU";
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
}
