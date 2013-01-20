package business.model.database;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Clé primaire composée de l'entité/table Pendingfriendhsip
 *
 * @author Mathieu Barberot
 */
public class PendingfriendshipPK implements Serializable
{
    private User user;
    private User friend;

    public PendingfriendshipPK()
    {
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

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final PendingfriendshipPK other = (PendingfriendshipPK) obj;
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        return hash;
    }
    
    
}