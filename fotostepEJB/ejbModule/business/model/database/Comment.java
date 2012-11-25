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
@Table(name = "comment")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),
    @NamedQuery(name = "Comment.findByIdComment", query = "SELECT c FROM Comment c WHERE c.idComment = :idComment"),
    @NamedQuery(name = "Comment.findByIdUser", query = "SELECT c FROM Comment c WHERE c.idUser = :idUser"),
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
    @Column(name = "idUser")
    private int idUser;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "date")
    private int date;
    @Basic(optional = false)
    @Column(name = "body")
    private int body;

    public Comment()
    {
    }

    public Comment(Integer idComment)
    {
        this.idComment = idComment;
    }

    public Comment(Integer idComment, int idUser, String title, int date, int body)
    {
        this.idComment = idComment;
        this.idUser = idUser;
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

    public int getIdUser()
    {
        return idUser;
    }

    public void setIdUser(int idUser)
    {
        this.idUser = idUser;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getDate()
    {
        return date;
    }

    public void setDate(int date)
    {
        this.date = date;
    }

    public int getBody()
    {
        return body;
    }

    public void setBody(int body)
    {
        this.body = body;
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
