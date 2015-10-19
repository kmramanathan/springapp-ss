package springapp.repository.hbnt;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import springapp.domain.NSS.NSSRequestBean;
import springapp.domain.NSS.NssProductBean;
import springapp.repository.NssRequestDao;


@Repository(value="nssRequestDao")
public class HbntNssRequestDao extends HibernateDaoSupport implements NssRequestDao
{

	@Autowired
	public HbntNssRequestDao(SessionFactory sessionFactory)
	{
		setSessionFactory(sessionFactory);
	}
	
	
	public void save(NSSRequestBean bean) {
		// TODO Auto-generated method stub
		
		getHibernateTemplate().save(bean);
		
	}
	

	@Override
	public NSSRequestBean getOneRequest(int requestId) {
		// TODO Auto-generated method stub
		//List<NSSRequestBean> list=getHibernateTemplate().find("from NSSRequestBean where bgcRequestId="+requestId);
		return (NSSRequestBean) getHibernateTemplate().get(NSSRequestBean.class, requestId);
	}


	@Override
	public List<NSSRequestBean> getMoreRequest(int userId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from NSSRequestBean where userId="+userId);
	}


	@Override
	public NssProductBean getProductId(int productId) {
		// TODO Auto-generated method stub
		
		return (NssProductBean) getHibernateTemplate().get(NssProductBean.class, productId);
	}


	@Override
	public List<NSSRequestBean> getRequest(int requestId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from NSSRequestBean where bgcRequestId="+requestId);
	}

}
