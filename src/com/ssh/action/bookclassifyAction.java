package com.ssh.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.BookDao;
import czj.ssh.model.Book;

public class bookclassifyAction extends ActionSupport{
	private BookDao bookdao;
	private int booktype;
	private int pageNo; //计数器,从第1页开始
	private int currentPage; //当前页
	private int totalPage;//总页数
	private int sortflag;//排序标志位
	private int currentsort;//当前排序标志位
	private float min,max;//价格范围
	public float getMin() {
		return min;
	}

	public void setMin(float min) {
		this.min = min;
	}

	public float getMax() {
		return max;
	}

	public void setMax(float max) {
		this.max = max;
	}

	public int getSortflag() {
		return sortflag;
	}

	public void setSortflag(int sortflag) {
		this.sortflag = sortflag;
	}

	public int getCurrentsort() {
		return currentsort;
	}

	public void setCurrentsort(int currentsort) {
		this.currentsort = currentsort;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getBooktype() {
		return booktype;
	}

	public void setBooktype(int booktype) {
		this.booktype = booktype;
	}

	public BookDao getBookdao() {
		return bookdao;
	}

	public void setBookdao(BookDao bookdao) {
		this.bookdao = bookdao;
	}
	@Override
	public String execute() throws Exception {
//		当前页数
		currentPage=pageNo;
//		当前排序状态
		currentsort=sortflag;
		List list = null;
		Map session = ActionContext.getContext().getSession();
		String booktypestr=bookdao.showbooktype(booktype);
		System.out.println(booktypestr);
		session.put("booktype", booktypestr);
		//正常排序
		if(sortflag==0){
			list=bookdao.classify(booktype,pageNo);
			totalPage=bookdao.getclassifynum(booktype);
			session.put("booklist", list);
		}
//		按价格排序
		if(sortflag==1){
			list=bookdao.classifypricedesc(booktype, pageNo);
			totalPage=bookdao.getclassifynum(booktype);	
			session.put("booklist", list);
		}
//		按销量降序
		if(sortflag==2){
			list=bookdao.classifysalesAmountsort(booktype, pageNo);
			totalPage=bookdao.getclassifynum(booktype);	
			session.put("booklist", list);
		}
//		按价格升序
		if(sortflag==3){
			list=bookdao.classifypriceasc(booktype, pageNo);
			totalPage=bookdao.getclassifynum(booktype);	
			session.put("booklist", list);
		}
//		按销量升序
		if(sortflag==4){
			list=bookdao.classifysalesAmountasc(booktype, pageNo);
			totalPage=bookdao.getclassifynum(booktype);	
			session.put("booklist", list);
		}
//		按范围查找
		if(sortflag==5){
			list=bookdao.classifypricerange(booktype, pageNo,min,max);
			totalPage=bookdao.getclassifynum(booktype);	
			session.put("booklist", list);
			for(int i=0;i<list.size();i++){
				Book book=(Book) list.get(i);
				System.out.println(book.getBid());
			}
		}
		return SUCCESS;
	}

}
