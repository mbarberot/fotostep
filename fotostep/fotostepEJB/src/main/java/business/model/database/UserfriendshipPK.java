package business.model.database;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the userfriendships database table.
 * 
 */
@Embeddable
public class UserfriendshipPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idUser1;

	private int idUser2;

	public UserfriendshipPK() {
	}
	public int getIdUser1() {
		return this.idUser1;
	}
	public void setIdUser1(int idUser1) {
		this.idUser1 = idUser1;
	}
	public int getIdUser2() {
		return this.idUser2;
	}
	public void setIdUser2(int idUser2) {
		this.idUser2 = idUser2;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserfriendshipPK)) {
			return false;
		}
		UserfriendshipPK castOther = (UserfriendshipPK)other;
		return 
			(this.idUser1 == castOther.idUser1)
			&& (this.idUser2 == castOther.idUser2);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idUser1;
		hash = hash * prime + this.idUser2;
		
		return hash;
	}
}