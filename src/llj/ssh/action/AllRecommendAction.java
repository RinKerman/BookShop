package llj.ssh.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.BookDao;

import czj.ssh.model.Book;

public class AllRecommendAction extends ActionSupport{
	private BookDao bookDao;
	private List<Book> bookList;

	public BookDao getBookDao() {
		return bookDao;
	}
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	public List<Book> getBookList() {
		return bookList;
	}
	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;

	}
	
	public String execute(){
		bookList = bookDao.getRecommend();
		return SUCCESS;
	}
}
