package business.model.database;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the commentimage database table.
 * 
 */
@Entity
public class Commentimage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idComment;

	@Lob
	private String body;

	private int date;

	private String title;

	//bi-directional many-to-one association to Image
	@ManyToOne
	@JoinColumn(name="idImage")
	private Image image;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="idUser")
	private User user;

	public Commentimage() {
	}

	public int getIdComment() {
		return this.idComment;
	}

	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getDate() {
		return this.date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
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