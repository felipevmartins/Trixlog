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
public class TagController {

	private LocationNgc locationNgc;
	private TagNgc tagNgc;
	private ControllerUtils utils;

	/**
	 * @deprecated CDI eyes only
	 */
	protected TagController() {
		this(null, null, null);
	}
	
	@Inject
	public TagController(ControllerUtils utils, LocationNgc locationNgc, TagNgc tagNgc){
		this.utils = utils;
		this.locationNgc = locationNgc;
		this.tagNgc = tagNgc;
	}
	
	
	@Transactional
	@Get("/tag/novo")
	public void pgcadastrartag(){
		try {
			System.out.println(locationNgc.listarTodos(Location.class).size());
			utils.setAtributoRequest("listaLocations", locationNgc.listarTodos(Location.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Transactional
	@Get("/tag/id/{id*}")
	public void tags(int id){
		Location location = locationNgc.buscarPorId(id);
		
		utils.getResult().use(Results.json()).from(location.getTags(), "tags").serialize();
	}	
	
	
	
	@Transactional
	@Post("/tag/cadastrar")
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
