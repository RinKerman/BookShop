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
	//����uid��bid���ҹ��ﳵ��Ŀ
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
	//���һ����Ʒ�����ﳵ
	public boolean addToShoppingCart(int uid,int bid,int num){
		Boolean flag = true;
		Session session = null;
		//�Ȳ�����û��,û�еĻ������
		Shoppingcart shoppingcart = getShoppingCartItem(uid, bid);
		if(shoppingcart == null){
			try {
//				System.out.println("���ﳵ�в����ڸ���");				
				session = sessionFactory.openSession();			
				String sql = "INSERT INTO shoppingcart(uId,bId,quantity) VALUES(" + uid + "," + bid + "," + num + ")";
				System.out.println("sql���:" + sql);
				Transaction tr = session.beginTransaction();
				int line = session.createSQLQuery(sql).executeUpdate();
				System.out.println("��Ӱ������:" + line);
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
			//������ڸ���Ŀ���ж���û�д���num�������еĻ�����������и���
			try {
//				System.out.println("���ﳵ�д��ڸ���");
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
	//�����û�id��ȡ���ﳵ����
	public List<Shoppingcart> queryBookById(int userId){
		List<Shoppingcart> shoppingCarts = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String hql = "select new Shoppingcart(s.book, s.user, s.quantity)from Shoppingcart as s where s.user.id = ?";	// 
			Query query = session.createQuery(hql);
			query.setInteger(0, userId);
			shoppingCarts = query.list();
			//��ʽ�س�ʼ��������
			//Hibernate.initialize(Book.class);
		} catch (Exception e) {
			shoppingCarts = null;
			e.printStackTrace();
		}finally{			
			session.close();
		}
		return shoppingCarts;		
	}
	//�����û�id��ͼ��idɾ�����ﳵ�е�һ��
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
