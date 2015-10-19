package springapp.repository;

import java.util.List;


import springapp.domain.CriminalSearch.CriminalRequestBean;

public interface CriminalRequestDao {
	
	/**
	 * Save the request info from the RealProeprty search form
	 * @param bean
	 */
	public void save(CriminalRequestBean bean);
	
	/**
	 * 
	 * @param requestId
	 * @return
	 */
	
	public CriminalRequestBean getOneRequest(int requestId);
	
	public List<CriminalRequestBean> getRequest(int requestId);
	
	/**
	 * get more results from Real Property Search Request using User Id
	 * @param userId
	 * @return
	 */
	
	public List<CriminalRequestBean> getMoreRequest(int userId);
	
	public List<CriminalRequestBean> getRequestByTransID(int iTransactionID);

}
