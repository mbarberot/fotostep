package business.model.database;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the imagelikes database table.
 * 
 */
@Entity
@Table(name="imagelikes")
public class Imagelike implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ImagelikePK id;

	private long date;

	//bi-directional many-to-one association to Image
	@ManyToOne
	@JoinColumn(name="idImage")
	private Image image;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="idUser")
	private User user;

	public Imagelike() {
	}

	public ImagelikePK getId() {
		return this.id;
	}

	public void setId(ImagelikePK id) {
		this.id = id;
	}

	public long getDate() {
		return this.date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}