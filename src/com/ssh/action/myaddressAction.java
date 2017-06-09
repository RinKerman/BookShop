package com.ssh.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.Userdao;

import czj.ssh.model.Address;
import czj.ssh.model.User;

public class myaddressAction extends ActionSupport{
	
	
	private String detail,email;
	private String sheng,shi,qu;
	private int flag;
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getSheng() {
		return sheng;
	}
	public void setSheng(String sheng) {
		this.sheng = sheng;
	}
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	public String getQu() {
		return qu;
	}
	public void setQu(String qu) {
		this.qu = qu;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	private Userdao userdao;
	
	public Userdao getUserdao() {
		return userdao;
	}
	public void setUserdao(Userdao userdao) {
		this.userdao = userdao;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String address=sheng+shi+qu+detail;
		Map session = ActionContext.getContext().getSession();
		User mubiao=(User) session.get("user");
		userdao.addaddress(address,email,mubiao);
		List addresslist=userdao.reachaddress(mubiao.getUid());
		session.put("address", addresslist);
		return SUCCESS;
	}
	

}
