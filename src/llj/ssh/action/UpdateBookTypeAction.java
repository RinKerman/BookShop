package llj.ssh.action;

import com.opensymphony.xwork2.ActionSupport;

import czj.ssh.dao.BookTypeDao;
import czj.ssh.model.Booktype;

public class UpdateBookTypeAction extends ActionSupport{
	private BookTypeDao dao;
	private String tid;
	private String type;
	public BookTypeDao getDao() {
		return dao;
	}
	public void setDao(BookTypeDao dao) {
		this.dao = dao;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String execute(){
		if(tid == null || tid.length() == 0){	//Ôö¼Ó
			Booktype bt = new Booktype();
			bt.setBtype(type);
			dao.save(bt);
		}else{	//ÐÞ¸Ä
			Booktype bt = dao.get(Integer.parseInt(tid));
			bt.setBtype(type);
			dao.save(bt);
		}
		return SUCCESS;
	}
}
