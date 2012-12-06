package business.model.database;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the commentalbum database table.
 * 
 */
@Embeddable
public class CommentalbumPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idComment;

	private int idAlbum;

	public CommentalbumPK() {
	}
	public int getIdComment() {
		return this.idComment;
	}
	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}
	public int getIdAlbum() {
		return this.idAlbum;
	}
	public void setIdAlbum(int idAlbum) {
		this.idAlbum = idAlbum;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CommentalbumPK)) {
			return false;
		}
		CommentalbumPK castOther = (CommentalbumPK)other;
		return 
			(this.idComment == castOther.idComment)
			&& (this.idAlbum == castOther.idAlbum);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idComment;
		hash = hash * prime + this.idAlbum;
		
		return hash;
	}
}