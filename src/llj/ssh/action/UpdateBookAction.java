package llj.ssh.action;

import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

import com.ssh.dao.BookDao;

import czj.ssh.model.Book;
import czj.ssh.model.Booktype;

public class UpdateBookAction extends ActionSupport {

	private BookDao bookDao;
	private Book book;
	private int newType;

	public UpdateBookAction() {

	}
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	public BookDao getBookDao() {
		return bookDao;
	}
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}	
	
	
	public int getNewType() {
		return newType;
	}
	public void setNewType(int newType) {
		this.newType = newType;
	}
	public String execute() {
		book.setBooktype(bookDao.getBookTypeById(newType));
		if(book.getAddDate()==null){
			book.setAddDate(new Date());
		}
		System.out.println("Using UpdateBookAction");
		if (bookDao.editBook(book))
			return SUCCESS;
		return INPUT;
	}
}
