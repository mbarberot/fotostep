<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<f:subview id="view-profile-content">
    <div class = "span9">
        <div class="page-header">
            <div class="media">
                <h:graphicImage
                        value="/images?UserId=#{param.UserId}&PictureId=#{userProfileData.avatar.idpicture}&Thumb=profileType"
                        styleClass="media-object pull-left"
                        rendered="#{userProfileData.avatar != null}"/>
                <h:graphicImage
                        value="../assets/img/avnormal.png"
                        styleClass="media-object pull-left"
                        rendered="#{userProfileData.avatar == null}"/>
                <div class="media-body">
                    <h2><h:outputFormat value="{0} {1}">
                        <f:param value="#{userProfileData.firstName}" />
                        <f:param value="#{userProfileData.lastName}" />
                    </h:outputFormat></h2>

                    <c:choose>
                        <c:when test="${userProfileData.isAFriend}">
                            <a href="#" class = "btn btn-success">Déjà dans vos contacts</a>
                        </c:when>
                        <c:when test="${userProfileData.isAFriend and !userProfileData.profileOfMine}">
                            <a href="#" class = "btn btn-primary"><i class="icon-user icon-white"></i>  Ajouter aux contacts </a>
                        </c:when>
                        <c:otherwise/>
                    </c:choose>

                </div>
            </div>
        </div>
        <div class = "span9">
            <ul id="profileTab" class="nav nav-pills">
                <li class="active">
                    <a href="#mapView">Carte</a>
                </li>
                <li>
                    <a href="#albums">Albums</a>
                </li>
                <li>
                    <a href="#infos">Infos</a>
                </li>
                <li>
                    <a href="#friends">Amis</a>
                </li>
            </ul>

            <div id="myTabContent" class="tab-content">

                <!-- Map view -->
                <div class="tab-pane fade in active" id="mapView">
                    <t:div rendered="#{userProfileData.jsonPictures != null}">
                        <%@include file="view-profile-content-map.jsp"%>
                    </t:div>

                    <h:outputText rendered="#{userProfileData.jsonPictures == null}">
                        <h3>Cet utilisateur n'a pas de photos localisées</h3>
                    </h:outputText>
                </div>

                <!-- Albums view -->
                <div class="tab-pane fade" id="albums">
                    <c:choose>
                        <c:when test="${fn:length(userProfileData.albums) gt 0}">
                        <ul class="thumbnails">
                            <c:forEach items="#{userProfileData.albums}" var="album">
                                <li class="span3">
                                    <div class = "thumbnail">
                                        <h:graphicImage
                                                value="/images?UserId=#{param.UserId}&PictureId=#{album.coverPicture.idpicture}&Thumb=albtype"
                                                styleClass="media-object pull-left"
                                                rendered="#{album.coverPicture != null}"/>
                                        <h:graphicImage
                                                value="../assets/img/albdefaut.png"
                                                styleClass="media-object pull-left"
                                                rendered="#{album.coverPicture == null}"/>
                                        <h3>${album.name}</h3>
                                        <p>${album.description}</p>
                                        <h:outputLink id="view-album" value="view-album.jsf">
                                            <f:param name="UserId" value="#{param.UserId}" />
                                            <f:param name="AlbumId" value="#{album.idalbum}" />
                                            <h:outputText value="Voir l'album"/>
                                        </h:outputLink>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                        </c:when>
                        <c:otherwise><h3>Cette personne n'a pas d'albums visibles</h3></c:otherwise>
                    </c:choose>
                </div>

                <!-- Info view -->
                <div class="tab-pane fade" id="infos">
                    <h3>Général</h3>
                    <dl class="dl-horizontal">
                        <dt>Nom</dt>
                        <dd><h:outputText value="#{userProfileData.lastName}"/></dd>
                        <dt>Pr&eacute;nom</dt>
                        <dd><h:outputText value="#{userProfileData.firstName}"/></dd>
                        <dt>N&eacute; le</dt>
                        <dd><h:outputText value="#{userProfileData.birthDate}"/></dd>
                        <dt>Habite &agrave;</dt>
                        <dd><h:outputText value="#{userProfileData.userPlace}"/></dd>
                        <dt>Sexe</dt>
                        <dd><h:outputText value="#{userProfileData.gender}"/></dd>
                        <dt>Inscrit le </dt>
                        <dd><h:outputText value="#{userProfileData.registerDate}"/></dd>
                    </dl>
                    <c:if test="${userProfileData.isAFriend or userProfileData.profileOfMine}">
                        <h3>Contact</h3>
                        <dl class="dl-horizontal">
                            <dt>Mail</dt>
                            <dd><h:outputText value="#{userProfileData.mail}"/></dd>
                            <dt>Twitter</dt>
                            <dd><h:outputText value="#{userProfileData.idTwitter}"/></dd>
                            <dt>Facebook</dt>
                            <dd><h:outputText value = "#{userProfileData.idFb}"/></dd>

                        </dl>
                    </c:if>
                </div>

                <!-- Friends view -->
                <div class="tab-pane fade" id="friends">
                    <c:choose>
                        <c:when test="${fn:length(userProfileData.friends) gt 0}">
                            <c:forEach items="#{userProfileData.friends}" var="friend">
                                <div class="media">
                                    <h:outputLink styleClass="pull-left" id="view-profile" value="view-profile.jsf">
                                        <f:param name="UserId" value="#{friend.iduser}" />
                                        <h:graphicImage
                                                value="/images?UserId=#{friend.iduser}&PictureId=#{friend.avatar.idpicture}&Thumb=profileMinType"
                                                rendered="#{friend.avatar != null}"/>
                                        <h:graphicImage
                                                value="../assets/img/avsmall.png"
                                                rendered="#{friend.avatar == null}"/>
                                    </h:outputLink>
                                    <div class="media-body">
                                        <h4>${friend.firstname} ${friend.lastname}</h4>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise><h3>Cette personne n'a pas d'amis :'(</h3></c:otherwise>
                    </c:choose>

                </div>

            </div>
            </form>
        </div>




    </div>



</f:subview>