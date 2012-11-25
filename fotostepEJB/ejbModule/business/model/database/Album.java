/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.model.database;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kawa
 */
@Entity
@Table(name = "album")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Album.findAll", query = "SELECT a FROM Album a"),
    @NamedQuery(name = "Album.findByIdAlbum", query = "SELECT a FROM Album a WHERE a.idAlbum = :idAlbum"),
    @NamedQuery(name = "Album.findByName", query = "SELECT a FROM Album a WHERE a.name = :name"),
    @NamedQuery(name = "Album.findByDescription", query = "SELECT a FROM Album a WHERE a.description = :description"),
    @NamedQuery(name = "Album.findByPerm", query = "SELECT a FROM Album a WHERE a.perm = :perm")
})
public class Album implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAlbum")
    private Integer idAlbum;
    @Column(name = "name")
    private Integer name;
    @Column(name = "description")
    private Integer description;
    @Column(name = "perm")
    private Integer perm;
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User idUser;

    public Album()
    {
    }

    public Album(Integer idAlbum)
    {
        this.idAlbum = idAlbum;
    }

    public Integer getIdAlbum()
    {
        return idAlbum;
    }

    public void setIdAlbum(Integer idAlbum)
    {
        this.idAlbum = idAlbum;
    }

    public Integer getName()
    {
        return name;
    }

    public void setName(Integer name)
    {
        this.name = name;
    }

    public Integer getDescription()
    {
        return description;
    }

    public void setDescription(Integer description)
    {
        this.description = description;
    }

    public Integer getPerm()
    {
        return perm;
    }

    public void setPerm(Integer perm)
    {
        this.perm = perm;
    }

    public User getIdUser()
    {
        return idUser;
    }

    public void setIdUser(User idUser)
    {
        this.idUser = idUser;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idAlbum != null ? idAlbum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Album))
        {
            return false;
        }
        Album other = (Album) object;
        if ((this.idAlbum == null && other.idAlbum != null) || (this.idAlbum != null && !this.idAlbum.equals(other.idAlbum)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "business.model.database.Album[ idAlbum=" + idAlbum + " ]";
    }
    
}
