package czj.ssh.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import czj.ssh.model.Order;
import czj.ssh.model.Orderdetail;
import czj.ssh.model.User;

public class PaymentDao {
	private SessionFactory sessionFactory;
	private OrderDao orderDao;
	private UserDao userDao;
	private OrderStateDao orderStateDao;
	private BookDao bookDao;
	public PaymentDao() {
		super();
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public OrderDao getOrderDao() {
		return orderDao;
	}
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public BookDao getBookDao() {
		return bookDao;
	}
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	public OrderStateDao getOrderStateDao() {
		return orderStateDao;
	}
	public void setOrderStateDao(OrderStateDao orderStateDao) {
		this.orderStateDao = orderStateDao;
	}
	//完成确认支付的所有操作
	//事物注解
	//PROPAGATION_REQUIRED--支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择。
	//rollbackFor = {RuntimeException.class} 遇到异常回滚
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor = {Exception.class} )
	public boolean confirmPay(Order order,String address,String receive) throws Exception{
		//减少用户余额
			//从订单表中获取订单的总金额
		if(address == null || receive == null || address.length() == 0 || receive.length() == 0 ){
			throw new Exception("收货地址为空");
		}
		System.out.println("PaymentDao开始运行");
		System.out.println("传递过来的参数为: " + order + ", " + address.trim());
		Set<Orderdetail> od = order.getOrderdetails();
		System.out.println("从order中获取Orderdetails:" + od);
		float sum = 0;
		for(Orderdetail o : od){
			sum += o.getQuantity() * o.getBook().getPrice();
		}
		System.out.println("计算消费金额:" + sum);
		User user = order.getUser();
		System.out.println("从order中获取user:" + user);
		if(sum > user.getBalance()){
			System.out.println("花费金额: " + sum);
			System.out.println("用户余额:" + user.getBalance());
			throw new Exception("账户余额不足");
		}
		user.setBalance((user.getBalance() - sum));
		userDao.save(user);
		//修改订单地址、收货人、支付状态
		order.setShipAddress(address);
		order.setReceive(receive);
		order.setOrderstate(orderStateDao.get(2));
		//修改图书库存、增加销量
		for(Orderdetail o : od){
			if(o.getBook().getStockNumber() < o.getQuantity()){
				System.out.println("购买数量:" + o.getQuantity());
				System.out.println("图书库存:" + o.getBook().getStockNumber());
				throw new Exception("图书库存不足 ");
			}
			o.getBook().setStockNumber((o.getBook().getStockNumber() - o.getQuantity()));
			o.getBook().setSalesAmount((o.getBook().getSalesAmount() + o.getQuantity()));
			//保存图书
			bookDao.save(o.getBook());
		}
		orderDao.save(order);
		return true;
	}
}
