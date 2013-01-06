<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<html> <!-- HTML -->
<f:view>

    <!-- HEAD -->
    <head>
        <title>
            <h:outputText value="Fotostep - Créer un nouvel album" />
        </title>
        <%@include file="templates/pageheader.jsp" %>
    </head>
    <!-- /HEAD -->

    <!-- BODY -->
    <t:documentBody>

        <!-- Menu -->
        <%@include file="templates/header.jsp" %>
        <!-- /Menu -->

        <div class="index">
            <div class="row">

                <!-- /Menu de navigation -->
                <%@include file="templates/nav-menu.jsp" %>

                <!-- Contenu -->
                <div class = "span9">
                    <div class = "page-header">
                        <h2>Création d'un nouvel album</h2>
                    </div>
                    <div class="span9">
                        <h:messages styleClass= "alert alert-error"/>
                        <h:form styleClass="form-horizontal">
                            <div class="control-group">
                                <label class="control-label">Titre</label>
                                <div class="controls">
                                    <h:inputText id="inputTitle" value="#{createAlbum.title}" validator="#{createAlbum.validateTitle}"
                                                 required="true" requiredMessage="Titre obligatoire pour le nouvel album"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">Description</label>
                                <div class="controls">
                                    <h:inputTextarea id="inputDescription" rows="4" value="#{createAlbum.description}"
                                                     validator="#{createAlbum.validateDescription}"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">
                                    Confidentialité de l'album
                                </label>
                                <div class = "controls">
                                    <h:selectOneMenu id="inputAuthorization" value="#{createAlbum.authorization}"
                                                     validator="#{createAlbum.validateAuthorization}">
                                        <f:selectItem id="friends" itemLabel="Amis" itemValue="FRIENDS" />
                                        <f:selectItem id="private" itemLabel="Privé" itemValue="PRIVATE" />
                                        <f:selectItem id="public" itemLabel="Publique" itemValue="PUBLIC" />
                                    </h:selectOneMenu>
                                </div>
                            </div>
                            <div class="control-group">
                                <div class = "controls">
                                    <div class="btn-toolbar">
                                        <h:commandButton styleClass="btn btn-success" action="#{createAlbum.doCreate}" value="Créer"/>
                                        <h:outputLink styleClass="btn btn-danger" value="view-albums.jsf">Retour</h:outputLink>
                                    </div>
                                </div>
                            </div>

                        </h:form>
                    </div>
                </div>
                <!-- /Contenu -->
            </div>
        </div>


        <%@ include file="templates/footer.jsp" %>
    </t:documentBody>
    <!-- /BODY -->
</f:view>
</html>
<!-- /HTML -->
