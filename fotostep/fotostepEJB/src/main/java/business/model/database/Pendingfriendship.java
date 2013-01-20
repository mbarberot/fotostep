package business.model.database;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Entités des amitiés en attente de validation.
 * 
 * @author Mathieu Barberot
 */
@Entity
@IdClass(PendingfriendshipPK.class)
@Table(name = "pendingfriendship")
public class Pendingfriendship implements Serializable
{
    @Id
    @JoinColumn(name = "user", referencedColumnName = "iduser", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    
    @Id
    @JoinColumn(name = "friend", referencedColumnName = "iduser", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User friend;
    
    @Temporal(TemporalType.DATE)
    private Date date;

    public Pendingfriendship()
    {
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public User getFriend()
    {
        return friend;
    }

    public void setFriend(User friend)
    {
        this.friend = friend;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
    
}
