package springapp.repository;

import java.util.List;

import springapp.domain.NSS.NSSOffenderBean;
import springapp.domain.NSS.NssOffenseBean;
import springapp.domain.NSS.NssOffenseSupplementBean;
import springapp.domain.NSS.NssResponseBean;

public interface NssResponseDao {
	
	/**
	 * Response 
	 * @param resBean
	 */
	public void saveResponse(NssResponseBean resBean);
	public NssResponseBean getNssResponseId(int responseId);
	
	public List<NssResponseBean> getNssResponseById(int transactiondId);
	//offenders
	public void saveOffenders(NSSOffenderBean offBean);
	public NSSOffenderBean getOffendersId(int offenderId);
	public List<NSSOffenderBean> getOffenderList(int responseId);
	//public List<NSSOffenderBean> geto
	
	
	//offenses
	public void saveOffenses(NssOffenseBean offenseBean);
	public List<NssOffenseBean> getOffenseList(int offenderId);
	
	//supplements
	public void saveSupplements(NssOffenseSupplementBean suppBean);
	public List<NssOffenseSupplementBean> getSupplementList(int offenseId);

}
