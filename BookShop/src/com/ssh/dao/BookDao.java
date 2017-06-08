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

	// 数据图书类型查询总页数
	public int getclassifynum(int booktype) {
		Session session = null;
		try {
			session = sessionFactory.openSession(); // 得到session对象
			String hql = "from Book as b where booktype.tid=? ";
			Query query = session.createQuery(hql);
			query.setParameter(0, booktype);
			List<Book> lists = query.list();
			int num = lists.size();
			num = num / 9 + 1;
			return num;
		} catch (Exception ex) {
			System.out.println("查询失败!");
			ex.printStackTrace();
			return 0;
		} finally { // 关闭session
			session.close();
		}
	}

	// 根据图书id查询图书
	public Book getbook(int id) {
		Session session = null;
		try {
			session = sessionFactory.openSession(); // 得到session对象
			String hql = "from Book where bid=? ";
			Query query = session.createQuery(hql);
			query.setParameter(0, id);
			List<Book> lists = query.list();
			for (Book book : lists) {
				return book;
			}
		} catch (Exception ex) {
			System.out.println("查询失败!");
			ex.printStackTrace();
			return null;
		} finally { // 关闭session
			session.close();
		}
		return null;
	}

	// 根据图书id获取图书类型
	public String getbooktype(int id) {
		Session session = null;
		try {
			session = sessionFactory.openSession(); // 得到session对象
			String hql = "select b.booktype.btype from Book as b where bid=? ";
			Query query = session.createQuery(hql);
			query.setParameter(0, id);
			List<String> lists = query.list();
			for (String str : lists) {
				return str;
			}
		} catch (Exception ex) {
			System.out.println("查询失败!");
			ex.printStackTrace();
			return null;
		} finally { // 关闭session
			session.close();
		}
		return null;
	}

	// 图书分类
	public List classify(int booktype, int pageNo) {
		Session session = null;
		try {
			session = sessionFactory.openSession(); // 得到session对象
			String hql = "from Book as b where booktype.tid=? ";
			Query query = session.createQuery(hql);
			query.setParameter(0, booktype);
			query.setFirstResult((pageNo - 1) * 9);
			query.setMaxResults(9);
			List<Book> lists = query.list();
			return lists;
		} catch (Exception ex) {
			System.out.println("查询失败!");
			ex.printStackTrace();
			return null;
		} finally { // 关闭session
			session.close();
		}
	}

	// 根据图书类型编号查询图书类型
	public String showbooktype(int id) {
		Session session = null;
		try {
			session = sessionFactory.openSession(); // 得到session对象
			String hql = "select btype from Booktype as b where tid=? ";
			Query query = session.createQuery(hql);
			query.setParameter(0, id);
			List<String> lists = query.list();
			for (String str : lists) {
				return str;
			}
		} catch (Exception ex) {
			System.out.println("查询失败!");
			ex.printStackTrace();
			return null;
		} finally { // 关闭session
			session.close();
		}
		return null;
	}

	// 图书分类价格降序
	public List classifypricedesc(int booktype, int pageNo) {
		Session session = null;
		try {
			session = sessionFactory.openSession(); // 得到session对象
			String hql = "from Book as b where booktype.tid=? order by price desc" + "";
			Query query = session.createQuery(hql);
			query.setParameter(0, booktype);
			query.setFirstResult((pageNo - 1) * 9);
			query.setMaxResults(9);
			List<Book> lists = query.list();
			return lists;
		} catch (Exception ex) {
			System.out.println("查询失败!");
			ex.printStackTrace();
			return null;
		} finally { // 关闭session
			session.close();
		}
	}

	// 图书分类价格升序
	public List classifypriceasc(int booktype, int pageNo) {
		Session session = null;
		try {
			session = sessionFactory.openSession(); // 得到session对象
			String hql = "from Book as b where booktype.tid=? order by price asc" + "";
			Query query = session.createQuery(hql);
			query.setParameter(0, booktype);
			query.setFirstResult((pageNo - 1) * 9);
			query.setMaxResults(9);
			List<Book> lists = query.list();
			return lists;
		} catch (Exception ex) {
			System.out.println("查询失败!");
			ex.printStackTrace();
			return null;
		} finally { // 关闭session
			session.close();
		}
	}

	// 图书分类销量降序
	public List classifysalesAmountsort(int booktype, int pageNo) {
		Session session = null;
		try {
			session = sessionFactory.openSession(); // 得到session对象
			String hql = "from Book as b where booktype.tid=? order by salesAmount desc" + "";
			Query query = session.createQuery(hql);
			query.setParameter(0, booktype);
			query.setFirstResult((pageNo - 1) * 9);
			query.setMaxResults(9);
			List<Book> lists = query.list();
			return lists;
		} catch (Exception ex) {
			System.out.println("查询失败!");
			ex.printStackTrace();
			return null;
		} finally { // 关闭session
			session.close();
		}
	}

	// 图书分类销量升序
	public List classifysalesAmountasc(int booktype, int pageNo) {
		Session session = null;
		try {
			session = sessionFactory.openSession(); // 得到session对象
			String hql = "from Book as b where booktype.tid=? order by salesAmount asc" + "";
			Query query = session.createQuery(hql);
			query.setParameter(0, booktype);
			query.setFirstResult((pageNo - 1) * 9);
			query.setMaxResults(9);
			List<Book> lists = query.list();
			return lists;
		} catch (Exception ex) {
			System.out.println("查询失败!");
			ex.printStackTrace();
			return null;
		} finally { // 关闭session
			session.close();
		}
	}

	// 图书分类价格降序
	public List classifypricerange(int booktype, int pageNo, float min, float max) {
		Session session = null;
		try {
			session = sessionFactory.openSession(); // 得到session对象
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
			System.out.println("查询失败!");
			ex.printStackTrace();
			return null;
		} finally { // 关闭session
			session.close();
		}
	}

	
	/*-------------------------------------------------------edit by Rin----------------------------------------------------*/
	/*--------------------------------------------------------2017/06/01----------------------------------------------------*/

	// 分页查询所有图书
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

	// 查询图书总页数
	public int getTotalPage(int pageSize) {
		Session session = null;
		try {
			session = sessionFactory.openSession(); // 得到session对象
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
			System.out.println("TotalBookPageNum查询失败!");
			ex.printStackTrace();
			return 0;
		} finally { // 关闭session
			session.close();
		}
	}

	// 根据图书id删除图书
	public boolean delBook(int id) {
		Session session = null;
		try {
			session = sessionFactory.openSession(); // 得到session对象
			String hql = "update Book b set b.deleteFlag=1 where b.bid=? ";
			Query query = session.createQuery(hql);
			query.setParameter(0, id);
			query.executeUpdate();
			System.out.println("删除成功!");
			return true;
		} catch (Exception ex) {
			System.out.println("查询失败!");
			ex.printStackTrace();
			return false;
		} finally { // 关闭session
			session.close();
		}

	}

	// 根据ID修改图书信息
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
			System.out.println("修改失败!");
			ex.printStackTrace();
			tran.rollback();
			return false;
		} finally { // 关闭session
			session.close();
		}

	}

	// 获取所有booktype
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

	// 通过ID获取书类别
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
	//通过ID更改图片
	public boolean updatePicture(String picture,int id){
		Session session = null;
		System.out.println("url:" +picture + " id: " + id);
		try {
			session = sessionFactory.openSession(); // 得到session对象
			String hql = "update Book b set b.picture=? where b.bid=? ";
			Query query = session.createQuery(hql);
			query.setParameter(0, picture);
			query.setParameter(1, id);
			query.executeUpdate();
			System.out.println("修改成功!");
			return true;
		} catch (Exception ex) {
			System.out.println("修改失败!");
			ex.printStackTrace();
			return false;
		} finally { // 关闭session
			session.close();
		}

	}	
}
