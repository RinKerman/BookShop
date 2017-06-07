package czj.ssh.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.mysql.jdbc.Connection;

import czj.ssh.model.Shoppingcart;

public class ShoppingCartDao {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	//根据uid和bid查找购物车条目
	public Shoppingcart getShoppingCartItem(int uid,int bid){
		Shoppingcart shoppingcart = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String hql = "from Shoppingcart as s where s.user.uid = ? and s.book.bid = ?";
			Query query = session.createQuery(hql);
			query.setInteger(0, uid);
			query.setInteger(1, bid);
			List r = query.list();
			if(r.size() > 0){
				shoppingcart = (Shoppingcart) r.get(0);
			}
		} catch (Exception e) {
			shoppingcart = null;
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
		}
		return shoppingcart;
	}
	//添加一个物品到购物车
	public boolean addToShoppingCart(int uid,int bid,int num){
		Boolean flag = true;
		Session session = null;
		//先查找有没有,没有的话才添加
		Shoppingcart shoppingcart = getShoppingCartItem(uid, bid);
		if(shoppingcart == null){
			try {
//				System.out.println("购物车中不存在该项");				
				session = sessionFactory.openSession();			
				String sql = "INSERT INTO shoppingcart(uId,bId,quantity) VALUES(" + uid + "," + bid + "," + num + ")";
				System.out.println("sql语句:" + sql);
				Transaction tr = session.beginTransaction();
				int line = session.createSQLQuery(sql).executeUpdate();
				System.out.println("受影响行数:" + line);
				tr.commit();
			} catch (Exception e) {
				flag = false;
				e.printStackTrace();
			} finally{
				if(session != null){
					session.close();
				}
			}
		}else{
			//如果存在该条目，判断有没有传递num过来，有的话则对数量进行更新
			try {
//				System.out.println("购物车中存在该项");
				session = sessionFactory.openSession();	
				Transaction tran = session.beginTransaction();
				num = shoppingcart.getQuantity() + num;
				shoppingcart.setQuantity(num);
				session.saveOrUpdate(shoppingcart);
				tran.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(session != null){
					session.close();
				}
			}
			
		}
		return flag;
	}
	//根据用户id获取购物车内容
	public List<Shoppingcart> queryBookById(int userId){
		List<Shoppingcart> shoppingCarts = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String hql = "select new Shoppingcart(s.book, s.user, s.quantity)from Shoppingcart as s where s.user.id = ?";	// 
			Query query = session.createQuery(hql);
			query.setInteger(0, userId);
			shoppingCarts = query.list();
			//显式地初始化懒加载
			//Hibernate.initialize(Book.class);
		} catch (Exception e) {
			shoppingCarts = null;
			e.printStackTrace();
		}finally{			
			session.close();
		}
		return shoppingCarts;		
	}
	//根据用户id和图书id删除购物车中的一项
	public boolean deleteItem(int userId,int bookId){
		boolean flag = false;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction trans = session.beginTransaction();
			String hql = "delete from Shoppingcart as s where s.user.id = ? and s.book.bid = ?";
			Query query = session.createQuery(hql);
			query.setInteger(0, userId);
			query.setInteger(1, bookId);
			int r = query.executeUpdate();
			trans.commit();
			if(r > 0){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			if(session != null)
				session.close();
		}
		return flag;
	}
}
