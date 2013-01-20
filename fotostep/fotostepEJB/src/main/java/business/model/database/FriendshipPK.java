package business.model.database;

import java.io.Serializable;

/**
 * Clé primaire composée de l'entité/table Friendship
 *
 * @author Mathieu Barberot
 */
public class FriendshipPK implements Serializable
{
    private User user;
    private User friend;

    public FriendshipPK()
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
        final FriendshipPK other = (FriendshipPK) obj;
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        return hash;
    }

   
    
    
    
}
