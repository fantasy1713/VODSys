package zhang.fan.vod.service.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zhang.fan.vod.dao.UserDao;
import zhang.fan.vod.domain.User;
import zhang.fan.vod.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	private UserDao userDao;

	@Override
	public boolean userlogin(String name,String pass) {
		User user;
		List<User> list = userDao.findByProperty(User.EMAIL, name);//根据注册邮箱登陆
		if(list!=null&&list.size()>0){
			user = list.get(0);
		}
		else{
			 list = userDao.findByProperty(User.USERNAME, name);//根据注册邮箱登陆
			 if(list!=null&&list.size()>0){
					user = list.get(0);
				}
			 else{
				 return false;
			 }
		}
		//找到用户后，判断口令
		boolean flag=false;
		if(pass!=null){
			flag =pass.equals(user.getPassword());
		}
		return flag;
	}

	public UserDao getUserDao() {
		return userDao;
	}
	@Resource(name="userDao")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	
}
