package business.model.database;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int idUser;

	private String login;

	private String nickname;

	private String password;

	//bi-directional many-to-one association to Album
	@OneToMany(mappedBy="user")
	private List<Album> albums;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="user")
	private List<Comment> comments;

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
	private List<Image> images;

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

	//bi-directional one-to-one association to Userdata
	@OneToOne
	@PrimaryKeyJoinColumn
	private Userdata userdata;

	public User() {
		super();
		this.albums = new ArrayList<Album>();
		this.comments = new ArrayList<Comment>();
		this.friends = new ArrayList<User>();
		this.images = new ArrayList<Image>();
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Image> getImages() {
		return this.images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<User> getFriends() {
		return this.friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public Userdata getUserdata() {
		return this.userdata;
	}

	public void setUserdata(Userdata userdata) {
		this.userdata = userdata;
	}

}