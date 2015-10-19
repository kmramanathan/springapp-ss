package springapp.repository;

import java.util.List;

import springapp.domain.NewBJLSearchBean;

public interface BJLSearchDao {
	
	public void bjlsave(NewBJLSearchBean bjlbean);
	public List<NewBJLSearchBean> getBJLSearch(String username, long userId);

}
