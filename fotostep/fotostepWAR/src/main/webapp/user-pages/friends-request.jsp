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
                <h:outputText value="Fotostep - Demandes d'amitiés"/>
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
                            <h2>Vos demandes</h2>
                        </div>
                        <t:div id="reloadRequest" styleClass="tabbable tabs-left">

                            <t:htmlTag value="ul" styleClass="nav nav-tabs" id="requestTab">
                                <t:htmlTag value="li" styleClass="active">
                                    <h:outputLink value="#requestTo">Envoyées</h:outputLink>
                                </t:htmlTag>
                                <t:htmlTag value="li">
                                    <h:outputLink value="#requestFrom">Reçues</h:outputLink>
                                </t:htmlTag>
                            </t:htmlTag>

                            <t:div id="requestTabContent" styleClass="tab-content">
                                
                                    <t:div id="requestTo" styleClass="tab-pane fade in active">
                                        <t:div id="no-request-to" rendered="#{!requestFriends.hasRequestTo}">
                                            <t:htmlTag value="h4">
                                                <t:outputText value="Vous n'avez pas de demandes pour le moment" />
                                            </t:htmlTag>
                                        </t:div>    
                                        <t:div id="have-request-to">
                                            <t:dataList
                                                id="list-request-to"
                                                value="#{requestFriends.requestingTo}" var="friend"
                                                styleClass="media-list" itemStyleClass=""
                                                layout="unorderedList"
                                                rendered="#{requestFriends.hasRequestTo}">

                                                <t:div id="element-request-to" styleClass="media span2 well">
                                                    <t:div styleClass="pull-right">
                                                        <a4j:form>
                                                            <a4j:commandLink id="btn-cancel-friend"
                                                                             onclick="$(this).parents('.media').fadeOut();"
                                                                             action="#{requestFriends.cancelRequest}" 
                                                                             styleClass="btn btn-small btn-inverse"
                                                                             reRender="requestTabContent">
                                                                <a4j:actionparam value="#{friend.iduser}" name="cancel-request"/>
                                                                <t:htmlTag value="i" styleClass="icon-remove icon-white"/>
                                                            </a4j:commandLink>
                                                        </a4j:form>
                                                    </t:div>
                                                    <h:outputLink styleClass="" value="view-profile.jsf?UserId=#{friend.iduser}">
                                                        <h:graphicImage styleClass="media-object" value="#{friend.avatar}" width="64" height="64"/>
                                                        <t:div styleClass="media-body">
                                                            <t:htmlTag value="h5">
                                                                <t:outputText styleClass="media-heading" value = "#{friend.firstname} #{friend.lastname}" />
                                                            </t:htmlTag>
                                                        </t:div>
                                                    </h:outputLink>
                                                </t:div>
                                            </t:dataList>
                                        </t:div>
                                    </t:div>


                                    <t:div id="requestFrom" styleClass="tab-pane fade in">
                                        <t:div id="no-request-from" rendered="#{!requestFriends.hasRequestFrom}">
                                            <t:htmlTag value="h4">
                                                <t:outputText value="Vous n'avez pas de demandes pour le moment" />
                                            </t:htmlTag>
                                        </t:div>
                                        <t:div id="have-request-from">
                                            <t:dataList
                                                id="list-request-from"
                                                value="#{requestFriends.requestingFrom}" var="friend"
                                                styleClass="media-list" itemStyleClass=""
                                                layout="unorderedList"
                                                rendered="#{requestFriends.hasRequestFrom}">

                                                <t:div id="element-request-from" styleClass="media span2 well">
                                                    <t:div styleClass="pull-right">
                                                        <a4j:form>
                                                            <t:div styleClass="btn-group btn-group-vertical" style="margin-right:8px;">
                                                                <a4j:commandLink id="btn-accept-friend"
                                                                                 onclick="$(this).parents('.media').fadeOut();"
                                                                                 action="#{requestFriends.acceptRequest}" 
                                                                                 styleClass="btn btn-small btn-success"
                                                                                 reRender="requestTabContent">
                                                                    <a4j:actionparam value="#{friend.iduser}" name="accept-request"/>
                                                                    <t:htmlTag value="i" styleClass="icon-ok icon-white"/>
                                                                </a4j:commandLink>
                                                                <a4j:commandLink id="btn-reject-friend"
                                                                                 onclick="$(this).parents('.media').fadeOut();"
                                                                                 action="#{requestFriends.rejectRequest}" 
                                                                                 styleClass="btn btn-small btn-inverse"
                                                                                 reRender="requestTabContent">
                                                                    <a4j:actionparam value="#{friend.iduser}" name="reject-request"/>
                                                                    <t:htmlTag value="i" styleClass="icon-remove icon-white"/>
                                                                </a4j:commandLink>
                                                            </t:div>
                                                        </a4j:form>

                                                    </t:div>
                                                    <h:outputLink styleClass="" value="view-profile.jsf?UserId=#{friend.iduser}">
                                                        <h:graphicImage styleClass="media-object" value="#{friend.avatar}" width="64" height="64"/>
                                                        <t:div styleClass="media-body">
                                                            <t:htmlTag value="h5">
                                                                <t:outputText styleClass="media-heading" value = "#{friend.firstname} #{friend.lastname}" />
                                                            </t:htmlTag>
                                                        </t:div>
                                                    </h:outputLink>
                                                </t:div>
                                            </t:dataList>
                                        </t:div>
                                    </t:div>
                                
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
