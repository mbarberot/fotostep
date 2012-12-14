package business.model.database;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the albumlikes database table.
 * 
 */
@Entity
@Table(name="albumlikes")
public class Albumlike implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AlbumlikePK id;

	private long date;

	//bi-directional many-to-one association to Album
	@ManyToOne
	@JoinColumn(name="idAlbum")
	private Album album;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="idUser")
	private User user;

	public Albumlike() {
	}

	public AlbumlikePK getId() {
		return this.id;
	}

	public void setId(AlbumlikePK id) {
		this.id = id;
	}

	public long getDate() {
		return this.date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}