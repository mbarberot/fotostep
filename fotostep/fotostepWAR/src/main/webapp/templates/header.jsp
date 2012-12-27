<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<f:subview id="header-component">
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container">
                <a class="brand" href="main.jsf"> <img
                        src="assets/img/logo.png"></a>

                <div class="nav-collapse collapse">
                    <ul class="nav">
                        <li><a href="main.jsf">Accueil</a></li>
                        <li><a href="register.jsf">Inscrivez-vous</a></li>
                    </ul>

                    <h:form title="Déjà inscrit ?" styleClass="form-inline pull-right"
                            style="display: inline; margin-bottom: 0; margin-left: 15px">

                        <h:messages styleClass="label label-important"/>


                        <h:inputText id="login" value="#{loginController.login}" styleClass="input-small"
                                     style="margin:5px"/>
                        <h:inputSecret redisplay="true" id="password" value="#{loginController.password}"
                                       styleClass="input-small" style="margin:5px"/>
                        <h:commandButton id="login-btn" value="Connectez vous" action="#{loginController.doLogin}"
                                         styleClass="btn-inverse" style="margin:5px"/>


                    </h:form>
                </div>
            </div>
        </div>
    </div>
</f:subview>
