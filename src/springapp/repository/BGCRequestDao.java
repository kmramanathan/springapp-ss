package springapp.repository;



import java.util.List;

import springapp.domain.BGCRequestBean;
import springapp.domain.CriminalPurposesBean;

public interface BGCRequestDao {
	//save the data into db
	public void bgcpurposeSave(BGCRequestBean brb);
	//retrieve the data from database 
	public List<BGCRequestBean> getPurposeResult(int requestid);
	public List<CriminalPurposesBean> getPurposeMatch(String puposename);

}
