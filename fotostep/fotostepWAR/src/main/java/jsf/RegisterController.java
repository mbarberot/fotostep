package jsf;

import business.model.database.User;
import business.model.databaseManager.userManager.UserManagerLocal;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import org.apache.commons.validator.routines.EmailValidator;

public class RegisterController {

	/**
	 * UserManager permettant d'effectuer l'inscription
	 */
	@EJB
	private UserManagerLocal userManager;

	/**
	 * Attributs mappés depuis le formulaire
	 */
	private String lastName = "";
	private String firstName = "";
	private String eMail = "";
	private String password = "";
	private String passwordRetype = "";
	private String gender = "";

	/** Constructeur */
	public RegisterController() {
	}

	/**
	 * Validators
	 */

	/** Vérifie que le mot de passe est valide (=> plus de 6 caractères) */
	public void validatePassword(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		String pass = (String) value;
		if (pass.length() < 6 || pass.length() > 32 || !pass.matches("[\\S]*")) {
			throw new ValidatorException(new FacesMessage(
					"Le mot de passe n'est pas valide (plus de 6 et moins de 32 lettres, "
							+ "chiffres ou caractères spéciaux"));
		}

	}

	/** Le nom est valide => plus de deux caractères / que des lettres */
	public void validateLastName(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		String lname = (String) value;
		if (lname.length() <= 2 || !lname.matches("[a-z|A-Z| ]*")) {
			throw new ValidatorException(new FacesMessage(
					"Le nom de famille entré n'est pas valide"));
		}

	}

	/** Le prénom est valide => plus de deux caractères */
	public void validateFirstName(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		String fname = (String) value;
		if (fname.length() <= 2 || !fname.matches("[a-z|A-Z| ]*")) {
			throw new ValidatorException(new FacesMessage(
					"Le prénom entré n'est pas valide"));
		}

	}

	/** L'e-mail est bien formé => xxx@yyy.zzz */
	public void validateEmail(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		String mail = (String) value;
		EmailValidator emailValidator = EmailValidator.getInstance();

		if (!emailValidator.isValid(mail)) {
			throw new ValidatorException(new FacesMessage(
					"L'adresse e-mail entrée n'est pas valide"));
		}
	}

	/**
	 * Méthode ajoutant l'utilisateur dans la base
	 * 
	 */
	public String register() throws ValidatorException {
		
		User user = userManager.registerNewUser(eMail, password, firstName, lastName, gender);
		
		return "REG_SUCCESS";
	}

	/**
	 * Getters & setters
	 */
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordRetype() {
		return passwordRetype;
	}

	public void setPasswordRetype(String passwordRetype) {
		this.passwordRetype = passwordRetype;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
