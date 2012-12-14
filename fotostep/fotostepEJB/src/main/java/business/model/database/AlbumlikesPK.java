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
public class AlbumlikesPK implements Serializable
{
    @Basic(optional = false)
    @Column(name = "idUser")
    private int idUser;
    @Basic(optional = false)
    @Column(name = "idAlbum")
    private int idAlbum;

    public AlbumlikesPK()
    {
    }

    public AlbumlikesPK(int idUser, int idAlbum)
    {
        this.idUser = idUser;
        this.idAlbum = idAlbum;
    }

    public int getIdUser()
    {
        return idUser;
    }

    public void setIdUser(int idUser)
    {
        this.idUser = idUser;
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
        hash += (int) idUser;
        hash += (int) idAlbum;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlbumlikesPK))
        {
            return false;
        }
        AlbumlikesPK other = (AlbumlikesPK) object;
        if (this.idUser != other.idUser)
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
        return "business.model.database.AlbumlikesPK[ idUser=" + idUser + ", idAlbum=" + idAlbum + " ]";
    }
    
}
