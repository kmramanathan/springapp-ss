package springapp.repository.hbnt;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import springapp.domain.CriminalSSN.CriminalSSNResponseBean;
import springapp.domain.RealProperty.RealPropResponseBean;
import springapp.repository.CriminalSSNResponseDao;
import springapp.repository.RealPropResponseDao;

@Repository(value="CriminalSSNResponseDao")
public class HbntCriminalSSNResponseDao extends HibernateDaoSupport implements CriminalSSNResponseDao {

	@Autowired
	public HbntCriminalSSNResponseDao(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);		
	}

	@Override 
	public void saveResponse(CriminalSSNResponseBean resBean) {
		getHibernateTemplate().save(resBean);
		
	}

}


