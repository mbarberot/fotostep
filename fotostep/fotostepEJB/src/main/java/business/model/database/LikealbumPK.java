package business.model.database;

import java.io.Serializable;

/**
 * Clé primaire composée de la classe/table Likealbum.
 * 
 * @author Mathieu Barberot
 */
public class LikealbumPK implements Serializable
{
    private User liker;
    private Album album;
    
    public LikealbumPK()
    {
        
    }

    public Album getAlbum()
    {
        return album;
    }

    public void setAlbum(Album album)
    {
        this.album = album;
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
        final LikealbumPK other = (LikealbumPK) obj;
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        return hash;
    }

           
    
    
}
