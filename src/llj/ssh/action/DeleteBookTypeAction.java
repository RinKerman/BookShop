package llj.ssh.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import czj.ssh.dao.BookDao;
import czj.ssh.dao.BookTypeDao;
import czj.ssh.model.Book;

public class DeleteBookTypeAction extends ActionSupport{
	private BookTypeDao dao;
	private BookDao bookDao;
	private Integer tid;
	public BookTypeDao getDao() {
		return dao;
	}
	public void setDao(BookTypeDao dao) {
		this.dao = dao;
	}	
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	
	public BookDao getBookDao() {
		return bookDao;
	}
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	public String execute(){	
		System.out.println("收到tid：" + tid);
		//取出数据中所有该类型的图书
		List<Book> list = bookDao.queryBookByType(tid);
		//将图书的类型设置为空
		for(Book b : list){
			b.setBooktype(null);
			bookDao.save(b);
		}
		boolean flag = dao.delete(tid);
		if(flag){
			System.out.println("成功删除图书类型");
		}
		return SUCCESS;
	}
}
