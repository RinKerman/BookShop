package com.ssh.action;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.Userdao;

public class checknameAction extends ActionSupport{
	public String logname;
    public String checkresult;
    private Userdao userdao;
	public String getLogname() {
		return logname;
	}
	public void setLogname(String logname) {
		this.logname = logname;
	}
	public String getCheckresult() {
		return checkresult;
	}
	public void setCheckresult(String checkresult) {
		this.checkresult = checkresult;
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
//		存在相同姓名返回true，不相同的话返回false
		boolean flag=userdao.checkname(logname);
		if(flag)
        {
			this.checkresult = "err";/*最终要返回的值，浏览器自动获取*/
        }
        else
        {
            this.checkresult = "ok";
        }
		return SUCCESS;
	}
}
