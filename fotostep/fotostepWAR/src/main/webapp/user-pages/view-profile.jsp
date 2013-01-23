<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html> <!-- HTML -->
<f:view>

    <!-- HEAD -->
    <head>
        <title>
            <c:choose>
                <c:when test="${userProfileData.visible == true}">
                    <h:outputFormat rendered="#{userProfileData.visible}" value="Fotostep - Profil de {0} {1}">
                        <f:param value="#{userProfileData.firstName}" />
                        <f:param value="#{userProfileData.lastName}" />
                    </h:outputFormat>
                </c:when>
                <c:otherwise>
                    <h:outputText value="Fotostep - Erreur !"/>
                </c:otherwise>
            </c:choose>
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
                <c:choose>

                    <c:when test="${param.UserId != null && userProfileData.visible == true}">
                        <%@include file = "templates/view-profile-content.jsp"%>
                    </c:when>
                    <c:otherwise>
                        <div class = "span9">
                            <div class="alert alert-error">
                                <a class="close" href="news.jsf">×</a>
                                <strong>Erreur!</strong> Le profil que vous tentez de visionner n'existe pas
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>

            </div>
        </div>


        <%@ include file="templates/footer.jsp" %>
    </t:documentBody>
    <!-- /BODY -->
</f:view>
</html>
<!-- /HTML -->
