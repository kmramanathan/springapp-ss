package springapp.repository;

import java.util.List;
import net.searchsystems.limestone.SsTimePeriods;

import springapp.domain.Rate;

public interface RateDao {   
    public Rate getRateByRateId(int rateId);
    public List<Rate> getAllRates();
    public List<Rate> getAllRates(boolean includeInactive);
    public void saveRate(Rate rate); 
    
    public List<SsTimePeriods> getAllTimePeriods();
    public SsTimePeriods getTimePeriod(Integer timePeriodId);
    
}