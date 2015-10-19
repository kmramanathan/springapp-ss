package springapp.repository.hbnt;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import springapp.domain.AdminUserIPBean;
import springapp.repository.AdminUserIPDao;



@Repository(value="AdminUserIPDao")
public class HbntAdminUserIPDao extends HibernateDaoSupport implements AdminUserIPDao
{
	protected Logger logger = Logger.getLogger(getClass());

	@Autowired
	public HbntAdminUserIPDao(SessionFactory sessionFactory)
	{
		setSessionFactory(sessionFactory);
	}
	
	
	public void save(AdminUserIPBean bean) {
		// TODO Auto-generated method stub
		logger.info("User ID-->Dao: "+ bean.getUserId());	
		getHibernateTemplate().save(bean);
		
	}
	

	

}
