<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
      html { height: 100% }
      body { height: 100%; margin: 0; padding: 0 }
      #map_canvas { height: 100% }
    </style>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript"
      src="http://maps.googleapis.com/maps/api/js?&sensor=false">
    </script>
    <script type="text/javascript">
      function initialize() {
        var mapOptions = {
          center: new google.maps.LatLng(-3.718394300000000000, -38.543394799999990000),
          zoom: 13,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        var map = new google.maps.Map(document.getElementById("map_canvas"),
            mapOptions);
        
        var polyOptions = {
        	    strokeColor: '#000000',
        	    strokeOpacity: 1.0,
        	    strokeWeight: 3
        	  };
        	  poly = new google.maps.Polyline(polyOptions);
        	  poly.setMap(map);

        	  // Add a listener for the click event
        	  google.maps.event.addListener(map, 'click', addLatLng);
        	}

        	/**
        	 * Handles click events on a map, and adds a new point to the Polyline.
        	 * @param {google.maps.MouseEvent} event
        	 */
        	function addLatLng(event) {

        	  var path = poly.getPath();

        	  // Because path is an MVCArray, we can simply append a new coordinate
        	  // and it will automatically appear.
        	  path.push(event.latLng);

        	  // Add a new marker at the new plotted point on the polyline.
        	  var marker = new google.maps.Marker({
        	    position: event.latLng,
        	    title: '#' + path.getLength(),
        	    map: map
        	  });
        	}

        	google.maps.event.addDomListener(window, 'load', initialize);

    </script>
    
    <style type="text/css">
    .cleanbutton {
		display:scroll;
		position:fixed;
		bottom:5px;
		right:2px;
		width: 100px;
		height: 20px;			
	} 
	.searchbutton {
		display:scroll;
		position:fixed;
		bottom:5px;
		right:110px;
		width: 100px;
		height: 20px;			
	}       
    </style>
    <script>
    	$("#clean").click(function(){
    		alert("Funcionou!!!");
    	});
    	
		$("#search").click(function(){
			alert("Funcionou!!!");
    	});
    </script>    
    
    
<title>VRaptor Blank Project</title>
</head>
<body onload="initialize()">	
    <div id="map_canvas" style="width:100%; height:100%">  
    </div>
    <button class="cleanbutton" id="clean">Limpar</button>
    <button class="searchbutton" id="search">Pesquisar</button>
  </body>
</html>