<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich" %>

<f:subview id="view-photo-content">
<script>$(document).ready(function(){$('#pic-localization').hide();});</script>
    <div class = "span9">
        <div class = "page-header">

            <h2><h:outputText value="#{viewAlbum.titre}"/> - Photo (<h:outputText value="#{viewPicture.albumInd}"/>/<h:outputText value = "#{viewAlbum.numberOfPictures}"/>)

                <div class="btn-group pull-right">
                    <a class="btn dropdown-toggle btn-primary" data-toggle="dropdown" href="#">
                        Actions sur la photos
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <h:form>
                                <li>
                                    <h:commandLink value="Utiliser comme photo de profil" action="#{viewPicture.chooseAsAvatar}"
                                                   onclick="if (!confirm('Voulez vous utiliser cette photo comme avatar ?')) return false">
                                    </h:commandLink>
                                </li>
                                <li>
                                    <h:commandLink value="Utiliser comme photo de couverture" action="#{viewPicture.chooseAsCoverImage}"
                                                   onclick="if (!confirm('Voulez vous utiliser cette photo en tant que photo de couverture'+
                                                    'pour cet album ?')) return false">
                                    </h:commandLink>
                                </li>
                        </h:form>
                    </ul>
                </div>
            </h2>


            <div class="btn-group">
                <a4j:form id="picture-like-buttons" ajaxSubmit="true">
                    <a4j:commandLink styleClass="btn btn-success" id="btn-like"
                                     action="#{viewPicture.unlike}" reRender="picture-like-buttons"
                                     value="#{fn:length(viewPicture.likers)}"
                                     rendered="#{viewPicture.likedByMe}">
                        Vous aimez déjà cette photo
                        <i class="icon-thumbs-down icon-white"></i>
                    </a4j:commandLink>
                    <a4j:commandLink styleClass="btn btn-info" id="btn-unlike"
                                     action="#{viewPicture.like}" reRender="picture-like-buttons"
                                     value="#{fn:length(viewPicture.likers)}"
                                     rendered="#{!viewPicture.likedByMe}">
                        Aimer
                        <i class=" icon-thumbs-up icon-white"></i>
                    </a4j:commandLink>
                </a4j:form>
            </div>



        </div>

        <div class = "span9" id = "albums-container">


            <div class = "row">
                <ul class="pager">
                    <li class="previous">
                        <h:outputLink value="view-photo.jsf"
                                      rendered="#{viewPicture.prevPic!=null
                                      and viewPicture.prevPic.idpicture != viewPicture.idPicture
                                      and viewPicture.prevPic.album.idalbum == param.AlbumId}">
                            <f:param name="UserId" value="#{param.UserId}"/>
                            <f:param name="AlbumId" value="#{viewAlbum.albId}"/>
                            <f:param name="PictureId" value="#{viewPicture.prevPic.idpicture}"/>
                            &larr; Image pr&eacute;c&eacute;dente
                        </h:outputLink>
                    </li>
                    <li>
                        <h:outputLink value="view-album.jsf">
                            <f:param name="UserId" value="#{viewPicture.myAlbum.user.iduser}" />
                            <f:param name="AlbumId" value="#{viewPicture.myAlbum.idalbum}" />
                            Revenir à l'album
                        </h:outputLink>
                    </li>
                    <li class="next">
                        <h:outputLink value="view-photo.jsf" rendered="#{viewPicture.nextPic != null
                        and viewPicture.nextPic.idpicture != viewPicture.idPicture
                        and viewPicture.nextPic.album.idalbum == param.AlbumId}">
                            <f:param name="UserId" value="#{param.UserId}"/>
                            <f:param name="AlbumId" value="#{viewAlbum.albId}"/>
                            <f:param name="PictureId" value="#{viewPicture.nextPic.idpicture}"/>
                            Image suivante &rarr;
                        </h:outputLink>
                    </li>
                </ul>

                <h:graphicImage
                        value="/images?UserId=#{sessionScope['userId']}&PictureId=#{viewPicture.idPicture}&Thumb=pictype"
                        onclick="openbox('Titre du formulaire',0,#{sessionScope['userId']},#{viewPicture.idPicture})"
                        styleClass="span8">
                </h:graphicImage>

                <div class = "row span10">
                    <dl class="dl-horizontal">
                        <dt>Description</dt>
                        <dd><h:outputText value="#{viewPicture.description}"/> </dd>
                        <dt>Tags</dt>
                        <dd>
                            <ul class = "unstyled inline">
                                <t:dataList var="tag" value="#{viewPicture.tags}">
                                    <li class="label"><h:outputText value = "#{tag}"/></li>
                                </t:dataList>
                            </ul>
                        </dd>
                        <dt>Dimensions</dt>
                        <dd><h:outputText value="#{viewPicture.width}"/> x <h:outputText value="#{viewPicture.height}"/></dd>
                        <a4j:outputPanel id = "refresh-loc">
                            <dt>Localisation</dt>
                            <dd><h:outputText value="#{viewPicture.lat}"/>,<h:outputText value="#{viewPicture.lgt}"/>
                                <a href ="#pic-localization" onclick=
                                        "$('#pic-localization').toggle(); $(this).text('&nbsp;(Cacher la carte)')">
                                    &nbsp;(Afficher sur une carte)
                                </a>
                        </a4j:outputPanel>
                    </dl>

                </div>
                <div class="row span10" id="pic-localization">
                    <h3>Localisation</h3>
                    <p class="alert alert-success" id = "local-success" style="display : none;">
                        La localisation a bien été ajoutée sur votre photo
                    </p>
                    <div id="map-pic" style="width: 800px; height: 400px"></div>
                    <script src="http://cdn.leafletjs.com/leaflet-0.4/leaflet.js"></script>
                    <script>

                        var lgt = <h:outputText value = "#{viewPicture.lgt}"/>;
                        var lat = <h:outputText value = "#{viewPicture.lat}"/>;

                        console.log("Latitude = " + lat);
                        console.log("Longitude = " + lgt);

                        if(lat==0.0 && lgt ==0.0)
                        {
                            lat = 48.86336; lgt = 2.352448;
                        }

                        var map = L.map('map-pic').setView([lat, lgt], 5);

                        L.tileLayer('http://{s}.tile.cloudmade.com/BC9A493B41014CAABB98F0471D759707/997/256/{z}/{x}/{y}.png', {
                            maxZoom: 18,
                            attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://cloudmade.com">CloudMade</a>'
                        }).addTo(map);


                        var picMarker = L.marker([lat, lgt]).addTo(map);


                        function onMapClick(e) {
                            picMarker.setLatLng(e.latlng);
                            $("#view-photo-content\\:localize-form\\:longview").val(e.latlng.lng);
                            $("#view-photo-content\\:localize-form\\:latview").val(e.latlng.lat);

                            $("#view-photo-content\\:localize-form\\:longitude").val(e.latlng.lng);
                            $("#view-photo-content\\:localize-form\\:latitude").val(e.latlng.lat);
                        }

                        map.on('click', onMapClick);

                    </script>

                    <hr>
                    <div class = "form-inline">
                    <a4j:form id = "localize-form" rendered="#{viewAlbum.isMine}">
                        <div class="control-group">
                            <label class="control-label" for="latview">Lat</label>
                            <div class="controls">
                                <h:inputText id= "latview" readonly="true" value = "#{viewPicture.lat}"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="latview">Lat</label>
                            <div class="controls">
                                <h:inputText id = "longview" readonly="true" value="#{viewPicture.lgt}"/>
                            </div>
                        </div>

                        <h:inputHidden id = "latitude"
                                       value = "#{viewPicture.lat}"/>
                        <h:inputHidden id = "longitude"
                                     value="#{viewPicture.lgt}"/>

                        <div class="control-group">
                            <div class="controls">
                                <a4j:commandLink value="Valider"
                                                 styleClass="btn"
                                                 action="#{viewPicture.validateLoc}"
                                                 reRender="refresh-loc"
                                                 oncomplete="$('#local-success').fadeIn(400, function(){$('#local-success')
                                                 .delay(2000).fadeOut();});"/>
                            </div>
                        </div>
                    </a4j:form>
                </div>
                </div>
            </div>

            <hr>
            <a4j:outputPanel id = "comments-panel">
            <div class = "row span10">

                    <h3>Commentaires</h3>

                    <h:outputText rendered="#{viewPicture.numberOfComments eq 0}">
                        <h4>Pas de commentaires sur la photo</h4>
                    </h:outputText>

                    <t:dataList var="comm" rendered="#{viewPicture.numberOfComments gt 0}"
                                styleClass="thumbnails" value="#{viewPicture.comments}">
                        <div class="media">
                            <a class="pull-left" href="#">
                                <h:graphicImage
                                        value="/images?UserId=#{comm.author.iduser}&PictureId=#{comm.author.avatar.idpicture}&Thumb=profileMinType"
                                        rendered="#{comm.author.avatar != null}"/>
                                <h:graphicImage
                                        value="../assets/img/avsmall.png"
                                        rendered="#{comm.author.avatar == null}"/>
                            </a>
                            <div class="media-body">
                                <h4 class="media-heading">
                                    <h:outputFormat value="{0} {1}">
                                        <f:param value="#{comm.author.firstname}"/>
                                        <f:param value="#{comm.author.lastname}"/>
                                    </h:outputFormat>
                                </h4>
                                <div class="media">
                                    <p><h:outputText value="#{comm.body}"/></p>
                                    <small>Le <h:outputText value="#{comm.date}"/></small>
                                </div>
                            </div>
                        </div>
                    </t:dataList>
                </div>
                <div class = "row span10">
                    <h3>Poster un commentaire</h3>
                    <h:form>
                        <h:inputTextarea styleClass="span7 offset2" value="#{viewPicture.commentText}"></h:inputTextarea>
                        <span class="help-block">Tapez votre commentaire plus cliquez sur "Valider"</span>
                        <a4j:commandButton styleClass="btn"
                                           value="Poster un commentaire"
                                           action="#{viewPicture.postComment}" reRender="comments-panel"/>
                    </h:form>
                </div>
                </a4j:outputPanel>
        </div>

    </div>
</f:subview>
