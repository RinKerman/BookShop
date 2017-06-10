package llj.ssh.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import czj.ssh.dao.BookTypeDao;
import czj.ssh.model.Booktype;

public class BookTypeListAction extends ActionSupport{
	private BookTypeDao dao;
	private List<Booktype> bookType;

	public BookTypeDao getDao() {
		return dao;
	}

	public void setDao(BookTypeDao dao) {
		this.dao = dao;
	}
	
	public List<Booktype> getBookType() {
		return bookType;
	}

	public void setBookType(List<Booktype> bookType) {
		this.bookType = bookType;
	}

	public String execute(){
		System.out.println("--BookListAction--");
		bookType = dao.getAllBookType();
		return SUCCESS;
	}

}
