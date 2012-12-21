<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<f:subview id = "header-component">
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container">
                <a class="brand" href="index.jsp"> <img
                        src="assets/img/logo.png"></a>
                <div class="nav-collapse collapse">
                    <ul class="nav">
                        <li><a href="index.jsf">Accueil</a></li>
                        <li><a href="#about">Rechercher des photos</a></li>
                        <li class="active"><a href="register.jsf">Inscription</a></li>
                    </ul>
                    <form class="form-inline pull-right"
                          style="display: inline; margin-bottom: 0; margin-left: 15px"
                          method="post">
                        
                        <h:inputText id="login" value="#{loginController.login}" styleClass="input-small" />
                        <h:inputSecret id="password" value="#{loginController.password}" styleClass="input-small" />
                        <h:commandButton id="login-btn" value="S'identifier" action="#{loginController.doLogin}" styleClass="btn" style="margin:5px" />
                        
                        <!--
                        <input type="text" placeholder="Email" class="input-small"
                               name="login"> 
                        <input type="password"
                               placeholder="Mot de passe" class="input-small" name="passwd">
                        <button class="btn" type="submit" style="margin: 5px">S'identifier</button>
                        -->
                    </form>
                </div>
                <!--/.nav-collapse -->
            </div>
        </div>
    </div>
</f:subview>