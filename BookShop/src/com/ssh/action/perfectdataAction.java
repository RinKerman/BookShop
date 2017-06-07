package com.ssh.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.Userdao;
import czj.ssh.model.User;

public class perfectdataAction extends ActionSupport{
	
	private User user;
	private Userdao userdao;
	
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
		// TODO Auto-generated method stub
		Map session = ActionContext.getContext().getSession();
		User mubiao=(User) session.get("user");
		System.out.println(mubiao.getUid());
		int id=mubiao.getUid();
		userdao.updateuser(user, id);
		User user1=userdao.getuser(user.getUserName());
		session.put("user", user1);
		return SUCCESS;
	}
}
