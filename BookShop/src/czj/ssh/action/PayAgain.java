package czj.ssh.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import czj.ssh.dao.OrderDao;
import czj.ssh.model.Order;

public class PayAgain extends ActionSupport{
	private int oid;	//����id
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
		//����oid��ȡorder
		Order order = orderDao.getOrder(oid);
		//��order�浽sessionsession������Ϊ order
		ActionContext.getContext().getSession().put("order", order);
		return SUCCESS;	//��ת��֧��ҳ��
	}
}
