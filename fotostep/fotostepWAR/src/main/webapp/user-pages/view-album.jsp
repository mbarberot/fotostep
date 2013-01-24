<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>

<!DOCTYPE html>

<html> <!-- HTML -->
<f:view>
    <!-- HEAD -->
    <t:outputText value="" rendered= "#{albumRequest.id != param.AlbumId}" />
    <head>
        <title>

            <c:choose>
                <c:when test="${viewAlbum.isAuthorized == true}">
                    <h:outputText value = "Fotostep - Album \"#{viewAlbum.titre}\""/>
                </c:when>
                <c:otherwise>
                     <h:outputText value = "Fotostep - Erreur !"/>
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
                    <c:when test="${viewAlbum.isAuthorized == true}">
                        <%@include file = "templates/view-album-content.jsp"%>
                    </c:when>
                    <c:otherwise>
                        <div class = "span9">
                            <div class="alert alert-error">
                                <a class="close" href="news.jsf">×</a>
                                <strong>Erreur!</strong> L'album que vous tentez de consulter n'existe pas ou vous ne disposez pas des autorisations
                                pour le visualiser.
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
