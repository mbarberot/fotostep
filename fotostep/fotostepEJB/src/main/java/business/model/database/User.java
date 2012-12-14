/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.model.database;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author kawa
 */
@Entity
@Table(name = "user")
@NamedQueries(
{
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByIdUser", query = "SELECT u FROM User u WHERE u.idUser = :idUser"),
    @NamedQuery(name = "User.findByLogin", query = "SELECT u FROM User u WHERE u.login = :login"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByEnabled", query = "SELECT u FROM User u WHERE u.enabled = :enabled"),
    @NamedQuery(name = "User.findByDate", query = "SELECT u FROM User u WHERE u.date = :date")
})
public class User implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUser")
    private Integer idUser;
    @Basic(optional = false)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "enabled")
    private boolean enabled;
    @Basic(optional = false)
    @Column(name = "date")
    private Long date;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Imagelikes> imagelikesList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Userdata userdata;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Userfriendships> userfriendshipsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user1")
    private List<Userfriendships> userfriendshipsList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private List<Commentimage> commentimageList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private List<Album> albumList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Albumlikes> albumlikesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private List<Commentalbum> commentalbumList;

    public User()
    {
    }

    public User(Integer idUser)
    {
        this.idUser = idUser;
    }

    public User(Integer idUser, String login, String password, boolean enabled, Long date)
    {
        this.idUser = idUser;
        this.login = login;
        this.password = password;
        this.enabled = enabled;
        this.date = date;
    }

    public Integer getIdUser()
    {
        return idUser;
    }

    public void setIdUser(Integer idUser)
    {
        this.idUser = idUser;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean getEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public Long getDate()
    {
        return date;
    }

    public void setDate(Long date)
    {
        this.date = date;
    }

    public List<Imagelikes> getImagelikes()
    {
        return imagelikesList;
    }

    public void setImagelikes(List<Imagelikes> imagelikesList)
    {
        this.imagelikesList = imagelikesList;
    }

    public Userdata getUserdata()
    {
        return userdata;
    }

    public void setUserdata(Userdata userdata)
    {
        this.userdata = userdata;
    }

    public List<Userfriendships> getUserfriendships()
    {
        return userfriendshipsList;
    }

    public void setUserfriendships(List<Userfriendships> userfriendshipsList)
    {
        this.userfriendshipsList = userfriendshipsList;
    }

    public List<Userfriendships> getUserfriendships1()
    {
        return userfriendshipsList1;
    }

    public void setUserfriendships1(List<Userfriendships> userfriendshipsList1)
    {
        this.userfriendshipsList1 = userfriendshipsList1;
    }

    public List<Commentimage> getCommentimageList()
    {
        return commentimageList;
    }

    public void setCommentimageList(List<Commentimage> commentimageList)
    {
        this.commentimageList = commentimageList;
    }

    public List<Album> getAlbumList()
    {
        return albumList;
    }

    public void setAlbum(List<Album> albumList)
    {
        this.albumList = albumList;
    }

    public List<Albumlikes> getAlbumlikes()
    {
        return albumlikesList;
    }

    public void setAlbumlikes(List<Albumlikes> albumlikesList)
    {
        this.albumlikesList = albumlikesList;
    }

    public List<Commentalbum> getCommentalbum()
    {
        return commentalbumList;
    }

    public void setCommentalbum(List<Commentalbum> commentalbumList)
    {
        this.commentalbumList = commentalbumList;
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
        if (!(object instanceof User))
        {
            return false;
        }
        User other = (User) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "business.model.database.User[ idUser=" + idUser + " ]";
    }
    
}
