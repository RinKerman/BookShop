package com.ssh.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.Userdao;
import czj.ssh.model.Myorder;
import czj.ssh.model.User;

public class myorderAction extends ActionSupport{
	private Userdao userdao;
	
	private int pageNo; //������,�ӵ�1ҳ��ʼ
	private int currentPage; //��ǰҳ
	private int totalPage;//��ҳ��
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public Userdao getUserdao() {
		return userdao;
	}
	public void setUserdao(Userdao userdao) {
		this.userdao = userdao;
	}

	@Override
	public String execute() throws Exception {
		//Ŀ��ҳ���ڵ�ǰҳ
		currentPage=pageNo;
		// TODO Auto-generated method stub
		Map session = ActionContext.getContext().getSession();
		User mubiao=(User) session.get("user");
		int userid=mubiao.getUid();
		List myorder=userdao.showorder(userid,pageNo);
		totalPage=userdao.getordernum(userid);
		System.out.println(pageNo);
		for(int i=0;i<myorder.size();i++){
			Myorder m=(Myorder) myorder.get(i);
			System.out.println(m.getBookname().size());
		}
		session.put("myorder", myorder);
		return SUCCESS;
	}
}
