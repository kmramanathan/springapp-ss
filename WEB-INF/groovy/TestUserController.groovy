// from the file '/WEB-INF/groovy/FortuneController.groovy'
package springapp.web.admin.test

import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.Controller

import springapp.service.user.UserManager

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class TestUserController implements Controller {    
	UserManager userManager

    ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        return new ModelAndView("admin/ViewUser", "user", userManager.getUserByUserId(9694));
    	//return new ModelAndView("admin/test/TestMap", "foo", 5678)
    }
}