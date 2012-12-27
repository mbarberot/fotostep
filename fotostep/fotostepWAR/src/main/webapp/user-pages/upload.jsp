<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>

<!DOCTYPE html>

<html> <!-- HTML -->
<f:view>

    <!-- HEAD -->
    <head>
        <title>Fotostep - Uploader une photo</title>
        <%@include file="templates/pageheader.jsp" %>
    </head>
    <!-- /HEAD -->

    <!-- BODY -->
    <t:documentBody>

        <!-- Menu -->
        <%@include file="templates/header.jsp" %>
        <!-- /Menu -->

        <div class="container">
            <h:form id="uploadForm" enctype="multipart/form-data">
                <h:panelGrid columns="3">
                    <h:outputLabel for="file" value="Sélectionner le fichier" />
                    <t:inputFileUpload id="file" value="#{webUpload.uploadedFile}" required="true" />
                    <h:message for="file" styleClass="label label-important" />

                    <h:panelGroup />
                    <h:commandButton value="Submit" action="#{myBean.submit}" />
                    <h:message for="uploadForm" infoStyle="color: green;" errorStyle="color: red;" />
                </h:panelGrid>
            </h:form>

            <h:outputLink value="file/#{webUpload.fileName}" rendered="#{webUpload.fileName != null}"></h:outputLink>
        </div>

        <%@ include file="templates/footer.jsp" %>
    </t:documentBody>
    <!-- /BODY -->
</f:view>
</html>
<!-- /HTML -->