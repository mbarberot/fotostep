package jsf.album;

import org.ajax4jsf.model.KeepAlive;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@KeepAlive
public class ViewAlbumRequest implements Serializable {

    private int id = 0;

    public ViewAlbumRequest() {}

    @PostConstruct
    public void refresh()
    {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application application = ctx.getApplication();

        ExpressionFactory exf = application.getExpressionFactory();
        ELContext elc = ctx.getELContext();
        ValueExpression expr = exf.createValueExpression(elc,"#{viewAlbum}",ViewAlbumController.class);
        ViewAlbumController vac = (ViewAlbumController)expr.getValue(elc);

        Integer idUser = null, idAlbum = null;
        // Récupération des paramètres de requête
        try
        {
            idUser = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("UserId"));
            idAlbum = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("AlbumId"));
        }catch(Exception e) {System.out.println("Recupere GET : \n"); e.printStackTrace();}
        this.id = idAlbum;

        // On reload l'album
        vac.reload(idUser, idAlbum);



    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
