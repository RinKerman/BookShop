package llj.ssh.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;

import com.ssh.dao.BookDao;
import czj.ssh.model.Book;
import czj.ssh.model.Booktype;

public class EditBookAction extends ActionSupport{
	private BookDao BookDao;
	private int bookNumber;
	private Book book;
	private String actionType;
	private List<Booktype> bookTypeList;
	
	public List<Booktype> getBookTypeList() {
		return bookTypeList;
	}

	public void setBookTypeList(List<Booktype> bookTypeList) {
		this.bookTypeList = bookTypeList;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public EditBookAction(){
		
	}

	public int getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(int bookNumber) {
		this.bookNumber = bookNumber;
	}

	public BookDao getBookDao() {
		return BookDao;
	}


	public void setBookDao(BookDao bookDao) {
		BookDao = bookDao;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String execute(){
		if(book == null)
		System.out.println("Using EditBookAction");
		System.out.println(actionType);
		bookTypeList = BookDao.getBookType();
		book = BookDao.getbook(bookNumber);
		if(actionType.equals("info"))
			return SUCCESS ;
		return INPUT;
	}

	
}
