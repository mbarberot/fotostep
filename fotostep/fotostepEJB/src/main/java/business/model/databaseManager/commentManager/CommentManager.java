package business.model.databaseManager.commentManager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import business.model.database.Album;
import business.model.database.Commentalbum;
import business.model.database.Commentpicture;
import business.model.database.Picture;
import business.model.database.User;
import java.util.Date;

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


	public Commentalbum addComment(Album album, User user, String text) {
		Commentalbum comment = new Commentalbum();
		comment.setAuthor(user);
                comment.setAlbum(album);
		comment.setBody(text);
		comment.setDate(new Date());
		em.persist(comment);
		return comment;
	}

	public void editComment(Commentalbum comment, String text){
		comment.setBody(text);
		em.persist(comment);
	}

	public void deleteComment(Commentalbum comment) {
		em.remove(em.find(Commentalbum.class, comment.getIdcommentalbum()));
	}

	public Commentpicture addComment(Picture picture, User user, String text) {
		Commentpicture comment = new Commentpicture();
                comment.setPicture(picture);
                comment.setAuthor(user);
                comment.setDate(new Date());
                comment.setBody(text);
		em.persist(comment);
		return comment;
	}

	public void editComment(Commentpicture comment, String text) {
		comment.setBody(text);
		em.persist(comment);
	}

	public void deleteComment(Commentpicture comment) {
		em.remove(em.find(Commentpicture.class, comment.getIdcommentpicture()));
	}

}
