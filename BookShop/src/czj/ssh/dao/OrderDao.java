package czj.ssh.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import czj.ssh.model.Order;

public class OrderDao {
	private SessionFactory sessionFactory;

	public OrderDao() {
		super();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	//根据id获取一个Order
	public Order getOrder(int oid){
		Session session = null;
		Order order = null;
		try {
			session = sessionFactory.openSession();
			order = (Order)session.get(Order.class, oid);
		} catch (Exception e) {
			order = null;
			e.printStackTrace();
		} finally{
			if(session != null){
				session.close();
			}
		}
		return order;		
	}
	//保存一个Order
	public void save(Order o){
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tran = session.beginTransaction();
			session.saveOrUpdate(o);
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(session != null){
				session.close();
			}
		}
	}
	
}
