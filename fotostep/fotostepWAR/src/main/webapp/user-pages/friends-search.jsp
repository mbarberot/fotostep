<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@taglib uri="http://richfaces.org/rich" prefix="rich"%>




<!DOCTYPE html>

<html> <!-- HTML -->
    <f:view>

        <!-- HEAD -->
        <head>
            <title>
                <h:outputText value="Fotostep - Rechercher des utilisateurs"/>
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
                            <h2>Recherche d'utilisateurs</h2>
                        </div>

                        <div class="search-form">
                            <a4j:form>
                                <div class="input-append">
                                    <h:inputText id="search-keywords" 
                                                 required="true"
                                                 value="#{searchFriends.keywords}"/>
                                    <a4j:commandLink 
                                        id="btn-search-friend"
                                        styleClass="btn btn-info" 
                                        action="#{searchFriends.search}"
                                        reRender="search-result">
                                        <i class="icon-search icon-white"></i>
                                    </a4j:commandLink>
                                </div>
                            </a4j:form>
                        </div>
                        <hr/>
                        <t:div id="search-result">
                            <t:div id="no-result" rendered="#{!searchFriends.hasResult}">
                                <t:htmlTag value="h4">
                                    <t:outputText value="Aucun résultat !" />
                                </t:htmlTag>
                            </t:div>
                            <t:div id="have-result">
                                <t:dataList
                                    id="list-result"
                                    value="#{searchFriends.searchResult}" var="friend"
                                    styleClass="media-list"
                                    layout="unorderedList"
                                    rendered="#{searchFriends.hasResult}">

                                    <t:div id="element-result" styleClass="media span2 well">
                                        <h:outputLink value="view-profile.jsf?UserId=#{friend.iduser}">
                                            <h:graphicImage styleClass="media-object" value="/images?UserId=#{friend.iduser}&PictureId=#{friend.avatar.idpicture}&Thumb=profileType" rendered="#{friend.hasAvatar}" />
                                            <h:graphicImage styleClass="media-object" value="../assets/img/avsmall.png" rendered="#{!friend.hasAvatar}" />
                                            <t:htmlTag value="h5" styleClass="media-body">
                                                <h:outputText styleClass="media-heading" value = "#{friend.firstname} #{friend.lastname}" />
                                            </t:htmlTag>
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
