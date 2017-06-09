package llj.ssh.action;

import com.opensymphony.xwork2.ActionSupport;

import com.ssh.dao.Userdao;
import czj.ssh.model.User;
import czj.ssh.model.Usertype;

public class AddManagerAction extends ActionSupport {

	private Userdao UserDao;
	private User user;
	private int newType;

	public Userdao getUserDao() {
		return UserDao;
	}

	public void setUserDao(Userdao userDao) {
		UserDao = userDao;
	}

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	public int getNewType() {
		return newType;
	}

	public void setNewType(int newType) {
		this.newType = newType;
	}

	public String execute() {
		user.setUsertype(UserDao.getUserTypeById(newType));
		System.out.println(user.getUsertype().getUtype());
		System.out.println("Using AddManagerAction");
		if (UserDao.AddManager(user))
			return SUCCESS;
		return INPUT;
	}
}
