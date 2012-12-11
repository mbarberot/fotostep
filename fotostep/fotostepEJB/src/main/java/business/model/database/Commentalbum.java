package business.model.database;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the commentalbum database table.
 * 
 */
@Entity
@Table(name="commentalbum")
public class Commentalbum implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idComment;

	@Lob
	@Column(nullable=false)
	private String body;

	@Column(nullable=false)
	private int date;

	@Column(nullable=false, length=255)
	private String title;

	//bi-directional many-to-one association to Album
	@ManyToOne
	@JoinColumn(name="idAlbum", nullable=false)
	private Album album;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="idUser", nullable=false)
	private User user;

	public Commentalbum() {
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