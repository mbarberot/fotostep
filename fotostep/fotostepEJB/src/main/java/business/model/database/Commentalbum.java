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
@Table(name = "commentalbum")
@NamedQueries(
{
    @NamedQuery(name = "Commentalbum.findAll", query = "SELECT c FROM Commentalbum c"),
    @NamedQuery(name = "Commentalbum.findByIdComment", query = "SELECT c FROM Commentalbum c WHERE c.idComment = :idComment"),
    @NamedQuery(name = "Commentalbum.findByTitle", query = "SELECT c FROM Commentalbum c WHERE c.title = :title"),
    @NamedQuery(name = "Commentalbum.findByBody", query = "SELECT c FROM Commentalbum c WHERE c.body = :body"),
    @NamedQuery(name = "Commentalbum.findByDate", query = "SELECT c FROM Commentalbum c WHERE c.date = :date")
})
public class Commentalbum implements Serializable
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
    @Column(name = "body")
    private String body;
    @Basic(optional = false)
    @Column(name = "date")
    private Long date;
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User idUser;
    @JoinColumn(name = "idAlbum", referencedColumnName = "idAlbum")
    @ManyToOne(optional = false)
    private Album idAlbum;

    public Commentalbum()
    {
    }

    public Commentalbum(Integer idComment)
    {
        this.idComment = idComment;
    }

    public Commentalbum(Integer idComment, String title, String body, Long date)
    {
        this.idComment = idComment;
        this.title = title;
        this.body = body;
        this.date = date;
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

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
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
        hash += (idComment != null ? idComment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commentalbum))
        {
            return false;
        }
        Commentalbum other = (Commentalbum) object;
        if ((this.idComment == null && other.idComment != null) || (this.idComment != null && !this.idComment.equals(other.idComment)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "business.model.database.Commentalbum[ idComment=" + idComment + " ]";
    }
    
}
