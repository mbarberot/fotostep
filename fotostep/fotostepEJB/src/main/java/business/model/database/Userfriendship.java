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

	private long date;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="idUser1")
	private User user1;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="idUser2")
	private User user2;

	public Userfriendship() {
	}

	public UserfriendshipPK getId() {
		return this.id;
	}

	public void setId(UserfriendshipPK id) {
		this.id = id;
	}

	public long getDate() {
		return this.date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

}