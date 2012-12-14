package business.model.database;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the userdata database table.
 * 
 */
@Entity
public class Userdata implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idUser;

	@Temporal(TemporalType.DATE)
	private Date birthDate;

	private String firstname;

	private String foreName;

	private Object gender;

	//bi-directional one-to-one association to User
	@OneToOne
	@JoinColumn(name="idUser")
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

	public Object getGender() {
		return this.gender;
	}

	public void setGender(Object gender) {
		this.gender = gender;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}