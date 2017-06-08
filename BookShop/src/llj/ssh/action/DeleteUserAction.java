package llj.ssh.action;

import com.opensymphony.xwork2.ActionSupport;

import com.ssh.dao.Userdao;

public class DeleteUserAction extends ActionSupport{
	private Userdao UserDao;
	private int number;
	
	public DeleteUserAction(){
	}
	public Userdao getUserDao() {
		return UserDao;
	}
	public void setUserDao(Userdao userDao) {
		UserDao = userDao;
	}
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String execute(){
		System.out.println(number);
		if(UserDao.delUser(number)){
		return SUCCESS;}
		return INPUT;
	}
}
