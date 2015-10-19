package springapp.repository;

import java.util.List;

import springapp.domain.CorpNameInfo;
import springapp.domain.CorpXMLParser;
import springapp.domain.CorpAddress;
import springapp.domain.CorpHistory;
import springapp.domain.CorpResponses;


public interface CorpDao {
	
	public void insertAllResults(CorpXMLParser cxp);
	public void insertCorpResposes(CorpResponses cp);
	public void corpNameSave(CorpNameInfo ni);
	public void corpAddressSave(CorpAddress ca);
	public void corpHistorySave(CorpHistory ch);
	
	//retrieve the results from the database
	public List<CorpResponses> getAllCorpResponses(long userSearchId);
	public List<CorpNameInfo> getNameAddressList(long userSearchId, String filingNumber);
	public List<CorpHistory> getCorpHistoryList(long userSearchId, String filingNumber);
	public List<CorpAddress> getCorpAddressList(long userSearchId, String filingNumber);
	

}
