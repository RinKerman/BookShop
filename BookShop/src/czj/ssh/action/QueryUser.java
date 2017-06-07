package czj.ssh.action;

import com.opensymphony.xwork2.ActionSupport;

import czj.ssh.dao.UserDao;
import czj.ssh.model.User;

public class QueryUser extends ActionSupport{
	private UserDao userDao;
	private User user;
	public QueryUser() {
		super();
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String execute(int uid){
		user = userDao.getUser(uid);
		return SUCCESS;
	}
}
