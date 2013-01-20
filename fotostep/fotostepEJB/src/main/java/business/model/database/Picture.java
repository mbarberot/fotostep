package business.model.database;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * The persistent class for the picture database table.
 *
 */
@Entity
@Table(name="picture")
public class Picture implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idpicture;

    @Lob
    private String description;
    @Lob
    private String tags;
    @Enumerated(EnumType.STRING)
    private FormatEnum format;
    private int height;
    private String path;
    private int width;

    private double lgt;
    private double lat;

    @Temporal(TemporalType.DATE)
    private Date date;
    
    //bi-directional many-to-one association to Commentpicture
    @OneToMany(mappedBy = "picture")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Commentpicture> comments;
    
    //bi-directional many-to-many association to User
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "likepicture", joinColumns =
            {
                    @JoinColumn(name = "idpicture")
            }, inverseJoinColumns =
            {
                    @JoinColumn(name = "iduser")
            })
    private List<User> likers;
    
    //bi-directional many-to-one association to Album
    @ManyToOne
    @JoinColumn(name = "idalbum")
    private Album album;

    public Picture()
    {
    }

    public int getIdpicture()
    {
        return this.idpicture;
    }

    public void setIdpicture(int idpicture)
    {
        this.idpicture = idpicture;
    }


    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getTags()
    {
        return tags;
    }

    public void setTags(String tags)
    {
        this.tags = tags;
    }

    public FormatEnum getFormat()
    {
        return this.format;
    }

    public void setFormat(FormatEnum format)
    {
        this.format = format;
    }

    public int getHeight()
    {
        return this.height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public String getPath()
    {
        return this.path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public int getWidth()
    {
        return this.width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }
    
    public List<Commentpicture> getComments()
    {
        return this.comments;
    }

    public void setComments(List<Commentpicture> comments)
    {
        this.comments = comments;
    }

    public List<User> getLikers()
    {
        return this.likers;
    }

    public void setLikers(List<User> likers)
    {
        this.likers = likers;
    }

    public void addLiker(User user)
    {
        this.likers.add(user);
    }

    public void removeLiker(User user)
    {
        this.likers.remove(user);
    }

    public Album getAlbum()
    {
        return this.album;
    }

    public void setAlbum(Album album)
    {
        this.album = album;
    }

    public double getLgt() {
        return lgt;
    }

    public void setLgt(double lgt) {
        this.lgt = lgt;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public boolean equals(Object object){
    	if(object instanceof Picture)
    		return ((Picture)object).idpicture == idpicture;
    	return false;
    }
    
    @Override
    public int hashCode(){
    	return idpicture;
    }
}