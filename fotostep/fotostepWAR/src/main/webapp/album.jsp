<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html">
<f:view>
    <html>
        <head>
            <title>Fotostep - Test sur les albums</title>
            <%@ include file = "pageheader.jsp" %>
        </head>
        <body>
            
            <h1>Les albums !</h1>
            
        <h:messages styleClass="alert alert-error"/>
            
        <h:form styleClass = "form-horizontal">
            <fieldset>
                <legend>[Test] Ajouter un album</legend>
                <div class="control-group">
                    <label class="control-label" for="inputAlbumName">Nom de l'album</label>
                    <div class="controls">
                        <h:inputText 
                            required="true" 
                            requiredMessage="Vous devez entrer le nom de l'album"
                            value="#{albumController.name}"
                            validator="#{albumController.validateName}"
                            />
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <h:commandButton 
                            styleClass="btn" 
                            value="Créer l'album"
                            action="#{albumController.createAlbum}"
                            />
                    </div>
                </div>
            </fieldset>
        </h:form>
        </body>
    </html>
</f:view>
