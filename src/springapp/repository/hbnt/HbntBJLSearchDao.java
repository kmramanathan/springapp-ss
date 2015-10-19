package springapp.repository.hbnt;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import springapp.domain.NewBJLSearchBean;
import springapp.domain.NewBJLSearchesBean;
import springapp.repository.BJLSearchDao;

@Repository(value="bjlSearchDao")
public class HbntBJLSearchDao extends HibernateDaoSupport implements BJLSearchDao {
	
	@Autowired
	public HbntBJLSearchDao(SessionFactory sessionFactory)
	{
		setSessionFactory(sessionFactory);
	}

	@Override
	public void bjlsave(NewBJLSearchBean bjlbean) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(bjlbean);
		
		
	}

	@Override
	public List<NewBJLSearchBean> getBJLSearch(String username, long userId) {
		// TODO Auto-generated method stub
		Session session =(Session) this.getSession();
		//Criteria cr=session.createCriteria(NewBJLSearchBean.class).add("username",username);
		//cr.add(null);
		//cr.add("username",username);
		//cr.add(userId);
		
		session.flush();
		return null;
	}

}
