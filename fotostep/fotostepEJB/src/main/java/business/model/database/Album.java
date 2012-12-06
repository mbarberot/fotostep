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

	private int idUser;

	private String name;

	private int perm;

	//uni-directional many-to-many association to Image
	@ManyToMany
	@JoinTable(
		name="imagealbum"
		, joinColumns={
			@JoinColumn(name="idAlbum")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idImage")
			}
		)
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

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPerm() {
		return this.perm;
	}

	public void setPerm(int perm) {
		this.perm = perm;
	}

	public List<Image> getImages() {
		return this.images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

}