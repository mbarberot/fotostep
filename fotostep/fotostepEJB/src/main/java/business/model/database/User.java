package business.model.database;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the user database table.
 *
 */
@Entity
@Table(name = "user")
public class User implements Serializable
{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int iduser;
    
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    
    @Enumerated(EnumType.STRING)
    private EnabledEnum enabled;
    
    private String avatar;
    
    private String firstname;
    
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
    
    private String lastname;
    
    private String login;
    
    private String password;
    
    private String place;
    
    private String twitterid;
    
    private String fbid;
    
    @Temporal(TemporalType.DATE)
    private Date registerdate;
    
    //bi-directional many-to-one association to Album
    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Album> albums;
   
    //bi-directional many-to-one association to Commentalbum
    @OneToMany(mappedBy = "author")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Commentalbum> commentsOnAlbums;
    
    //bi-directional many-to-one association to Commentpicture
    @OneToMany(mappedBy = "author")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Commentpicture> commentsOnPictures;
    
    //bi-directional many-to-many association to Album
    @OneToMany(mappedBy = "liker")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Likealbum> likedAlbums;
    
    //bi-directional many-to-many association to Picture
    @OneToMany(mappedBy = "liker")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Likepicture> likedPictures;
    
    //uni-directional many-to-many association to User
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "userfriendship", joinColumns =
    {
        @JoinColumn(name = "iduser2")
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "iduser1")
    })
    private List<User> friends;

    public User()
    {
    }

    public int getIduser()
    {
        return this.iduser;
    }

    public void setIduser(int iduser)
    {
        this.iduser = iduser;
    }

    public Date getBirthdate()
    {
        return this.birthdate;
    }

    public void setBirthdate(Date birthdate)
    {
        this.birthdate = birthdate;
    }

    public EnabledEnum getEnabled()
    {
        return this.enabled;
    }

    public void setEnabled(EnabledEnum enabled)
    {
        this.enabled = enabled;
    }

    public String getFirstname()
    {
        return this.firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public GenderEnum getGender()
    {
        return this.gender;
    }

    public void setGender(GenderEnum gender)
    {
        this.gender = gender;
    }

    public String getLastname()
    {
        return this.lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getLogin()
    {
        return this.login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Date getRegisterdate()
    {
        return this.registerdate;
    }

    public void setRegisterdate(Date registerdate)
    {
        this.registerdate = registerdate;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getPlace()
    {
        return place;
    }

    public void setPlace(String place)
    {
        this.place = place;
    }

    public String getTwitterid()
    {
        return twitterid;
    }

    public void setTwitterid(String twitterid)
    {
        this.twitterid = twitterid;
    }

    public String getFbid()
    {
        return fbid;
    }

    public void setFbid(String fbid)
    {
        this.fbid = fbid;
    }

    public List<Album> getAlbums()
    {
        return this.albums;
    }

    public void setAlbums(List<Album> albums)
    {
        this.albums = albums;
    }

    public List<Commentalbum> getCommentsOnAlbums()
    {
        return this.commentsOnAlbums;
    }

    public void setCommentsOnAlbums(List<Commentalbum> commentsOnAlbums)
    {
        this.commentsOnAlbums = commentsOnAlbums;
    }

    public List<Commentpicture> getCommentsOnPictures()
    {
        return this.commentsOnPictures;
    }

    public void setCommentsOnPictures(List<Commentpicture> commentsOnPictures)
    {
        this.commentsOnPictures = commentsOnPictures;
    }

    public List<Likealbum> getLikedAlbums()
    {
        return this.likedAlbums;
    }

    public void setLikedAlbums(List<Likealbum> likedAlbums)
    {
        this.likedAlbums = likedAlbums;
    }

    public void addLikedAlbum(Likealbum album)
    {
        this.likedAlbums.add(album);
    }

    public void removeLikedAlbum(Likealbum album)
    {
        this.likedAlbums.remove(album);
    }

    public List<Likepicture> getLikedPictures()
    {
        return this.likedPictures;
    }

    public void setLikedPictures(List<Likepicture> likedPictures)
    {
        this.likedPictures = likedPictures;
    }

    public void addLikedPicture(Likepicture picture)
    {
        this.likedPictures.add(picture);
    }

    public void removeLikedPicture(Likepicture picture)
    {
        this.likedPictures.remove(picture);
    }

    public List<User> getFriends()
    {
        return this.friends;
    }

    public void setFriends(List<User> friends)
    {
        this.friends = friends;
    }
}