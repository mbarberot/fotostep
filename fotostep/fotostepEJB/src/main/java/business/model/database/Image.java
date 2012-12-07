package business.model.database;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the image database table.
 * 
 */
@Entity
public class Image implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idImage;

	private String description;

	private String format;

	private short height;

	private int idAlbum;

	private String name;

	private short width;

	//bi-directional many-to-one association to Commentimage
	@OneToMany(mappedBy="image")
	private List<Commentimage> commentimages;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="idUser")
	private User user;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="images2")
	private List<User> users;

	public Image() {
	}

	public int getIdImage() {
		return this.idImage;
	}

	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFormat() {
		return this.format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public short getHeight() {
		return this.height;
	}

	public void setHeight(short height) {
		this.height = height;
	}

	public int getIdAlbum() {
		return this.idAlbum;
	}

	public void setIdAlbum(int idAlbum) {
		this.idAlbum = idAlbum;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getWidth() {
		return this.width;
	}

	public void setWidth(short width) {
		this.width = width;
	}

	public List<Commentimage> getCommentimages() {
		return this.commentimages;
	}

	public void setCommentimages(List<Commentimage> commentimages) {
		this.commentimages = commentimages;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}