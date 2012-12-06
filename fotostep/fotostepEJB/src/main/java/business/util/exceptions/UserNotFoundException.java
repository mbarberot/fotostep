package business.util.exceptions;

/**
 * Exception : Utilisateur non trouvé
 * @author Mathieu Barberot
 */
public class UserNotFoundException extends Exception
{
    public UserNotFoundException(String message)
    {
        super(message);
    }
}
