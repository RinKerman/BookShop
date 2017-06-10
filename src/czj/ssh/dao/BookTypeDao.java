package czj.ssh.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import czj.ssh.model.Booktype;

public class BookTypeDao {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	//��ѯ���е�ͼ������
	public List<Booktype> getAllBookType(){
		List<Booktype> result = new ArrayList<Booktype>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String hql = "from Booktype";
			Query query = session.createQuery(hql);
			result = query.list();
		} catch (Exception e) {
			result = null;
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
		}
		return result;
	}	
	//��ҳ��ѯ�ķ���		<!-- nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnew -->
		public List<Booktype> queryByPage(int currentPage,int pageSize){
			List<Booktype> orders = null;
			try {
				Session session = sessionFactory.openSession();
				Query query = session.createQuery("from Booktype");
				query.setFirstResult((currentPage - 1) * pageSize);
				query.setMaxResults(pageSize);
				orders = query.list();
				session.close();
			} catch (HibernateException e) {
				e.printStackTrace();
			}
			return orders;		
		}
	
	//����id��ȡͼ������
	public Booktype get(int id){
		Booktype type = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String hql = "from Booktype as b where b.tid = ?";
			Query query = session.createQuery(hql);
			query.setInteger(0, id);
			List<Booktype> r = query.list();
			if(r.size() > 0){
				type = r.get(0);
			}
		} catch (Exception e) {
			type = null;
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
		}
		return type;
	}
	//������޸�ͼ������
	public void save(Booktype bt){
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction trans = session.beginTransaction();
			session.saveOrUpdate(bt);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(session != null){
				session.close();
			}
		}		
	}
	//����idɾ��ͼ�����ͣ�����ɾ������ɾ�����������ͼ�飩
	public boolean delete(int id){
		boolean flag = false;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction trans = session.beginTransaction();
			String hql = "delete from Booktype as b where b.tid = ?";
			Query query = session.createQuery(hql);
			query.setInteger(0, id);
			query.executeUpdate();
			int r = query.executeUpdate();
			trans.commit();
			if(r > 0){
				flag = true;
			}
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		} finally{
			if(session != null){
				session.close();
			}
		}
		return flag;				
	}	
}
