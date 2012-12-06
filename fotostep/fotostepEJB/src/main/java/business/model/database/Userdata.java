/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.model.database;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author kawa
 */
@Entity
@Table(name = "userdata")
@NamedQueries(
{
    @NamedQuery(name = "Userdata.findAll", query = "SELECT u FROM Userdata u"),
    @NamedQuery(name = "Userdata.findByIdUser", query = "SELECT u FROM Userdata u WHERE u.idUser = :idUser"),
    @NamedQuery(name = "Userdata.findByFirstname", query = "SELECT u FROM Userdata u WHERE u.firstname = :firstname"),
    @NamedQuery(name = "Userdata.findByForeName", query = "SELECT u FROM Userdata u WHERE u.foreName = :foreName"),
    @NamedQuery(name = "Userdata.findByBirthDate", query = "SELECT u FROM Userdata u WHERE u.birthDate = :birthDate"),
    @NamedQuery(name = "Userdata.findByGender", query = "SELECT u FROM Userdata u WHERE u.gender = :gender")
})
public class Userdata implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUser")
    private Integer idUser;
    @Basic(optional = false)
    @Column(name = "firstname")
    private String firstname;
    @Basic(optional = false)
    @Column(name = "foreName")
    private String foreName;
    @Basic(optional = false)
    @Column(name = "birthDate")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Basic(optional = false)
    @Column(name = "gender")
    private int gender;
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;

    public Userdata()
    {
    }

    public Userdata(Integer idUser)
    {
        this.idUser = idUser;
    }

    public Userdata(Integer idUser, String firstname, String foreName, Date birthDate, int gender)
    {
        this.idUser = idUser;
        this.firstname = firstname;
        this.foreName = foreName;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public Integer getIdUser()
    {
        return idUser;
    }

    public void setIdUser(Integer idUser)
    {
        this.idUser = idUser;
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

    public Date getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(Date birthDate)
    {
        this.birthDate = birthDate;
    }

    public int getGender()
    {
        return gender;
    }

    public void setGender(int gender)
    {
        this.gender = gender;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
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
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "business.model.database.Userdata[ idUser=" + idUser + " ]";
    }
    
}
