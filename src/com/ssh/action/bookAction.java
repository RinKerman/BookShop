package com.ssh.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.BookDao;
import czj.ssh.model.Book;

public class bookAction extends ActionSupport{
	private BookDao bookdao;
	private int bookid;
	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public BookDao getBookdao() {
		return bookdao;
	}

	public void setBookdao(BookDao bookdao) {
		this.bookdao = bookdao;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(bookid);
		Book book=bookdao.getbook(bookid);
		String booktype=bookdao.getbooktype(bookid);
		Map session = ActionContext.getContext().getSession();
		session.put("book", book);
		session.put("booktype", booktype);
		return SUCCESS;
	}
	
}
