package springapp.repository;

import java.util.List;



import springapp.domain.AdminUserIPBean;

public interface AdminUserIPDao {
	
	/**
	 * Save the request info from the Userd IP search form
	 * @param bean
	 */
	public void save(AdminUserIPBean  bean);
	
}
