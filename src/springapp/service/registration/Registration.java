package springapp.service.registration;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import springapp.domain.CreditCard;
import springapp.domain.User;

public class Registration {
    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    // we can use these objects to make things easier
    private User user;
    private CreditCard creditCard;
    
    // need to fix this
    private String cardZipCode;

    // fields handled directly by controller
    private String confirmEmail;
    private String password;
    private String confirmPassword;
    private String cardCvv;
    private String cardNumber;
    
    private int hintQuestion;
    private String hintAnswer;
    
    private Integer purchasePlan;    
    private boolean receiveNewsletter;

    /* objects that populate select boxes */
    //private HashMap<Integer, String> hintQuestions;
    private HashMap<Integer, String> purchasePlans;
    //private HashMap<String, String> states;
    private Integer hintQuestions[] = {1, 4, 7};
    //private Integer purchasePlans[] = {1, 2};
    private String states[] = {"CA", "NV", "TN"};
    private Integer cardExpirationMonths[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    private Integer cardExpirationYears[] = {2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023, 2024};

    public Registration() {
    	purchasePlans = new HashMap<Integer, String>();
    	purchasePlans.put(1, "4.95 Monthly");
    	purchasePlans.put(2, "48.50 Annually");
    }    
    
	public String getConfirmEmail() {
		return confirmEmail;
	}
	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public int getHintQuestion() {
		return hintQuestion;
	}
	public void setHintQuestion(int hintQuestion) {
		this.hintQuestion = hintQuestion;
	}
	public String getHintAnswer() {
		return hintAnswer;
	}
	public void setHintAnswer(String hintAnswer) {
		this.hintAnswer = hintAnswer;
	}
	public Integer getPurchasePlan() {
		return purchasePlan;
	}
	public void setPurchasePlan(Integer purchasePlan) {
		this.purchasePlan = purchasePlan;
	}

	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardCvv() {
		return cardCvv;
	}
	public void setCardCvv(String cardCvv) {
		this.cardCvv = cardCvv;
	}
	

	public boolean isReceiveNewsletter() {
		return receiveNewsletter;
	}
	public void setReceiveNewsletter(boolean receiveNewsletter) {
		this.receiveNewsletter = receiveNewsletter;
	}
	public Integer[] getHintQuestions() {
		return hintQuestions;
	}
	public void setHintQuestions(Integer[] hintQuestions) {
		this.hintQuestions = hintQuestions;
	}

	public String[] getStates() {
		return states;
	}
	public void setStates(String[] states) {
		this.states = states;
	}
	public Integer[] getCardExpirationMonths() {
		return cardExpirationMonths;
	}
	public void setCardExpirationMonths(Integer[] cardExpirationMonths) {
		this.cardExpirationMonths = cardExpirationMonths;
	}
	public Integer[] getCardExpirationYears() {
		return cardExpirationYears;
	}
	public void setCardExpirationYears(Integer[] cardExpirationYears) {
		this.cardExpirationYears = cardExpirationYears;
	}
	public Log getLogger() {
		return logger;
	}
	public synchronized User getUser() {
		return user;
	}
	public synchronized void setUser(User user) {
		this.user = user;
	}
	public synchronized CreditCard getCreditCard() {
		return creditCard;
	}
	public synchronized void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	public synchronized String getCardZipCode() {
		return cardZipCode;
	}
	public synchronized void setCardZipCode(String cardZipCode) {
		this.cardZipCode = cardZipCode;
	}

	public HashMap<Integer, String> getPurchasePlans() {
		return purchasePlans;
	}

	public void setPurchasePlans(HashMap<Integer, String> purchasePlans) {
		this.purchasePlans = purchasePlans;
	}
	
}