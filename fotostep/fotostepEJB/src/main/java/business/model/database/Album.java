package business.model.database;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the album database table.
 * 
 */
@Entity
public class Album implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idAlbum;

	private String description;

	private String name;

	private byte perm;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="idUser")
	private User user;

	//bi-directional many-to-one association to Commentalbum
	@OneToMany(mappedBy="album")
	private List<Commentalbum> comments;

	//bi-directional many-to-one association to Image
	@OneToMany(mappedBy="album")
	private List<Image> images;

	public Album() {
	}

	public int getIdAlbum() {
		return this.idAlbum;
	}

	public void setIdAlbum(int idAlbum) {
		this.idAlbum = idAlbum;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getPerm() {
		return this.perm;
	}

	public void setPerm(byte perm) {
		this.perm = perm;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Commentalbum> getComments() {
		return this.comments;
	}

	public void setComments(List<Commentalbum> comments) {
		this.comments = comments;
	}

	public List<Image> getImages() {
		return this.images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

}