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

	@EmbeddedId
	private CommentimagePK id;

	public Commentimage() {
	}

	public CommentimagePK getId() {
		return this.id;
	}

	public void setId(CommentimagePK id) {
		this.id = id;
	}

}