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
import springapp.domain.RealProperty.RealPropRequestBean;
import springapp.domain.RealProperty.RealPropResponseBean;
import springapp.repository.RealPropRequestDao;


@Repository(value="RealPropRequestDao")
public class HbntRealPropRequestDao extends HibernateDaoSupport implements RealPropRequestDao
{

	@Autowired
	public HbntRealPropRequestDao(SessionFactory sessionFactory)
	{
		setSessionFactory(sessionFactory);
	}
	
	
	public void save(RealPropRequestBean bean) {
		// TODO Auto-generated method stub
		
		getHibernateTemplate().save(bean);
		
	}
	

	@Override
	public RealPropRequestBean getOneRequest(int requestId) {
		// TODO Auto-generated method stub
		//List<RealPropRequestBean> list=getHibernateTemplate().find("from RealPropRequestBean where bgcRequestId="+requestId);
		return (RealPropRequestBean) getHibernateTemplate().get(RealPropRequestBean.class, requestId);
	}


	@Override
	public List<RealPropRequestBean> getMoreRequest(int userId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from RealPropRequestBean where userId="+userId);
	}


	/*@Override
	public NssProductBean getProductId(int productId) {
		// TODO Auto-generated method stub
		
		return (NssProductBean) getHibernateTemplate().get(NssProductBean.class, productId);
	}
	 */

	@Override
	public List<RealPropRequestBean> getRequest(int iTransactionID) 
	{
		// TODO Auto-generated method stub
		logger.info("hybynt_RealProp_Report::::"+ getHibernateTemplate().find("from RealPropRequestBean where transaction_id="+iTransactionID));		
		return getHibernateTemplate().find("from RealPropRequestBean where transaction_id="+iTransactionID);
	}
	
	@Override
	public List<RealPropRequestBean> getRequestByTransID(int transactionID)
	{
		List<RealPropRequestBean> lstReq = new ArrayList<RealPropRequestBean>();
		try
		{			
			 /*logger.info("hybynt_RealProp_Report::::"+ getHibernateTemplate().find("from RealPropRequestBean where transactionId="+transactionID));			 
			 logger.info("RP_List Count::--"+ lstReq.size());
			 for(int i=0; i<lstReq.size(); i++)
			 {
				RealPropRequestBean bean = lstReq.get(i);				
				logger.info("TransactionID is ##::"+ bean.getTransactionId());//userSearchId
			 }*/
			
			 lstReq = getHibernateTemplate().find("from RealPropRequestBean where transactionId="+transactionID);
		}
		catch (Exception e)
		{
			logger.info("RealProp Request###:::" + e.getStackTrace());
		}
		
		return lstReq ;
	}
	

}
