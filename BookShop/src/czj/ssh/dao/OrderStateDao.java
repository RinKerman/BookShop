package czj.ssh.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import czj.ssh.model.Orderstate;

public class OrderStateDao {
	private SessionFactory sessionFactory;

	public OrderStateDao() {
		super();
	}	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	//通过 id 获取OrderState对象
	public Orderstate get(int id){
		Orderstate os = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			os = (Orderstate) session.get(Orderstate.class, id);
		} catch (Exception e) {
			os = null;
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
		}
		return os;
	}
}
