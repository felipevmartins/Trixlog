package br.com.trixlog.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.trixlog.factory.HibernateFactory;
import br.com.trixlog.model.BaseModel;


public abstract class GenericDao {	
	
	protected final Session session;
	
    public GenericDao() {
    	this.session = HibernateFactory.getHibernateSession();
    }
    
    public void salvar(BaseModel bm){
    	session.saveOrUpdate(bm);
    }
    
    public void deletar(BaseModel bm){
    	session.delete(bm);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<BaseModel> listarTodos(Class bm){
    	return session.createQuery("from "+bm.getName()+" e").list();
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<BaseModel> listarTodosComLimite(Class bm, Integer limite){
    	return session.createQuery("from "+bm.getName()+" e").setMaxResults(limite).list();
    }
    
    @SuppressWarnings("rawtypes")
	public BaseModel selecionarUm(Class bmClass, BaseModel bm){
    	return (BaseModel)session.createQuery("from "+bmClass.getName()+" e where e.id = "+bm.getId()).uniqueResult();
    }
    
}
