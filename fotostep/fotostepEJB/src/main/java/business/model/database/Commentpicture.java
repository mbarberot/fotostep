package business.model.database;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * The persistent class for the commentpicture database table.
 *
 */
@Entity
@Table(name="commentpicture")
public class Commentpicture implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idcommentpicture;
    @Lob
    private String body;
    @Temporal(TemporalType.DATE)
    private Date date;
    //bi-directional many-to-one association to User
    @ManyToOne
    @JoinColumn(name = "iduser")
    private User author;
    //bi-directional many-to-one association to Picture
    @ManyToOne
    @JoinColumn(name = "idpicture")
    private Picture picture;

    public Commentpicture()
    {
    }

    public int getIdcommentpicture()
    {
        return this.idcommentpicture;
    }

    public void setIdcommentpicture(int idcommentpicture)
    {
        this.idcommentpicture = idcommentpicture;
    }

    public String getBody()
    {
        return this.body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public Date getDate()
    {
        return this.date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public User getAuthor()
    {
        return this.author;
    }

    public void setAuthor(User author)
    {
        this.author = author;
    }

    public Picture getPicture()
    {
        return this.picture;
    }

    public void setPicture(Picture picture)
    {
        this.picture = picture;
    }
}