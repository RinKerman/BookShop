package czj.ssh.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import czj.ssh.model.Book;

public class BookDao {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	//按照添加日期查找图书
	public List<Book> queryBookByDate(){
		List<Book> list = null;
		Session session = null;
		try {
			list = new ArrayList();
			session = sessionFactory.openSession();
			String sql = "select * from book order by addDate desc limit 8";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			list = sqlQuery.addEntity(Book.class).list();
			if(list.size() == 0){
				return null;
			}
		} catch (Exception e) {
			list = null;
			e.printStackTrace();
		} finally{
			if(session != null){
				session.close();
			}
		}
		return list;
	}
	//按照销量查找图书
	public List<Book> queryBookBySaleAmount(){
		List<Book> list = new ArrayList();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String sql = "select * from book order by SalesAmount desc limit 8";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			list = sqlQuery.addEntity(Book.class).list();
			if(list.size() == 0){
				return null;
			}
		} catch (Exception e) {
			list = null;
			e.printStackTrace();
		} finally{
			if(session != null){
				session.close();
			}
		}
		return list;
	}
	//推荐图书
	public List<Book> queryBookByRandom(){
		List<Book> list = new ArrayList();
		Session session = null;
		try {
			session = sessionFactory.openSession();
//			String sql = "select * from book order by rand() desc limit 5";
//			SQLQuery sqlQuery = session.createSQLQuery(sql);
//			list = sqlQuery.addEntity(Book.class).list();
			String hql = "select book from Recommend";
			Query query = session.createQuery(hql);
			list = query.list();
			if(list.size() == 0){
				return null;
			}
		} catch (Exception e) {
			list = null;
			e.printStackTrace();
		} finally{
			if(session != null){
				session.close();
			}
		}
		return list;
	}
	
	//更新图书信息
	public void save(Book book){
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tran = session.beginTransaction();
			session.saveOrUpdate(book);
			tran.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(session != null){
				session.close();
			}
		}
	}
	
	//根据关键字进行模糊查询
	public List<Book> fuzzyLookup(String keyword,String type){
		List<Book> result = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String hql = "from Book b where b." + type + " like '%" + keyword + "%'";
			System.out.println(hql);
			Query query = session.createQuery(hql);
			result = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
		}
		return result;
	}
	//根据价格查询图书
	public List<Book> queryBookByPrice(int min,int max){
		List<Book> result = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String hql = "from Book b where b.price between ? and ?";
			Query query = session.createQuery(hql);
			query.setInteger(0, min);
			query.setInteger(1, max);
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
}
