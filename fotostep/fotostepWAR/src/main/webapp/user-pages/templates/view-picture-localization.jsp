<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<f:subview id="view-pic-loc">

    <div id="shadowing"></div>
    <div id="box">
        <span id="boxtitle"><span id="boxclose" onclick="closebox()">x</span></span>
        <div id="map-picture"></div>
        <script src="http://cdn.leafletjs.com/leaflet-0.4/leaflet.js"></script>
        <script src="../assets/js/view-pic-loc.js"></script>
    </div>

</f:subview>