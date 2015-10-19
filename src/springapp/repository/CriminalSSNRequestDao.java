package springapp.repository;

import java.util.List;


import springapp.domain.CriminalSSN.CriminalSSNRequestBean;

public interface CriminalSSNRequestDao {
	
	/**
	 * Save the request info from the CriminalSSN search form
	 * @param bean
	 */
	public void save(CriminalSSNRequestBean bean);
	
	/**
	 * 
	 * @param requestId
	 * @return
	 */
	public CriminalSSNRequestBean getOneRequest(int requestId);
	
	public List<CriminalSSNRequestBean> getRequest(int requestId);
	
	/**
	 * get more results from Real Property Search Request using User Id
	 * @param userId
	 * @return
	 */
	
	public List<CriminalSSNRequestBean> getMoreRequest(int userId);
	
	public List<CriminalSSNRequestBean> getRequestByTransID(int iTransactionID);

}
