<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<f:subview id="view-profile-content">
    <div class = "span9">
        <div class="page-header">
            <div class="media">
                <h:graphicImage value="#{userProfileData.avatarPath}" styleClass="media-object pull-left" />
                <div class="media-body">
                    <h2><h:outputFormat value="{0} {1}">
                        <f:param value="#{userProfileData.firstName}" />
                        <f:param value="#{userProfileData.lastName}" />
                    </h:outputFormat></h2>

                    <c:if test="${userProfileData.isAFriend == false}">
                        <a href="#" class = "btn btn-primary"><i class="icon-user icon-white"></i>  Ajouter aux contacts </a>
                    </c:if>
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

                    <div id  = "map" style= "width: 99%; height: 400px"></div>
                    <script src="http://cdn.leafletjs.com/leaflet-0.4/leaflet.js"></script>
                    <script>

                        var map = L.map('map').setView([51.505, -0.09], 13);

                        L.tileLayer('http://{s}.tile.cloudmade.com/BC9A493B41014CAABB98F0471D759707/997/256/{z}/{x}/{y}.png', {
                            maxZoom: 18,
                            attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://cloudmade.com">CloudMade</a>'
                        }).addTo(map);


                        L.marker([51.5, -0.09]).addTo(map);


                        var popup = L.popup();

                        function onMapClick(e) {
                            popup
                                    .setLatLng(e.latlng)
                                    .setContent("You clicked the map at " + e.latlng.toString())
                                    .openOn(map);
                        }

                        map.on('click', onMapClick);

                    </script>

                    <c:choose>

                        <c:when test="${fn:length(userProfileData.localizedAlbums) gt 0}">
                            <ul class="pager">
                                <li class="bordered"><a href="#" ><</a></li>
                                <c:forEach items="#{userProfileData.localizedAlbums}" var="alb">
                                    <li><a href="#"><img src="holder.js/64x64" class="img-polaroid"></a></li>
                                 </c:forEach>
                                <li class="bordered"><a href="#">></a></li>
                            </ul>
                        </c:when>
                        <c:otherwise>
                            <h3>Cette personne n'a pas d'albums localisés</h3>
                        </c:otherwise>
                    </c:choose>

                </div>

                <!-- Albums view -->
                <div class="tab-pane fade" id="albums">
                    <c:choose>
                        <c:when test="${fn:length(userProfileData.albums) gt 0}">
                        <ul class="thumbnails">
                            <c:forEach items="#{userProfileData.albums}" var="album">
                                <li class="span3">
                                    <div class = "thumbnail">
                                        <img src="holder.js/250x200" alt="">
                                        <h3>${album.name}</h3>
                                        <p>${album.description}</p>
                                        <h:outputLink id="view-album" value="view-album.jsf">
                                            <f:param name="UserId" value="#{sessionScope['userId']}" />
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
                    <h3>Contact</h3>
                    <dl class="dl-horizontal">
                        <dt>Mail</dt>
                        <dd><h:outputText value="#{userProfileData.mail}"/></dd>
                        <dt>Twitter</dt>
                        <dd><h:outputText value="#{userProfileData.idTwitter}"/></dd>
                        <dt>Facebook</dt>
                        <dd><h:outputText value = "#{userProfileData.idFb}"/></dd>

                    </dl>
                </div>

                <!-- Friends view -->
                <div class="tab-pane fade" id="friends">
                    <c:choose>
                        <c:when test="${fn:length(userProfileData.friends) gt 0}">
                            <c:forEach items="#{userProfileData.friends}" var="friend">
                                <div class="media">
                                    <h:outputLink styleClass="pull-left" id="view-profile" value="view-profile.jsf">
                                        <f:param name="UserId" value="#{friend.iduser}" />
                                        <h:graphicImage value="../assets/img/avsmall.png" styleClass="media-object"/>
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