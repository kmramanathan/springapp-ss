package springapp.web.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/search/search.do")

public class SearchController extends AbstractWizardFormController {
	private static Logger logger = Logger.getLogger("Search");

	private String cancelView;
	private SearchForm searchForm;
	
	public String getCancelView() {
		return cancelView;
	}
	public void setCancelView(String cancelView) {
		this.cancelView = cancelView;
	}
	public SearchForm getSearchForm() {
		return searchForm;
	}
	public void setSearchForm(SearchForm searchForm) {
		this.searchForm = searchForm;
	}

	/*
	 * page flow:
	 * 
	 * 1) view: select search product
	 * 2) view: product search form
	 * 3) view: confirm
	 * 4) view: result summary
	 * 5) view: result details
	 */	
	public SearchController() {
        setPages(new String[] { "search/SelectSearchProduct", "search/EnterSearchTerms", "search/ConfirmSearch" } );
        this.setSessionForm(true);
        this.setCommandName("searchForm");
        this.setCommandClass(SearchForm.class);
        this.setCancelView("cancel.htm");
        this.setValidator(new SearchFormValidator());        
	}
		
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		return new SearchForm();
	}	
	
	protected Map<String, Object> referenceData(HttpServletRequest request, int page) {
		if (page == 0) {
			HashMap<Integer, String> products = new HashMap<Integer, String>();
			products.put(1, "Test Product");
			products.put(2, "Nationwide");
			products.put(3, "State");
			
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("products", products);
			return model;
		}
		return null;
	}
	
	protected void validatePage(Object command, Errors errors, int page) {
		SearchForm searchForm = (SearchForm) command;
		if (searchForm == null) {
			errors.reject("form is null?");
		} else {
			SearchFormValidator validator = (SearchFormValidator) getValidator();
			switch (page) {
				case 0:
					validator.validateProduct(searchForm, errors);
					break;
				case 1:
					validator.validateSearchFields(searchForm, errors);
					break;
				case 2:
					break;
				case 3:
					break;
			}
		}
	}
	
	protected ArrayList<SearchResult> createSampleResults() {
		ArrayList<SearchResult> results = new ArrayList<SearchResult>();
		for (int i=0; i<5; i++) {
			SearchResult r = new SearchResult();
			r.setSearchId(i);
			r.setFirstName("Judd" + i);
			r.setLastName("Bourgeois" + i);
			results.add(r);
		}
		return results;
	}
	
	@Override
	protected ModelAndView processFinish(
			HttpServletRequest request,
			HttpServletResponse response, 
			Object command, 
			BindException errors)
			throws Exception {
		SearchForm searchForm = (SearchForm) command;
		HashMap<String, Object> model = new HashMap<String, Object>();
		model.put("message", "Thank you, your order has been submitted.");

		String firstName = searchForm.getFirstName();
		if (firstName.equals("judd")) {
			model.put("results", createSampleResults());
			model.put("resultCount", "2");			
		} else {
			model.put("resultCount", "0");
		}
		// run search
		/*
		this.petStore.insertOrder(orderForm.getOrder());
		request.getSession().removeAttribute("sessionCart");
		model.put("order", orderForm.getOrder());
		*/
		return new ModelAndView("search/SearchResultsSummary", model);
	}
	
	
	@Override
	protected ModelAndView processCancel(
			HttpServletRequest arg0,
			HttpServletResponse arg1, 
			Object arg2, 
			BindException arg3)
			throws Exception {
		return new ModelAndView(new RedirectView(cancelView));
	}
}