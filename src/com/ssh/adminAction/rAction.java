package com.ssh.adminAction;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.AdminDao;

public class rAction extends ActionSupport{
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
		adminDao.revisestate(orderid);
		return SUCCESS;
	}
}
