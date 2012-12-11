package business.model.database;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the userdata database table.
 * 
 */
@Entity
@Table(name="userdata")
public class Userdata implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int idUser;

	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@Column(nullable=false, length=50)
	private String firstname;

	@Column(nullable=false, length=50)
	private String foreName;

	@Column(nullable=false)
	private byte gender;

	//bi-directional one-to-one association to User
	@OneToOne
	@JoinColumn(name="idUser", nullable=false, insertable=false, updatable=false)
	private User user;

	public Userdata() {
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getForeName() {
		return this.foreName;
	}

	public void setForeName(String foreName) {
		this.foreName = foreName;
	}

	public byte getGender() {
		return this.gender;
	}

	public void setGender(byte gender) {
		this.gender = gender;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}