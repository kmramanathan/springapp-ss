package springapp.repository;

import java.util.List;
import java.util.Map;

import springapp.domain.CreditCard;

public interface CreditCardDao {   
	public CreditCard getCreditCard(int userId);
    //public void createCreditCard(CreditCard card);
    public void saveCreditCard(CreditCard card);
    
    public List<Map<String,Object>> getModifiedCards();
    public List<Map<String,Object>> getExpiredCards();
    //public Map<String,Object> getSuspendedCards();
}