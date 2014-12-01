package br.com.trixlog.controller;

import java.util.Date;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.view.Results;
import br.com.trixlog.model.Location;
import br.com.trixlog.model.Tag;
import br.com.trixlog.ngc.LocationNgc;
import br.com.trixlog.ngc.TagNgc;
import br.com.trixlog.util.Transactional;

@Controller
@Path("/public")
public class PublicController {
	
	private LocationNgc locationNgc;
	private TagNgc tagNgc;
	private ControllerUtils utils;

	/**
	 * @deprecated CDI eyes only
	 */
	protected PublicController() {
		this(null, null, null);
	}
	
	@Inject
	public PublicController(ControllerUtils utils, LocationNgc locationNgc, TagNgc tagNgc){
		this.utils = utils;
		this.locationNgc = locationNgc;
		this.tagNgc = tagNgc;
	}
	
	@Path("/pghome")
	public void pghome(){}
	
	@Get
	@Path("/pgcadastrarlocation")
	public void pgcadastrarlocation(){}
	
	@Get
	@Transactional
	@Path("/pgdeletarlocations")
	public void pgdeletarlocation(){
		try {
			utils.setAtributoRequest("listaLocations", locationNgc.listarTodos(Location.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Transactional
	@Post
	@Path("/funcao/cadastrarlocation")
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
//	
	@Get
	@Transactional
	@Path("/pglistarlocations")
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
//	
	@Get
	@Transactional
	@Path("/coordenadas/id/{id*}")
	public void coordenadas(int id){
		Location location = locationNgc.buscarPorId(id);
		
		utils.getResult().use(Results.json()).from(location, "location").serialize();
	}
	
	@Get
	@Transactional
	@Path("/tags/id/{id*}")
	public void tags(int id){
		Location location = locationNgc.buscarPorId(id);
		
		utils.getResult().use(Results.json()).from(location.getTags(), "tags").serialize();
	}
	
	@Get
	@Transactional
	@Path("/pgalterarlocations")
	public void pgalterarlocations() {
		try {
			utils.setAtributoRequest("listaLocations", locationNgc.listarTodos(Location.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Get
	@Transactional
	@Path("/alterarlocations/id/{id*}")
	public void getLocation(int id) {
		Location location = locationNgc.buscarPorId(id);
		
		utils.getResult().use(Results.json()).from(location, "location").serialize();
		
	}
	
	@Post
	@Transactional
	@Path("/alterar/id/{id*}")
	public void alterarlocation(int id,Location location) {
		Location locationAlterar = locationNgc.buscarPorId(id);
		
		locationAlterar.setName(location.getName());
		locationAlterar.setLatitude(location.getLatitude());
		locationAlterar.setLongitude(location.getLongitude());
		try {
			locationNgc.salvar(locationAlterar);
			utils.redirectTo(PublicController.class).pgalterarlocations();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Post
	@Transactional
	@Path("/deletar/id/{id*}")
	public void deletarlocation(int id) {
		Location locationAlterar = locationNgc.buscarPorId(id);
		
		try {
			locationNgc.deletar(locationAlterar);
			utils.redirectTo(PublicController.class).pgdeletarlocation();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Get
	@Transactional
	@Path("/pgcadastrartag")
	public void pgcadastrartag(){
		try {
			System.out.println(locationNgc.listarTodos(Location.class).size());
			utils.setAtributoRequest("listaLocations", locationNgc.listarTodos(Location.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Post
	@Transactional
	@Path("/funcao/cadastrartag")
	public void cadastrarTag(int idlocation, Tag tag) {
		Location location = locationNgc.buscarPorId(idlocation);
		tag.setDataCriacao(new Date());
		tag.setLocation(location);
		try {
			tagNgc.salvar(tag);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
			utils.redirectTo(PublicController.class).pghome();
	}
}
