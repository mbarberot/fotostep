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
@Table(name = "userdata")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Userdata.findAll", query = "SELECT u FROM Userdata u"),
    @NamedQuery(name = "Userdata.findById", query = "SELECT u FROM Userdata u WHERE u.id = :id"),
    @NamedQuery(name = "Userdata.findByFirstname", query = "SELECT u FROM Userdata u WHERE u.firstname = :firstname"),
    @NamedQuery(name = "Userdata.findByForeName", query = "SELECT u FROM Userdata u WHERE u.foreName = :foreName"),
    @NamedQuery(name = "Userdata.findByBirthDate", query = "SELECT u FROM Userdata u WHERE u.birthDate = :birthDate")
})
public class Userdata implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "firstname")
    private String firstname;
    @Basic(optional = false)
    @Column(name = "foreName")
    private String foreName;
    @Basic(optional = false)
    @Column(name = "birthDate")
    private int birthDate;

    public Userdata()
    {
    }

    public Userdata(Integer id)
    {
        this.id = id;
    }

    public Userdata(Integer id, String firstname, String foreName, int birthDate)
    {
        this.id = id;
        this.firstname = firstname;
        this.foreName = foreName;
        this.birthDate = birthDate;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getForeName()
    {
        return foreName;
    }

    public void setForeName(String foreName)
    {
        this.foreName = foreName;
    }

    public int getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(int birthDate)
    {
        this.birthDate = birthDate;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userdata))
        {
            return false;
        }
        Userdata other = (Userdata) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "business.model.database.Userdata[ id=" + id + " ]";
    }
    
}
