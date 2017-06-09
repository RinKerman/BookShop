package com.ssh.adminAction;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.AdminDao;

public class adminorderAction extends ActionSupport{
	private int pageNo; //������,�ӵ�1ҳ��ʼ
	private int currentPage; //��ǰҳ
	private int totalPage;//��ҳ��
	private AdminDao adminDao;
	public int getPageNo() {
		return pageNo;
	}
	public AdminDao getAdminDao() {
		return adminDao;
	}
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
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
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		currentPage=pageNo;
		// TODO Auto-generated method stub
		Map session = ActionContext.getContext().getSession();
		List list=adminDao.showAllorder(pageNo);
		totalPage=adminDao.getallnumorder();
		session.put("allorder", list);
		return SUCCESS;	
	}
}
