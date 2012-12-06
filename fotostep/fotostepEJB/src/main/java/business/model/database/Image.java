package business.model.database;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author kawa
 */
@Entity
@Table(name = "image")
@NamedQueries(
{
    @NamedQuery(name = "Image.findAll", query = "SELECT i FROM Image i"),
    @NamedQuery(name = "Image.findByIdImage", query = "SELECT i FROM Image i WHERE i.idImage = :idImage"),
    @NamedQuery(name = "Image.findByName", query = "SELECT i FROM Image i WHERE i.name = :name"),
    @NamedQuery(name = "Image.findByDescription", query = "SELECT i FROM Image i WHERE i.description = :description"),
    @NamedQuery(name = "Image.findByFormat", query = "SELECT i FROM Image i WHERE i.format = :format"),
    @NamedQuery(name = "Image.findByHeight", query = "SELECT i FROM Image i WHERE i.height = :height"),
    @NamedQuery(name = "Image.findByWidth", query = "SELECT i FROM Image i WHERE i.width = :width")
})
public class Image implements Serializable
{
    @ManyToMany(mappedBy = "imageCollection")
    private Collection<User> userCollection;
    @JoinTable(name = "commentimage", joinColumns =
    {
        @JoinColumn(name = "idImage", referencedColumnName = "idImage")
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "idComment", referencedColumnName = "idComment")
    })
    @ManyToMany
    private Collection<Comment> commentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coverImage")
    private List<Album> albumList1;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idImage")
    private Integer idImage;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "format")
    private String format;
    @Basic(optional = false)
    @Column(name = "height")
    private short height;
    @Basic(optional = false)
    @Column(name = "width")
    private short width;
    @JoinTable(name = "imagelikes", joinColumns =
    {
        @JoinColumn(name = "idImage", referencedColumnName = "idImage")
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    })
    @ManyToMany
    private List<User> userList;
    @JoinTable(name = "imagealbum", joinColumns =
    {
        @JoinColumn(name = "idImage", referencedColumnName = "idImage")
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "idAlbum", referencedColumnName = "idAlbum")
    })
    @ManyToMany
    private List<Album> albumList;
    @ManyToMany(mappedBy = "imageList")
    private List<Comment> commentList;

    public Image()
    {
    }

    public Image(Integer idImage)
    {
        this.idImage = idImage;
    }

    public Image(Integer idImage, String name, String description, String format, short height, short width)
    {
        this.idImage = idImage;
        this.name = name;
        this.description = description;
        this.format = format;
        this.height = height;
        this.width = width;
    }

    public Integer getIdImage()
    {
        return idImage;
    }

    public void setIdImage(Integer idImage)
    {
        this.idImage = idImage;
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

    public String getFormat()
    {
        return format;
    }

    public void setFormat(String format)
    {
        this.format = format;
    }

    public short getHeight()
    {
        return height;
    }

    public void setHeight(short height)
    {
        this.height = height;
    }

    public short getWidth()
    {
        return width;
    }

    public void setWidth(short width)
    {
        this.width = width;
    }

    public List<User> getUserList()
    {
        return userList;
    }

    public void setUserList(List<User> userList)
    {
        this.userList = userList;
    }

    public List<Album> getAlbumList()
    {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList)
    {
        this.albumList = albumList;
    }

    public List<Comment> getCommentList()
    {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList)
    {
        this.commentList = commentList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idImage != null ? idImage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Image))
        {
            return false;
        }
        Image other = (Image) object;
        if ((this.idImage == null && other.idImage != null) || (this.idImage != null && !this.idImage.equals(other.idImage)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "business.model.database.Image[ idImage=" + idImage + " ]";
    }

    public List<Album> getAlbumList1()
    {
        return albumList1;
    }

    public void setAlbumList1(List<Album> albumList1)
    {
        this.albumList1 = albumList1;
    }

    public Collection<User> getUserCollection()
    {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection)
    {
        this.userCollection = userCollection;
    }

    public Collection<Comment> getCommentCollection()
    {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection)
    {
        this.commentCollection = commentCollection;
    }
    
}
>>>>>>> refs/remotes/origin/master
