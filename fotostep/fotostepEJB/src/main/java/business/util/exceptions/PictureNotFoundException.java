package business.util.exceptions;

/**
 * Exception : Image non trouvée dans la base de données
 * @author Mathieu Barberot
 */
public class PictureNotFoundException extends Exception
{
    public PictureNotFoundException(String message)
    {
        super(message);
    }
}
