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
            <h:outputText value="Fotostep - Mes albums" />
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
                        <h2>Gestion de mes albums
                            <h:outputLink styleClass="btn btn-primary pull-right" value="create-album.jsf">
                                <i class = "icon-plus-sign icon-white" ></i> Nouvel album
                            </h:outputLink>
                        </h2>

                    </div>

                    <div class = "span9" id = "albums-container">
                        <c:forEach items="#{viewAlbums.albums}" var="alb">
                            <div class="media">
                                <h:outputLink value="view-album.jsf" styleClass="pull-left">
                                    <f:param name="UserId" value="#{sessionScope['userId']}" />
                                    <f:param name="AlbumId" value="#{alb.idalbum}" />
                                    <h:graphicImage styleClass="media-object"
                                            value="/images?UserId=#{sessionScope['userId']}&PictureId=#{alb.coverPicture.idpicture}&Thumb=albtype"
                                            rendered="#{alb.coverPicture != null}"/>
                                    <h:graphicImage styleClass="media-object"
                                                    value="../assets/img/albdefaut.png"
                                                    rendered="#{alb.coverPicture == null}"/>
                                </h:outputLink>

                                <div class="media-body">

                                    <h3 class = "media-heading">${alb.name}</h3>
                                    <p>${alb.description}</p>
                                    <p><b>Nombre de photos :</b> ${fn:length(alb.pictures)}</p>
                                    <p><b>Confidentialité :</b>
                                        <c:choose>
                                            <c:when test="${alb.authorization == 'PRIVATE'}">
                                                 Privé
                                            </c:when>
                                            <c:when test="${alb.authorization == 'FRIENDS'}">
                                                Amis
                                            </c:when>
                                            <c:otherwise>
                                                Public
                                            </c:otherwise>
                                        </c:choose> </p>
                                </div>
                            </div>
                        </c:forEach>



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
