<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>

<!DOCTYPE html>

<html> <!-- HTML -->
<f:view>

    <!-- HEAD -->
    <head>
        <title>Fotostep - Bienvenue John Doe !</title>
        <%@include file="templates/pageheader.jsp" %>
    </head>
    <!-- /HEAD -->

    <!-- BODY -->
    <t:documentBody>

        <!-- Menu -->
        <%@include file="templates/header.jsp" %>
        <!-- /Menu -->

        <div class="container">

        </div>

        <%@ include file="templates/footer.jsp" %>
    </t:documentBody>
    <!-- /BODY -->
</f:view>
</html>
<!-- /HTML -->
