<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://richfaces.org/a4j" prefix="a4j"%>

<!DOCTYPE html>

<html> <!-- HTML -->
    <f:view>

        <!-- HEAD -->
        <head>
            <title>
                <h:outputText value="Fotostep - Vos contacts"/>
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
                            <h2>Vos contacts</h2>
                        </div>
                        <t:div id="view-friends">
                            <t:div id="no-friend" rendered="#{!viewFriends.hasFriend}">
                                <t:htmlTag value="h4">
                                    <t:outputText value="Vous n'avez pas de contacts pour le moment" />
                                </t:htmlTag>
                                <t:outputText value="Ajoutez-en tout de suite en allant à la page \"Chercher des contacts\" !"/>
                            </t:div>
                            <t:div id="have-friend">
                                <t:dataList
                                    id = "list-friends"
                                    value = "#{viewFriends.friends}" var = "friend"
                                    styleClass = "media-list" itemStyleClass=""
                                    layout = "unorderedList"
                                    rendered = "#{viewFriend.hasFriend}">

                                    <t:div id="friend-list" styleClass="media span2 well">
                                        <t:div styleClass="pull-right">
                                            <a4j:form>
                                                <a4j:commandLink id="btn-remove-friend"
                                                                 onclick="$(this).parents('.media').fadeOut();"
                                                                 action="#{viewFriends.removeFriend}" 
                                                                 styleClass="btn btn-small btn-inverse"
                                                                 reRender="view-friends">
                                                    <a4j:actionparam value="#{friend.iduser}" name="remove-user"/>
                                                    <t:htmlTag value="i" styleClass="icon-remove icon-white"/>
                                                </a4j:commandLink>
                                            </a4j:form>
                                        </t:div>
                                        <h:outputLink value="view-profile.jsf?UserId=#{friend.iduser}">
                                            <t:div>
                                                <h:graphicImage styleClass="media-object" value="/images?UserId=#{friend.iduser}&PictureId=#{friend.avatar.idpicture}&Thumb=profileType" rendered="#{friend.hasAvatar}" />
                                                <h:graphicImage styleClass="media-object" value="../assets/img/avsmall.png" rendered="#{!friend.hasAvatar}" />
                                                <t:div styleClass="media-body">
                                                    <t:htmlTag value="h5">
                                                        <t:outputText styleClass="media-heading" value = "#{friend.firstname} #{friend.lastname}" />
                                                    </t:htmlTag>
                                                </t:div>
                                            </t:div>
                                        </h:outputLink>
                                    </t:div>
                                </t:dataList>
                            </t:div>
                        </t:div>

                    </div>
                </div>

            </div>


            <%@ include file="templates/footer.jsp" %>
        </t:documentBody>
        <!-- /BODY -->
    </f:view>
</html>
<!-- /HTML -->
