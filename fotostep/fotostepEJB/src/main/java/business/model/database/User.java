package business.model.database;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idUser;

	@Column(nullable=false, length=50)
	private String login;

	@Column(nullable=false, length=50)
	private String password;

	//bi-directional many-to-one association to Album
	@OneToMany(mappedBy="user")
	private List<Album> listeAlbums;

	//uni-directional many-to-many association to Album
	@ManyToMany
	@JoinTable(
		name="albumlikes"
		, joinColumns={
			@JoinColumn(name="idUser", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="idAlbum", nullable=false)
			}
		)
	private List<Album> likesOnAlbums;

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
			@JoinColumn(name="idUser", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="idImage", nullable=false)
			}
		)
	private List<Image> likesOnImages;


	//uni-directional many-to-many association to User
	@ManyToMany
	@JoinTable(
		name="userfriendships"
		, joinColumns={
			@JoinColumn(name="idUser1", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="idUser2", nullable=false)
			}
		)
	private List<User> friends;

	//bi-directional one-to-one association to Userdata
	@OneToOne(mappedBy="user")
	private Userdata userdata;

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

	public List<Album> getLikesOnAlbums() {
		return this.likesOnAlbums;
	}

	public void setLikesOnAlbums(List<Album> likesOnAlbums) {
		this.likesOnAlbums = likesOnAlbums;
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

}