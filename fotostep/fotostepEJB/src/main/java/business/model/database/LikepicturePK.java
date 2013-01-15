
package business.model.database;

import java.io.Serializable;

/**
 * Clé primaire composée.
 * 
 * @author Mathieu Barberot
 */
public class LikepicturePK implements Serializable
{
    private User liker;
    private Picture picture;
    
    public LikepicturePK()
    {
        
    }

    public Picture getPicture()
    {
        return picture;
    }

    public void setPicture(Picture picture)
    {
        this.picture = picture;
    }

    public User getLiker()
    {
        return liker;
    }

    public void setLiker(User liker)
    {
        this.liker = liker;
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
        final LikepicturePK other = (LikepicturePK) obj;
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        return hash;
    }


   
    
    
}
