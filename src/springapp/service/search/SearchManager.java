package springapp.service.search;

import java.util.List;

import org.apache.torque.TorqueException;

import springapp.domain.NSS.NSSRequestBean;
import springapp.domain.NSS.NssResponseBean;

public interface SearchManager {
	public springapp.domain.BGCRequest getBGCRequest(int requestId);
	
	public springapp.domain.BGCResponse getBGCResponse(int responseId);
	
	public springapp.domain.BGCResponse getBGCResponseForRequest(int requestId);
	
	public void runTestSearch() throws TorqueException;
	
	public List<net.searchsystems.limestone.BGCRequest> getSearchesForUser(int userId);
	public void deleteSearch(int requestId);
	
	public List<NSSRequestBean> getNssRequest(int requestId);
	public List<NssResponseBean> getNssResponse(int transactionId);
	
}