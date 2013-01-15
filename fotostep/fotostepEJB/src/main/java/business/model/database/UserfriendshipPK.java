package business.model.database;

import java.io.Serializable;

/**
 * Clé primaire composée de l'entité/table Userfriendship
 *
 * @author Mathieu Barberot
 */
public class UserfriendshipPK implements Serializable
{
    private User user1;
    private User user2;

    public UserfriendshipPK()
    {
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
        final UserfriendshipPK other = (UserfriendshipPK) obj;
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        return hash;
    }
    
    
    
}
