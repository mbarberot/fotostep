<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix = "f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix = "h" %>
<!DOCTYPE html>
<html>
<head>
<title>Fotostep - Inscrivez-vous</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">

		<!-- Le styles -->
		<link href="assets/css/bootstrap.css" rel="stylesheet">
		<style>
			body {
				padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
			}
			
			.register {
				background-color: #F2F2F2;
				box-shadow: 1px 1px 8px grey;
				border-radius: 10px;
				width: 450px;
				padding: 5px 10px 5px 20px;
			}
		</style>
		<link href="assets/css/bootstrap-responsive.css" rel="stylesheet">

		<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
		<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
</head>
<body>
<f:view>
	<%@ include file = "header.jsp" %>
	<div class="container">
		<div class="page-header">
			<h1><img src="assets/img/header-logo.png"/></br><small>Partagez votre vision du monde (ou autre slogan alakon)</small></h1>
		</div>
		<%@ include file = "footer.jsp" %>
	</div>	
</f:view>
</body>
</html>