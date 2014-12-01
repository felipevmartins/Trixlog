package br.com.trixlog.interceptor;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.trixlog.factory.HibernateFactory;
import br.com.trixlog.util.Transactional;

@Intercepts
public class TransactionInterceptor implements Interceptor {
	

	@Override
	public boolean accepts(ControllerMethod method) {
		return method
        .getMethod()
        .isAnnotationPresent(Transactional.class);
	}

	@Override
	public void intercept(InterceptorStack stack, ControllerMethod method,
			Object instance) throws InterceptionException {
		Transaction transaction = null;
		Session session = null;
		try {
			session = HibernateFactory.getHibernateSession();
			transaction = session.beginTransaction();
			
			stack.next(method, instance);
			transaction.commit();
		} 		
		catch (Exception e) {
			if (transaction.isActive()) {				
				transaction.rollback();
				System.out.println("deu rollback na transação para da sessão \""+session.hashCode()+"\"");
			}
			e.printStackTrace();
		}
		
	}
}