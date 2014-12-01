var latLng;
var marker;
var ende;
var idLocation;
var tags = "";

function initialize(lat, lon, z, plotar) {
	tag = " ";
	var geocoder = new google.maps.Geocoder();

	var mapOptions = {
		center : new google.maps.LatLng(lat, lon),
		zoom : z,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	var map = new google.maps.Map(document.getElementById("map_canvas"),
			mapOptions);
	latLng = new google.maps.LatLng(lat, lon);

	if (plotar) {
		marker = new google.maps.Marker({
			position : latLng,
			title : '' + latLng,
			map : map,
		});

		google.maps.event.addDomListener(marker, 'click', function() {
			var infowindow = new google.maps.InfoWindow();

			geocoder.geocode({
				'latLng' : latLng
			}, function(results, status) {
				if (status == google.maps.GeocoderStatus.OK) {
					if (results[1]) {
						infowindow.setContent("<div>"+results[0].formatted_address+"</div><br><br>"+tags);
					} else {
						alert('No results found');
					}
				} else {
					alert('Geocoder failed due to: ' + status);
				}
			});
			infowindow.open(map, marker);
		});
	}
}


function clicaItemLista(id) {
	var jqxhr = $.get( "coordenadas/id/" + id, function(data) {
		if (data != null) {
			var coordenadas= {"lat": data.location.latitude, "lon": data.location.longitude};
			var mapa = coordenadas.map;
			var latitude = data.location.latitude;
			var longitude = data.location.longitude;
			if (latitude != null && longitude != null) {
				initialize(latitude, longitude, 18, true);
			}
		}
		 $.get( "tags/id/" + id, function(data2) {
			 for (i = 0; i < data2.tags.length; i++) { 
				    tags += "<div>"+data2.tags[i].name + "</div><br>";
				}
		});
	});
	
}
