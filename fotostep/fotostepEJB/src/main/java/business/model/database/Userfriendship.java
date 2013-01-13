package business.model.database;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Entité des amitiés
 *
 * @author Mathieu Barberot
 */
@Entity
@IdClass(UserfriendshipPK.class)
@Table(name = "userfriendship")
public class Userfriendship implements Serializable
{
    @Id
    @JoinColumn(name = "iduser2", referencedColumnName = "iduser", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user1;
    
    @Id
    @JoinColumn(name = "iduser1", referencedColumnName = "iduser", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user2;
    
    @Temporal(TemporalType.DATE)
    private Date date;

    public Userfriendship()
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

    public User getUser1()
    {
        return user1;
    }

    public void setUser1(User user1)
    {
        this.user1 = user1;
    }

    public User getUser2()
    {
        return user2;
    }

    public void setUser2(User user2)
    {
        this.user2 = user2;
    }

}
