package llj.ssh.action;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.BookDao;
import czj.ssh.model.Book;

public class SwitchRecommendAction extends ActionSupport{

	private BookDao bookDao;
	private int number;
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public BookDao getBookDao() {
		return bookDao;
	}
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	
	public String execute() throws Exception{
		int bid = number;
		int type = bookDao.getbook(bid).getRecommendFlag();
		System.out.println("bid = " + bid + "type=" + type);
		bookDao.switchRecommend(bid, type);
		return SUCCESS;
	}
}
