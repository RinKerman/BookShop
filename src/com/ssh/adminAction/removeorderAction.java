package com.ssh.adminAction;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.AdminDao;

public class removeorderAction extends ActionSupport{
	
	public removeorderAction() {
		// TODO Auto-generated constructor stub
	}
	private AdminDao adminDao;
	
	private int orderid;

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public AdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(orderid);
		adminDao.Deleteorder(orderid);
		return SUCCESS;
	}
}	
