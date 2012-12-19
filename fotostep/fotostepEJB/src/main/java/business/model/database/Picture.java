package business.model.database;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the picture database table.
 * 
 */
@Entity
public class Picture implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idpicture;

	private Object coord;

	@Lob
	private String description;

	private FormatEnum format;

	private int height;

	private String path;

	private int width;

	//bi-directional many-to-one association to Commentpicture
	@OneToMany(mappedBy="picture")
	private List<Commentpicture> comments;

	//bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(
		name="likepicture"
		, joinColumns={
			@JoinColumn(name="idpicture")
			}
		, inverseJoinColumns={
			@JoinColumn(name="iduser")
			}
		)
	private List<User> likers;

	//bi-directional many-to-one association to Album
	@ManyToOne
	@JoinColumn(name="idalbum")
	private Album album;

	public Picture() {
	}

	public int getIdpicture() {
		return this.idpicture;
	}

	public void setIdpicture(int idpicture) {
		this.idpicture = idpicture;
	}

	public Object getCoord() {
		return this.coord;
	}

	public void setCoord(Object coord) {
		this.coord = coord;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public FormatEnum getFormat() {
		return this.format;
	}

	public void setFormat(FormatEnum format) {
		this.format = format;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public List<Commentpicture> getComments() {
		return this.comments;
	}

	public void setComments(List<Commentpicture> comments) {
		this.comments = comments;
	}

	public List<User> getLikers() {
		return this.likers;
	}

	public void setLikers(List<User> likers) {
		this.likers = likers;
	}

	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

}