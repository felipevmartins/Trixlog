package br.com.trixlog.ngc;

import java.util.List;

import br.com.trixlog.dao.GenericDao;
import br.com.trixlog.model.BaseModel;

public abstract class GenericNgc{	
	
	private final GenericDao genericDao;
	
    public GenericNgc(GenericDao genericDao) {
    	this.genericDao = genericDao;
    }
    
    public void salvar(BaseModel bm)throws Exception{
    	genericDao.salvar(bm);
    }
    
    public void deletar(BaseModel bm)throws Exception{
    	genericDao.deletar(bm);
    }
      
	@SuppressWarnings("rawtypes")
	public List<BaseModel> listarTodos(Class bm)throws Exception{
    	return genericDao.listarTodos(bm);
    }
      
	@SuppressWarnings("rawtypes")
	public List<BaseModel> listarTodosComLimite(Class bm, Integer limite)throws Exception{
    	return genericDao.listarTodosComLimite(bm, limite);
    }
    
    @SuppressWarnings("rawtypes")
	public BaseModel selecionarUm(Class bmClass, BaseModel bm)throws Exception{    	
    	return genericDao.selecionarUm(bmClass,bm);
    }
    
}
