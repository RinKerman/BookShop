package llj.ssh.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.Userdao;
import czj.ssh.model.User;

import java.util.List;


//分页获取所有用户
public class UserListAction extends ActionSupport{
	private Userdao userDao;
	private List<User> userList;
	private int pageNum;
	private int pageSize;
	public int TotalPage;
		
	public Userdao getUserDao() {
		return userDao;
	}
	public void setUserDao(Userdao userDao) {
		this.userDao = userDao;
	}
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return TotalPage;
	}

	public void setTotalPage(int totalPage) {
		TotalPage = totalPage;
	}

	public String execute(){
		pageNum = 1;
		TotalPage = userDao.getTotalPage(15);
		userList = userDao.getUserByPage(pageNum,15);
		return SUCCESS;
	}
	

}
