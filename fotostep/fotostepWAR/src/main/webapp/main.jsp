<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix = "f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix = "h" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<!DOCTYPE html>

<html> <!-- HTML -->
<f:view>

    <!-- HEAD -->
    <head>
        <title>Fotostep - Bienvenue !</title>
        <%@include file="templates/pageheader.jsp"%>
    </head>
    <!-- /HEAD -->

    <!-- BODY -->
    <t:documentBody>

        <!-- Menu -->
        <%@include file="templates/header.jsp"%>
        <!-- /Menu -->
        <div class="container">
            <div class="page-header">
                <h1><img src="assets/img/header-logo.png"/></br><small>Partagez votre vision du monde</small></h1>
            </div>

            <div id="map">
                <!-- La map publique -->
            </div>
        </div>

     <%@ include file = "templates/footer.jsp" %>
    </t:documentBody>
    <!-- /BODY -->
</f:view>
</html>   <!-- /HTML -->
