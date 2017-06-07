package com.ssh.action;


import java.util.Map;

import org.apache.velocity.runtime.directive.InputBase;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.Userdao;
import czj.ssh.model.User;
import czj.ssh.model.Usertype;

public class RegisterAction extends ActionSupport{
	private User user;
	private Userdao userdao;
	private Usertype usertype=new Usertype();
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Userdao getUserdao() {
		return userdao;
	}
	public void setUserdao(Userdao userdao) {
		this.userdao = userdao;
	}
	@Override
	public String execute() throws Exception {
		
		usertype.setUtId(2);
		user.setUsertype(usertype);
		Map session = ActionContext.getContext().getSession();
		session.put("error", "");
		
		if(!userdao.checkname(user.getUserName())){
			if(userdao.registerUser(user)){
				User user1=userdao.getuser(user.getUserName());
				session.put("user", user1);
				return SUCCESS;
				
			}else{
				return INPUT;
			}
		}else{
			session.put("error", "用户名重复");
			return INPUT;
		}
		
	}
}
