<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>
<%@ taglib prefix="a4j" uri="http://myfaces.apache.org/tomahawk" %>

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
        
        <div class="index">
            <div class="row">
                <!-- /Menu de navigation -->
                <%@include file="templates/nav-menu.jsp" %>

                <!-- Contenu -->
                <div class = "span9">
                    <div class = "page-header">
                        <h2>Uploader une photo</h2>
                    </div>

                    <div class="span9" >
                        <h:form styleClass="form-horizontal" id="uploadForm" enctype="multipart/form-data">
                            <h:messages styleClass="alert alert-error"/>

                            <div class="control-group">
                                <label class="control-label">Sélectionnez la photo</label>
                                <div class="controls">
                                    <t:inputFileUpload id="fileField" required="true" 
                                                       value="#{webUpload.uploadedFile}"
                                                       validator="#{webUpload.validateUploadedFile}"/>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label">Description</label>
                                <div class="controls">
                                    <h:inputTextarea id="descriptionField" value="#{webUpload.description}" />
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label">TAGS</label>
                                <div class="controls">
                                    <h:inputTextarea id="tagsField" value="#{webUpload.tags}" />
                                </div>
                            </div>

                            <div class="control-group">
                                <h:commandButton styleClass="btn" value="Enregistrer" action="#{webUpload.submit}" />
                            </div>                                
                        </h:form>
                    </div>
                </div>
                <!-- /Contenu -->
            </div>
        </div>
        <%@ include file="templates/footer.jsp" %>
    </t:documentBody>
    <!-- /BODY -->
    
</f:view>
</html>
<!-- /HTML -->