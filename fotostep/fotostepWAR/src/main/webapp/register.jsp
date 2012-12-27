<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib prefix="t" uri="http://myfaces.apache.org/tomahawk" %>
<!DOCTYPE html>
<f:view>

    <t:document> <!-- HTML -->

        <!-- HEAD -->
        <t:documentHead>
            <title>Fotostep - Inscription</title>
            <%@include file="templates/pageheader.jsp" %>
        </t:documentHead>
        <!-- /HEAD -->

        <t:documentBody>

            <div class="container ">
            <div class="row">
                <div class="span12 pagination-centered">

                    <div class="page-header">
                        <h1><a href="main.jsf"><img src="assets/img/header-logo.png"/></a><br>
                            <small>Venez nous rejoindre !</small>
                        </h1>
                    </div>

                    <div class="register">

                        <!-- Affichage des messages d'erreurs -->



                        <!--  Début formulaire -->
                        <h:form styleClass="form-horizontal">
                            <fieldset>
                                <legend>Inscription</legend>
                                <h4>C'est gratuit (pour le moment)!</h4>

                                <h:messages styleClass="alert alert-error"/>

                                <div class="control-group">
                                    <label class="control-label">Votre nom</label>

                                    <div class="controls">
                                        <h:inputText id="lastNameField" required="true"
                                                     requiredMessage="Vous devez entrer votre nom"
                                                     value="#{registerController.lastName}"
                                                     validator="#{registerController.validateLastName }"/>
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label">Votre prénom</label>

                                    <div class="controls">
                                        <h:inputText id="firstNameField" required="true"
                                                     requiredMessage="Vous devez entrer votre prénom"
                                                     value="#{registerController.firstName}"
                                                     validator="#{registerController.validateFirstName}"/>
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label">Votre email</label>

                                    <div class="controls">
                                        <h:inputText id="emailField" required="true"
                                                     requiredMessage="Vous devez entrer votre e-mail"
                                                     value="#{registerController.eMail}"
                                                     validator="#{registerController.validateEmail}"/>
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label">Votre mot de passe</label>

                                    <div class="controls">
                                        <h:inputSecret id="passwordField" required="true"
                                                       requiredMessage="Vous devez entrer un mot de passe"
                                                       value="#{registerController.password}"
                                                       validator="#{registerController.validatePassword}"/>
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label">Répétez votre mot de passe</label>

                                    <div class="controls">
                                        <h:inputSecret id="repasswordField" required="true"
                                                       requiredMessage="Vous devez retaper votre mot de passe"
                                                       value="#{registerController.passwordRetype}"/>
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label">Vous êtes </label>

                                    <div class="controls">
                                        <h:selectOneMenu id="genderField" required="true"
                                                         value="#{registerController.gender}">
                                            <f:selectItem itemValue="f" itemLabel="une femme"/>
                                            <f:selectItem itemValue="m" itemLabel="un homme"/>
                                        </h:selectOneMenu>
                                    </div>
                                </div>

                                <div class="control-group">
                                    <div class="controls">
                                        <h:commandButton styleClass="btn" value="Valider l'inscription"
                                                         action="#{registerController.register}"/>
                                    </div>
                                </div>


                            </fieldset>
                        </h:form>
                        <!-- Fin formulaire -->
                    </div>
                </div>
            </div>
            <%@ include file="templates/footer.jsp" %>

        </t:documentBody>
    </t:document>
</f:view>
