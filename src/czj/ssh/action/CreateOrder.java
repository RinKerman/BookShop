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
		System.out.println("createOrder 开始运行");	
		System.out.print("索引:");
		for(int i = 0; i < index.length; i++){
			System.out.print(index[i]);
		}
		System.out.println();
		System.out.print("数量:");
		for(int i = 0; i < amount.length; i++){
			System.out.print(amount[i]);
		}
		System.out.println();
		shoppingCarts = (List<Shoppingcart>) ActionContext.getContext().getSession().get("shopping");
		//生成订单表
		Orderstate os = orderStateDao.get(1);//未支付
		o = new Order();
		o.setUser(shoppingCarts.get(0).getUser());
		o.setOrderstate(os);
		o.setOrderDate(new Date());
		//可能Orderdetail也要进行保存
		for(int i = 0; i < index.length; i++){
			Orderdetail od = new Orderdetail();
			od.setOrder(o);
			System.out.println("图书名称:" + shoppingCarts.get(index[i]).getBook().getTitle());
			od.setBook(shoppingCarts.get(index[i]).getBook());
			od.setBookPrice(shoppingCarts.get(index[i]).getBook().getPrice());
			od.setQuantity(amount[i]);
			//System.out.println(od.getBook().getTitle());
			o.getOrderdetails().add(od);
			//删除数据库中购物车表中相应的数据
			shoppingCartDao.deleteItem(shoppingCarts.get(i).getUser().getUid(), shoppingCarts.get(index[i]).getBook().getBid());
		}
		//调用OrderDao将订单 o 保存到数据库中
		orderDao.save(o);
		//将Order保存到session中
		ActionContext.getContext().getSession().put("order", o);
//		System.out.println(o.getOid());
//		System.out.println(o.getOrderDate());
//		System.out.println(o.getOrderdetails());
		return SUCCESS;
	}
}
