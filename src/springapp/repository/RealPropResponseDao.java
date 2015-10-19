package springapp.repository;

import java.util.List;

import springapp.domain.NSS.NSSOffenderBean;
import springapp.domain.NSS.NssOffenseBean;
import springapp.domain.NSS.NssOffenseSupplementBean;
import springapp.domain.NSS.NssResponseBean;
import springapp.domain.RealProperty.RealPropResponseBean;

public interface RealPropResponseDao {
	
	/**
	 * Response 
	 * @param resBean
	 */
	public void saveResponse(RealPropResponseBean resBean);
	
	
}
