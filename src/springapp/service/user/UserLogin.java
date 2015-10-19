package springapp.service.user;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import springapp.domain.User;
import springapp.repository.SubscriptionDao;
import springapp.service.NeonPasswordMD5;
import springapp.web.funnel.AbstractFunnelController;

public class UserLogin extends AbstractFunnelController {

	protected static Logger logger = Logger.getRootLogger();
	
	//For SearchSystems member 
	public static void authenticateSsMember(User user, Errors errors, String password, HttpSession session){
		
		if(user == null){
			errors.rejectValue("ssUsername", "Username-Not-exits", "Username doesn't exist");
			return;
		}else if(user.getDisabled() == true){
			errors.rejectValue("ssUsername", "Username-disabled", "Username Disabled");
			return;
		}else if(!user.getPassword().equals(NeonPasswordMD5.encryptPassword(password))){
			errors.rejectValue("ssUsername", "login-failed", "Username and password doesn't match");
			return;
		}		
		return;		
	}
	
	//For Flat Rate customers
	public static void authenticateFRMember(User user, Errors errors, String password, HttpSession session){
		if(user == null){
			errors.rejectValue("username", "Username-Not-exits", "Username doesn't exist");
			return;
		}else if(user.getDisabled() == true){
			errors.rejectValue("username", "Username-disabled", "Username Disabled");
			return;
		}else if(!user.getPassword().equals(NeonPasswordMD5.encryptPassword(password))){
			errors.rejectValue("username", "login-failed", "Username and password doesn't match");
			return;
		}
		return;		
	}
		
}
