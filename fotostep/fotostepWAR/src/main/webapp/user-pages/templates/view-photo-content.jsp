<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich" %>

<f:subview id="view-photo-content">
    <div class = "span9">
        <div class = "page-header">
            <h2><h:outputText value="#{viewAlbum.titre}"/> - Photo (<h:outputText value="#{viewPicture.albumInd}"/>/<h:outputText value = "#{viewAlbum.numberOfPictures}"/>)</h2>

            <div class="btn-group">
                <a4j:form id="control-buttons">
                    <a4j:commandLink styleClass="btn btn-success" id="btn-like"
                                     action="#{viewPicture.unlike}" reRender="control-buttons"
                                     value="#{fn:length(viewPicture.likers)}"
                                     rendered="#{viewPicture.likedByMe}">
                        Vous aimez déjà cet album
                        <i class="icon-thumbs-down icon-white"></i>
                    </a4j:commandLink>
                    <a4j:commandLink styleClass="btn btn-info" id="btn-unlike"
                                     action="#{viewPicture.like}" reRender="control-buttons"
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
                        <h:outputLink value="view-photo.jsf" rendered="#{viewPicture.prevPic!=null and viewPicture.prevPic.idpicture != viewPicture.idPicture}">
                            <f:param name="UserId" value="#{sessionScope['userId']}"/>
                            <f:param name="AlbumId" value="#{viewAlbum.albId}"/>
                            <f:param name="PictureId" value="#{viewPicture.prevPic.idpicture}"/>
                            &larr; Image pr&eacute;c&eacute;dente
                        </h:outputLink>
                    </li>
                    <li class="next">
                        <h:outputLink value="view-photo.jsf" rendered="#{viewPicture.nextPic != null and viewPicture.nextPic.idpicture != viewPicture.idPicture}">
                            <f:param name="UserId" value="#{sessionScope['userId']}"/>
                            <f:param name="AlbumId" value="#{viewAlbum.albId}"/>
                            <f:param name="PictureId" value="#{viewPicture.nextPic.idpicture}"/>
                            Image suivante &rarr;
                        </h:outputLink>
                    </li>
                </ul>

                <%@include file="view-picture.jsp" %>

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
                                <li class="label">Tag 1</li>
                                <li class="label">Tag 2</li>
                            </ul>
                        </dd>
                        <dt>Dimensions</dt>
                        <dd><h:outputText value="#{viewPicture.width}"/> x <h:outputText value="#{viewPicture.height}"/></dd>
                        <dt>Localisation</dt>
                        <dd><h:outputText value="#{viewPicture.lgt}"/>,<h:outputText value="#{viewPicture.lat}"/></dd>
                    </dl>
                </div>
            </div>

            <hr>
            <div class = "row span10">
                <h3>Commentaires</h3>

                <h:outputText rendered="#{viewPicture.numberOfComments eq 0}">
                    <h4>Pas de commentaires sur la photo</h4>
                </h:outputText>

                <t:dataList var="comm" rendered="#{viewPicture.numberOfComments gt 0}"
                            styleClass="thumbnails" value="#{viewPicture.comments}">

                    <div class="media">
                        <a class="pull-left" href="#">
                            <img class="media-object" data-src="holder.js/64x64">
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


            </div>
            <div class = "row span10">
                <h3>Poster un commentaire</h3>

                <form>
                    <fieldset>
                        <textarea  class = "span7 offset2"></textarea>
                        <span class="help-block">Tapez votre commentaire plus cliquez sur "Valider"</span>

                        <button type="submit" class="btn">Valider</button>
                    </fieldset>
                </form>

            </div>

        </div>

    </div>
</f:subview>
