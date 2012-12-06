package business.model.database;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the image database table.
 * 
 */
@Entity
public class Image implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idImage;

	private String description;

	private String format;

	private short height;

	private String name;

	private short width;

	//uni-directional many-to-many association to Comment
	@ManyToMany
	@JoinTable(
		name="commentimage"
		, joinColumns={
			@JoinColumn(name="idImage")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idComment")
			}
		)
	private List<Comment> comments;

	public Image() {
	}

	public int getIdImage() {
		return this.idImage;
	}

	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFormat() {
		return this.format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public short getHeight() {
		return this.height;
	}

	public void setHeight(short height) {
		this.height = height;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getWidth() {
		return this.width;
	}

	public void setWidth(short width) {
		this.width = width;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}