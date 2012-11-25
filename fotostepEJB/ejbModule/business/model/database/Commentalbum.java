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
@Table(name = "commentalbum")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Commentalbum.findAll", query = "SELECT c FROM Commentalbum c"),
    @NamedQuery(name = "Commentalbum.findByIdComment", query = "SELECT c FROM Commentalbum c WHERE c.commentalbumPK.idComment = :idComment"),
    @NamedQuery(name = "Commentalbum.findByIdAlbum", query = "SELECT c FROM Commentalbum c WHERE c.commentalbumPK.idAlbum = :idAlbum")
})
public class Commentalbum implements Serializable
{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CommentalbumPK commentalbumPK;

    public Commentalbum()
    {
    }

    public Commentalbum(CommentalbumPK commentalbumPK)
    {
        this.commentalbumPK = commentalbumPK;
    }

    public Commentalbum(int idComment, int idAlbum)
    {
        this.commentalbumPK = new CommentalbumPK(idComment, idAlbum);
    }

    public CommentalbumPK getCommentalbumPK()
    {
        return commentalbumPK;
    }

    public void setCommentalbumPK(CommentalbumPK commentalbumPK)
    {
        this.commentalbumPK = commentalbumPK;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (commentalbumPK != null ? commentalbumPK.hashCode() : 0);
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
        if ((this.commentalbumPK == null && other.commentalbumPK != null) || (this.commentalbumPK != null && !this.commentalbumPK.equals(other.commentalbumPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "business.model.database.Commentalbum[ commentalbumPK=" + commentalbumPK + " ]";
    }
    
}
