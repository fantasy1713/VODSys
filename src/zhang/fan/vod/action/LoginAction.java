package zhang.fan.vod.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import zhang.fan.vod.domain.User;
import zhang.fan.vod.service.UserService;

@Component( "loginAction")
@Scope("prototype")
public class LoginAction extends ActionSupport {
	private UserService userService;
	private String emailOrName;
	private String password;

	public String userlogin(){
		if(userService.userlogin(emailOrName, password))
		return SUCCESS;
		else
			return LOGIN;
	}

	public UserService getUserService() {
		return userService;
	}
	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getEmailOrName() {
		return emailOrName;
	}

	public void setEmailOrName(String emailOrName) {
		this.emailOrName = emailOrName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
