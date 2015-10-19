package springapp.repository.hbnt;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import springapp.domain.RealProperty.RealPropResponseBean;
import springapp.repository.RealPropResponseDao;

@Repository(value="RealPropResponseDao")
public class HbntRealPropResponseDao extends HibernateDaoSupport implements RealPropResponseDao {

	@Autowired
	public HbntRealPropResponseDao(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);		
	}

	@Override 
	public void saveResponse(RealPropResponseBean resBean) {
		getHibernateTemplate().save(resBean);
		
	}

}


