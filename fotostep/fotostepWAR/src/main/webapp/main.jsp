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
        <style>
            body {
                padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
            }

            .carousel-caption h1,
            .carousel-caption .lead {
                margin: 0;
                line-height: 1.25;
                color: black;
                text-shadow: 0 1px 1px rgba(0,0,0,.4);
            }

            .carousel-caption .btn{
                margin-top : 15px;
            }
            .carousel-caption {
                background: -moz-linear-gradient(top, rgba(255,255,255,1) 0%, rgba(255,255,255,0) 100%); /* FF3.6+ */
                background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,255,255,1)), color-stop(100%,rgba(255,255,255,0))); /* Chrome,Safari4+ */
                background: -webkit-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(255,255,255,0) 100%); /* Chrome10+,Safari5.1+ */
                background: -o-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(255,255,255,0) 100%); /* Opera 11.10+ */
                background: -ms-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(255,255,255,0) 100%); /* IE10+ */
                background: linear-gradient(to bottom, rgba(255,255,255,1) 0%,rgba(255,255,255,0) 100%); /* W3C */
                filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffffff', endColorstr='#00ffffff',GradientType=0 ); /* IE6-9 */
            }
        </style>
        <script src="assets/js/jquery.js"></script>
        <script src="assets/js/bootstrap.js"></script>
        <script src = "assets/js/bootstrap-carousel.js"></script>

    </head>
    <!-- /HEAD -->

    <!-- BODY -->
    <t:documentBody>

        <!-- Menu -->
        <%@include file="templates/header.jsp"%>
        <!-- /Menu -->

        <div id="myCarousel" class="carousel slide">
            <!-- Carousel items -->
            <div class="carousel-inner">
                <div class="active item">
                    <img src="assets/img/rock.jpg" alt="">
                    <div class="container">
                        <div class="carousel-caption">
                            <h1>Bienvenue sur Fotostep</h1>
                            <p class="lead">Venez partager votre vision du monde</p>
                            <a class="btn btn-large btn-primary" href="register.jsf">Inscrivez vous !</a>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <img src="assets/img/church.jpg" alt="">
                    <div class="container">
                        <div class="carousel-caption">
                            <h1>Partagez vos photos...</h1>
                            <p class="lead">... et indiquez où elles ont été prises grâce à notre outil de géolocalisation</p>
                            <a class="btn btn-large btn-primary" href="register.jsf">Inscrivez vous !</a>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <img src="assets/img/alatna.jpg" alt="">
                    <div class="container">
                        <div class="carousel-caption">
                            <h1>Retrouvez vos amis !</h1>
                            <p class="lead">Parce qu'il est plus plaisant de partager avec nos proches, entrez en contact avec eux sur Fotostep et partagez vos plus beaux clichés !</p>
                            <a class="btn btn-large btn-primary" href="register.jsf">Inscrivez vous !</a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Carousel nav -->
            <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
            <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
        </div>

        <div class="row">

            <div class="span4">
                <h2>Inscrivez-vous</h2>
                <p>Cliquez sur le bouton Inscription, remplissez le formulaire en quelques minutes et rejoignez-nous !</p>
            </div><!-- /.span4 -->
            <div class="span4">
                <h2>Retrouvez vos amis</h2>
                <p>Retrouvez vos connaissances sur Fotostep ou faites en de nouvelles et partagez vos photos avec eux.</p>
            </div><!-- /.span4 -->
            <div class="span4">
                <h2>Partagez vos photos</h2>
                <p>Créez vos albums, décidez avec qui les partager puis ajoutez-y vos photos et localisez les pour les diffuser de manière originale</p>
            </div><!-- /.span4 -->
        </div><!-- /.row -->
        <hr/>
        <center><img src = "assets/img/header-logo.png"></center>

        <%@ include file = "templates/footer.jsp" %>
    </t:documentBody>
    <!-- /BODY -->
</f:view>
</html>   <!-- /HTML -->
