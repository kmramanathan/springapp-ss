package springapp.repository.jdbc;

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

import org.apache.torque.NoRowsException;
import org.apache.torque.TooManyRowsException;
import org.apache.torque.TorqueException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import springapp.repository.SearchDao;

@Repository("searchDao")
public class JdbcSearchDao extends AbstractJdbcDao implements SearchDao {	
	protected ParameterizedRowMapper<BGCRequest> getBGCRequestMapper() {
		return ParameterizedBeanPropertyRowMapper.newInstance(BGCRequest.class);
	}
	protected ParameterizedRowMapper<BGCResponse> getBGCResponseMapper() {
		return ParameterizedBeanPropertyRowMapper.newInstance(BGCResponse.class);
	}

	protected ParameterizedRowMapper<springapp.domain.BGCRequest> getBGCRequestMapperOld() {
		return ParameterizedBeanPropertyRowMapper.newInstance(springapp.domain.BGCRequest.class);
	}
	protected ParameterizedRowMapper<springapp.domain.BGCResponse> getBGCResponseMapperOld() {
		return ParameterizedBeanPropertyRowMapper.newInstance(springapp.domain.BGCResponse.class);
	}
	
	public springapp.domain.BGCRequest getBGCRequestOld(int requestId) {
		String sql = sqlStrings.get("selectBgcRequest");
    	try {  
	        return getSimpleJdbcTemplate().queryForObject(sql, getBGCRequestMapperOld(), requestId);
        } catch (EmptyResultDataAccessException e) {        	
        	return null;
        }
    }
	public springapp.domain.BGCResponse getBGCResponseOld(int responseId) {
		String sql = sqlStrings.get("selectBgcResponse");
    	try {  
	        return getSimpleJdbcTemplate().queryForObject(sql, getBGCResponseMapperOld(), responseId);
        } catch (EmptyResultDataAccessException e) {        	
        	return null;
        }
	}
	public springapp.domain.BGCResponse getBGCResponseForRequestOld(int requestId) {
		String sql = sqlStrings.get("selectBgcResponseForRequest");
    	try {  
	        return getSimpleJdbcTemplate().queryForObject(sql, getBGCResponseMapperOld(), requestId);
        } catch (EmptyResultDataAccessException e) {        	
        	return null;
        }
	}
	
	// uses torque 
	public BGCOffense getBGCOffense(int offenseId) throws NoRowsException, TooManyRowsException, TorqueException {
		return BGCOffensePeer.retrieveByPK(offenseId);
	}
	public BGCRequest getBGCRequest(int requestId) throws NoRowsException, TooManyRowsException, TorqueException {
		return BGCRequestPeer.retrieveByPK(requestId);
	}
	public BGCResponse getBGCResponse(int responseId) throws NoRowsException, TooManyRowsException, TorqueException {
		return BGCResponsePeer.retrieveByPK(responseId);
	}
	public BGCAlias getBGCAlias(int aliasId) throws NoRowsException, TooManyRowsException, TorqueException {
		return BGCAliasPeer.retrieveByPK(aliasId);
	}
	public BGCOffender getBGCOffender(int offenderId) throws NoRowsException, TooManyRowsException, TorqueException {
		return BGCOffenderPeer.retrieveByPK(offenderId);
	}
	public BGCOffenseSupplement getBGCOffenseSupplement(int suppId) throws NoRowsException, TooManyRowsException, TorqueException {
		return BGCOffenseSupplementPeer.retrieveByPK(suppId);
	}
	
	public BGCResponse getBGCResponseForRequest(int requestId) throws NoRowsException, TooManyRowsException, TorqueException {
		BGCRequest req = getBGCRequest(requestId);
		List<BGCResponse> list = req.getBGCResponses();
		return list.get(0);
	}
		
	public List<BGCAlias> getAliases(int offenderId) throws NoRowsException, TooManyRowsException, TorqueException {
		return getBGCOffender(offenderId).getBGCAliass();
	}
	public List<BGCOffender> getOffenders(int responseId) throws NoRowsException, TooManyRowsException, TorqueException {
		return getBGCResponse(responseId).getBGCOffenders();
	}
	public List<BGCOffenseSupplement> getOffenseSupplements(int offenseId) throws NoRowsException, TooManyRowsException, TorqueException {
		return getBGCOffense(offenseId).getBGCOffenseSupplements();
	}
	public List<BGCOffense> getOffenses(int offenderId) throws NoRowsException, TooManyRowsException, TorqueException {
		return getBGCOffender(offenderId).getBGCOffenses();
	}	

	// remote call to fetch details
	public void fetchOffenderDetails(List<Integer> offendersToGet) {
		// XXX do this somehow, not strictly a repository function
	}


}