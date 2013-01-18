package business.model.database;

import com.vividsolutions.jts.geom.Point;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

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
    @Type(type = "org.hibernate.spatial.dialect.mysql.MySQLGeometryTypeDescriptor")
    @Column(columnDefinition = "Point")
    private Point coord;
    @Lob
    private String description;
    @Lob
    private String tags;
    @Enumerated(EnumType.STRING)
    private FormatEnum format;
    private int height;
    private String path;
    private int width;
    @Temporal(TemporalType.DATE)
    private Date date;
    
    //bi-directional many-to-one association to Commentpicture
    @OneToMany(mappedBy = "picture")
    private List<Commentpicture> comments;
    
    //bi-directional many-to-many association to User
    @OneToMany(mappedBy = "picture")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Likepicture> likepictures;
    
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

    public Object getCoord()
    {
        return this.coord;
    }

    public void setCoord(Point coord)
    {
        this.coord = coord;
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

    public List<Likepicture> getLikers()
    {
        return this.likepictures;
    }

    public void setLikers(List<Likepicture> likers)
    {
        this.likepictures = likers;
    }

    public void addLiker(Likepicture user)
    {
        this.likepictures.add(user);
    }

    public void removeLiker(Likepicture user)
    {
        this.likepictures.remove(user);
    }

    public Album getAlbum()
    {
        return this.album;
    }

    public void setAlbum(Album album)
    {
        this.album = album;
    }

    @Override
    public boolean equals(Object object){
    	if(object instanceof User)
    		return ((Picture)object).idpicture == idpicture;
    	return false;
    }
    
    @Override
    public int hashCode(){
    	return idpicture;
    }
}