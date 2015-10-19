package springapp.repository;

import java.util.List;

import springapp.domain.CriminalSSN.CriminalSSNResponseBean; 

public interface CriminalSSNResponseDao {
	
	/**
	 * Response 
	 * @param resBean
	 */
	public void saveResponse(CriminalSSNResponseBean resBean);
	
	
}
