package czj.ssh.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import czj.ssh.dao.BookDao;
import czj.ssh.model.Book;
/*
 * QueryBookForIndex 查询网站 index 初始化所需要的数据
 * 在网页加载的时候显示出来
 */
public class QueryBookForIndex extends ActionSupport{
	private BookDao bookDao;
	private List<Book> bookListOfDate;
	private List<Book> bookListOfSaleAmount;
	private List<Book> bookListOfRandom;
	
	public QueryBookForIndex(){
		
	}

	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public List<Book> getBookListOfDate() {
		return bookListOfDate;
	}

	public void setBookListOfDate(List<Book> bookListOfDate) {
		this.bookListOfDate = bookListOfDate;
	}

	public List<Book> getBookListOfSaleAmount() {
		return bookListOfSaleAmount;
	}

	public void setBookListOfSaleAmount(List<Book> bookListOfSaleAmount) {
		this.bookListOfSaleAmount = bookListOfSaleAmount;
	}

	public List<Book> getBookListOfRandom() {
		return bookListOfRandom;
	}

	public void setBookListOfRandom(List<Book> bookListOfRandom) {
		this.bookListOfRandom = bookListOfRandom;
	}
	public String execute(){
		bookListOfDate = bookDao.queryBookByDate();
		bookListOfSaleAmount = bookDao.queryBookBySaleAmount();
		bookListOfRandom = bookDao.queryBookByRandom();
		return SUCCESS;
	}
}
