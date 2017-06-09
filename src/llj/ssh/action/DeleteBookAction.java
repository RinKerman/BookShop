package llj.ssh.action;

import com.opensymphony.xwork2.ActionSupport;

import com.ssh.dao.BookDao;

public class DeleteBookAction extends ActionSupport{
	private BookDao BookDao;
	private int number;
	
	public DeleteBookAction(){
		
	}

	public BookDao getBookDao() {
		return BookDao;
	}


	public void setBookDao(BookDao bookDao) {
		BookDao = bookDao;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String execute(){
		System.out.println(number);
		if(BookDao.delBook(number)){
		return SUCCESS;}
		return INPUT;
	}
}
