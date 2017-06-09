package com.ssh.adminAction;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.AdminDao;

public class revicepriceAction extends ActionSupport{
	private AdminDao adminDao;
	private float newprice;
	private int index;
	private int num;
	private String address;
	private int oid;
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public AdminDao getAdminDao() {
		return adminDao;
	}
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	public float getNewprice() {
		return newprice;
	}
	public void setNewprice(float newprice) {
		this.newprice = newprice;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("list“≥∫≈"+index+"dddd"+newprice+"num"+num);
		adminDao.updateprice(index,newprice,num);
		
		address= new String(address.getBytes("ISO-8859-1"), "UTF-8");
		System.out.println(address);
		adminDao.updateaddress(oid,address);
		return SUCCESS;
	}
}
