package springapp.web.admin.user;

import java.util.List;
import java.util.Map;

import net.searchsystems.limestone.BGCRequest;
import net.searchsystems.limestone.SsCreditCards;
import net.searchsystems.limestone.SsCreditCardsPeer;
import net.searchsystems.limestone.SsSubscriptionsPeer;
import net.searchsystems.limestone.SsUsers;
import net.searchsystems.limestone.SsUsersPeer;

import org.apache.torque.NoRowsException;
import org.apache.torque.TooManyRowsException;
import org.apache.torque.TorqueException;
import org.apache.torque.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.User;
import springapp.service.search.SearchManager;
import springapp.service.user.UserManager;
import springapp.service.user.UserManagerException;
import springapp.web.NeonController;
import springapp.web.admin.AbstractAdminController;

@Controller
public class DeleteUserForm extends AbstractAdminController {
	@Autowired
	private SearchManager searchManager;
	
	@RequestMapping(value = "/admin/deleteUser.do", method = RequestMethod.GET)
	public ModelAndView setupForm() {
		return new ModelAndView(viewDeleteUser);
	}
	
	@RequestMapping(value = "/admin/deleteUser.do", method = RequestMethod.POST)
	public String processSubmit(@RequestParam("username") String username) {
		User u = userManager.getUserByUsername(username);
		if (u == null) {
			return viewDeleteUser;
		} else {
			userManager.deleteUser(u.getUserId());
			return "redirect:findUser.do";
//			try {
//				userManager.deleteUser(u.getUserId());
//				return "redirect:findUser.do";
//			} catch (NestedRuntimeException e) {			
//				logger.error("Delete user failed: " + e.getMessage());
//				return viewDeleteUser;
//			}			
		}
	}
	
	@RequestMapping(value = "/admin/cleanupTestUsers.do", method = RequestMethod.GET)
	public ModelAndView doGet_cleanupTestUsers(
			ModelMap map) {
		List<Map<String, Object>> users = userManager.getTestUsersForCleanup();
		map.addAttribute("users", users);
		return new ModelAndView("admin/CleanupTestUsers");
	}
	
	// XXX clean up user id 127788
	@RequestMapping(value = "/admin/cleanupTestUsers.do", method = RequestMethod.POST)
	public ModelAndView doPost_cleanupTestUsers(
			ModelMap map) {
		List<Map<String, Object>> users = userManager.getTestUsersForCleanup();
		int deleted = 0;
		for (Map<String, Object> userMap : users) {
			Integer userId = (Integer) userMap.get("user_id");
			List<BGCRequest> searchesForUser = searchManager.getSearchesForUser(userId);
			for (BGCRequest r : searchesForUser) {
				logger.info("deleting searches for user: " + userId);
				searchManager.deleteSearch(r.getBgcRequestId());
				//break;
			}
			userManager.deleteUser(userId);
			deleted++;
			break;
		}
				
		map.addAttribute("deleted", deleted);
		map.addAttribute("users", users);
		return new ModelAndView("admin/CleanupTestUsers");
	}
}