package com.ssh.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.Userdao;

import czj.ssh.model.User;

public class removerAAction extends ActionSupport{
	private Userdao userdao;
	private int addressid;
	public int getAddressid() {
		return addressid;
	}

	public void setAddressid(int addressid) {
		this.addressid = addressid;
	}

	public Userdao getUserdao() {
		return userdao;
	}

	public void setUserdao(Userdao userdao) {
		this.userdao = userdao;
	}
	@Override
	public String execute() throws Exception {
		userdao.removeA(addressid);
		Map session = ActionContext.getContext().getSession();
		User user=(User) session.get("user");	
		List addresslist=userdao.reachaddress(user.getUid());
		session.put("address", addresslist);
		// TODO Auto-generated method stub
		return super.execute();
	}
}
