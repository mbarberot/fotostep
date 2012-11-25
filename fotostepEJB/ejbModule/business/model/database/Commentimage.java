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
@Table(name = "commentimage")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Commentimage.findAll", query = "SELECT c FROM Commentimage c"),
    @NamedQuery(name = "Commentimage.findByIdComment", query = "SELECT c FROM Commentimage c WHERE c.commentimagePK.idComment = :idComment"),
    @NamedQuery(name = "Commentimage.findByIdImage", query = "SELECT c FROM Commentimage c WHERE c.commentimagePK.idImage = :idImage")
})
public class Commentimage implements Serializable
{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CommentimagePK commentimagePK;

    public Commentimage()
    {
    }

    public Commentimage(CommentimagePK commentimagePK)
    {
        this.commentimagePK = commentimagePK;
    }

    public Commentimage(int idComment, int idImage)
    {
        this.commentimagePK = new CommentimagePK(idComment, idImage);
    }

    public CommentimagePK getCommentimagePK()
    {
        return commentimagePK;
    }

    public void setCommentimagePK(CommentimagePK commentimagePK)
    {
        this.commentimagePK = commentimagePK;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (commentimagePK != null ? commentimagePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commentimage))
        {
            return false;
        }
        Commentimage other = (Commentimage) object;
        if ((this.commentimagePK == null && other.commentimagePK != null) || (this.commentimagePK != null && !this.commentimagePK.equals(other.commentimagePK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "business.model.database.Commentimage[ commentimagePK=" + commentimagePK + " ]";
    }
    
}
