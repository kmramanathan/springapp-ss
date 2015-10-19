package springapp.repository;

import java.util.List;

import org.apache.torque.NoRowsException;
import org.apache.torque.TooManyRowsException;
import org.apache.torque.TorqueException;

import net.searchsystems.limestone.BGCOffender;
import net.searchsystems.limestone.BGCOffense;
import net.searchsystems.limestone.BGCOffenseSupplement;
import net.searchsystems.limestone.BGCRequest;
import net.searchsystems.limestone.BGCResponse;
import net.searchsystems.limestone.BGCAlias;

public interface SearchDao {
	// old style: JdbcTemplate, etc
	// new style: Torque
	
	// get single row methods
	// old, PK
	public springapp.domain.BGCRequest getBGCRequestOld(int requestId);	
	public springapp.domain.BGCResponse getBGCResponseOld(int responseId);

	// old, non-PK
	public springapp.domain.BGCResponse getBGCResponseForRequestOld(int requestId);
	
	// new, PK
	public BGCRequest getBGCRequest(int requestId) throws NoRowsException, TooManyRowsException, TorqueException ;
	public BGCResponse getBGCResponse(int responseId) throws NoRowsException, TooManyRowsException, TorqueException ;
	public BGCOffense getBGCOffense(int offenseId) throws NoRowsException, TooManyRowsException, TorqueException ;	
	public BGCOffender getBGCOffender(int offenderId) throws NoRowsException, TooManyRowsException, TorqueException ;
	public BGCAlias getBGCAlias(int aliasId) throws NoRowsException, TooManyRowsException, TorqueException ;
	public BGCOffenseSupplement getBGCOffenseSupplement(int supplementId) throws NoRowsException, TooManyRowsException, TorqueException ;

	// new, non-PK
	public BGCResponse getBGCResponseForRequest(int requestId) throws NoRowsException, TooManyRowsException, TorqueException ;

	// get list methods
	public List<BGCOffender> getOffenders(int responseId) throws NoRowsException, TooManyRowsException, TorqueException ;
	public List<BGCOffense> getOffenses(int offenderId) throws NoRowsException, TooManyRowsException, TorqueException ;
	public List<BGCAlias> getAliases(int offenderId) throws NoRowsException, TooManyRowsException, TorqueException ;
	public List<BGCOffenseSupplement> getOffenseSupplements(int offenseId) throws NoRowsException, TooManyRowsException, TorqueException ;
	
	// this is used to fetch details from a summary
	public void fetchOffenderDetails(List<Integer> offendersToGet) throws NoRowsException, TooManyRowsException, TorqueException ;

	// run a search
}