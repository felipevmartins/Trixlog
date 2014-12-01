package br.com.trixlog.dao;

import javax.enterprise.context.RequestScoped;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.trixlog.model.Location;

@RequestScoped
public class LocationDao extends GenericDao{

	public Location buscarPorId(Location location){
		Criteria criteria = session.createCriteria(Location.class);
		
		criteria.add(Restrictions.eq("id", location.getId()));
		
		return (Location) criteria.uniqueResult();
	}
	
	public Location buscarPorId(int id){
		Criteria criteria = session.createCriteria(Location.class);
		
		criteria.add(Restrictions.eq("id", id));
		
		return (Location) criteria.uniqueResult();
	}
}
