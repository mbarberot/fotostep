package business.model.database;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Mathieu Barberot
 */
@Table(name = "likepicture")
@IdClass(LikepicturePK.class)
@Entity
public class Likepicture implements Serializable 
{
    @Id
    @ManyToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "iduser")
    private User liker;
    
    @Id
    @ManyToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "idpicture")
    private Picture picture;
    
    @Temporal(TemporalType.DATE)
    private Date date;
    
    public Likepicture()
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
    public boolean equals(Object object){
    	if(object instanceof Album)
    		return ((Likepicture)object).picture.equals(picture) && ((Likepicture)object).liker.equals(liker);
    	return false;
    }
  
}
