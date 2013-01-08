package business.model.database;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * The persistent class for the commentalbum database table.
 *
 */
@Entity
@Table(name="commentalbum")
public class Commentalbum implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idcommentalbum;
    @Lob
    private String body;
    @Temporal(TemporalType.DATE)
    private Date date;
    //bi-directional many-to-one association to Album
    @ManyToOne
    @JoinColumn(name = "idalbum")
    private Album album;
    //bi-directional many-to-one association to User
    @ManyToOne
    @JoinColumn(name = "iduser")
    private User author;

    public Commentalbum()
    {
    }

    public int getIdcommentalbum()
    {
        return this.idcommentalbum;
    }

    public void setIdcommentalbum(int idcommentalbum)
    {
        this.idcommentalbum = idcommentalbum;
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

    public Album getAlbum()
    {
        return this.album;
    }

    public void setAlbum(Album album)
    {
        this.album = album;
    }

    public User getAuthor()
    {
        return this.author;
    }

    public void setAuthor(User author)
    {
        this.author = author;
    }
}