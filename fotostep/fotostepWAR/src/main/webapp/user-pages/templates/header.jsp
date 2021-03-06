<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
<f:subview id = "header-component">
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container">
                <a class="brand" href="news.jsf"> <img
                        src="../assets/img/logo.png"></a>

                <ul class="nav">
                    <li> <a href="news.jsf">Accueil</a> </li>
                    <li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" href="#">Mon profil<b class="caret"></b> </a>
                        <ul class="dropdown-menu">
                            <li><h:outputLink id="view-my-profile" value="view-profile.jsf">
                                <f:param name="UserId" value="#{sessionScope['userId']}" />
                                <h:outputText value="Voir mon profil" />
                            </h:outputLink></li>
                            <li class="divider"></li>
                            <li><a href="view-albums.jsf">Gérer mes photos</a></li>
                        </ul>
                    </li>
                    <li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" href="#">Mes contacts<b class="caret"></b> </a>
                        <ul class="dropdown-menu">
                            <li><a href="view-friends.jsf">Mes contacts</a></li>
                            <li><a href="friends-request.jsf">Répondre aux demandes</a></li>
                        </ul>
                    </li>
                </ul>


                <h:form styleClass="form-inline pull-right"
                        style="display: inline; margin-bottom: 0; margin-left: 15px">
                        <h:commandButton id="logout-btn" value="Se déconnecter" action="#{loginController.doLogout}" styleClass="btn-inverse" style="margin:5px" />
                </h:form>

                </div>
                <!--/.nav-collapse -->
            </div>
        </div>

</f:subview>
