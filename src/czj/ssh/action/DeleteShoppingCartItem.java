package czj.ssh.action;

import com.opensymphony.xwork2.ActionSupport;

import czj.ssh.dao.ShoppingCartDao;

public class DeleteShoppingCartItem extends ActionSupport{
	private ShoppingCartDao shoppingCartDao;
	private Integer userId;
	private Integer bookId;
	
	public DeleteShoppingCartItem(){
		
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

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String execute(){
		if(shoppingCartDao.deleteItem(userId, bookId))		
			return SUCCESS;
		return INPUT;
	}
}
