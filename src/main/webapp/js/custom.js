$( document ).ready(function() {
	 $('.selectpicker').selectpicker();
	 
	 $('.selectpicker').selectpicker({
	      style: 'btn-info',
	      size: 4
	  });
});

function clicaalterarlocation(idLocation){
	var id = idLocation;
	$.get( "alterarlocations/id/" + id, function(data) {
		if (data != null) {
			console.log(data);
			$('#alterar-location').removeClass('hidden');
			$('#nome').val(data.location.name);
			$('#latitude').val(data.location.latitude);
			$('#longitude').val(data.location.longitude);
			console.log(data.location.name);
			$('#alterar-location-form').prop('action', "alterar/id/"+id );
		}
	});
}

function clicadeletarlocation(idLocation){
	var id = idLocation;
	$.get( "alterarlocations/id/" + id, function(data) {
		if (data != null) {
			$('#alterar-location').removeClass('hidden');
			$('#nome').val(data.location.name);
			$('#latitude').val(data.location.latitude);
			$('#longitude').val(data.location.longitude);
			 console.log(data.location.name);
			 $('#deletar-location-form').prop('action', "deletar/id/"+id );
		}
	});
}