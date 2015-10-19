package springapp.service.billing;

import java.util.List;

import net.searchsystems.limestone.SsTimePeriods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springapp.domain.Rate;
import springapp.repository.RateDao;

@Service
public class SimpleRateManager implements RateManager {
	@Autowired
	private RateDao rateDao;
	
	public Rate getRate(int rateId) {
		return rateDao.getRateByRateId(rateId);
	}
	public List<Rate> getAllRates() {
		return rateDao.getAllRates();
	}
	public void saveRate(Rate rate) {
		if (rate.getRateId() == 0) {
			rate.setNew(true);
		} else {
			rate.setNew(false);
		}
		rateDao.saveRate(rate);
	}
	
    public List<SsTimePeriods> getAllTimePeriods() {
    	return rateDao.getAllTimePeriods();
    }
    public SsTimePeriods getTimePeriod(Integer timePeriodId) {
    	return rateDao.getTimePeriod(timePeriodId);
    }
}