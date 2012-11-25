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
public class CommentimagePK implements Serializable
{
    @Basic(optional = false)
    @Column(name = "idComment")
    private int idComment;
    @Basic(optional = false)
    @Column(name = "idImage")
    private int idImage;

    public CommentimagePK()
    {
    }

    public CommentimagePK(int idComment, int idImage)
    {
        this.idComment = idComment;
        this.idImage = idImage;
    }

    public int getIdComment()
    {
        return idComment;
    }

    public void setIdComment(int idComment)
    {
        this.idComment = idComment;
    }

    public int getIdImage()
    {
        return idImage;
    }

    public void setIdImage(int idImage)
    {
        this.idImage = idImage;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) idComment;
        hash += (int) idImage;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommentimagePK))
        {
            return false;
        }
        CommentimagePK other = (CommentimagePK) object;
        if (this.idComment != other.idComment)
        {
            return false;
        }
        if (this.idImage != other.idImage)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "business.model.database.CommentimagePK[ idComment=" + idComment + ", idImage=" + idImage + " ]";
    }
    
}
