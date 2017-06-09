package com.ssh.adminAction;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.AdminDao;

public class reachorderAction extends ActionSupport{
	private int orderid;
	private AdminDao adminDao;
	public AdminDao getAdminDao() {
		return adminDao;
	}
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(orderid+"dd");
		Map session = ActionContext.getContext().getSession();
		List list=adminDao.reachorder(orderid);
		session.put("allorder", list);
		return SUCCESS;
	}
}
