/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.model.database;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kawa
 */
@Entity
@Table(name = "imagelikes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Imagelikes.findAll", query = "SELECT i FROM Imagelikes i"),
    @NamedQuery(name = "Imagelikes.findByIdImage", query = "SELECT i FROM Imagelikes i WHERE i.imagelikesPK.idImage = :idImage"),
    @NamedQuery(name = "Imagelikes.findByIdUser", query = "SELECT i FROM Imagelikes i WHERE i.imagelikesPK.idUser = :idUser")
})
public class Imagelikes implements Serializable
{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ImagelikesPK imagelikesPK;

    public Imagelikes()
    {
    }

    public Imagelikes(ImagelikesPK imagelikesPK)
    {
        this.imagelikesPK = imagelikesPK;
    }

    public Imagelikes(int idImage, int idUser)
    {
        this.imagelikesPK = new ImagelikesPK(idImage, idUser);
    }

    public ImagelikesPK getImagelikesPK()
    {
        return imagelikesPK;
    }

    public void setImagelikesPK(ImagelikesPK imagelikesPK)
    {
        this.imagelikesPK = imagelikesPK;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (imagelikesPK != null ? imagelikesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Imagelikes))
        {
            return false;
        }
        Imagelikes other = (Imagelikes) object;
        if ((this.imagelikesPK == null && other.imagelikesPK != null) || (this.imagelikesPK != null && !this.imagelikesPK.equals(other.imagelikesPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "business.model.database.Imagelikes[ imagelikesPK=" + imagelikesPK + " ]";
    }
    
}
