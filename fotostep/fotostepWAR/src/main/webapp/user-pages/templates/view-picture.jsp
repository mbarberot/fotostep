<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<f:subview id="view-pic-loc" rendered="#{viewAlbum.numberOfPictures gt 0}">

    <div id="shadowing"></div>
    <div id="box">
        <span id="boxtitle"></span><span id="boxclose" onclick="closebox()"><i class = "icon-check"></i></span>
        <h:graphicImage
                id="viewed-image"
                value="none">
        </h:graphicImage>
    </div>

</f:subview>