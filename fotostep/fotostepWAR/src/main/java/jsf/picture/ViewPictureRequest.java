package jsf.picture;

import business.model.database.Picture;
import jsf.album.ViewAlbumController;
import org.ajax4jsf.model.KeepAlive;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@KeepAlive
public class ViewPictureRequest implements Serializable {

    private boolean authorized;
    private int idPic;
    private int idAlb;

    public ViewPictureRequest() {
    }

    @PostConstruct
    public void init()
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

        if(idAlbum == null || idUser == null)
        {
            System.out.println("idAlbum ou idUser à null");
        }

        // Récupération de l'id de l'image
        Integer idPicture = null;

        try
        {
            idPicture = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("PictureId"));
        }catch (Exception e) {System.out.println("Recupere GET : \n"); e.printStackTrace();}

        // L'identifiant n'est pas récupéré
        if(idPicture == null)
        {
            authorized = false;
            System.out.println("PictureId à null");
            return;
        }

        // L'image appartient bien à l'album
        Picture viewedPic = new Picture();
        viewedPic.setIdpicture(idPicture);

        this.idPic = idPicture;
        this.idAlb = idAlbum;
        if(!vac.getPictures().contains(viewedPic))
        {
            authorized = false;
            System.out.println("Photo non présente dans l'album");
            return;
        }

        authorized = true;

        // On peut récupérer les données
        expr = exf.createValueExpression(elc,"#{viewPicture}",ViewPictureController.class);
        ViewPictureController vpc = (ViewPictureController)expr.getValue(elc);


        if(vpc != null && vpc.getIdPicture() != this.idPic)
        {
            vpc.reload(idPicture);
        }
    }


    public boolean getAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public int getIdPic() {
        return idPic;
    }

    public void setIdPic(int idPic) {
        this.idPic = idPic;
    }

    public int getIdAlb() {
        return idAlb;
    }

    public void setIdAlb(int idAlb) {
        this.idAlb = idAlb;
    }
}
