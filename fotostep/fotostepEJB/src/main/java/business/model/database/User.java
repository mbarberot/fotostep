/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.model.database;

import java.io.Serializable;
import java.util.Collection;
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
    @NamedQuery(name = "User.findByNickname", query = "SELECT u FROM User u WHERE u.nickname = :nickname")
})
public class User implements Serializable
{
    @JoinTable(name = "imagelikes", joinColumns =
    {
        @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "idImage", referencedColumnName = "idImage")
    })
    @ManyToMany
    private Collection<Image> imageCollection;
    @JoinTable(name = "albumlikes", joinColumns =
    {
        @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "idAlbum", referencedColumnName = "idAlbum")
    })
    @ManyToMany
    private Collection<Album> albumCollection;
    private static final long serialVersionUID = 1L;
    @Id
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
    @Column(name = "nickname")
    private String nickname;
    @ManyToMany(mappedBy = "userList")
    private List<Image> imageList;
    @JoinTable(name = "userfriendships", joinColumns =
    {
        @JoinColumn(name = "idUser1", referencedColumnName = "idUser")
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "idUser2", referencedColumnName = "idUser")
    })
    @ManyToMany
    private List<User> userList;
    @ManyToMany(mappedBy = "userList")
    private List<User> userList1;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Userdata userdata;
    @OneToMany(mappedBy = "idUser")
    private List<Album> albumList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private List<Comment> commentList;

    public User()
    {
    }

    public User(Integer idUser)
    {
        this.idUser = idUser;
    }

    public User(Integer idUser, String login, String password, String nickname)
    {
        this.idUser = idUser;
        this.login = login;
        this.password = password;
        this.nickname = nickname;
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

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public List<Image> getImageList()
    {
        return imageList;
    }

    public void setImageList(List<Image> imageList)
    {
        this.imageList = imageList;
    }

    public List<User> getUserList()
    {
        return userList;
    }

    public void setUserList(List<User> userList)
    {
        this.userList = userList;
    }

    public List<User> getUserList1()
    {
        return userList1;
    }

    public void setUserList1(List<User> userList1)
    {
        this.userList1 = userList1;
    }

    public Userdata getUserdata()
    {
        return userdata;
    }

    public void setUserdata(Userdata userdata)
    {
        this.userdata = userdata;
    }

    public List<Album> getAlbumList()
    {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList)
    {
        this.albumList = albumList;
    }

    public List<Comment> getCommentList()
    {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList)
    {
        this.commentList = commentList;
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

    public Collection<Image> getImageCollection()
    {
        return imageCollection;
    }

    public void setImageCollection(Collection<Image> imageCollection)
    {
        this.imageCollection = imageCollection;
    }

    public Collection<Album> getAlbumCollection()
    {
        return albumCollection;
    }

    public void setAlbumCollection(Collection<Album> albumCollection)
    {
        this.albumCollection = albumCollection;
    }
    
}
