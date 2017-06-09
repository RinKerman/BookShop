package czj.ssh.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import czj.ssh.dao.OrderDao;
import czj.ssh.dao.OrderStateDao;
import czj.ssh.dao.ShoppingCartDao;
import czj.ssh.model.Order;
import czj.ssh.model.Orderdetail;
import czj.ssh.model.Orderstate;
import czj.ssh.model.Shoppingcart;

public class CreateOrder extends ActionSupport{
	private int[] index;
	private int[] amount;
	private List<Shoppingcart> shoppingCarts;
	private OrderDao orderDao;
	private ShoppingCartDao shoppingCartDao;
	private OrderStateDao orderStateDao;
	private Order o;
	public CreateOrder() {
		super();
	}

	public int[] getIndex() {
		return index;
	}

	public void setIndex(int[] index) {
		this.index = index;
	}	

	public int[] getAmount() {
		return amount;
	}

	public void setAmount(int[] amount) {
		this.amount = amount;
	}

	public List<Shoppingcart> getShoppingCarts() {
		return shoppingCarts;
	}

	public void setShoppingCarts(List<Shoppingcart> shoppingCarts) {
		this.shoppingCarts = shoppingCarts;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public ShoppingCartDao getShoppingCartDao() {
		return shoppingCartDao;
	}

	public void setShoppingCartDao(ShoppingCartDao shoppingCartDao) {
		this.shoppingCartDao = shoppingCartDao;
	}

	public OrderStateDao getOrderStateDao() {
		return orderStateDao;
	}

	public void setOrderStateDao(OrderStateDao orderStateDao) {
		this.orderStateDao = orderStateDao;
	}

	public Order getO() {
		return o;
	}

	public void setO(Order o) {
		this.o = o;
	}
	
	public String execute(){
		System.out.println("createOrder ��ʼ����");	
		System.out.print("����:");
		for(int i = 0; i < index.length; i++){
			System.out.print(index[i]);
		}
		System.out.println();
		System.out.print("����:");
		for(int i = 0; i < amount.length; i++){
			System.out.print(amount[i]);
		}
		System.out.println();
		shoppingCarts = (List<Shoppingcart>) ActionContext.getContext().getSession().get("shopping");
		//���ɶ�����
		Orderstate os = orderStateDao.get(1);//δ֧��
		o = new Order();
		o.setUser(shoppingCarts.get(0).getUser());
		o.setOrderstate(os);
		o.setOrderDate(new Date());
		//����OrderdetailҲҪ���б���
		for(int i = 0; i < index.length; i++){
			Orderdetail od = new Orderdetail();
			od.setOrder(o);
			System.out.println("ͼ������:" + shoppingCarts.get(index[i]).getBook().getTitle());
			od.setBook(shoppingCarts.get(index[i]).getBook());
			od.setBookPrice(shoppingCarts.get(index[i]).getBook().getPrice());
			od.setQuantity(amount[i]);
			//System.out.println(od.getBook().getTitle());
			o.getOrderdetails().add(od);
			//ɾ�����ݿ��й��ﳵ������Ӧ������
			shoppingCartDao.deleteItem(shoppingCarts.get(i).getUser().getUid(), shoppingCarts.get(index[i]).getBook().getBid());
		}
		//����OrderDao������ o ���浽���ݿ���
		orderDao.save(o);
		//��Order���浽session��
		ActionContext.getContext().getSession().put("order", o);
//		System.out.println(o.getOid());
//		System.out.println(o.getOrderDate());
//		System.out.println(o.getOrderdetails());
		return SUCCESS;
	}
}
