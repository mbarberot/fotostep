<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
                        <!--h4>Aucun contacts</h4-->
                        <t:dataList
                            id = "list-friends"
                            value = "#{viewFriends.friends}" var = "friend"
                            styleClass = "media-list" itemStyleClass=""
                            layout = "unorderedList">
                            
                            <t:div styleClass="media span2 well">
                                 <t:commandLink action="#" styleClass="btn-small btn-info pull-right">
                                    <t:htmlTag value="i" styleClass="icon-remove icon-white" />
                                </t:commandLink>
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
                    </div>
                </div>

            </div>


            <%@ include file="templates/footer.jsp" %>
        </t:documentBody>
        <!-- /BODY -->
    </f:view>
</html>
<!-- /HTML -->
