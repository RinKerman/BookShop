package llj.ssh.action;

import com.opensymphony.xwork2.ActionSupport;

import com.ssh.dao.BookDao;

import czj.ssh.model.Book;
import czj.ssh.model.Booktype;

public class UpdateBookAction extends ActionSupport {

	private BookDao bookDao;
	private Book book;
	private Booktype booktypeNew;

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
	
	public Booktype getBooktypeNew() {
		return booktypeNew;
	}
	public void setBooktypeNew(Booktype booktypeNew) {
		this.booktypeNew = booktypeNew;
	}
	public String execute() {
		book.setBooktype(booktypeNew);
		System.out.println("Using UpdateBookAction");
		if (bookDao.editBook(book))
			return SUCCESS;
		return INPUT;
	}
}
