<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>

<f:subview id="view-profile-content-map">
    <div id="map" style="width: 100%; height: 400px"></div>
    <script src="http://cdn.leafletjs.com/leaflet-0.4/leaflet.js"></script>
    <script>

        // Récupére les pictures au format JSON + parsage
        var picJsonStr = '<h:outputText value="#{userProfileData.jsonPictures}"></h:outputText>';
        var picsList = $.parseJSON(picJsonStr);

        var map = L.map('map');

        // Pour chaque image : création du marker + calcul de la moyenne des lat et long
        var params = extractUrlParams();
        var moyLat = 0.0 , moyLgt = 0.0;
        var size = 0;

        console.log("Min Lat : " + getMinElement(picsList, 'lat'));
        console.log("Max lgt : " + getMaxOfElement(picsList, 'lgt'));

        var southWest = null , northEast = null;
        if(picsList.length > 1 )
        {
            southWest = new L.LatLng(getMinElement(picsList, 'lat'), getMaxOfElement(picsList, 'lgt'));
            northEast = new L.LatLng(getMaxOfElement(picsList, 'lat'), getMinElement(picsList, 'lgt'));
        }

        var picMarker;
        $.each(picsList, function() {
            console.log(this["id"]);
            var lgt = this["lgt"];
            var lat = this["lat"];
            var picId = this["id"];
            var albId = this["idalb"];

            if(lgt != 0.0 && lat != 0.0 && picId !=null)
            {
                size++;
                moyLgt += lgt;
                moyLat += lat;
                var viewaddr = "view-photo.jsf?UserId=" + params['UserId'] + "&AlbumId=" + albId +"&PictureId=" +picId;
                picMarker = L.marker([lat, lgt])
                        .addTo(map)
                        .bindPopup('<a href="'+viewaddr+'"><img src="/fotostep/images?UserId='+params['UserId']+'&PictureId='+picId+'&Thumb=profileMinType"/></a>');
            }
        });

        if(size != 0)
        {
            moyLat = moyLat / size;
            moyLgt = moyLgt / size;
        }

        // Création de la map + initialisation
        map.setView([moyLat, moyLgt], 5);

        if(southWest != null && northEast != null)
        {
            zoombounds = new L.LatLngBounds(southWest, northEast);
            map.fitBounds(zoombounds);
            map.zoomOut(3);
        }
        else
        {
            if(picsList.length == 0)
            {
                map.fitWorld();
            }

        }

        L.tileLayer('http://{s}.tile.cloudmade.com/BC9A493B41014CAABB98F0471D759707/997/256/{z}/{x}/{y}.png', {
            maxZoom: 18,
            attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://cloudmade.com">CloudMade</a>'
        }).addTo(map);



        function extractUrlParams(){
            var t = location.search.substring(1).split('&');
            var f = [];
            for (var i=0; i<t.length; i++){
                var x = t[ i ].split('=');
                f[x[0]]=x[1];
            }
            return f;
        }

        function getMaxOfElement(array, property)
        {
            var max = array[0][property];
            $.each(picsList, function() {
                if(this[property] > max)
                {
                    max = this[property];
                }
            });

            return max;
        }

        function getMinElement(array, property)
        {
            var min = array[0][property];
            $.each(picsList, function() {
                if(this[property] < min)
                {
                    min = this[property];
                }
            });

            return min;
        }


    </script>
</f:subview>