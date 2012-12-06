package business.util.exceptions;

/**
 * Exception : Image non trouvée dans la base de données
 * @author Mathieu Barberot
 */
public class ImageNotFoundException extends Exception
{
    public ImageNotFoundException(String message)
    {
        super(message);
    }
}
