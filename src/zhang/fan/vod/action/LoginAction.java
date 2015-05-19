package zhang.fan.vod.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import zhang.fan.vod.domain.User;
import zhang.fan.vod.service.UserService;

@Controller(value="loginAction")
@Scope(value="prototype")
public class LoginAction {
	private UserService userService;
	private User user;

}
