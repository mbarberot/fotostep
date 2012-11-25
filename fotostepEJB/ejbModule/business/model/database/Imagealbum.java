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
@Table(name = "imagealbum")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Imagealbum.findAll", query = "SELECT i FROM Imagealbum i"),
    @NamedQuery(name = "Imagealbum.findByIdImage", query = "SELECT i FROM Imagealbum i WHERE i.imagealbumPK.idImage = :idImage"),
    @NamedQuery(name = "Imagealbum.findByIdAlbum", query = "SELECT i FROM Imagealbum i WHERE i.imagealbumPK.idAlbum = :idAlbum")
})
public class Imagealbum implements Serializable
{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ImagealbumPK imagealbumPK;

    public Imagealbum()
    {
    }

    public Imagealbum(ImagealbumPK imagealbumPK)
    {
        this.imagealbumPK = imagealbumPK;
    }

    public Imagealbum(int idImage, int idAlbum)
    {
        this.imagealbumPK = new ImagealbumPK(idImage, idAlbum);
    }

    public ImagealbumPK getImagealbumPK()
    {
        return imagealbumPK;
    }

    public void setImagealbumPK(ImagealbumPK imagealbumPK)
    {
        this.imagealbumPK = imagealbumPK;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (imagealbumPK != null ? imagealbumPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Imagealbum))
        {
            return false;
        }
        Imagealbum other = (Imagealbum) object;
        if ((this.imagealbumPK == null && other.imagealbumPK != null) || (this.imagealbumPK != null && !this.imagealbumPK.equals(other.imagealbumPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "business.model.database.Imagealbum[ imagealbumPK=" + imagealbumPK + " ]";
    }
    
}
