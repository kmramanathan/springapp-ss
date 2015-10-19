package springapp.service.billing;

import java.util.List;

import net.searchsystems.limestone.SsTimePeriods;

import org.springframework.stereotype.Service;

import springapp.domain.Rate;

@Service
public interface RateManager {
	public Rate getRate(int rateId);
	public List<Rate> getAllRates();
	public void saveRate(Rate rate);
	
    public List<SsTimePeriods> getAllTimePeriods();
    public SsTimePeriods getTimePeriod(Integer timePeriodId);
}