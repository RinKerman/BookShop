package czj.ssh.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import czj.ssh.dao.OrderDao;
import czj.ssh.model.Order;

public class PayAgain extends ActionSupport{
	private int oid;	//订单id
	private OrderDao orderDao;
	public PayAgain(){
		
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

	public String execute(){
		//根据oid获取order
		Order order = orderDao.getOrder(oid);
		//将order存到sessionsession，名字为 order
		ActionContext.getContext().getSession().put("order", order);
		return SUCCESS;	//跳转到支付页面
	}
}
