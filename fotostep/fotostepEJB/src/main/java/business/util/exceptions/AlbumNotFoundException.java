package business.util.exceptions;

/**
 * Exception : Album non trouvé dans la base de données.
 * @author Mathieu Barberot
 */
public class AlbumNotFoundException extends Exception
{
    public AlbumNotFoundException(String message)
    {
        super(message);
    }
}
