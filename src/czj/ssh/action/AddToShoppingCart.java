package czj.ssh.action;

import com.opensymphony.xwork2.ActionSupport;

import czj.ssh.dao.ShoppingCartDao;

public class AddToShoppingCart extends ActionSupport{
	private ShoppingCartDao shoppingCartDao;
	private String bid;
	private String uid;
	private String num;
	public AddToShoppingCart() {
		super();
	}
	
	public ShoppingCartDao getShoppingCartDao() {
		return shoppingCartDao;
	}

	public void setShoppingCartDao(ShoppingCartDao shoppingCartDao) {
		this.shoppingCartDao = shoppingCartDao;
	}

	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String execute(){
		System.out.println("���ݹ�����uid: " + uid);
		System.out.println("���ݹ�����bid: " + bid);
		System.out.println("���ݹ�����num: " + num);
		shoppingCartDao.addToShoppingCart(Integer.parseInt(uid), Integer.parseInt(bid),Integer.parseInt(num));
		return SUCCESS;
	}
}
