package com.ssh.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.Userdao;
import czj.ssh.model.Address;

import czj.ssh.model.User;
import czj.ssh.model.Usertype;

public class LoginAction extends ActionSupport{
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

		if(userdao.login(user)){
			User user1=userdao.getuser(user.getUserName());
			session.put("user", user1);
			session.put("errorpwd", "");
			List addresslist=userdao.reachaddress(user1.getUid());
			for(int i=0;i<addresslist.size();i++){
				Address address=(Address) addresslist.get(i);
				System.out.println(address.getAddress());
			}
			session.put("address", addresslist);
			if(user1.getUsertype().getUtId()== 2)
			return SUCCESS;
			return NONE;
		}else{
			session.put("errorlogin","密码或用户名错误");
			return INPUT;
		}
	}
}
