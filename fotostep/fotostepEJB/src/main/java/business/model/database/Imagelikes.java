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
@Table(name = "imagelikes")
@NamedQueries(
{
    @NamedQuery(name = "Imagelikes.findAll", query = "SELECT i FROM Imagelikes i"),
    @NamedQuery(name = "Imagelikes.findByIdUser", query = "SELECT i FROM Imagelikes i WHERE i.imagelikesPK.idUser = :idUser"),
    @NamedQuery(name = "Imagelikes.findByIdImage", query = "SELECT i FROM Imagelikes i WHERE i.imagelikesPK.idImage = :idImage"),
    @NamedQuery(name = "Imagelikes.findByDate", query = "SELECT i FROM Imagelikes i WHERE i.date = :date")
})
public class Imagelikes implements Serializable
{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ImagelikesPK imagelikesPK;
    @Basic(optional = false)
    @Column(name = "date")
    private Long date;
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @JoinColumn(name = "idImage", referencedColumnName = "idImage", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Image image;

    public Imagelikes()
    {
    }

    public Imagelikes(ImagelikesPK imagelikesPK)
    {
        this.imagelikesPK = imagelikesPK;
    }

    public Imagelikes(ImagelikesPK imagelikesPK, Long date)
    {
        this.imagelikesPK = imagelikesPK;
        this.date = date;
    }

    public Imagelikes(int idUser, int idImage)
    {
        this.imagelikesPK = new ImagelikesPK(idUser, idImage);
    }

    public ImagelikesPK getImagelikesPK()
    {
        return imagelikesPK;
    }

    public void setImagelikesPK(ImagelikesPK imagelikesPK)
    {
        this.imagelikesPK = imagelikesPK;
    }

    public Long getDate()
    {
        return date;
    }

    public void setDate(Long date)
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

    public Image getImage()
    {
        return image;
    }

    public void setImage(Image image)
    {
        this.image = image;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (imagelikesPK != null ? imagelikesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Imagelikes))
        {
            return false;
        }
        Imagelikes other = (Imagelikes) object;
        if ((this.imagelikesPK == null && other.imagelikesPK != null) || (this.imagelikesPK != null && !this.imagelikesPK.equals(other.imagelikesPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "business.model.database.Imagelikes[ imagelikesPK=" + imagelikesPK + " ]";
    }
    
}
