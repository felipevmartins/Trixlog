package br.com.trixlog.ngc;

import javax.inject.Inject;

import br.com.trixlog.dao.LocationDao;
import br.com.trixlog.model.Location;


public class LocationNgc extends GenericNgc{

	private LocationDao locationDao;

	
	@Inject
	public LocationNgc(LocationDao locationDao){
		super(locationDao);
		
		this.locationDao = locationDao;
	}
	
	public Location buscarPorId(Location location){
		return locationDao.buscarPorId(location);
	}
	public Location buscarPorId(int id){
		return locationDao.buscarPorId(id);
	}
}
