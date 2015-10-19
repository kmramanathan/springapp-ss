package springapp.repository.hbnt;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import springapp.domain.CorpXMLParser;
import springapp.domain.corporation.CorporationSearches;
import springapp.repository.CorpDao;
import springapp.domain.CorpAddress;
import springapp.domain.CorpHistory;
import springapp.domain.CorpNameInfo;
import springapp.domain.CorpResponses;


@Repository("corpDao")
public class HbntCorpDao extends HibernateDaoSupport implements CorpDao {

	@Autowired
	public HbntCorpDao(SessionFactory sessionFactory)
	{
		setSessionFactory(sessionFactory);
	}
	public void insertAllResults(CorpXMLParser cxp) {
		
		CorporationSearches corpsearch=new CorporationSearches();
		cxp.setUserSearchId(corpsearch.getUserSearchId());
		getHibernateTemplate().save(cxp);
	}
	
	public void corpAddressSave(CorpAddress ca) {
		
		getHibernateTemplate().save(ca);
		
	}
	
	public void corpHistorySave(CorpHistory ch) {
		// TODO Auto-generated method stub
		
		getHibernateTemplate().save(ch);
		
	}
	
	public void corpNameSave(CorpNameInfo ni) {
		// TODO Auto-generated method stub
		
		getHibernateTemplate().save(ni);
		
	}
	
	public void insertCorpResposes(CorpResponses cp) {
		// TODO Auto-generated method stub
		
		getHibernateTemplate().save(cp);
		
	}
	
	public List<CorpResponses> getAllCorpResponses(long userSearchId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("FROM CorpResponses WHERE user_search_id ="+userSearchId);
	}
	
	public List<CorpAddress> getCorpAddressList(long userSearchId,
			String filingNumber) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("FROM CorpAddress WHERE user_search_id="+userSearchId+" AND filing_number='"+filingNumber.toUpperCase()+"'");
	}
	
	public List<CorpHistory> getCorpHistoryList(long userSearchId,
			String filingNumber) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("FROM CorpHistory WHERE user_search_id="+userSearchId+" AND filing_number='"+filingNumber.toUpperCase()+"'");
	}
	
	public List<CorpNameInfo> getNameAddressList(long userSearchId,
			String filingNumber) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("FROM CorpNameInfo WHERE user_search_id="+userSearchId+" AND filing_number='"+filingNumber.toUpperCase()+"'");
	}

}
