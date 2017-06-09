package com.ssh.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import czj.ssh.model.Myorder;

public class AdminDao {
	public AdminDao(){}
	
	SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public int getallnumorder(){
		Session session=null;
		try {
			session=sessionFactory.openSession();
			String hql="from Order as a";
			Query query=session.createQuery(hql);
			List lists=query.list();
			int num=lists.size();
			System.out.println(num);
			if(num%3==0){
				num=num/3;
			}else{
				num=num/3+1;
			}
			return num;
		} catch (Exception e) {
			System.out.println("查询失败!");
			e.printStackTrace();
			return 0;
			}finally{ //关闭session
				session.close();
			}
	}
	
	//查询所有订单
	public List showAllorder(int pageNo){
			Session session=null;
			List<Myorder> myorders=new ArrayList<>();
			try{
				session=sessionFactory.openSession(); //得到session对象
				String hql="select a.oid ,a.orderstate.orderState,a.shipAddress from Order as a ";
				Query query=session.createQuery(hql);
				query.setFirstResult((pageNo-1)*3);
				query.setMaxResults(3);
				List lists=query.list();
				for(int i=0;i<lists.size();i++){
					Object[] object = (Object[])lists.get(i);
					Myorder myorder=new Myorder();
					int oid=(int)object[0];
					System.out.println("订单di"+oid);
					myorder.setOrderId(oid);
					String stats=(String)object[1];
					myorder.setOrderstate(stats);
					String oaddress=(String)object[2];
					myorder.setOaddress(oaddress);
					System.out.println(oaddress);
					//订单id查找订单状态，图书id
					String hqlbook="select a.book.title,a.book.picture,a.quantity,a.bookPrice,a.odId from"
							+ " Orderdetail as a where a.order.oid=?";
					Query querybook=session.createQuery(hqlbook);
					querybook.setParameter(0, oid);
					List booklist=querybook.list();
					List titleList = new ArrayList();
					List pictureList=new ArrayList();
					List quantityList=new ArrayList();
					List priceList=new ArrayList();
					List idList=new ArrayList();
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
						int bookid=(int)objectbook[4];
						idList.add(bookid);
						money+=q*price;
						System.out.println(title+"|"+p+"|"+q+"|!"+price+"|"+bookid+"||");
					}
					myorder.setBookname(titleList);
					myorder.setBookpicture(pictureList);
					myorder.setBookprice(priceList);
					myorder.setBooknum(quantityList);
					myorder.setTotalprice(money);
					myorder.setOrderdetailid(idList);
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
	public void Deleteorder(int oid){
		Session session=null;
		try {
			session=sessionFactory.openSession();
			String hql="Delete from Order as a where a.oid=? ";
			Query query=session.createQuery(hql);
			query.setParameter(0, oid);
			query.executeUpdate();
		} catch (Exception e) {
			System.out.println("查询失败!");
			e.printStackTrace();

		}finally{ //关闭session
				session.close();
		}
	}

	public void updateprice(int index, float newprice,int num) {
		// TODO Auto-generated method stub
		Session session=null;
		try {
			session=sessionFactory.openSession();
			String hql="update Orderdetail s set s.bookPrice=? , s.quantity=?where s.odId=? ";
			Query query=session.createQuery(hql);
			query.setParameter(0, newprice);
			query.setParameter(1, num);
			query.setParameter(2, index);
			query.executeUpdate();
		} catch (Exception e) {
			System.out.println("查询失败!");
			e.printStackTrace();

		}finally{ //关闭session
				session.close();
		}
	}

	public void updateaddress(int oid, String address) {
		// TODO Auto-generated method stub
		Session session=null;
		try {
			session=sessionFactory.openSession();
			String hql="update Order s set s.shipAddress=? where s.oid=? ";
			Query query=session.createQuery(hql);
			query.setParameter(0, address);
			query.setParameter(1, oid);
			query.executeUpdate();
		} catch (Exception e) {
			System.out.println("查询失败!");
			e.printStackTrace();

		}finally{ //关闭session
				session.close();
		}
	}
	
	//查询所有订单
		public List showStateorder(int pageNo){
				Session session=null;
				List<Myorder> myorders=new ArrayList<>();
				try{
					session=sessionFactory.openSession(); //得到session对象
					String hql="select a.oid ,a.orderstate.orderState,a.shipAddress from Order as a where a.orderstate.osId=2";
					Query query=session.createQuery(hql);
					query.setFirstResult((pageNo-1)*3);
					query.setMaxResults(3);
					List lists=query.list();
					for(int i=0;i<lists.size();i++){
						Object[] object = (Object[])lists.get(i);
						Myorder myorder=new Myorder();
						int oid=(int)object[0];
						System.out.println("订单di"+oid);
						myorder.setOrderId(oid);
						String stats=(String)object[1];
						myorder.setOrderstate(stats);
						String oaddress=(String)object[2];
						myorder.setOaddress(oaddress);
						System.out.println(oaddress);
						//订单id查找订单状态，图书id
						String hqlbook="select a.book.title,a.book.picture,a.quantity,a.bookPrice,a.odId from"
								+ " Orderdetail as a where a.order.oid=?";
						Query querybook=session.createQuery(hqlbook);
						querybook.setParameter(0, oid);
						List booklist=querybook.list();
						List titleList = new ArrayList();
						List pictureList=new ArrayList();
						List quantityList=new ArrayList();
						List priceList=new ArrayList();
						List idList=new ArrayList();
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
							int bookid=(int)objectbook[4];
							idList.add(bookid);
							money+=q*price;
							System.out.println(title+"|"+p+"|"+q+"|!"+price+"|"+bookid+"||");
						}
						myorder.setBookname(titleList);
						myorder.setBookpicture(pictureList);
						myorder.setBookprice(priceList);
						myorder.setBooknum(quantityList);
						myorder.setTotalprice(money);
						myorder.setOrderdetailid(idList);
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
//		获得状态为未支付的的订单
		public int getstatenumorder(){
			Session session=null;
			try {
				session=sessionFactory.openSession();
				String hql="from Order as a where a.orderstate.osId=2";
				Query query=session.createQuery(hql);
				List lists=query.list();
				int num=lists.size();
				System.out.println(num);
				if(num%3==0){
					num=num/3;
				}else{
					num=num/3+1;
				}
				return num;
			} catch (Exception e) {
				System.out.println("查询失败!");
				e.printStackTrace();
				return 0;
				}finally{ //关闭session
					session.close();
				}
		}
//修改订单状态
		public void revisestate(int oid) {

				// TODO Auto-generated method stub
				Session session=null;
				try {
					session=sessionFactory.openSession();
					String hql="update Order s set s.orderstate.osId=4 where s.oid=? ";
					Query query=session.createQuery(hql);
					query.setParameter(0, oid);
					query.executeUpdate();
				} catch (Exception e) {
					System.out.println("查询失败!");
					e.printStackTrace();

				}finally{ //关闭session
						session.close();
				}
		}
		//搜索订单
				public List reachorder(int orderid){
						Session session=null;
						List<Myorder> myorders=new ArrayList<>();
						try{
							session=sessionFactory.openSession(); //得到session对象
							String hql="select a.orderstate.orderState,a.shipAddress from Order as a where a.oid=?";
							Query query=session.createQuery(hql);
							query.setParameter(0, orderid);
							List lists=query.list();
							for(int i=0;i<lists.size();i++){
								Object[] object = (Object[])lists.get(i);
								Myorder myorder=new Myorder();
								myorder.setOrderId(orderid);
								String stats=(String)object[0];
								myorder.setOrderstate(stats);
								String oaddress=(String)object[1];
								myorder.setOaddress(oaddress);
								System.out.println(oaddress);
								//订单id查找订单状态，图书id
								String hqlbook="select a.book.title,a.book.picture,a.quantity,a.bookPrice,a.odId from"
										+ " Orderdetail as a where a.order.oid=?";
								Query querybook=session.createQuery(hqlbook);
								querybook.setParameter(0, orderid);
								List booklist=querybook.list();
								List titleList = new ArrayList();
								List pictureList=new ArrayList();
								List quantityList=new ArrayList();
								List priceList=new ArrayList();
								List idList=new ArrayList();
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
									int bookid=(int)objectbook[4];
									idList.add(bookid);
									money+=q*price;
									System.out.println(title+"|"+p+"|"+q+"|!"+price+"|"+bookid+"||");
								}
								myorder.setBookname(titleList);
								myorder.setBookpicture(pictureList);
								myorder.setBookprice(priceList);
								myorder.setBooknum(quantityList);
								myorder.setTotalprice(money);
								myorder.setOrderdetailid(idList);
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
}
