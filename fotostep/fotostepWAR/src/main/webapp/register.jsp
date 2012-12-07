<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix = "f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix = "h" %>
<!DOCTYPE html>
<f:view>
<html>
<head>
		<title>Fotostep - Inscrivez-vous</title>
		<%@ include file = "pageheader.jsp" %>
</head>

<body>


	<%@ include file = "header.jsp" %>
	<div class="container">
	
			<div class="page-header">
				<h1><img src="assets/img/header-logo.png"/><br><small>Partagez votre vision du monde (ou autre slogan alakon)</small></h1>
			</div>
			
			<div class="register">
			
				
				<!-- Affichage des messages d'erreurs -->
    				<h:messages styleClass="alert alert-error"/>
				
				
				<!--  Début formulaire -->
				<h:form styleClass = "form-horizontal">
					<fieldset>
						<legend>Inscription</legend>
						<h4>C'est gratuit (pour le moment)!</h4>
						
						<div class = "control-group">
							<label class="control-label" for="inputLastName">Votre nom</label>							
							<div class="controls">
								<h:inputText id = "lastNameField" required="true" requiredMessage="Vous devez entrer votre nom" 
								value = "#{registerController.lastName}" 
								validator="#{registerController.validateLastName }" />
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="inputFirstName">Votre prénom</label>
							<div class="controls">
								<h:inputText id = "firstNameField" required="true" requiredMessage="Vous devez entrer votre prénom" 
								value = "#{registerController.firstName}" 
								validator="#{registerController.validateFirstName}"/>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="inputEmail">Votre email</label>
							<div class="controls">
								<h:inputText id = "emailField" required="true" requiredMessage="Vous devez entrer votre e-mail" 
								value = "#{registerController.eMail}" 
								validator="#{registerController.validateEmail}"/>
							</div>
						</div>
					
						<div class="control-group">
							<label class="control-label" for="inputPassword">Votre mot de passe</label>
							<div class="controls">
								<h:inputSecret id = "passwordField" required="true" requiredMessage="Vous devez entrer un mot de passe" 
								value = "#{registerController.password}" 
								validator="#{registerController.validatePassword}"/>
							</div>
						</div>	
					
						<div class="control-group">
							<label class="control-label" for="inputRepeatPassword">Répétez votre mot de passe</label>
							<div class="controls">
							<!-- TODO / Vérification du MDP -->
								<h:inputSecret id = "repasswordField" required="true" requiredMessage="Vous devez retaper votre mot de passe" 
								value = "#{registerController.passwordRetype}"/>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="inputKind">Vous êtes </label>
							<div class="controls">
								<h:selectOneMenu id = "genderField" required="true" value = "#{registerController.gender}">
									<f:selectItem itemValue = "f" itemLabel = "une femme"/>
									<f:selectItem itemValue = "m" itemLabel = "un homme"/>
								</h:selectOneMenu>
							</div>
						</div>
					
						<div class="control-group">
							<div class="controls">
								<h:commandButton styleClass="btn" value = "Valider l'inscription" action = "#{registerController.register}"/>
							</div>
						</div>		
										
										
					
					</fieldset>
				</h:form>
				<!-- Fin formulaire -->
			</div>
			<%@ include file = "footer.jsp" %>
	</div>
			
</body>
</html>
</f:view>
