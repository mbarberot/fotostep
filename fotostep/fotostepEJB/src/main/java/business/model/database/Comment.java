/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.model.database;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author kawa
 */
@Entity
@Table(name = "comment")
@NamedQueries(
{
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),
    @NamedQuery(name = "Comment.findByIdComment", query = "SELECT c FROM Comment c WHERE c.idComment = :idComment"),
    @NamedQuery(name = "Comment.findByTitle", query = "SELECT c FROM Comment c WHERE c.title = :title"),
    @NamedQuery(name = "Comment.findByDate", query = "SELECT c FROM Comment c WHERE c.date = :date"),
    @NamedQuery(name = "Comment.findByBody", query = "SELECT c FROM Comment c WHERE c.body = :body")
})
public class Comment implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idComment")
    private Integer idComment;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "body")
    private String body;
    @JoinTable(name = "commentimage", joinColumns =
    {
        @JoinColumn(name = "idComment", referencedColumnName = "idComment")
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "idImage", referencedColumnName = "idImage")
    })
    @ManyToMany
    private List<Image> imageList;
    @JoinTable(name = "commentalbum", joinColumns =
    {
        @JoinColumn(name = "idComment", referencedColumnName = "idComment")
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "idAlbum", referencedColumnName = "idAlbum")
    })
    @ManyToMany
    private List<Album> albumList;
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User idUser;

    public Comment()
    {
    }

    public Comment(Integer idComment)
    {
        this.idComment = idComment;
    }

    public Comment(Integer idComment, String title, Date date, String body)
    {
        this.idComment = idComment;
        this.title = title;
        this.date = date;
        this.body = body;
    }

    public Integer getIdComment()
    {
        return idComment;
    }

    public void setIdComment(Integer idComment)
    {
        this.idComment = idComment;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public List<Image> getImageList()
    {
        return imageList;
    }

    public void setImageList(List<Image> imageList)
    {
        this.imageList = imageList;
    }

    public List<Album> getAlbumList()
    {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList)
    {
        this.albumList = albumList;
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
        hash += (idComment != null ? idComment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comment))
        {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.idComment == null && other.idComment != null) || (this.idComment != null && !this.idComment.equals(other.idComment)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "business.model.database.Comment[ idComment=" + idComment + " ]";
    }
    
}
