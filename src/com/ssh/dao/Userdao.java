package com.ssh.dao;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import czj.ssh.model.Address;
import czj.ssh.model.Booktype;
import czj.ssh.model.Myorder;
import czj.ssh.model.User;
import czj.ssh.model.Usertype;


public class Userdao {
	SessionFactory sessionFactory;
	public Userdao(){
		
	}
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	//注册方法
	public boolean registerUser(User user){
		int num=0;
		Session session=null;
		try{
			session=sessionFactory.openSession();
			Transaction trans=session.beginTransaction();
			session.save(user);
			trans.commit();
			return true;
		}catch(Exception ex){
			System.out.println("注册失败!");
			ex.printStackTrace();
			return false;
		}finally{ //关闭session
			session.close();
		}
	}
	public boolean checkname(String name){
		Session session=null;
		try{
			session=sessionFactory.openSession(); //得到session对象
			String hql="from User where userName=? ";
			Query query=session.createQuery(hql);
			query.setParameter(0, name);

			List list=query.list();
			if(list.size()>0){
				return true;
			}else{
				return false;
			}
		}catch(Exception ex){
			System.out.println("登录失败!");
			ex.printStackTrace();
			return false;
		}finally{ //关闭session
			session.close();
		}
	}
	
	public boolean login(User user){
		Session session=null;
		try{
			session=sessionFactory.openSession(); //得到session对象
			String hql="from User where userName=? and password=?";
			Query query=session.createQuery(hql);
			query.setParameter(0, user.getUserName());
			query.setParameter(1, user.getPassword());
			List list=query.list();
			if(list.size()>0){
				return true;
			}else{
				return false;
			}
		}catch(Exception ex){
			System.out.println("登录失败!");
			ex.printStackTrace();
			return false;
		}finally{ //关闭session
			session.close();
		}
	}
	
	
	//获取user对象
	public User getuser(String name){
		Session session=null;
		try{
			session=sessionFactory.openSession(); //得到session对象
			String hql="from User where userName=? ";
			Query query=session.createQuery(hql);
			query.setParameter(0, name);
			List<User> lists=query.list();
			for(User user:lists){  
				return user;
		    }  
		}catch(Exception ex){
			System.out.println("登录失败!");
			ex.printStackTrace();
			return null;
		}finally{ //关闭session
			session.close();
		}
		return null;
	}
	
	//修改个人信息
	public void updateuser(User user,int id){
		Session session=null;
		try{
			session=sessionFactory.openSession(); //得到session对象
			String hql="update User t "
					+ "set t.userName = ? , t.email=? , t.sex=? "
					+ ", t.birthday=? , t.name=? where uId = ?";
			Query query=session.createQuery(hql);
			query.setParameter(0, user.getUserName());
			query.setParameter(1, user.getEmail());
			query.setParameter(2, user.getSex());
			query.setParameter(3, user.getBirthday());
			query.setParameter(4, user.getName());
			query.setParameter(5, id);
			query.executeUpdate(); 
			 
		}catch(Exception ex){
			System.out.println("更新失败!");
			ex.printStackTrace();
		}finally{ //关闭session
			session.close();
		}
	}
	
	//查询地址
	public List reachaddress(int id){
		List addresslist=new ArrayList<>();
		Session session=null;
		try{
			session=sessionFactory.openSession(); //得到session对象
			String hql="from Address where user.uid=? ";
			Query query=session.createQuery(hql);
			query.setParameter(0, id);
			List<Address> lists=query.list();
			for(Address address:lists){  
				System.out.println(address.getAddress());
				addresslist.add(address);
		    }  
			return addresslist;
		}catch(Exception ex){
			System.out.println("登录失败!");
			ex.printStackTrace();
			return null;
		}finally{ //关闭session
			session.close();
		}
		
	}


	public void addaddress(String address, String email,User mubiao) {
		// TODO Auto-generated method stub
		
		Session session=null;
		try{
			session=sessionFactory.openSession();
			Transaction trans=session.beginTransaction();
			Address a=new Address();
			a.setAddress(address);
			a.setZipCode(email);
			a.setUser(mubiao);
			session.save(a);
			trans.commit();
		}catch(Exception ex){
			System.out.println("注册失败!");
			ex.printStackTrace();
		}finally{ //关闭session
			session.close();
		}
	}
	
	public void revisepwd(User user,String pwd){
		Session session=null;
		try{
			session=sessionFactory.openSession(); //得到session对象
			String hql="update User t "
					+ "set t.password = ? where uId = ?";
			Query query=session.createQuery(hql);
			query.setParameter(0, pwd);
			query.setParameter(1, user.getUid());
			query.executeUpdate(); 
			System.out.println("dd");
		}catch(Exception ex){
			System.out.println("更新失败!");
			ex.printStackTrace();
		}finally{ //关闭session
			session.close();
		}
	}
	
	//查询个人所有订单
	public List showorder(int id, int pageNo){
		Session session=null;
		List<Myorder> myorders=new ArrayList<>();
		try{
			session=sessionFactory.openSession(); //得到session对象
			String hql="select a.oid ,a.orderstate.orderState from Order as a where a.user.uid=? ";
			Query query=session.createQuery(hql);
			query.setParameter(0, id);
			query.setFirstResult((pageNo-1)*3);
			query.setMaxResults(3);
			List lists=query.list();
			for(int i=0;i<lists.size();i++){
				Object[] object = (Object[])lists.get(i);
				Myorder myorder=new Myorder();
				int oid=(int)object[0];
				myorder.setOrderId(oid);
				String stats=(String)object[1];
				myorder.setOrderstate(stats);
				//订单id查找订单状态，图书id
				String hqlbook="select a.book.title,a.book.picture,a.quantity,a.book.price from"
						+ " Orderdetail as a where a.order.oid=?";
				Query querybook=session.createQuery(hqlbook);
				querybook.setParameter(0, oid);
				List booklist=querybook.list();
				List titleList = new ArrayList();
				List pictureList=new ArrayList();
				List quantityList=new ArrayList();
				List priceList=new ArrayList();
				float money=0;
				for(int j=0;j<booklist.size();j++){
					Object[] objectbook = (Object[])booklist.get(j);
					String title=(String) objectbook[0];
					titleList.add(title);
					String p=(String)objectbook[1];
					pictureList.add(p);
					int q=(int)objectbook[2];
					quantityList.add(q);
					float price=(float)objectbook[3];
					priceList.add(price);
					money+=q*price;
					System.out.println(title+"|"+p+"|"+q+"|"+price);
				}
				myorder.setBookname(titleList);
				myorder.setBookpicture(pictureList);
				myorder.setBookprice(priceList);
				myorder.setBooknum(quantityList);
				myorder.setTotalprice(money);
				myorders.add(myorder);
			} 
			return myorders;
		}catch(Exception ex){
			System.out.println("查询失败!");
			ex.printStackTrace();
			return null;
		}finally{ //关闭session
			session.close();
		}
	}
	
	//查询该用户所有
	public int getordernum(int id){
		Session session=null;
		try{
			session=sessionFactory.openSession(); //得到session对象
			String hql="select a.oid ,a.orderstate.orderState from Order as a where a.user.uid=? ";
			Query query=session.createQuery(hql);
			query.setParameter(0, id);
			List lists=query.list();
			int num=lists.size()/5;
			num=num+1;
			return num;
		}catch(Exception ex){
			System.out.println("查询失败!");
			ex.printStackTrace();
			return 0;
		}finally{ //关闭session
			session.close();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*----------------------------------------edit by rin-------------------------------*/
	//分页获取用户
	public List getUserByPage(int pageNum, int pageSize) {
		System.out.println("getUserByPage(int "+pageNum+", int "+pageSize);
		Session session = null;
		List<User> lists = null;
		try {
			session = sessionFactory.openSession();
			String hql = "from User order by uid desc";
			Query q = session.createQuery(hql);
			q.setFirstResult((pageNum - 1) * pageSize);
			q.setMaxResults(pageSize);
			lists = q.list();
			System.out.println("returning user list page :" + pageNum);
			return lists;
		} catch (Exception e) {
			System.out.println("getUserByPage query fail");
			return null;
		} finally {
			session.close();
		}
	}

	// 查询用户表页数
	public int getTotalPage(int pageSize) {
		Session session = null;
		try {
			session = sessionFactory.openSession(); // 得到session对象
			String hql = "from User order by uid desc";
			Query query = session.createQuery(hql);
			System.out.println(query.toString());
			List<User> lists = query.list();
			int num = lists.size();
			System.out.println("TotalUserNum=" + num);
			System.out.println("PageSize=" + pageSize);
			num = (num / pageSize) + 1;
			System.out.println("TotalPageNum=" + num);
			return num;
		} catch (Exception ex) {
			System.out.println("TotalUserPageNum查询失败!");
			ex.printStackTrace();
			return 0;
		} finally { // 关闭session
			session.close();
		}
	}
	
	//通过ID删除用户
	public boolean delUser(int id){
		Session session =null;
		try{
			session = sessionFactory.openSession();
			String hql= "delete from User u where u.uid = ?";
			Query q = session.createQuery(hql);
			q.setParameter(0, id);
			q.executeUpdate();
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
	
	public Usertype getUserTypeById(int id){
		Session session = null;
		try {
			System.out.println("get user type by id");
			session = sessionFactory.openSession();
			String hql = "from Usertype where utId = ?";
			Query query = session.createQuery(hql);
			query.setParameter(0, id);
			List<Usertype> lists = query.list();
			for (Usertype usertype : lists) {
				return usertype;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			session.close();
		}
		return null;
	}
		
	public boolean AddManager(User user){
		Session session = null;
		Transaction tran = null;
		try {
			session = sessionFactory.openSession();
			System.out.println("adding manager ...");
			tran = session.beginTransaction();
			session.save(user);
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
	
	
}
