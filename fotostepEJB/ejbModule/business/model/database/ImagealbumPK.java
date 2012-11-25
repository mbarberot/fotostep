/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.model.database;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author kawa
 */
@Embeddable
public class ImagealbumPK implements Serializable
{
    @Basic(optional = false)
    @Column(name = "idImage")
    private int idImage;
    @Basic(optional = false)
    @Column(name = "idAlbum")
    private int idAlbum;

    public ImagealbumPK()
    {
    }

    public ImagealbumPK(int idImage, int idAlbum)
    {
        this.idImage = idImage;
        this.idAlbum = idAlbum;
    }

    public int getIdImage()
    {
        return idImage;
    }

    public void setIdImage(int idImage)
    {
        this.idImage = idImage;
    }

    public int getIdAlbum()
    {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum)
    {
        this.idAlbum = idAlbum;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) idImage;
        hash += (int) idAlbum;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImagealbumPK))
        {
            return false;
        }
        ImagealbumPK other = (ImagealbumPK) object;
        if (this.idImage != other.idImage)
        {
            return false;
        }
        if (this.idAlbum != other.idAlbum)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "business.model.database.ImagealbumPK[ idImage=" + idImage + ", idAlbum=" + idAlbum + " ]";
    }
    
}
