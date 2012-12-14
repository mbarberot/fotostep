package business.model.database;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idUser;

	private long date;

	private boolean enabled;

	private String login;

	private String password;

	//bi-directional many-to-one association to Album
	@OneToMany(mappedBy="user")
	private List<Album> albums1;

	//bi-directional many-to-one association to Albumlike
	@OneToMany(mappedBy="user")
	private List<Albumlike> albumlikes;

	//bi-directional many-to-one association to Commentalbum
	@OneToMany(mappedBy="user")
	private List<Commentalbum> commentalbums;

	//bi-directional many-to-one association to Commentimage
	@OneToMany(mappedBy="user")
	private List<Commentimage> commentimages;

	//uni-directional many-to-many association to Image
	@ManyToMany
	@JoinTable(
		name="imagelikes"
		, joinColumns={
			@JoinColumn(name="idUser")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idImage")
			}
		)
	private List<Image> images1;

	//bi-directional many-to-one association to Imagelike
	@OneToMany(mappedBy="user")
	private List<Imagelike> imagelikes;

	//bi-directional many-to-many association to Album
	@ManyToMany
	@JoinTable(
		name="albumlikes"
		, joinColumns={
			@JoinColumn(name="idUser")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idAlbum")
			}
		)
	private List<Album> albums2;

	//bi-directional many-to-many association to Image
	@ManyToMany
	@JoinTable(
		name="imagelikes"
		, joinColumns={
			@JoinColumn(name="idUser")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idImage")
			}
		)
	private List<Image> images2;

	//uni-directional many-to-many association to User
	@ManyToMany
	@JoinTable(
		name="userfriendships"
		, joinColumns={
			@JoinColumn(name="idUser1")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idUser2")
			}
		)
	private List<User> friends;

	//bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(
		name="userfriendships"
		, joinColumns={
			@JoinColumn(name="idUser2")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idUser1")
			}
		)
	private List<User> users2;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="users2")
	private List<User> users3;

	//bi-directional one-to-one association to Userdata
	@OneToOne(mappedBy="user")
	private Userdata userdata;

	//bi-directional many-to-one association to Userfriendship
	@OneToMany(mappedBy="user1")
	private List<Userfriendship> userfriendships1;

	//bi-directional many-to-one association to Userfriendship
	@OneToMany(mappedBy="user2")
	private List<Userfriendship> userfriendships2;

	public User() {
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public long getDate() {
		return this.date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Album> getAlbums1() {
		return this.albums1;
	}

	public void setAlbums1(List<Album> albums1) {
		this.albums1 = albums1;
	}

	public List<Albumlike> getAlbumlikes() {
		return this.albumlikes;
	}

	public void setAlbumlikes(List<Albumlike> albumlikes) {
		this.albumlikes = albumlikes;
	}

	public List<Commentalbum> getCommentalbums() {
		return this.commentalbums;
	}

	public void setCommentalbums(List<Commentalbum> commentalbums) {
		this.commentalbums = commentalbums;
	}

	public List<Commentimage> getCommentimages() {
		return this.commentimages;
	}

	public void setCommentimages(List<Commentimage> commentimages) {
		this.commentimages = commentimages;
	}

	public List<Image> getImages1() {
		return this.images1;
	}

	public void setImages1(List<Image> images1) {
		this.images1 = images1;
	}

	public List<Imagelike> getImagelikes() {
		return this.imagelikes;
	}

	public void setImagelikes(List<Imagelike> imagelikes) {
		this.imagelikes = imagelikes;
	}

	public List<Album> getAlbums2() {
		return this.albums2;
	}

	public void setAlbums2(List<Album> albums2) {
		this.albums2 = albums2;
	}

	public List<Image> getImages2() {
		return this.images2;
	}

	public void setImages2(List<Image> images2) {
		this.images2 = images2;
	}

	public List<User> getFriends() {
		return this.friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public List<User> getUsers2() {
		return this.users2;
	}

	public void setUsers2(List<User> users2) {
		this.users2 = users2;
	}

	public List<User> getUsers3() {
		return this.users3;
	}

	public void setUsers3(List<User> users3) {
		this.users3 = users3;
	}

	public Userdata getUserdata() {
		return this.userdata;
	}

	public void setUserdata(Userdata userdata) {
		this.userdata = userdata;
	}

	public List<Userfriendship> getUserfriendships1() {
		return this.userfriendships1;
	}

	public void setUserfriendships1(List<Userfriendship> userfriendships1) {
		this.userfriendships1 = userfriendships1;
	}

	public List<Userfriendship> getUserfriendships2() {
		return this.userfriendships2;
	}

	public void setUserfriendships2(List<Userfriendship> userfriendships2) {
		this.userfriendships2 = userfriendships2;
	}

}