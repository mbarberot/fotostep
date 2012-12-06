package business.model.database;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the userfriendships database table.
 * 
 */
@Entity
@Table(name="userfriendships")
public class Userfriendship implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserfriendshipPK id;

	public Userfriendship() {
	}

	public UserfriendshipPK getId() {
		return this.id;
	}

	public void setId(UserfriendshipPK id) {
		this.id = id;
	}

}