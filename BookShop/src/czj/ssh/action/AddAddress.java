package czj.ssh.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import czj.ssh.dao.UserDao;
import czj.ssh.model.Address;
import czj.ssh.model.Shoppingcart;
import czj.ssh.model.User;

public class AddAddress extends ActionSupport{
	private String name;
	private String addr;
	private String cellphone;
	private String zipCode;
	private UserDao userDao;
	public AddAddress() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public String execute(){
		List<Shoppingcart> shoppingCarts = (List<Shoppingcart>) ActionContext.getContext().getSession().get("shopping");
		User user = shoppingCarts.get(0).getUser();
		if(user.getName() == null){			
			user.setName(name);
		}
		if(user.getCellphone() == null){			
			user.setCellphone(cellphone);
		}
		Address address = new Address();
		address.setUser(user);
		address.setAddress(addr);
		address.setZipCode(zipCode);
		user.getAddresses().add(address);
		userDao.save(user);
//		System.out.println(name);
//		System.out.println(addr);
//		System.out.println(cellphone);
//		System.out.println(zipCode);
		return SUCCESS;
	}
}
