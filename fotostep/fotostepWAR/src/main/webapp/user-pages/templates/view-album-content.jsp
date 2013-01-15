<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<f:subview id="view-album-content">
    <div class = "span9">
        <div class = "page-header">
            <h2><h:outputText value="#{viewAlbum.titre}"/>
                <c:if test="${viewAlbum.isMine}">
                    <div class="btn-group pull-right">
                        <a class="btn dropdown-toggle btn-primary" data-toggle="dropdown" href="#">
                            Actions sur l'album
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <h:form>
                                <li>
                                    <a href = "#">Nouvelle photo</a>
                                </li>
                                <c:if test="${!viewAlbum.isDefault}">
                                    <li>
                                        <h:commandLink value="Supprimer" action="#{viewAlbum.deleteAlbum}"
                                                       onclick="if (!confirm('Voulez vous vraiment supprimer cet album ?')) return false">
                                            <f:param value="#{viewAlbum.albId}" name="deletedalb"/>
                                        </h:commandLink>
                                    </li>
                                    <li>
                                        <a href = "#">Editer l'album</a>
                                    </li>
                                </c:if>
                            </h:form>
                        </ul>
                    </div>
                </c:if>
            </h2>

            <div class="btn-group">
                <c:choose>
                    <c:when test="${viewAlbum.isLikedByMe}">
                        <a href="#" class="btn btn-success" id="btn-unlike"><i class="icon-minus icon-white"></i>
                            <h:outputText value="#{fn:length(viewAlbum.likers)}"/>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="#" class="btn btn-info" id="btn-like"><i class="icon-heart icon-white"></i>
                            <h:outputText value="#{fn:length(viewAlbum.likers)}"/>
                        </a>
                    </c:otherwise>
                </c:choose>

                <a href="#" class="btn btn-inverse" id = "btn-comment"><i class="icon-comment icon-white"></i>
                    <h:outputText value="#{fn:length(viewAlbum.comments)}"/>
                </a>


            </div>


        </div>

        <div class = "span9" id = "albums-container">
            <ul id="profileTab" class="nav nav-pills">
                <li class="active">
                    <a href="#mapView">Carte</a>
                </li>
                <li>
                    <a href="#pictures">Photos</a>
                </li>
                <li>
                    <a href="#infos">Infos</a>
                </li>
                <li>
                    <a href="#comments">Commentaires</a>
                </li>
            </ul>

            <div id="myTabContent" class="tab-content">
                <!-- Map view -->
                <div class="tab-pane fade in active" id="mapView">
                    <div id  = "map" style= "width: 100%; height: 400px"></div>
                    <script src="http://cdn.leafletjs.com/leaflet-0.4/leaflet.js"></script>
                    <script>

                        var map = L.map('map').setView([51.505, -0.09], 13);

                        L.tileLayer('http://{s}.tile.cloudmade.com/BC9A493B41014CAABB98F0471D759707/997/256/{z}/{x}/{y}.png', {
                            maxZoom: 18,
                            attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://cloudmade.com">CloudMade</a>'
                        }).addTo(map);



                        var markerPopup = L.popup({minWidth:50});
                        markerPopup.setContent("<p>Hello</p>");

                        L.marker([51.5, -0.09]).addTo(map).bindPopup('<img src="img/avsmall.png"/>').openPopup();
                        var popup = L.popup();

                        function onMapClick(e) {
                            popup
                                    .setLatLng(e.latlng)
                                    .setContent("You clicked the map at " + e.latlng.toString())
                                    .openOn(map);
                        }

                        map.on('click', onMapClick);

                    </script>
                </div>

                <!-- Gallery view -->
                <div class="tab-pane fade" id="pictures">
                    <c:choose>
                        <c:when test ="${fn:length(viewAlbum.pictures) gt 0}">
                            <ul class="thumbnails">
                                <c:forEach items="#{viewAlbum.pictures}" var="pic">
                                    <li class="span3">
                                        <a href="#" class="thumbnail">
                                            <h:graphicImage value="/images" />
                                        </a>
                                    </li>
                                 </c:forEach>
                            </ul>
                        </c:when>
                         <c:otherwise>
                             <h3>Pas de photos dans l'album</h3>
                         </c:otherwise>
                    </c:choose>
                </div>

                <!-- Infos view -->
                <div class="tab-pane fade" id="infos">
                    <h3>Général</h3>
                    <dl class="dl-horizontal">
                        <dt>Description</dt>
                        <dd><h:outputText value="#{viewAlbum.description}"/></dd>
                        <dt>Date de cr&eacute;ation</dt>
                        <dd><h:outputText value="#{viewAlbum.creationDate}"/></dd>
                        <dt>Ouvert à</dt>
                        <dd><h:outputText value="#{viewAlbum.authorization}"/></dd>
                    </dl>
                </div>

                <!--Comments -->
                <div class="tab-pane fade" id="comments">
                    <c:choose>
                        <c:when test ="${fn:length(viewAlbum.comments) gt 0}">
                            <div class="media">
                                <c:forEach items="#{viewAlbum.comments}" var="comm">
                                    <a class="pull-left" href="#">
                                        <img class="media-object" data-src="../assets/js/holder.js/64x64">
                                    </a>
                                    <div class="media-body">
                                        <h4 class="media-heading">
                                            <h:outputFormat value="{0} {1}">
                                                <f:param value="#{comm.author.firstname}" />
                                                <f:param value="#{comm.author.lastname}" />
                                            </h:outputFormat>
                                        </h4>
                                        <div class="media">
                                            <h:outputText value="#{comm.body}"/>
                                            <p>Le <h:outputText value="#{comm.date}"/></p>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <h3>Pas de commentaires sur l'album</h3>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </div>
    </div>
</f:subview>
