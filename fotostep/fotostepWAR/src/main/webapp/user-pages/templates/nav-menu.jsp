<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<f:subview id="nav-menu">
<div class="span3">
    <div class="well sidebar-nav">
        <ul class="nav nav-list">

            <li><h:outputLink value="news.jsf">Accueil</h:outputLink></li>
            <li><h:outputLink value="#">Rechercher des photos</h:outputLink></li>
            <li class="nav-header">Mon compte</li>
            <li><h:outputLink value="#">Visualiser mon profil</h:outputLink></li>
            <li><h:outputLink value="#">Modifier mon compte</h:outputLink></li>

            <li class="nav-header">Mes photos</li>
            <li><h:outputLink value="#">Ajouter des photos</h:outputLink></li>
            <li><h:outputLink value="#">Gérer mes albums</h:outputLink></li>

            <li class="nav-header">Mes contacts</li>
            <li><h:outputLink value="#">Voir mes contacts</h:outputLink></li>
            <li><h:outputLink value="#">Répondre aux demandes</h:outputLink></li>
            <li><h:outputLink value="#">Chercher des contacts</h:outputLink></li>

        </ul>
    </div>
</div>
</f:subview>