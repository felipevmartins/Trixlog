package br.com.trixlog.ngc;

import javax.inject.Inject;

import br.com.trixlog.dao.TagDao;

public class TagNgc extends GenericNgc{

	private TagDao tagDao;
	
	@Inject
	public TagNgc(TagDao tagDao){
		super(tagDao);
		
		this.tagDao = tagDao;
	}
}
