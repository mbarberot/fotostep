package business.model.database;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * The persistent class for the album database table.
 *
 */
@Entity
@Table(name = "album")
public class Album implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idalbum;
    @Enumerated(EnumType.STRING)
    private AuthorizationEnum authorization;
    private int isdefault;
    @Lob
    private String description;
    private String name;
    @Temporal(TemporalType.DATE)
    private Date date;
    //bi-directional many-to-one association to User
    @ManyToOne
    @JoinColumn(name = "iduser")
    private User user;
    //bi-directional many-to-one association to Commentalbum
    @OneToMany(mappedBy = "album")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Commentalbum> comments;
    //uni-directional many-to-one association to Picture
    @ManyToOne
    @JoinColumn(name = "coverimage")
    private Picture coverPicture;
    //bi-directional many-to-many association to User
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "likealbum", joinColumns =
    {
        @JoinColumn(name = "idalbum")
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "iduser")
    })
    private List<User> likers;
    //bi-directional many-to-one association to Picture
    @OneToMany(mappedBy = "album")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Picture> pictures;

    public Album()
    {
    }

    public int getIdalbum()
    {
        return this.idalbum;
    }

    public void setIdalbum(int idalbum)
    {
        this.idalbum = idalbum;
    }

    public AuthorizationEnum getAuthorization()
    {
        return this.authorization;
    }

    public void setAuthorization(AuthorizationEnum authorization)
    {
        this.authorization = authorization;
    }

    public int getIsdefault()
    {
        return isdefault;
    }

    public void setIsdefault(int isdefault)
    {
        this.isdefault = isdefault;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Date getDate()
    {
        return this.date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public User getUser()
    {
        return this.user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public List<Commentalbum> getComments()
    {
        return this.comments;
    }

    public void setComments(List<Commentalbum> comments)
    {
        this.comments = comments;
    }

    public Picture getCoverPicture()
    {
        return this.coverPicture;
    }

    public void setCoverPicture(Picture coverPicture)
    {
        this.coverPicture = coverPicture;
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

    public List<Picture> getPictures()
    {
        return this.pictures;
    }

    public void setPictures(List<Picture> pictures)
    {
        this.pictures = pictures;
    }
    
    @Override
    public boolean equals(Object object){
    	if(object instanceof Album)
    		return ((Album)object).idalbum == idalbum;
    	return false;
    }
    
    @Override
    public int hashCode(){
    	return idalbum;
    }
}