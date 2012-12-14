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
public class ImagelikesPK implements Serializable
{
    @Basic(optional = false)
    @Column(name = "idUser")
    private int idUser;
    @Basic(optional = false)
    @Column(name = "idImage")
    private int idImage;

    public ImagelikesPK()
    {
    }

    public ImagelikesPK(int idUser, int idImage)
    {
        this.idUser = idUser;
        this.idImage = idImage;
    }

    public int getIdUser()
    {
        return idUser;
    }

    public void setIdUser(int idUser)
    {
        this.idUser = idUser;
    }

    public int getIdImage()
    {
        return idImage;
    }

    public void setIdImage(int idImage)
    {
        this.idImage = idImage;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) idUser;
        hash += (int) idImage;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImagelikesPK))
        {
            return false;
        }
        ImagelikesPK other = (ImagelikesPK) object;
        if (this.idUser != other.idUser)
        {
            return false;
        }
        if (this.idImage != other.idImage)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "business.model.database.ImagelikesPK[ idUser=" + idUser + ", idImage=" + idImage + " ]";
    }
    
}
