<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich" %>

<f:subview id="view-album-content">
    <div class="span9">
        <div class="page-header">
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
                                    <h:outputLink value="upload.jsf">
                                        <f:param name="AlbumId" value="#{viewAlbum.albId}" />
                                        Nouvelle photo
                                    </h:outputLink>
                                </li>
                                <c:if test="${!viewAlbum.isDefault}">
                                    <li>
                                        <h:commandLink value="Supprimer" action="#{viewAlbum.deleteAlbum}"
                                                       onclick="if (!confirm('Voulez vous vraiment supprimer cet album ?')) return false">
                                            <f:param value="#{viewAlbum.albId}" name="deletedalb"/>
                                        </h:commandLink>
                                    </li>
                                    <li>
                                        <a href="#">Editer l'album</a>
                                    </li>
                                </c:if>
                            </h:form>
                        </ul>
                    </div>
                </c:if>
            </h2>

            <div class="btn-group">
                <a4j:form id="control-buttons">
                    <a4j:commandLink styleClass="btn btn-success" id="btn-like"
                                     action="#{viewAlbum.unlike}" reRender="control-buttons"
                                     value="#{fn:length(viewAlbum.likers)}"
                                     rendered="#{viewAlbum.isLikedByMe}">
                        <a4j:actionparam value="#{viewAlbum.albId}" name="unlikealbum"/>
                        Vous aimez déjà cet album
                        <i class="icon-thumbs-down icon-white"></i>
                    </a4j:commandLink>
                    <a4j:commandLink styleClass="btn btn-info" id="btn-unlike"
                                     action="#{viewAlbum.like}" reRender="control-buttons"
                                     value="#{fn:length(viewAlbum.likers)}"
                                     rendered="#{!viewAlbum.isLikedByMe}">
                        <a4j:actionparam value="#{viewAlbum.albId}" name="likealbum"/>
                        Aimer
                        <i class=" icon-thumbs-up icon-white"></i>
                    </a4j:commandLink>
                </a4j:form>

            </div>


        </div>

        <div class="span9" id="albums-container">
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
                    <div id="map" style="width: 100%; height: 400px"></div>
                    <script src="http://cdn.leafletjs.com/leaflet-0.4/leaflet.js"></script>
                    <script>

                        var map = L.map('map').setView([51.505, -0.09], 13);

                        L.tileLayer('http://{s}.tile.cloudmade.com/BC9A493B41014CAABB98F0471D759707/997/256/{z}/{x}/{y}.png', {
                            maxZoom: 18,
                            attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://cloudmade.com">CloudMade</a>'
                        }).addTo(map);


                        var markerPopup = L.popup({minWidth: 50});
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
                    <h:outputText rendered="#{viewAlbum.numberOfPictures eq 0}">
                        <h3>Pas de photos dans l'album</h3>
                    </h:outputText>



                    <ul class="thumbnails">

                        <t:dataList var="pic" rendered="#{viewAlbum.numberOfPictures gt 0}"
                                    styleClass="thumbnails" value="#{viewAlbum.pictures}">
                            <li class="span3">
                                <h:outputLink styleClass="thumbnail" value="view-photo.jsf">
                                    <f:param name="UserId" value="#{sessionScope['userId']}"/>
                                    <f:param name="AlbumId" value="#{viewAlbum.albId}"/>
                                    <f:param name="PictureId" value="#{pic.idpicture}"/>
                                    <h:graphicImage
                                            value="/images?UserId=#{sessionScope['userId']}&PictureId=#{pic.idpicture}&Thumb=albtype"
                                            >
                                    </h:graphicImage>
                                </h:outputLink>
                            </li>
                        </t:dataList>
                    </ul>

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
                    <a4j:outputPanel id="comments-list">
                        <h:outputText rendered="#{viewAlbum.numberOfComments eq 0}">
                            <h4>Pas de commentaires sur l'album</h4>
                        </h:outputText>
                        <t:dataList var="comm" rendered="#{viewAlbum.numberOfComments gt 0}"
                                    styleClass="thumbnails" value="#{viewAlbum.comments}">
                            <div class="media">
                                <a class="pull-left" href="#">
                                    <img class="media-object" data-src="../assets/js/holder.js/64x64">
                                </a>

                                <div class="media-body">
                                    <h4 class="media-heading">
                                        <h:outputFormat value="{0} {1}">
                                            <f:param value="#{comm.author.firstname}"/>
                                            <f:param value="#{comm.author.lastname}"/>
                                        </h:outputFormat>
                                    </h4>

                                    <div class="media">
                                        <h:outputText value="#{comm.body}"/>
                                        <small>Le <h:outputText value="#{comm.date}"/></small>
                                    </div>
                                </div>
                            </div>
                        </t:dataList>

                            <h3>Poster un commentaire</h3>
                            <h:form>

                                <div class="control-group">
                                    <div class="controls">
                                        <h:inputTextarea value="#{viewAlbum.commentText}"
                                                         styleClass="span5" id="comment-text">
                                        </h:inputTextarea>
                                        <span class="help-block">Tapez votre commentaire plus cliquez sur "Valider"</span>
                                        <a4j:commandButton action="#{viewAlbum.postComment}"
                                                           reRender="comments-list"
                                                           styleClass="btn" value="Poster le commentaire"/>
                                    </div>
                                </div>
                            </h:form>



                    </a4j:outputPanel>
                </div>
            </div>
        </div>
    </div>
</f:subview>
