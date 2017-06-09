package llj.ssh.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.BookDao;
import czj.ssh.model.Book;
import java.util.List;


//分页获取所有图书
public class BookListAction extends ActionSupport{
	private BookDao bookDao;
	private List<Book> bookList;
	private int pageNum;
	private int pageSize;
	public int TotalPage;
	
	public int getTotalPage() {
		return TotalPage;
	}
	public void setTotalPage(int totalPage) {
		TotalPage = totalPage;
	}
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
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageList) {
		this.pageSize= pageSize;
	}
	
	public String execute(){
		
		
		TotalPage = bookDao.getTotalPage(15);
		bookList = bookDao.getBookByPage(pageNum,15);
		return SUCCESS;
	}
	

}
