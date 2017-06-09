package czj.ssh.action;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import czj.ssh.dao.PaymentDao;
import czj.ssh.model.Order;
import net.sf.json.JSONObject;

public class Payment extends ActionSupport{
	private PaymentDao paymentDao;
	private String address;
	private String receive;//收件人
	private String returndata;
	public Payment() {
		super();
	}
	
	public PaymentDao getPaymentDao() {
		return paymentDao;
	}


	public void setPaymentDao(PaymentDao paymentDao) {
		this.paymentDao = paymentDao;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getReceive() {
		return receive;
	}

	public void setReceive(String receive) {
		this.receive = receive;
	}

	public String getReturndata() {
		return returndata;
	}

	public void setReturndata(String returndata) {
		this.returndata = returndata;
	}

	public String execute(){
		Map<String,String> map=new HashMap<String,String>();
		System.out.println("Payment开始运行");
		System.out.println("获取到的地址是: " + address.trim());
		System.out.println("获取到的收货人是: " + receive.trim());
		//获取session中保存的order对象作为参数传送给paymentDao
		Order order = (Order) ActionContext.getContext().getSession().get("order");
		//System.out.println("从session中获取的order: " + order);
		try {
			if(paymentDao.confirmPay(order, address, receive)){
				System.out.println("成功");
				map.put("result", "支付成功");
			}
		} catch (Exception e) {	//支付失败或抛出错误信息
			e.printStackTrace();
			System.out.println("失败");
			//保存支付失败的信息
			map.put("result", e.getMessage());
		}
		 JSONObject json=JSONObject.fromObject(map);
	     returndata=json.toString();
		return SUCCESS;			
	}
}
