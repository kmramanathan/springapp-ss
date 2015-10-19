package springapp.repository.hbnt;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import springapp.domain.NSS.NSSOffenderBean;
import springapp.domain.NSS.NssOffenseBean;
import springapp.domain.NSS.NssOffenseSupplementBean;
import springapp.domain.NSS.NssResponseBean;
import springapp.repository.NssResponseDao;

@Repository(value="nssResponseDao")
public class HbntNssResponseDao extends HibernateDaoSupport implements NssResponseDao {

	@Autowired
	public HbntNssResponseDao(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
		
	}

	@Override
	public void saveResponse(NssResponseBean resBean) {
		getHibernateTemplate().save(resBean);
		
	}

	@Override
	public NssResponseBean getNssResponseId(int responseId) {
		// TODO Auto-generated method stub
		//List<NssResponseBean> list = getHibernateTemplate().find("from NssResponseBean where nssResponseId="+responseId);
		return (NssResponseBean) getHibernateTemplate().get(NssResponseBean.class, responseId);
	}

	@Override
	public void saveOffenders(NSSOffenderBean offBean) {
		// TODO Auto-generated method stub
		getHibernateTemplate().saveOrUpdate(offBean);
		
	}

	@Override
	public NSSOffenderBean getOffendersId(int offenderId) {
		// TODO Auto-generated method stub
		//List<NSSOffenderBean> list=getHibernateTemplate().find("from NSSOffenderBean where nssOffenderId="+offenderId);
		return (NSSOffenderBean) getHibernateTemplate().get(NSSOffenderBean.class, offenderId);
	}

	@Override
	public void saveOffenses(NssOffenseBean offenseBean) {
		getHibernateTemplate().save(offenseBean);
		
	}

	@Override
	public List<NSSOffenderBean> getOffenderList(int responseId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from NSSOffenderBean where nssResponseId="+responseId);
	}

	@Override
	public List<NssOffenseBean> getOffenseList(int offenderId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from NssOffenseBean where nssOffenderId="+offenderId);
	}

	@Override
	public void saveSupplements(NssOffenseSupplementBean suppBean) {
		// TODO Auto-generated method stub
		
		getHibernateTemplate().save(suppBean);
		
	}

	@Override
	public List<NssOffenseSupplementBean> getSupplementList(int offenseId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from NssOffenseSupplementBean where nssOffenseId ="+offenseId);
	}

	@Override
	public List<NssResponseBean> getNssResponseById(int transactiondId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from NssResponseBean where transactionId="+transactiondId);
	}
	
	
	

}
