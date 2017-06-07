package czj.ssh.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import czj.ssh.dao.ShoppingCartDao;
import czj.ssh.model.Shoppingcart;

public class QueryShoppingCart extends ActionSupport{
	private List<Shoppingcart> shoppingCarts;
	private ShoppingCartDao shoppingCartDao;
	private Integer userId;
	public QueryShoppingCart() {
		super();
	}	
		
	public List<Shoppingcart> getShoppingCarts() {
		return shoppingCarts;
	}
	
	public void setShoppingCarts(List<Shoppingcart> shoppingCarts) {
		this.shoppingCarts = shoppingCarts;
	}

	public ShoppingCartDao getShoppingCartDao() {
		return shoppingCartDao;
	}

	public void setShoppingCartDao(ShoppingCartDao shoppingCartDao) {
		this.shoppingCartDao = shoppingCartDao;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String execute(){
		shoppingCarts = shoppingCartDao.queryBookById(userId);
		ActionContext.getContext().getSession().put("shopping", shoppingCarts);
		return "success";
	}
}
