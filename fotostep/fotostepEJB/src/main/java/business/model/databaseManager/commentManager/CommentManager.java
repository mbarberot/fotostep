package business.model.databaseManager.commentManager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import business.model.database.Album;
import business.model.database.Commentalbum;
import business.model.database.Commentimage;
import business.model.database.Image;
import business.model.database.User;

/**
 * Bean de manipulation de l'entité Comment dans la base de données.
 *
 * @author Mathieu Barberot
 */
@Stateless
public class CommentManager implements CommentManagerLocal
{
	@PersistenceContext
	EntityManager em;


	public Commentalbum addComment(Album album, User user, String title, String text) {
		Commentalbum comment = new Commentalbum();
		comment.setIdUser(user);
		comment.setTitle(title);
		comment.setBody(text);
		comment.setDate(System.currentTimeMillis());
		em.persist(comment);
		return comment;
	}

	public void editComment(Commentalbum comment, String text){
		comment.setBody(text);
		em.persist(comment);
	}

	public void deleteComment(Commentalbum comment) {
		em.remove(comment);
	}

	public Commentimage addComment(Image image, User user, String title, String text) {
		Commentimage comment = new Commentimage();
		comment.setIdUser(user);
		comment.setTitle(title);
		comment.setBody(text);
		comment.setDate(System.currentTimeMillis());
		em.persist(comment);
		return comment;
	}

	public void editComment(Commentimage comment, String text) {
		comment.setBody(text);
		em.persist(comment);
	}

	public void deleteComment(Commentimage comment) {
		em.remove(comment);
	}

}
