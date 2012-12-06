package business.util;

/**
 * Énumération des droits d'accès.
 * 
 * @author Mathieu Barberot
 */
public class Permission
{
    // Liste des droits d'accès
    public static final int PRIVATE = 0;
    public static final int FRIENDS = 1;
    public static final int PUBLIC = 2;
    
    /**
     * Tableau faisant le lien entre les valeurs et le nom des permissions
     */
    private String[] names = new String[]{ "PRIVATE", "FRIENDS", "PUBLIC" };
    
    /**
     * Permission actuelle
     */
    private int permission;
    
    /**
     * Constructeur
     * 
     * @param permission Nom de la permission
     */
    public Permission(String permission)
    {
        int len = names.length;
        for(int i = 0; i < len; i++)
        {
            if(permission.equalsIgnoreCase(names[i])) { this.permission = i; }
        }
    }
    
    public int getPermission()
    {
        return this.permission;
    }
    
    @Override
    public String toString()
    {
        return this.names[this.permission];
    }
            
}
