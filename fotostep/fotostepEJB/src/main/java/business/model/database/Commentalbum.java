package business.model.database;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the commentalbum database table.
 * 
 */
@Entity
public class Commentalbum implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idComment;

	@Lob
	private String body;

	private int date;

	private int idUser;

	private String title;

	//bi-directional many-to-one association to Album
	@ManyToOne
	@JoinColumn(name="idAlbum")
	private Album album;

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

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
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

}