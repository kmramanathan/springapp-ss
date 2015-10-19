package springapp.web.admin.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import springapp.crypt.Crypt;
import springapp.domain.CreditCard;
import springapp.domain.User;
import springapp.service.NeonValidator;
import springapp.service.user.CreditCardValidator;
import springapp.web.admin.AbstractAdminController;

@Controller
@RequestMapping("/admin/editCreditCard.do")
@SessionAttributes({"command"})

/*
 * after lots of trial and error this appears to be the best way
 * to handle complex forms. the command object is a container for 
 * all the other objects. we save it in the session and we don't 
 * need to worry about other objects.
 */
public class EditCreditCardForm extends AbstractAdminController {
	/*
	 * GET sets up the command object and returns the mav.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView setupForm(@RequestParam("username") String username) {
		// don't allow edit if user is not active
		User user = userManager.getUserByUsername(username);
		if (user.getDisabled() || user.getInactive()) {
			return new ModelAndView(viewEditCreditCardInvalid, "username", username);
		}
		
		ModelAndView mav = new ModelAndView(viewEditCreditCard);
		EditCreditCardFormCommand command = new EditCreditCardFormCommand();
		command.setUser(user);
		CreditCard cc = userManager.getCreditCard(user.getUserId());
		if (cc == null) {
			cc = new CreditCard();
			cc.setUserId(user.getUserId());
			command.setCreditCard(cc);
		} else {
			command.setCreditCard(cc);
			command.setCardNumber(Crypt.decrypt(cc.getNumber()));
		}
		mav.addObject("command", command);
		return mav;
	}

	/*
	 * POST needs to be careful. first we get the cmd object.
	 * validation requires nested path. BUT, we need to use push/pop
	 * because using set will break the jsp when we return.
	 * 
	 * on success, setComplete will destroy the session object. so 
	 * we can't just return a mav, we need to redirect. this is 
	 * probably a good idea anyway as we don't need to monkey with
	 * the command object anymore. but be aware of it.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			ModelMap map,
			@ModelAttribute("command") EditCreditCardFormCommand command,
			Errors errors,
			SessionStatus status) {
		EditCreditCardFormValidator v = new EditCreditCardFormValidator();
		v.validate(command, errors);

		User user = command.getUser();
		CreditCard cc = command.getCreditCard();
		cc.setCardModified(true);

		if (errors.hasErrors()) {
			map.addAttribute(errors);
			return viewEditCreditCard;
		} else {
			String ccNumber = command.getCardNumber();
			cc.setNumber(Crypt.encrypt(ccNumber));
			int ccLen = ccNumber.length();
			String last4 = command.getCardNumber().substring(ccLen - 4, ccLen);
			cc.setLastDigits(Short.parseShort(last4));
			userManager.saveCreditCard(cc);			
			status.setComplete();
			return "redirect:viewUser.do?username=" + user.getUsername();
		}
	}	
	
	public static class EditCreditCardFormCommand {
		private User user;
		private CreditCard creditCard;
		private String cardNumber;

		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		public CreditCard getCreditCard() {
			return creditCard;
		}
		public void setCreditCard(CreditCard card) {
			this.creditCard = card;
		}
		public String getCardNumber() {
			return cardNumber;
		}
		public void setCardNumber(String cardNumber) {
			this.cardNumber = cardNumber;
		}		
	}
	
	public class EditCreditCardFormValidator extends NeonValidator implements Validator {
		public boolean supports(Class clazz) {
			return clazz.equals(EditCreditCardFormCommand.class);
		}
		
		public void validate(Object target, Errors errors) {
			EditCreditCardFormCommand cmd = (EditCreditCardFormCommand) target;

			// cc number must be validated here before encrypt
			validateString("cardNumber", cmd.getCardNumber(), errors, 13, 18, regexNumeric);			

			// validate cc
			errors.pushNestedPath("creditCard");
			new CreditCardValidator().validate(cmd.getCreditCard(), errors);
			errors.popNestedPath();
		}		
	}
}