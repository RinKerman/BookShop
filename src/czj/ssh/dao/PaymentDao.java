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
	//���ȷ��֧�������в���
	//����ע��
	//PROPAGATION_REQUIRED--֧�ֵ�ǰ���������ǰû�����񣬾��½�һ���������������ѡ��
	//rollbackFor = {RuntimeException.class} �����쳣�ع�
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor = {Exception.class} )
	public boolean confirmPay(Order order,String address,String receive) throws Exception{
		//�����û����
			//�Ӷ������л�ȡ�������ܽ��
		if(address == null || receive == null || address.length() == 0 || receive.length() == 0 ){
			throw new Exception("�ջ���ַΪ��");
		}
		System.out.println("PaymentDao��ʼ����");
		System.out.println("���ݹ����Ĳ���Ϊ: " + order + ", " + address.trim());
		Set<Orderdetail> od = order.getOrderdetails();
		System.out.println("��order�л�ȡOrderdetails:" + od);
		float sum = 0;
		for(Orderdetail o : od){
			sum += o.getQuantity() * o.getBook().getPrice();
		}
		System.out.println("�������ѽ��:" + sum);
		User user = order.getUser();
		System.out.println("��order�л�ȡuser:" + user);
		if(sum > user.getBalance()){
			System.out.println("���ѽ��: " + sum);
			System.out.println("�û����:" + user.getBalance());
			throw new Exception("�˻�����");
		}
		user.setBalance((user.getBalance() - sum));
		userDao.save(user);
		//�޸Ķ�����ַ���ջ��ˡ�֧��״̬
		order.setShipAddress(address);
		order.setReceive(receive);
		order.setOrderstate(orderStateDao.get(2));
		//�޸�ͼ���桢��������
		for(Orderdetail o : od){
			if(o.getBook().getStockNumber() < o.getQuantity()){
				System.out.println("��������:" + o.getQuantity());
				System.out.println("ͼ����:" + o.getBook().getStockNumber());
				throw new Exception("ͼ���治�� ");
			}
			o.getBook().setStockNumber((o.getBook().getStockNumber() - o.getQuantity()));
			o.getBook().setSalesAmount((o.getBook().getSalesAmount() + o.getQuantity()));
			//����ͼ��
			bookDao.save(o.getBook());
		}
		orderDao.save(order);
		return true;
	}
}
