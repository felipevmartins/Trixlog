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
	
}
