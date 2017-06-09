package czj.ssh.action;

import com.opensymphony.xwork2.ActionSupport;

import czj.ssh.dao.OrderDao;
import czj.ssh.dao.OrderStateDao;
import czj.ssh.model.Order;
import czj.ssh.model.Orderstate;

public class ReceiveConfirm extends ActionSupport{
	private int oid;
	private OrderDao orderDao;
	private OrderStateDao orderStateDao;
	
	public ReceiveConfirm(){
		
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	public OrderStateDao getOrderStateDao() {
		return orderStateDao;
	}

	public void setOrderStateDao(OrderStateDao orderStateDao) {
		this.orderStateDao = orderStateDao;
	}

	public String execute(){	//确认收货，修改Order的状态
		//根据oid获取该Order
		Order order = orderDao.getOrder(oid);
		//修改Order的orderstate
		Orderstate orderState = orderStateDao.get(3);
		order.setOrderstate(orderState);
		//对order进行更新		
		orderDao.save(order);
		return SUCCESS;
	}
}
