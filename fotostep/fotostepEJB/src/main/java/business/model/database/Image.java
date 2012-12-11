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

	private String name;

	private String path;

	private short width;

	//bi-directional many-to-one association to Commentimage
	@OneToMany(mappedBy="image")
	private List<Commentimage> comments;

	//bi-directional many-to-one association to Album
	@ManyToOne
	@JoinColumn(name="idAlbum")
	private Album album;

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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public short getWidth() {
		return this.width;
	}

	public void setWidth(short width) {
		this.width = width;
	}

	public List<Commentimage> getComments() {
		return this.comments;
	}

	public void setComments(List<Commentimage> comments) {
		this.comments = comments;
	}

	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}