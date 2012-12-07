<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix = "f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix = "h" %>
<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="utf-8">
		<title>Fotostep - modify</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">

		<!-- Le styles -->
		<link href="assets/css/bootstrap.css" rel="stylesheet">
		<style>
			body {
				padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
			}
		</style>
		<link href="assets/css/bootstrap-responsive.css" rel="stylesheet">

		<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
		<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->

		<!-- Fav and touch icons -->
		<link rel="shortcut icon" href="../assets/ico/favicon.ico">
		<link rel="apple-touch-icon-precomposed" sizes="144x144" href="ico/apple-touch-icon-144-precomposed.png">
		<link rel="apple-touch-icon-precomposed" sizes="114x114" href="ico/apple-touch-icon-114-precomposed.png">
		<link rel="apple-touch-icon-precomposed" sizes="72x72" href="ico/apple-touch-icon-72-precomposed.png">
		<link rel="apple-touch-icon-precomposed" href="ico/apple-touch-icon-57-precomposed.png">
	</head>

	<body>
		<f:view>		
			<%@ include file = "header.jsp" %>	
			<div class="container">
			
				<div class="page-header">
					<h1><img src="assets/img/header-logo.png"/></br><small>Partagez votre vision du monde (ou autre slogan alakon)</small></h1>
				</div>		
				
				<div class="modify">			
					<h2>Modifier son profil</h2>
					<ul id="myTab" class="nav nav-tabs">
						<li class="active">
							<a href="#infos1" data-toggle="tab">Infos 1</a>
						</li>
						<li>
							<a href="#infos2" data-toggle="tab">Infos 2</a>
						</li>
						<li>
							<a href="#infos3" data-toggle="tab">Infos 3</a>
						</li>
						<li>
							<a href="#infos4" data-toggle="tab">Infos 4</a>
						</li>
					</ul>
					<form class="form-horizontal">	
						<div id="myTabContent" class="tab-content">
							<div class="tab-pane fade in active" id="infos1">						
								<div class="control-group">
									<label class="control-label" for="inputLastName">Votre nom</label>
									<div class="controls">
										<input type="text" id="inputLastName" placeholder="Nom">
									</div>
								</div>				
								<div class="control-group">
									<label class="control-label" for="inputFirstName">Votre prénom</label>
									<div class="controls">
										<input type="text" id="inputFirstName" placeholder="Prénom">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="inputEmail">Votre email</label>
									<div class="controls">
										<input type="text" id="inputEmail" placeholder="Email">
									</div>
								</div>										
							</div>
							<div class="tab-pane fade" id="infos2">
								<div class="control-group">
									<label class="control-label" for="inputKind">Vous êtes</label>
									<div class="controls">
										<select>
											<option>une Femme</option>
											<option>un Homme</option>
										</select>
									</div>
								</div>
							</div>
							<div class="tab-pane fade" id="infos3">
								<p>En cours</p>
							</div>
							<div class="tab-pane fade" id="infos4">
								<p>En cours</p>
							</div>
							<div class="control-group">
								<div class="controls">
									<button type="submit" class="btn btn-primary">Modifier</button>
									<button type="button" class="btn">Annuler</button>								
								</div>
							</div>								
						</div>
					</form>		
				</div>
				<%@ include file = "footer.jsp" %>			
			</div> <!-- /container -->		
		</f:view>
		
		<!-- Le javascript -->
		<script src="assets/js/jquery.js"></script>
		<script src="assets/js/bootstrap.js"></script>
		<script src="assets/js/bootstrap-tabs.js"></script>

		<script>
			$('#myTab a').click(function (e) {
				e.preventDefault();
				$(this).tab('show');
			})
		</script>

	</body>
</html>