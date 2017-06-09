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
	private int pageNo; //������,�ӵ�1ҳ��ʼ
	private int currentPage; //��ǰҳ
	private int totalPage;//��ҳ��
	private int sortflag;//�����־λ
	private int currentsort;//��ǰ�����־λ
	private float min,max;//�۸�Χ
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
//		��ǰҳ��
		currentPage=pageNo;
//		��ǰ����״̬
		currentsort=sortflag;
		List list = null;
		Map session = ActionContext.getContext().getSession();
		String booktypestr=bookdao.showbooktype(booktype);
		System.out.println(booktypestr);
		session.put("booktype", booktypestr);
		//��������
		if(sortflag==0){
			list=bookdao.classify(booktype,pageNo);
			totalPage=bookdao.getclassifynum(booktype);
			session.put("booklist", list);
		}
//		���۸�����
		if(sortflag==1){
			list=bookdao.classifypricedesc(booktype, pageNo);
			totalPage=bookdao.getclassifynum(booktype);	
			session.put("booklist", list);
		}
//		����������
		if(sortflag==2){
			list=bookdao.classifysalesAmountsort(booktype, pageNo);
			totalPage=bookdao.getclassifynum(booktype);	
			session.put("booklist", list);
		}
//		���۸�����
		if(sortflag==3){
			list=bookdao.classifypriceasc(booktype, pageNo);
			totalPage=bookdao.getclassifynum(booktype);	
			session.put("booklist", list);
		}
//		����������
		if(sortflag==4){
			list=bookdao.classifysalesAmountasc(booktype, pageNo);
			totalPage=bookdao.getclassifynum(booktype);	
			session.put("booklist", list);
		}
//		����Χ����
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
