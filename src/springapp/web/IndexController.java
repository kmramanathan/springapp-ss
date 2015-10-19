package springapp.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//@RequestMapping({"/index.do", "/**/index.do"})
@RequestMapping({"/**/index.do"})

public class IndexController implements Controller {
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// this magically works. to find out why, see section 13.11 of the docs
		// or look up DefaultRequestToViewNameTranslator.
		return new ModelAndView();
	}
}
