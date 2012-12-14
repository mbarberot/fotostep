/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.model.database;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author kawa
 */
@Entity
@Table(name = "album")
@NamedQueries(
{
    @NamedQuery(name = "Album.findAll", query = "SELECT a FROM Album a"),
    @NamedQuery(name = "Album.findByIdAlbum", query = "SELECT a FROM Album a WHERE a.idAlbum = :idAlbum"),
    @NamedQuery(name = "Album.findByName", query = "SELECT a FROM Album a WHERE a.name = :name"),
    @NamedQuery(name = "Album.findByDescription", query = "SELECT a FROM Album a WHERE a.description = :description"),
    @NamedQuery(name = "Album.findByPerm", query = "SELECT a FROM Album a WHERE a.perm = :perm"),
    @NamedQuery(name = "Album.findByDate", query = "SELECT a FROM Album a WHERE a.date = :date")
})
public class Album implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAlbum")
    private Integer idAlbum;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "perm")
    private short perm;
    @Column(name = "date")
    private Long date;
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User idUser;
    @JoinColumn(name = "idMainImage", referencedColumnName = "idImage")
    @ManyToOne
    private Image idMainImage;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAlbum")
    private List<Image> imageList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "album")
    private List<Albumlikes> albumlikesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAlbum")
    private List<Commentalbum> commentalbumList;

    public Album()
    {
    }

    public Album(Integer idAlbum)
    {
        this.idAlbum = idAlbum;
    }

    public Album(Integer idAlbum, String name, String description, short perm)
    {
        this.idAlbum = idAlbum;
        this.name = name;
        this.description = description;
        this.perm = perm;
    }

    public Integer getIdAlbum()
    {
        return idAlbum;
    }

    public void setIdAlbum(Integer idAlbum)
    {
        this.idAlbum = idAlbum;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public short getPerm()
    {
        return perm;
    }

    public void setPerm(short perm)
    {
        this.perm = perm;
    }

    public Long getDate()
    {
        return date;
    }

    public void setDate(Long date)
    {
        this.date = date;
    }

    public User getIdUser()
    {
        return idUser;
    }

    public void setIdUser(User idUser)
    {
        this.idUser = idUser;
    }

    public Image getIdMainImage()
    {
        return idMainImage;
    }

    public void setIdMainImage(Image idMainImage)
    {
        this.idMainImage = idMainImage;
    }

    public List<Image> getImageList()
    {
        return imageList;
    }

    public void setImageList(List<Image> imageList)
    {
        this.imageList = imageList;
    }

    public List<Albumlikes> getAlbumlikesList()
    {
        return albumlikesList;
    }

    public void setAlbumlikesList(List<Albumlikes> albumlikesList)
    {
        this.albumlikesList = albumlikesList;
    }

    public List<Commentalbum> getCommentalbumList()
    {
        return commentalbumList;
    }

    public void setCommentalbumList(List<Commentalbum> commentalbumList)
    {
        this.commentalbumList = commentalbumList;
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
