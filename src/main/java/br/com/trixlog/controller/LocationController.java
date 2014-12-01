package br.com.trixlog.controller;

import java.util.Date;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.view.Results;
import br.com.trixlog.model.Location;
import br.com.trixlog.ngc.LocationNgc;
import br.com.trixlog.util.Transactional;

@Controller
public class LocationController {

	private LocationNgc locationNgc;
	private ControllerUtils utils;

	/**
	 * @deprecated CDI eyes only
	 */
	protected LocationController() {
		this(null, null);
	}
	
	@Inject
	public LocationController(ControllerUtils utils, LocationNgc locationNgc){
		this.utils = utils;
		this.locationNgc = locationNgc;
	}
	
	@Get("/location/novo")
	public void pgcadastrarlocation(){}
	
	@Transactional
	@Post("/location")
	public void cadastrarLocation(Location location){
		location.setDataCriacao(new Date());
		try {
			locationNgc.salvar(location);
			utils.redirectTo(PublicController.class).pghome();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Get
	@Transactional
	@Path("/location/listar")
	public void pglistarlocations() {
		try {
			Location locationPadrao = (Location) utils.getRequest().getAttribute("location");
			if (locationPadrao == null) {
				locationPadrao = new Location();
				locationPadrao.setLatitude((float) -3.7447435);
				locationPadrao.setLongitude((float) -38.5277421);
				
				utils.setAtributoRequest("location", locationPadrao);
				utils.setAtributoRequest("zoom", 13);
				utils.setAtributoRequest("plotar", "false");
			}
			utils.setAtributoRequest("listaLocations", locationNgc.listarTodos(Location.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Get
	@Transactional
	@Path("/location/editar")
	public void pgeditarlocations() {
		try {
			utils.setAtributoRequest("listaLocations", locationNgc.listarTodos(Location.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Get
	@Transactional
	@Path("/location/buscar/id/{id*}")
	public void getLocation(int id) {
		Location location = locationNgc.buscarPorId(id);
		
		utils.getResult().use(Results.json()).from(location, "location").serialize();
		
	}
	
	@Put
	@Transactional
	@Path("/location/editar/id/{id*}")
	public void alterarlocation(int id,Location location) {
		Location locationAlterar = locationNgc.buscarPorId(id);
		locationAlterar.setName(location.getName());
		locationAlterar.setLatitude(location.getLatitude());
		locationAlterar.setLongitude(location.getLongitude());
		try {
			locationNgc.salvar(locationAlterar);
			utils.redirectTo(LocationController.class).pgeditarlocations();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Get
	@Transactional
	@Path("/location/deletar")
	public void pgdeletarlocation(){
		try {
			utils.setAtributoRequest("listaLocations", locationNgc.listarTodos(Location.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Delete
	@Transactional
	@Path("/location/deletar/id/{id*}")
	public void deletarlocation(int id) {
		Location locationAlterar = locationNgc.buscarPorId(id);
		
		try {
			locationNgc.deletar(locationAlterar);
			utils.redirectTo(LocationController.class).pgdeletarlocation();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	@Get
	@Transactional
	@Path("/location/coordenadas/id/{id*}")
	public void coordenadas(int id){
		Location location = locationNgc.buscarPorId(id);
		
		utils.getResult().use(Results.json()).from(location, "location").serialize();
	}
	
	
}
