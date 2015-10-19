package springapp.service.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import springapp.domain.User;
import springapp.repository.RateDao;
import springapp.repository.SubscriptionDao;
import springapp.repository.UserDao;
import springapp.service.NeonService;

@Service
public class SimpleRegistrationManager extends NeonService implements RegistrationManager {
    @Autowired @Qualifier("userDao")
	private UserDao userDao;
    @Autowired @Qualifier("rateDao")
	private RateDao rateDao;
    @Autowired @Qualifier("subscriptionDao")
	private SubscriptionDao subscriptionDao;
   
    public User registerNewUser(Registration reg) {
    	return null;
    }
}