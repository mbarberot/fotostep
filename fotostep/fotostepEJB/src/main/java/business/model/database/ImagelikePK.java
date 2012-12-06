package business.model.database;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the imagelikes database table.
 * 
 */
@Embeddable
public class ImagelikePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idImage;

	private int idUser;

	public ImagelikePK() {
	}
	public int getIdImage() {
		return this.idImage;
	}
	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}
	public int getIdUser() {
		return this.idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ImagelikePK)) {
			return false;
		}
		ImagelikePK castOther = (ImagelikePK)other;
		return 
			(this.idImage == castOther.idImage)
			&& (this.idUser == castOther.idUser);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idImage;
		hash = hash * prime + this.idUser;
		
		return hash;
	}
}