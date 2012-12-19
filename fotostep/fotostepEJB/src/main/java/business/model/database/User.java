package business.model.database;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entityé Utilisateur
 *
 * @author Mathieu Barberot
 */
@Entity
@Table(name="user")
public class User implements Serializable
{
    /**
     * ID de l'utilisateur
     */
    @Id
    private int idUser;
    
    /**
     * Login de l'utilisateur
     */
    private String login;

    /**
     * Mot de passe (hashé en md5) du l'utilisateur
     */
    private String password;
    
    /**
     * Pseudonyme de l'utilisateur
     */
    private String nickname;
    
    /**
     * Statut de l'utilisateur : autorisé, banni, supprimé
     */
    @Enumerated(EnumType.ORDINAL)
    private Status enabled;
    
    /**
     * Prénom de l'utilisateur
     */
    private String firstname;
    
    /**
     * Nom de famille de l'utilisateur
     */
    private String lastname;
    
    /**
     * Date de naissance de l'utilisateur
     */
    @Temporal(value = TemporalType.DATE)
    private Date birthdate;
    
    /**
     * Genre de l'utilisateur
     */
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;
}
