package net.rrworld.valorant.client.ratelimiter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import net.rrworld.valorant.client.model.Match;

public class RiotRateLimiter {

	/**
	 * Unique instance
	 */
	private static RiotRateLimiter INSTANCE;
	
	
	// Registry
	private RateLimiterRegistry registry;

	// Application rate limiters
	private RateLimiter appShortRL;
	private RateLimiter appLongRL;
	
	// VAL-MATCH-V1 GET_getMatch Method rate limiters
	private List<RateLimiter> getMatchRL = new ArrayList<>();
	
	
    /**
     * Private constructor
     */
    private RiotRateLimiter() {
    	// Default Application config
    	// 20 requests every 1 seconds
    	RateLimiterConfig appShortConfig = RateLimiterConfig.custom()
    			.limitForPeriod(20)
    			.limitRefreshPeriod(Duration.ofSeconds(1))
    			.timeoutDuration(Duration.ofMillis(5000))
    			.build();
    	// 100 requests every 2 minutes
    	RateLimiterConfig appLongConfig = RateLimiterConfig.custom()
    			.limitForPeriod(100)
    			.limitRefreshPeriod(Duration.ofSeconds(120))
    			.timeoutDuration(Duration.ofMillis(5000))
    			.build();   	
    	
		this.registry = RateLimiterRegistry.of(appShortConfig);
		this.appShortRL = registry.rateLimiter("shortAppLimit", appShortConfig);
		this.appLongRL = registry.rateLimiter("longAppLimit", appLongConfig);
    }
    
    /**
     * Gets unique instance of <code>RateLimiterRegistry</code>
     * @return unique instance
     */
    public static RiotRateLimiter getInstance() {
    	if(INSTANCE == null) {
    		INSTANCE = new RiotRateLimiter();
    	}
    	return INSTANCE;
    }
    
    public Match getMatch(Supplier<Match> getMatchMethod){
    	Supplier<Match> m = RateLimiter.decorateSupplier(this.appShortRL, getMatchMethod);
    	m = RateLimiter.decorateSupplier(this.appLongRL, m);
    	for (RateLimiter rl : getMatchRL) {
			m = RateLimiter.decorateSupplier(rl, m);
		}
    	return m.get();
    }
    
    public void updateGetMatchLimits(List<String> appRates, List<String> methodRates) {
    	if(getMatchRL.isEmpty()) {
    		int i = 0;
        	for (String s : methodRates) {
    			String[] rate = s.split(":");
    			Integer permits = Integer.parseInt(rate[0]);
    			Long period = Long.parseLong(rate[1]); // seconds
    	    	RateLimiterConfig rlc = RateLimiterConfig.custom()
    	    			.limitForPeriod(permits)
    	    			.limitRefreshPeriod(Duration.ofSeconds(period))
    	    			.timeoutDuration(Duration.ofMillis(5000))
    	    			.build();
    	    	getMatchRL.add(registry.rateLimiter("getMatchRL#"+i, rlc));
    	    	i++;
    		}
    	}
    }

	public void updateGetMatchListLimits(List<String> appLimits, List<String> methodLimits) {
		// TODO Auto-generated method stub
		
	}
	
}
