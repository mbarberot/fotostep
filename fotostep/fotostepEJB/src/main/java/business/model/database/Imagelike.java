package business.model.database;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the imagelikes database table.
 * 
 */
@Entity
@Table(name="imagelikes")
public class Imagelike implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ImagelikePK id;

	public Imagelike() {
	}

	public ImagelikePK getId() {
		return this.id;
	}

	public void setId(ImagelikePK id) {
		this.id = id;
	}

}