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
@Table(name = "userfriendships")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Userfriendships.findAll", query = "SELECT u FROM Userfriendships u"),
    @NamedQuery(name = "Userfriendships.findByIdUser1", query = "SELECT u FROM Userfriendships u WHERE u.userfriendshipsPK.idUser1 = :idUser1"),
    @NamedQuery(name = "Userfriendships.findByIdUser2", query = "SELECT u FROM Userfriendships u WHERE u.userfriendshipsPK.idUser2 = :idUser2")
})
public class Userfriendships implements Serializable
{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserfriendshipsPK userfriendshipsPK;

    public Userfriendships()
    {
    }

    public Userfriendships(UserfriendshipsPK userfriendshipsPK)
    {
        this.userfriendshipsPK = userfriendshipsPK;
    }

    public Userfriendships(int idUser1, int idUser2)
    {
        this.userfriendshipsPK = new UserfriendshipsPK(idUser1, idUser2);
    }

    public UserfriendshipsPK getUserfriendshipsPK()
    {
        return userfriendshipsPK;
    }

    public void setUserfriendshipsPK(UserfriendshipsPK userfriendshipsPK)
    {
        this.userfriendshipsPK = userfriendshipsPK;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (userfriendshipsPK != null ? userfriendshipsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userfriendships))
        {
            return false;
        }
        Userfriendships other = (Userfriendships) object;
        if ((this.userfriendshipsPK == null && other.userfriendshipsPK != null) || (this.userfriendshipsPK != null && !this.userfriendshipsPK.equals(other.userfriendshipsPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "business.model.database.Userfriendships[ userfriendshipsPK=" + userfriendshipsPK + " ]";
    }
    
}
