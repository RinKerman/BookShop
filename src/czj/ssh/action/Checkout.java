package czj.ssh.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Checkout extends ActionSupport{
	public Checkout(){
		
	}
	public String execute(){
		Map session = ActionContext.getContext().getSession();
		if(session.containsKey("user")){
			session.remove("user");
		}
		return SUCCESS;
	}
}
