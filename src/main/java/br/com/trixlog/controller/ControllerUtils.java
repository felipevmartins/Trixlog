package br.com.trixlog.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.serialization.Serializer;
import br.com.caelum.vraptor.view.Results;

public class ControllerUtils {
	
	private final HttpServletRequest req;
	
	private final HttpServletResponse resp;
	
	private final Result result;
	
	private final String api_key = "AIzaSyAdRpFIZ76cgvWR9PWNiOs9FyUwHKK4vYc";
	
	@Inject
	public ControllerUtils(HttpServletRequest req, Result result,HttpServletResponse resp){
		this.req = req;
		this.result = result;
		this.resp = resp;
	}
	
	public void setAtributoSessao(String chave, Object o){
		req.getSession().setAttribute(chave, o);
	}
	
	public Object getAtributoSessao(String chave){
		return req.getSession().getAttribute(chave);
	}
	
	public void removeAtributoSessao(String chave){
		req.getSession().removeAttribute(chave);
	}
	
	public void invalidarSessao(){
		HttpSession session = req.getSession();
		
		if (session != null)
			session.invalidate();
	}
	
	public void forwardTo(String foward){
		result.forwardTo(foward);
	}
	
	public <T> T forwardTo(Class<T> arg0){
		return result.forwardTo(arg0);
	}
	
	public void forwardMessage(String msg){
		setAtributoRequest("msg", msg);
		forwardTo("/mensagem.jsp");
	}
	
	public void redirectTo(String redirect){
		result.redirectTo(redirect);
	}
	
	public <T> T redirectTo(Class<T> arg0){
		return result.redirectTo(arg0);
	}
	
	public void setAtributoRequest(String key, Object value){
		result.include(key,value);
	}
	
	public Serializer gerarJson(Object object, String nomeRoot){
		return result.use(Results.json()).from(object,nomeRoot);
	}
	
	public Serializer gerarXml(Object object, String nomeRoot){
		return result.use(Results.xml()).from(object, nomeRoot);
	}
	
	public HttpServletRequest getRequest(){
		return req;
	}
	
	public HttpServletResponse getResponse(){
		return resp;
	}
	
	public Result getResult(){
		return result;
	}

	public String getApi_key() {
		return api_key;
	}
	
}
