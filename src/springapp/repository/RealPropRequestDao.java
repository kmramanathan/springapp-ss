package springapp.repository;

import java.util.List;


import springapp.domain.RealProperty.RealPropRequestBean;

public interface RealPropRequestDao {
	
	/**
	 * Save the request info from the RealProeprty search form
	 * @param bean
	 */
	public void save(RealPropRequestBean bean);
	
	/**
	 * 
	 * @param requestId
	 * @return
	 */
	public RealPropRequestBean getOneRequest(int requestId);
	
	public List<RealPropRequestBean> getRequest(int requestId);
	
	/**
	 * get more results from Real Property Search Request using User Id
	 * @param userId
	 * @return
	 */
	
	public List<RealPropRequestBean> getMoreRequest(int userId);
	
	public List<RealPropRequestBean> getRequestByTransID(int iTransactionID);

}
