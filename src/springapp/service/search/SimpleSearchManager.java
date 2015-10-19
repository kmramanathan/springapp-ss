package springapp.service.search;

import java.util.ArrayList;
import java.util.List;

import net.searchsystems.limestone.BGCAlias;
import net.searchsystems.limestone.BGCAliasPeer;
import net.searchsystems.limestone.BGCOffender;
import net.searchsystems.limestone.BGCOffenderPeer;
import net.searchsystems.limestone.BGCOffense;
import net.searchsystems.limestone.BGCOffensePeer;
import net.searchsystems.limestone.BGCOffenseSupplement;
import net.searchsystems.limestone.BGCOffenseSupplementPeer;
import net.searchsystems.limestone.BGCRequest;
import net.searchsystems.limestone.BGCRequestPeer;
import net.searchsystems.limestone.BGCResponse;
import net.searchsystems.limestone.BGCResponsePeer;
import net.searchsystems.limestone.SsUsers;
import net.searchsystems.limestone.SsUsersPeer;
import net.searchsystems.limestone.bean.BGCResponseBean;

import org.apache.log4j.Logger;
import org.apache.torque.NoRowsException;
import org.apache.torque.TooManyRowsException;
import org.apache.torque.TorqueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springapp.domain.NSS.NSSRequestBean;
import springapp.domain.NSS.NssResponseBean;
import springapp.manager.SpringBGCSearchManager;
import springapp.manager.SpringNSSSearchManager;
import springapp.repository.NssRequestDao;
import springapp.repository.NssResponseDao;
import springapp.repository.SearchDao;

@Service
public class SimpleSearchManager implements SearchManager {
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private SearchDao searchDao;
	
	@Autowired
	private SpringBGCSearchManager bgcManager;
	
	
	
	@Autowired
	private NssResponseDao responseDao;
	@Autowired
	private NssRequestDao requestDao;
	
	public springapp.domain.BGCRequest getBGCRequest(int requestId) {
		return searchDao.getBGCRequestOld(requestId);
	}
	
	public springapp.domain.BGCResponse getBGCResponse(int responseId) {
		return searchDao.getBGCResponseOld(responseId);
	}

	public springapp.domain.BGCResponse getBGCResponseForRequest(int requestId) {
		return searchDao.getBGCResponseForRequestOld(requestId);
	}
	
	public void runTestSearch() throws TorqueException {
		int reqId = bgcManager.prepareSearchTest();
		BGCResponseBean res = bgcManager.runSearch(reqId);
		logger.info("found: " + res.getQuantityFound());
		logger.info("returned: " + res.getQuantityReturned());
	}
	
	public List<net.searchsystems.limestone.BGCRequest> getSearchesForUser(int userId) {
		List<net.searchsystems.limestone.BGCRequest> requests = new ArrayList<BGCRequest>();
		try {
			SsUsers user = SsUsersPeer.retrieveByPK(userId);
			requests.addAll(user.getBGCRequests());
			return requests;
			
		} catch (NoRowsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TooManyRowsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TorqueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return requests;
	}
	
	public void deleteSearch(int requestId) {
		try {
			BGCRequest request = searchDao.getBGCRequest(requestId);
			for (BGCResponse response : request.getBGCResponses()) {
				for (BGCOffender offender : response.getBGCOffenders()) {
					for (BGCOffense offense : offender.getBGCOffenses()) {
						for (BGCOffenseSupplement supp : offense.getBGCOffenseSupplements()) {
							logger.info("supp: " + supp.getBgcOffenseSupplementId());
							BGCOffenseSupplementPeer.doDelete(supp);
						}
						logger.info("offense: " + offense.getBgcOffenseId());
						BGCOffensePeer.doDelete(offense);
					}
					for (BGCAlias alias : offender.getBGCAliass()) {
						logger.info("alias: " + alias.getBgcAliasId());
						BGCAliasPeer.doDelete(alias);
					}
					logger.info("offender: " + offender.getBgcOffenderId());				
					BGCOffenderPeer.doDelete(offender);
				}
				logger.info("response: " + response.getBgcResponseId());
				BGCResponsePeer.doDelete(response);
			}
			logger.info("request: " + request.getBgcRequestId());
			BGCRequestPeer.doDelete(request);

		} catch (NoRowsException e) {
			logger.error(e);
		} catch (TooManyRowsException e) {
			logger.error(e);
		} catch (TorqueException e) {
			logger.error(e);
		}
	}

	@Override
	public List<NSSRequestBean> getNssRequest(int requestId) {
		// TODO Auto-generated method stub
		return requestDao.getRequest(requestId);
	}

	@Override
	public List<NssResponseBean> getNssResponse(int transactionId) {
		// TODO Auto-generated method stub
		return responseDao.getNssResponseById(transactionId);
	}


	
}