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
public class UserfriendshipsPK implements Serializable
{
    @Basic(optional = false)
    @Column(name = "idUser1")
    private int idUser1;
    @Basic(optional = false)
    @Column(name = "idUser2")
    private int idUser2;

    public UserfriendshipsPK()
    {
    }

    public UserfriendshipsPK(int idUser1, int idUser2)
    {
        this.idUser1 = idUser1;
        this.idUser2 = idUser2;
    }

    public int getIdUser1()
    {
        return idUser1;
    }

    public void setIdUser1(int idUser1)
    {
        this.idUser1 = idUser1;
    }

    public int getIdUser2()
    {
        return idUser2;
    }

    public void setIdUser2(int idUser2)
    {
        this.idUser2 = idUser2;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) idUser1;
        hash += (int) idUser2;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserfriendshipsPK))
        {
            return false;
        }
        UserfriendshipsPK other = (UserfriendshipsPK) object;
        if (this.idUser1 != other.idUser1)
        {
            return false;
        }
        if (this.idUser2 != other.idUser2)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "business.model.database.UserfriendshipsPK[ idUser1=" + idUser1 + ", idUser2=" + idUser2 + " ]";
    }
    
}
