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

	private String login;

	private String password;

	//bi-directional many-to-one association to Album
	@OneToMany(mappedBy="user")
	private List<Album> listeAlbums;

	//bi-directional many-to-one association to Commentalbum
	@OneToMany(mappedBy="user")
	private List<Commentalbum> commentsOnAlbums;

	//bi-directional many-to-one association to Commentimage
	@OneToMany(mappedBy="user")
	private List<Commentimage> commentsOnImages;

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
	private List<Image> likesOnImages;

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
	@OneToOne(mappedBy="user")
	private Userdata userdata;

	//uni-directional many-to-many association to Album
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
	private List<Album> likesOnAlbums;

	public User() {
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Album> getListeAlbums() {
		return this.listeAlbums;
	}

	public void setListeAlbums(List<Album> listeAlbums) {
		this.listeAlbums = listeAlbums;
	}

	public List<Commentalbum> getCommentsOnAlbums() {
		return this.commentsOnAlbums;
	}

	public void setCommentsOnAlbums(List<Commentalbum> commentsOnAlbums) {
		this.commentsOnAlbums = commentsOnAlbums;
	}

	public List<Commentimage> getCommentsOnImages() {
		return this.commentsOnImages;
	}

	public void setCommentsOnImages(List<Commentimage> commentsOnImages) {
		this.commentsOnImages = commentsOnImages;
	}

	public List<Image> getLikesOnImages() {
		return this.likesOnImages;
	}

	public void setLikesOnImages(List<Image> likesOnImages) {
		this.likesOnImages = likesOnImages;
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

	public List<Album> getLikesOnAlbums() {
		return this.likesOnAlbums;
	}

	public void setLikesOnAlbums(List<Album> likesOnAlbums) {
		this.likesOnAlbums = likesOnAlbums;
	}

}