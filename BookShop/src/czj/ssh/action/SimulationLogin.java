
package czj.ssh.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import czj.ssh.dao.UserDao;
import czj.ssh.model.User;

public class SimulationLogin extends ActionSupport{
	private User user;
	private UserDao userDao;

	public SimulationLogin() {
		super();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public String execute(){
		User login = userDao.checkLogin(user.getUserName(), user.getPassword());
		if(login != null){
			//将用户保存到session中
			ActionContext.getContext().getSession().put("user", login);
			return SUCCESS;
		}
		return INPUT;
	}
}
