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
@Table(name = "albumlikes")
@NamedQueries(
{
    @NamedQuery(name = "Albumlikes.findAll", query = "SELECT a FROM Albumlikes a"),
    @NamedQuery(name = "Albumlikes.findByIdUser", query = "SELECT a FROM Albumlikes a WHERE a.albumlikesPK.idUser = :idUser"),
    @NamedQuery(name = "Albumlikes.findByIdAlbum", query = "SELECT a FROM Albumlikes a WHERE a.albumlikesPK.idAlbum = :idAlbum"),
    @NamedQuery(name = "Albumlikes.findByDate", query = "SELECT a FROM Albumlikes a WHERE a.date = :date")
})
public class Albumlikes implements Serializable
{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AlbumlikesPK albumlikesPK;
    @Basic(optional = false)
    @Column(name = "date")
    private Long date;
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @JoinColumn(name = "idAlbum", referencedColumnName = "idAlbum", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Album album;

    public Albumlikes()
    {
    }

    public Albumlikes(AlbumlikesPK albumlikesPK)
    {
        this.albumlikesPK = albumlikesPK;
    }

    public Albumlikes(AlbumlikesPK albumlikesPK, Long date)
    {
        this.albumlikesPK = albumlikesPK;
        this.date = date;
    }

    public Albumlikes(int idUser, int idAlbum)
    {
        this.albumlikesPK = new AlbumlikesPK(idUser, idAlbum);
    }

    public AlbumlikesPK getAlbumlikesPK()
    {
        return albumlikesPK;
    }

    public void setAlbumlikesPK(AlbumlikesPK albumlikesPK)
    {
        this.albumlikesPK = albumlikesPK;
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

    public Album getAlbum()
    {
        return album;
    }

    public void setAlbum(Album album)
    {
        this.album = album;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (albumlikesPK != null ? albumlikesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Albumlikes))
        {
            return false;
        }
        Albumlikes other = (Albumlikes) object;
        if ((this.albumlikesPK == null && other.albumlikesPK != null) || (this.albumlikesPK != null && !this.albumlikesPK.equals(other.albumlikesPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "business.model.database.Albumlikes[ albumlikesPK=" + albumlikesPK + " ]";
    }
    
}
