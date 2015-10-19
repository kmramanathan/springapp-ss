package springapp.repository;

import java.util.List;

import springapp.domain.NSS.NSSRequestBean;
import springapp.domain.NSS.NssProductBean;

public interface NssRequestDao {
	
	/**
	 * Save the request info from the National Security search form
	 * @param bean
	 */
	public void save(NSSRequestBean bean);
	
	/**
	 * 
	 * @param requestId
	 * @return
	 */
	public NSSRequestBean getOneRequest(int requestId);
	
	public List<NSSRequestBean> getRequest(int requestId);
	
	/**
	 * get more results from National Security Search Request using User Id
	 * @param userId
	 * @return
	 */
	public List<NSSRequestBean> getMoreRequest(int userId);
	
	public NssProductBean getProductId(int productId);

}
