package springapp.service.registration;

import springapp.domain.User;

public interface RegistrationManager {
	public User registerNewUser(Registration reg);
}