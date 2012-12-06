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

	@EmbeddedId
	private CommentalbumPK id;

	public Commentalbum() {
	}

	public CommentalbumPK getId() {
		return this.id;
	}

	public void setId(CommentalbumPK id) {
		this.id = id;
	}

}