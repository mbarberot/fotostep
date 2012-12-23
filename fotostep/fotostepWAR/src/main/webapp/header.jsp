<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<f:subview id = "header-component">
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container">
                <a class="brand" href="main.jsp"> <img
                        src="assets/img/logo.png"></a>
                <div class="nav-collapse collapse">
                    <ul class="nav">
                        <li><a href="main.jsf">Accueil</a></li>
                        <li><a href="#about">Rechercher des photos</a></li>
                        <li class="active"><a href="register.jsf">Inscription</a></li>
                    </ul>
                    <h:form styleClass="form-inline pull-right"
                          style="display: inline; margin-bottom: 0; margin-left: 15px">
                         <h:messages styleClass= "label label-important"/>
                        <c:choose>
                            <c:when test="${empty sessionScope.userId}">
                                <h:inputText id="login" value="#{loginController.login}" styleClass="input-small" style="margin:5px" />
                                <h:inputSecret redisplay="true" id="password" value="#{loginController.password}" styleClass="input-small" style="margin:5px" />
                                <h:commandButton id="login-btn" value="S'identifier" action="#{loginController.doLogin}" styleClass="btn" style="margin:5px" />
                            </c:when>
                            
                            <c:otherwise>
                                <h:commandButton id="logout-btn" value="Se déconnecter" action="#{loginController.doLogout}" styleClass="btn" style="margin:5px" />
                            </c:otherwise>
                        </c:choose>
                        
                        <!--
                        <input type="text" placeholder="Email" class="input-small"
                               name="login"> 
                        <input type="password"
                               placeholder="Mot de passe" class="input-small" name="passwd">
                        <button class="btn" type="submit" style="margin: 5px">S'identifier</button>
                        -->
                    </h:form>
                </div>
                <!--/.nav-collapse -->
            </div>
        </div>
    </div>
</f:subview>
