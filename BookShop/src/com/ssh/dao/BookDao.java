package com.ssh.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import czj.ssh.model.Book;
import czj.ssh.model.Booktype;

public class BookDao {
	SessionFactory sessionFactory;

	public BookDao() {

	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// ����ͼ�����Ͳ�ѯ��ҳ��
	public int getclassifynum(int booktype) {
		Session session = null;
		try {
			session = sessionFactory.openSession(); // �õ�session����
			String hql = "from Book as b where booktype.tid=? ";
			Query query = session.createQuery(hql);
			query.setParameter(0, booktype);
			List<Book> lists = query.list();
			int num = lists.size();
			num = num / 9 + 1;
			return num;
		} catch (Exception ex) {
			System.out.println("��ѯʧ��!");
			ex.printStackTrace();
			return 0;
		} finally { // �ر�session
			session.close();
		}
	}

	// ����ͼ��id��ѯͼ��
	public Book getbook(int id) {
		Session session = null;
		try {
			session = sessionFactory.openSession(); // �õ�session����
			String hql = "from Book where bid=? ";
			Query query = session.createQuery(hql);
			query.setParameter(0, id);
			List<Book> lists = query.list();
			for (Book book : lists) {
				return book;
			}
		} catch (Exception ex) {
			System.out.println("��ѯʧ��!");
			ex.printStackTrace();
			return null;
		} finally { // �ر�session
			session.close();
		}
		return null;
	}

	// ����ͼ��id��ȡͼ������
	public String getbooktype(int id) {
		Session session = null;
		try {
			session = sessionFactory.openSession(); // �õ�session����
			String hql = "select b.booktype.btype from Book as b where bid=? ";
			Query query = session.createQuery(hql);
			query.setParameter(0, id);
			List<String> lists = query.list();
			for (String str : lists) {
				return str;
			}
		} catch (Exception ex) {
			System.out.println("��ѯʧ��!");
			ex.printStackTrace();
			return null;
		} finally { // �ر�session
			session.close();
		}
		return null;
	}

	// ͼ�����
	public List classify(int booktype, int pageNo) {
		Session session = null;
		try {
			session = sessionFactory.openSession(); // �õ�session����
			String hql = "from Book as b where booktype.tid=? ";
			Query query = session.createQuery(hql);
			query.setParameter(0, booktype);
			query.setFirstResult((pageNo - 1) * 9);
			query.setMaxResults(9);
			List<Book> lists = query.list();
			return lists;
		} catch (Exception ex) {
			System.out.println("��ѯʧ��!");
			ex.printStackTrace();
			return null;
		} finally { // �ر�session
			session.close();
		}
	}

	// ����ͼ�����ͱ�Ų�ѯͼ������
	public String showbooktype(int id) {
		Session session = null;
		try {
			session = sessionFactory.openSession(); // �õ�session����
			String hql = "select btype from Booktype as b where tid=? ";
			Query query = session.createQuery(hql);
			query.setParameter(0, id);
			List<String> lists = query.list();
			for (String str : lists) {
				return str;
			}
		} catch (Exception ex) {
			System.out.println("��ѯʧ��!");
			ex.printStackTrace();
			return null;
		} finally { // �ر�session
			session.close();
		}
		return null;
	}

	// ͼ�����۸���
	public List classifypricedesc(int booktype, int pageNo) {
		Session session = null;
		try {
			session = sessionFactory.openSession(); // �õ�session����
			String hql = "from Book as b where booktype.tid=? order by price desc" + "";
			Query query = session.createQuery(hql);
			query.setParameter(0, booktype);
			query.setFirstResult((pageNo - 1) * 9);
			query.setMaxResults(9);
			List<Book> lists = query.list();
			return lists;
		} catch (Exception ex) {
			System.out.println("��ѯʧ��!");
			ex.printStackTrace();
			return null;
		} finally { // �ر�session
			session.close();
		}
	}

	// ͼ�����۸�����
	public List classifypriceasc(int booktype, int pageNo) {
		Session session = null;
		try {
			session = sessionFactory.openSession(); // �õ�session����
			String hql = "from Book as b where booktype.tid=? order by price asc" + "";
			Query query = session.createQuery(hql);
			query.setParameter(0, booktype);
			query.setFirstResult((pageNo - 1) * 9);
			query.setMaxResults(9);
			List<Book> lists = query.list();
			return lists;
		} catch (Exception ex) {
			System.out.println("��ѯʧ��!");
			ex.printStackTrace();
			return null;
		} finally { // �ر�session
			session.close();
		}
	}

	// ͼ�������������
	public List classifysalesAmountsort(int booktype, int pageNo) {
		Session session = null;
		try {
			session = sessionFactory.openSession(); // �õ�session����
			String hql = "from Book as b where booktype.tid=? order by salesAmount desc" + "";
			Query query = session.createQuery(hql);
			query.setParameter(0, booktype);
			query.setFirstResult((pageNo - 1) * 9);
			query.setMaxResults(9);
			List<Book> lists = query.list();
			return lists;
		} catch (Exception ex) {
			System.out.println("��ѯʧ��!");
			ex.printStackTrace();
			return null;
		} finally { // �ر�session
			session.close();
		}
	}

	// ͼ�������������
	public List classifysalesAmountasc(int booktype, int pageNo) {
		Session session = null;
		try {
			session = sessionFactory.openSession(); // �õ�session����
			String hql = "from Book as b where booktype.tid=? order by salesAmount asc" + "";
			Query query = session.createQuery(hql);
			query.setParameter(0, booktype);
			query.setFirstResult((pageNo - 1) * 9);
			query.setMaxResults(9);
			List<Book> lists = query.list();
			return lists;
		} catch (Exception ex) {
			System.out.println("��ѯʧ��!");
			ex.printStackTrace();
			return null;
		} finally { // �ر�session
			session.close();
		}
	}

	// ͼ�����۸���
	public List classifypricerange(int booktype, int pageNo, float min, float max) {
		Session session = null;
		try {
			session = sessionFactory.openSession(); // �õ�session����
			String hql = "from Book as b where booktype.tid=? and price>=? and price<=?" + "";
			Query query = session.createQuery(hql);
			query.setParameter(0, booktype);
			query.setParameter(1, min);
			query.setParameter(2, max);
			query.setFirstResult((pageNo - 1) * 9);
			query.setMaxResults(9);
			List<Book> lists = query.list();
			return lists;
		} catch (Exception ex) {
			System.out.println("��ѯʧ��!");
			ex.printStackTrace();
			return null;
		} finally { // �ر�session
			session.close();
		}
	}

	
	/*-------------------------------------------------------edit by Rin----------------------------------------------------*/
	/*--------------------------------------------------------2017/06/01----------------------------------------------------*/

	// ��ҳ��ѯ����ͼ��
	public List getBookByPage(int pageNum, int pageSize) {
		System.out.println("getBookByPage page num=" +pageNum);
		Session session = null;
		List<Book> lists = null;
		try {
			session = sessionFactory.openSession();
			String hql = "from Book where deleteFlag=0 order by bid desc";
			Query q = session.createQuery(hql);
			q.setFirstResult((pageNum - 1) * pageSize);
			q.setMaxResults(pageSize);
			lists = q.list();
			System.out.println("returning list page :" + pageNum);
			return lists;
		} catch (Exception e) {
			System.out.println("query fail");
			return null;
		} finally {
			session.close();
		}
	}

	// ��ѯͼ����ҳ��
	public int getTotalPage(int pageSize) {
		Session session = null;
		try {
			session = sessionFactory.openSession(); // �õ�session����
			String hql = "from Book where deleteFlag=0 order by bid desc";
			Query query = session.createQuery(hql);
			System.out.println(query.toString());
			List<Book> lists = query.list();
			int num = lists.size();
			System.out.println("TotalBookNum=" + num);
			System.out.println("PageSize=" + pageSize);
			num = (num / pageSize) + 1;
			System.out.println("TotalPageNum=" + num);
			return num;
		} catch (Exception ex) {
			System.out.println("TotalBookPageNum��ѯʧ��!");
			ex.printStackTrace();
			return 0;
		} finally { // �ر�session
			session.close();
		}
	}

	// ����ͼ��idɾ��ͼ��
	public boolean delBook(int id) {
		Session session = null;
		try {
			session = sessionFactory.openSession(); // �õ�session����
			String hql = "update Book b set b.deleteFlag=1 where b.bid=? ";
			Query query = session.createQuery(hql);
			query.setParameter(0, id);
			query.executeUpdate();
			System.out.println("ɾ���ɹ�!");
			return true;
		} catch (Exception ex) {
			System.out.println("��ѯʧ��!");
			ex.printStackTrace();
			return false;
		} finally { // �ر�session
			session.close();
		}

	}

	// ����ID�޸�ͼ����Ϣ
	public boolean editBook(Book book) {
		Session session = null;
		Transaction tran = null;
		try {

			session = sessionFactory.openSession();
			System.out.println("trying to update");
			tran = session.beginTransaction();
			book.setDeleteFlag(0);
			session.saveOrUpdate(book);
			tran.commit();
			return true;
		} catch (Exception ex) {
			System.out.println("�޸�ʧ��!");
			ex.printStackTrace();
			tran.rollback();
			return false;
		} finally { // �ر�session
			session.close();
		}

	}

	// ��ȡ����booktype
	public List getBookType() {
		Session session = null;
		List<Booktype> lists = null;
		try {
			session = sessionFactory.openSession();
			String hql = "from Booktype order by tid desc";
			Query q = session.createQuery(hql);
			lists = q.list();
			System.out.println(lists.size());

			return lists;
		} catch (Exception e) {
			System.out.println("query fail");
			return null;
		} finally {
			session.close();
		}
	}

	// ͨ��ID��ȡ�����
	public Booktype getBookTypeById(int id) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String hql = "from Booktype where tid = ?";
			Query query = session.createQuery(hql);
			query.setParameter(0, id);
			List<Booktype> lists = query.list();
			for (Booktype booktype : lists) {
				return booktype;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			session.close();
		}
		return null;
	}
	//ͨ��ID����ͼƬ
	public boolean updatePicture(String picture,int id){
		Session session = null;
		System.out.println("url:" +picture + " id: " + id);
		try {
			session = sessionFactory.openSession(); // �õ�session����
			String hql = "update Book b set b.picture=? where b.bid=? ";
			Query query = session.createQuery(hql);
			query.setParameter(0, picture);
			query.setParameter(1, id);
			query.executeUpdate();
			System.out.println("�޸ĳɹ�!");
			return true;
		} catch (Exception ex) {
			System.out.println("�޸�ʧ��!");
			ex.printStackTrace();
			return false;
		} finally { // �ر�session
			session.close();
		}

	}	
}
