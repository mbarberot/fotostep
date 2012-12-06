package business.model.database;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the commentimage database table.
 * 
 */
@Embeddable
public class CommentimagePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idComment;

	private int idImage;

	public CommentimagePK() {
	}
	public int getIdComment() {
		return this.idComment;
	}
	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}
	public int getIdImage() {
		return this.idImage;
	}
	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CommentimagePK)) {
			return false;
		}
		CommentimagePK castOther = (CommentimagePK)other;
		return 
			(this.idComment == castOther.idComment)
			&& (this.idImage == castOther.idImage);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idComment;
		hash = hash * prime + this.idImage;
		
		return hash;
	}
}