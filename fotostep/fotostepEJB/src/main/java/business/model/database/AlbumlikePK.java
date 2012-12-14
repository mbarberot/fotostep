package business.model.database;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the albumlikes database table.
 * 
 */
@Embeddable
public class AlbumlikePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idUser;

	private int idAlbum;

	public AlbumlikePK() {
	}
	public int getIdUser() {
		return this.idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
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
		if (!(other instanceof AlbumlikePK)) {
			return false;
		}
		AlbumlikePK castOther = (AlbumlikePK)other;
		return 
			(this.idUser == castOther.idUser)
			&& (this.idAlbum == castOther.idAlbum);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idUser;
		hash = hash * prime + this.idAlbum;
		
		return hash;
	}
}