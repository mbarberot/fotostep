<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>

<html> <!-- HTML -->
    <f:view>

        <!-- HEAD -->
        <head>
            <title>
                <h:outputFormat value="Fotostep - Bienvenue {0} {1}">
                    <f:param value="#{userSessionController.userFirstName}" />
                    <f:param value="#{userSessionController.userLastName}" />
                </h:outputFormat>
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
                        <div class="page-header">
                            <h2>Actualit&eacute;s</h2>
                        </div>
                        <c:choose>
                            <c:when test="${fn:length(newsController.news) gt 0}">
                                <ul class="media-list">
                                    <c:forEach items="#{newsController.news}" var="new">
                                        <li class="media">
                                            <h5 class="media-heading">${new.title}</h5>
                                            <p class="media-body span7">${new.body}</p>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </c:when>
                            <c:otherwise><h4>Aucune news</h4></c:otherwise>
                        </c:choose>
                    </div>
                </div>

            </div>


            <%@ include file="templates/footer.jsp" %>
        </t:documentBody>
        <!-- /BODY -->
    </f:view>
</html>
<!-- /HTML -->
