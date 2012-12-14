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
@Table(name = "image")
@NamedQueries(
{
    @NamedQuery(name = "Image.findAll", query = "SELECT i FROM Image i"),
    @NamedQuery(name = "Image.findByIdImage", query = "SELECT i FROM Image i WHERE i.idImage = :idImage"),
    @NamedQuery(name = "Image.findByName", query = "SELECT i FROM Image i WHERE i.name = :name"),
    @NamedQuery(name = "Image.findByDescription", query = "SELECT i FROM Image i WHERE i.description = :description"),
    @NamedQuery(name = "Image.findByFormat", query = "SELECT i FROM Image i WHERE i.format = :format"),
    @NamedQuery(name = "Image.findByHeight", query = "SELECT i FROM Image i WHERE i.height = :height"),
    @NamedQuery(name = "Image.findByWidth", query = "SELECT i FROM Image i WHERE i.width = :width"),
    @NamedQuery(name = "Image.findByPath", query = "SELECT i FROM Image i WHERE i.path = :path"),
    @NamedQuery(name = "Image.findByDate", query = "SELECT i FROM Image i WHERE i.date = :date")
})
public class Image implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idImage")
    private Integer idImage;
    @Column(name = "name")
    private String name;
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
    @Basic(optional = false)
    @Column(name = "path")
    private String path;
    @Basic(optional = false)
    @Column(name = "date")
    private Long date;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "image")
    private List<Imagelikes> imagelikesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idImage")
    private List<Commentimage> commentimageList;
    @OneToMany(mappedBy = "idMainImage")
    private List<Album> albumList;
    @JoinColumn(name = "idAlbum", referencedColumnName = "idAlbum")
    @ManyToOne(optional = false)
    private Album idAlbum;

    public Image()
    {
    }

    public Image(Integer idImage)
    {
        this.idImage = idImage;
    }

    public Image(Integer idImage, String format, short height, short width, String path, Long date)
    {
        this.idImage = idImage;
        this.format = format;
        this.height = height;
        this.width = width;
        this.path = path;
        this.date = date;
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

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public Long getDate()
    {
        return date;
    }

    public void setDate(Long date)
    {
        this.date = date;
    }

    public List<Imagelikes> getImagelikesList()
    {
        return imagelikesList;
    }

    public void setImagelikesList(List<Imagelikes> imagelikesList)
    {
        this.imagelikesList = imagelikesList;
    }

    public List<Commentimage> getCommentimageList()
    {
        return commentimageList;
    }

    public void setCommentimageList(List<Commentimage> commentimageList)
    {
        this.commentimageList = commentimageList;
    }

    public List<Album> getAlbumList()
    {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList)
    {
        this.albumList = albumList;
    }

    public Album getIdAlbum()
    {
        return idAlbum;
    }

    public void setIdAlbum(Album idAlbum)
    {
        this.idAlbum = idAlbum;
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
    
}
