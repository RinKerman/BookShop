package llj.ssh.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import czj.ssh.dao.BookTypeDao;
import czj.ssh.model.Booktype;

public class BookTypeListAction extends ActionSupport{
	private BookTypeDao dao;
	private List<Booktype> bookType;
	//nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnew
	private int pageSize = 10;
	private int currentPage = 1;
	private int totalPage;
	//nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnew

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
	//nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnew
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	//nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnew
	public String execute(){//分页查询图书类型
		System.out.println("--BookListAction--");
		bookType = dao.getAllBookType();
		// 设置分页
		if (bookType.size() % pageSize == 0) {
			totalPage = bookType.size() / pageSize;
		} else {
			totalPage = bookType.size() / pageSize + 1;
		}			
		
		if (currentPage <= 0) {
			currentPage = 1;
		} else if (currentPage > totalPage) {
			currentPage = totalPage;
		}
		
		bookType = dao.queryByPage(currentPage, pageSize);
		
		return SUCCESS;
	}

}
