package czj.ssh.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import czj.ssh.model.User;

public class UserDao {
	private SessionFactory sessionFactory;

	public UserDao() {
		super();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	//登录
	public User checkLogin(String userName,String password){
		User user = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String hql = "from User as u where u.userName = ? and u.password = ?";
			Query query = session.createQuery(hql);
			query.setString(0, userName);
			query.setString(1, password);
			List r = query.list();
			if(r.size() > 0){
				user = (User) r.get(0);
			}
		} catch (Exception e) {
			user = null;
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
		}
		return user;
	}
	//通过id查找用户的信息
	public  User getUser(int uid){
		Session session = null;
		User user = null;
		try {
			session = sessionFactory.openSession();
			String hql = "from User as u where u.uid = ?";
			Query query = session.createQuery(hql);
			user = (User) query.list().get(0);
		} catch (Exception e) {
			user = null;
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
		}
		return user;
	}
	//更新用户信息
	public void save(User user){
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tran = session.beginTransaction();
			session.saveOrUpdate(user);
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}		
	}
	
}
