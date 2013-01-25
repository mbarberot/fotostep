package business.model.database;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Entité des 'likes' sur les albums.
 * 
 * @author Mathieu Barberot
 */
@Entity
@IdClass(LikealbumPK.class)
@Table(name = "likealbum")
public class Likealbum implements Serializable
{
    @Id
    @ManyToOne
    @JoinColumn(name = "iduser")
    private User liker;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "idalbum")
    private Album album;
    
    @Temporal(TemporalType.DATE)
    private Date date;
    
    public Likealbum()
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

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
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
    public boolean equals(Object object){
    	if(object instanceof Album)
    		return ((Likealbum)object).album.equals(album) && ((Likealbum)object).liker.equals(liker);
    	return false;
    }
}
