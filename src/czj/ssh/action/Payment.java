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
	private String receive;//�ռ���
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
		System.out.println("Payment��ʼ����");
		System.out.println("��ȡ���ĵ�ַ��: " + address.trim());
		System.out.println("��ȡ�����ջ�����: " + receive.trim());
		//��ȡsession�б����order������Ϊ�������͸�paymentDao
		Order order = (Order) ActionContext.getContext().getSession().get("order");
		//System.out.println("��session�л�ȡ��order: " + order);
		try {
			if(paymentDao.confirmPay(order, address, receive)){
				System.out.println("�ɹ�");
				map.put("result", "֧���ɹ�");
			}
		} catch (Exception e) {	//֧��ʧ�ܻ��׳�������Ϣ
			e.printStackTrace();
			System.out.println("ʧ��");
			//����֧��ʧ�ܵ���Ϣ
			map.put("result", e.getMessage());
		}
		 JSONObject json=JSONObject.fromObject(map);
	     returndata=json.toString();
		return SUCCESS;			
	}
}
