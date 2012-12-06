package business.model.database;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;

/**
 * Entité Album
 * @author Mathieu Barberot
 */
@Entity
@Table(name = "album")
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
    @JoinTable(name = "imagealbum", joinColumns =
    {
        @JoinColumn(name = "idAlbum", referencedColumnName = "idAlbum")
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "idImage", referencedColumnName = "idImage")
    })
    @ManyToMany
    private Collection<Image> imageCollection;
    @JoinTable(name = "commentalbum", joinColumns =
    {
        @JoinColumn(name = "idAlbum", referencedColumnName = "idAlbum")
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "idComment", referencedColumnName = "idComment")
    })
    @ManyToMany
    private Collection<Comment> commentCollection;
    @ManyToMany(mappedBy = "albumCollection")
    private Collection<User> userCollection;
    @JoinColumn(name = "coverImage", referencedColumnName = "idImage")
    @ManyToOne(optional = false)
    private Image coverImage;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAlbum")
    private Integer idAlbum;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "perm")
    private Integer perm;
    @ManyToMany(mappedBy = "albumList")
    private List<Image> imageList;
    @ManyToMany(mappedBy = "albumList")
    private List<Comment> commentList;
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    @ManyToOne
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

    public Integer getPerm()
    {
        return perm;
    }

    public void setPerm(Integer perm)
    {
        this.perm = perm;
    }

    public List<Image> getImageList()
    {
        return imageList;
    }

    public void setImageList(List<Image> imageList)
    {
        this.imageList = imageList;
    }

    public List<Comment> getCommentList()
    {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList)
    {
        this.commentList = commentList;
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

    public Image getCoverImage()
    {
        return coverImage;
    }

    public void setCoverImage(Image coverImage)
    {
        this.coverImage = coverImage;
    }

    public Collection<Image> getImageCollection()
    {
        return imageCollection;
    }

    public void setImageCollection(Collection<Image> imageCollection)
    {
        this.imageCollection = imageCollection;
    }

    public Collection<Comment> getCommentCollection()
    {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection)
    {
        this.commentCollection = commentCollection;
    }

    public Collection<User> getUserCollection()
    {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection)
    {
        this.userCollection = userCollection;
    }
    
}
