package business.model.databaseManager.commentManager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import business.model.database.Comment;
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


	public Comment addComment(Image image, User user, String title, String text) {
		Comment comment = new Comment();
		comment.setIdUser(user);
		comment.setTitle(title);
		comment.setBody(text);
		em.persist(comment);
		return comment;
	}

	public void editComment(Comment comment, String text){
		comment.setBody(text);
		em.persist(comment);
	}
	
	public void deleteComment(Comment comment) {
		em.remove(comment);
	}

}
