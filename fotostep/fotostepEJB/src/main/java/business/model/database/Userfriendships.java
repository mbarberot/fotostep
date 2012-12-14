/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.model.database;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author kawa
 */
@Entity
@Table(name = "userfriendships")
@NamedQueries(
{
    @NamedQuery(name = "Userfriendships.findAll", query = "SELECT u FROM Userfriendships u"),
    @NamedQuery(name = "Userfriendships.findByIdUser1", query = "SELECT u FROM Userfriendships u WHERE u.userfriendshipsPK.idUser1 = :idUser1"),
    @NamedQuery(name = "Userfriendships.findByIdUser2", query = "SELECT u FROM Userfriendships u WHERE u.userfriendshipsPK.idUser2 = :idUser2"),
    @NamedQuery(name = "Userfriendships.findByDate", query = "SELECT u FROM Userfriendships u WHERE u.date = :date")
})
public class Userfriendships implements Serializable
{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserfriendshipsPK userfriendshipsPK;
    @Basic(optional = false)
    @Column(name = "date")
    private int date;
    @JoinColumn(name = "idUser2", referencedColumnName = "idUser", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @JoinColumn(name = "idUser1", referencedColumnName = "idUser", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user1;

    public Userfriendships()
    {
    }

    public Userfriendships(UserfriendshipsPK userfriendshipsPK)
    {
        this.userfriendshipsPK = userfriendshipsPK;
    }

    public Userfriendships(UserfriendshipsPK userfriendshipsPK, int date)
    {
        this.userfriendshipsPK = userfriendshipsPK;
        this.date = date;
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

    public int getDate()
    {
        return date;
    }

    public void setDate(int date)
    {
        this.date = date;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public User getUser1()
    {
        return user1;
    }

    public void setUser1(User user1)
    {
        this.user1 = user1;
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
