package springapp.repository.hbnt;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Node;

import springapp.domain.FRBGCResponse;  
import springapp.domain.CriminalSSN.CriminalSSNRequestBean;
import springapp.repository.CriminalSSNRequestDao; 


@Repository(value="CriminalSSNRequestDao")
public class HbntCriminalSSNRequestDao extends HibernateDaoSupport implements CriminalSSNRequestDao
{

	@Autowired
	public HbntCriminalSSNRequestDao(SessionFactory sessionFactory)
	{
		setSessionFactory(sessionFactory);
	}
	
	
	public void save(CriminalSSNRequestBean bean) {
		// TODO Auto-generated method stub
		
		getHibernateTemplate().save(bean);
		
	}
	

	@Override
	public CriminalSSNRequestBean getOneRequest(int requestId) {
		// TODO Auto-generated method stub
		return (CriminalSSNRequestBean) getHibernateTemplate().get(CriminalSSNRequestBean.class, requestId);
	}


	@Override
	public List<CriminalSSNRequestBean> getMoreRequest(int userId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from CriminalSSNRequestBean where userId="+userId);
	}

 

	@Override
	public List<CriminalSSNRequestBean> getRequest(int iTransactionID) 
	{
		// TODO Auto-generated method stub
		logger.info("hybynt_RealProp_Report::::"+ getHibernateTemplate().find("from CriminalSSNRequestBean where transaction_id="+iTransactionID));		
		return getHibernateTemplate().find("from CriminalSSNRequestBean where transaction_id="+iTransactionID);
	}
	
	@Override
	public List<CriminalSSNRequestBean> getRequestByTransID(int transactionID)
	{
		List<CriminalSSNRequestBean> lstReq = new ArrayList<CriminalSSNRequestBean>();
		try
		{			
			 /*logger.info("hybynt_RealProp_Report::::"+ getHibernateTemplate().find("from RealPropRequestBean where transactionId="+transactionID));			 
			 logger.info("RP_List Count::--"+ lstReq.size());
			 for(int i=0; i<lstReq.size(); i++)
			 {
				RealPropRequestBean bean = lstReq.get(i);				
				logger.info("TransactionID is ##::"+ bean.getTransactionId());//userSearchId
			 }*/
			
			 lstReq = getHibernateTemplate().find("from CriminalSSNRequestBean where transactionId="+transactionID);
		}
		catch (Exception e)
		{
			logger.info("CriminalSSN Request###:::" + e.getStackTrace());
		}
		
		return lstReq ;
	}
	

}
