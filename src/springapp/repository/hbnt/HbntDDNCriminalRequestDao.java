package springapp.repository.hbnt;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import springapp.domain.CriminalSearch.CriminalRequestBean;
import springapp.repository.CriminalRequestDao;


@Repository(value="CriminalRequestDao")
public class HbntDDNCriminalRequestDao extends HibernateDaoSupport implements CriminalRequestDao
{

	@Autowired
	public HbntDDNCriminalRequestDao(SessionFactory sessionFactory)
	{
		setSessionFactory(sessionFactory);
	}
	
	
	public void save(CriminalRequestBean bean) {
		
		// TODO Auto-generated method stub		
		getHibernateTemplate().save(bean);
		
	}
	

	@Override
	public CriminalRequestBean getOneRequest(int requestId) {
		// TODO Auto-generated method stub

		return (CriminalRequestBean) getHibernateTemplate().get(CriminalRequestBean.class, requestId);
	}


	@Override
	public List<CriminalRequestBean> getMoreRequest(int userId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from CriminalRequestBean where userId="+userId);
	}


	@Override
	public List<CriminalRequestBean> getRequest(int iTransactionID) 
	{
		// TODO Auto-generated method stub
		logger.info("hybynt_DDN_Request::::"+ getHibernateTemplate().find("from CriminalRequestBean where transaction_id="+iTransactionID));		
		return getHibernateTemplate().find("from CriminalRequestBean where transaction_id="+iTransactionID);
	}
	
	@Override
	public List<CriminalRequestBean> getRequestByTransID(int transactionID)
	{
		List<CriminalRequestBean> lstReq = new ArrayList<CriminalRequestBean>();
		try
		{			
			
			 lstReq = getHibernateTemplate().find("from CriminalRequestBean where transactionId="+transactionID);
		}
		catch (Exception e)
		{
			logger.info("DDN Ciminl Request###:::" + e.getStackTrace());
		}
		
		return lstReq ;
	}
	

}
