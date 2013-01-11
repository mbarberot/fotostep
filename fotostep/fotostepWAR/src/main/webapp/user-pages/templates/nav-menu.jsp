<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<f:subview id="nav-menu">

    <div class="span3">
        <div class="well sidebar-nav">
            <ul class="nav nav-list">
                <li><h:outputLink value="news.jsf">Accueil</h:outputLink></li>
                <li><h:outputLink value="#">Rechercher des photos</h:outputLink></li>
                    <li class="nav-header">Mon compte</li>
                    <li><h:outputLink id="view-my-profile" value="view-profile.jsf">
                        <f:param name="UserId" value="#{sessionScope['userId']}" />
                        <h:outputText value="Voir mon profil" />
                    </h:outputLink></li>
                <li><h:outputLink value="#">Modifier mon compte</h:outputLink></li>

                    <li class="nav-header">Mes photos</li>
                    <li><h:outputLink value="upload.jsf">Ajouter des photos</h:outputLink></li>
                <li><h:outputLink value="view-albums.jsf">Gérer mes albums</h:outputLink></li>

                    <li class="nav-header">Mes contacts</li>
                    <li><h:outputLink value="#">Voir mes contacts</h:outputLink></li>
                <li><h:outputLink value="#">Répondre aux demandes</h:outputLink></li>
                <li><h:outputLink value="#">Chercher des contacts</h:outputLink></li>

                </ul>
            </div>
        </div>
</f:subview>