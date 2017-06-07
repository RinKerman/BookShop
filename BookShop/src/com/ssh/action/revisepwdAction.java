package com.ssh.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.Userdao;
import czj.ssh.model.User;

public class revisepwdAction extends ActionSupport{
	private Userdao userdao;
	private String oldpassword,newpassword;
	public Userdao getUserdao() {
		return userdao;
	}
	public void setUserdao(Userdao userdao) {
		this.userdao = userdao;
	}
	public String getOldpassword() {
		return oldpassword;
	}
	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Map session = ActionContext.getContext().getSession();
		User mubiao=(User) session.get("user");
		System.out.println(oldpassword+"dd"+newpassword+"dd"+mubiao.getPassword());
		
		if(!(mubiao.getPassword()).equals(oldpassword)){
			session.put("errorpwd", "旧密码与原密码不同");
			return INPUT;
		}else{
			userdao.revisepwd(mubiao, newpassword);
			return SUCCESS;
		}
		
	}
}
