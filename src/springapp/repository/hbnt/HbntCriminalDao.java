package springapp.repository.hbnt;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import springapp.domain.BGCRequestBean;
import springapp.domain.CriminalPurposesBean;
import springapp.repository.BGCRequestDao;



@Repository("bGCRequestDao")
public class HbntCriminalDao extends HibernateDaoSupport implements BGCRequestDao {

	@Autowired
	public HbntCriminalDao(SessionFactory sessionFactory)
	{
		setSessionFactory(sessionFactory);
	}

	
	public void bgcpurposeSave(BGCRequestBean brb) {
		getHibernateTemplate().save(brb);
		
	}


	
	public List<BGCRequestBean> getPurposeResult(int requestid) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("FROM BGCRequestBean WHERE bgc_request_id="+requestid);
	}

	public List<CriminalPurposesBean> getPurposeMatch(String puposename) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("FROM CriminalPurposesBean WHERE title='"+puposename+"'");
	}
	
}
