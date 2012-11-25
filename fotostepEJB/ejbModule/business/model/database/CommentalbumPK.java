/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.model.database;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author kawa
 */
@Embeddable
public class CommentalbumPK implements Serializable
{
    @Basic(optional = false)
    @Column(name = "idComment")
    private int idComment;
    @Basic(optional = false)
    @Column(name = "idAlbum")
    private int idAlbum;

    public CommentalbumPK()
    {
    }

    public CommentalbumPK(int idComment, int idAlbum)
    {
        this.idComment = idComment;
        this.idAlbum = idAlbum;
    }

    public int getIdComment()
    {
        return idComment;
    }

    public void setIdComment(int idComment)
    {
        this.idComment = idComment;
    }

    public int getIdAlbum()
    {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum)
    {
        this.idAlbum = idAlbum;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) idComment;
        hash += (int) idAlbum;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommentalbumPK))
        {
            return false;
        }
        CommentalbumPK other = (CommentalbumPK) object;
        if (this.idComment != other.idComment)
        {
            return false;
        }
        if (this.idAlbum != other.idAlbum)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "business.model.database.CommentalbumPK[ idComment=" + idComment + ", idAlbum=" + idAlbum + " ]";
    }
    
}
