package net.rrworld.valorant.client.ratelimiter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import net.rrworld.valorant.client.model.Match;
import net.rrworld.valorant.client.model.Matchlist;

/*
 * Example :
 * 	App
 *   20 requests every 1 seconds
 *   100 requests every 2 minutes
 * Match method 
 *  400 requests every 10 seconds
 * 
 *   "X-App-Rate-Limit": "20:1,100:120",
 *   "X-App-Rate-Limit-Count": "1:1,1:120",
 *   "X-Method-Rate-Limit": "400:10",
 *   "X-Method-Rate-Limit-Count": "1:10",
 */
public class RiotRateLimiter {
	
	/**
	 * Application rate limit response header
	 */
	public static final String APP_RATE_LIMIT_HEADER = "X-App-Rate-Limit";
	/**
	 * Application rate limit count response header
	 */
	public static final String APP_RATE_LIMITE_COUNT_HEADER = "X-App-Rate-Limit-Count";
	/**
	 * Method rate limit response header
	 */
	public static final String METHOD_RATE_LIMIT_HEADER = "X-Method-Rate-Limit";
	/**
	 * Method rate limit count response header
	 */
	public static final String METHOD_RATE_LIMIT_COUNT_HEADER = "X-Method-Rate-Limit-Count";

	/**
	 * Unique instance
	 */
	private static RiotRateLimiter INSTANCE;
	
	
	// Registry
	private RateLimiterRegistry registry;

	// Application level rate limiters
	private RateLimiter appShortRL;
	private RateLimiter appLongRL;
	
	// VAL-MATCH-V1 GET_getMatch Method rate limiters
	private List<RateLimiter> getMatchRL = new ArrayList<>();
	private List<RateLimiter> getMatchlistRL = new ArrayList<>();
	
	
    /**
     * Private constructor
     */
    private RiotRateLimiter() {
    	// Default Application config
		this.registry = RateLimiterRegistry.ofDefaults();
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
    	// First call, no RL
    	if(appShortRL == null && appLongRL == null) {
    		return getMatchMethod.get();
    	}
    	Supplier<Match> m = RateLimiter.decorateSupplier(this.appShortRL, getMatchMethod);
    	m = RateLimiter.decorateSupplier(this.appLongRL, m);
    	for (RateLimiter rl : getMatchRL) {
			m = RateLimiter.decorateSupplier(rl, m);
		}
    	return m.get();
    }
    
    public Matchlist getMatchlist(Supplier<Matchlist> getMatchlistMethod){
    	// First call, no RL
    	if(appShortRL == null && appLongRL == null) {
    		return getMatchlistMethod.get();
    	}
    	Supplier<Matchlist> m = RateLimiter.decorateSupplier(this.appShortRL, getMatchlistMethod);
    	m = RateLimiter.decorateSupplier(this.appLongRL, m);
    	for (RateLimiter rl : getMatchlistRL) {
			m = RateLimiter.decorateSupplier(rl, m);
		}
    	return m.get();
    }
    
    
    public void updateGetMatchLimits(List<String> appRates, List<String> methodRates) {
    	if(getMatchRL.isEmpty()) {
    		
    		this.appShortRL = registry.rateLimiter("shortAppLimit", buildRateLimiterConfig(appRates.get(0)));
    		this.appLongRL = registry.rateLimiter("longAppLimit", buildRateLimiterConfig(appRates.get(1)));
    		
    		int i = 0;
        	for (String s : methodRates) {
    			RateLimiterConfig rlc = buildRateLimiterConfig(s);
    	    	this.getMatchRL.add(registry.rateLimiter("getMatchRL#"+i, rlc));
    	    	i++;
    		}
    	}
    }

	public void updateGetMatchListLimits(List<String> appRates, List<String> methodRates) {
    	if(getMatchlistRL.isEmpty()) {
    		int i = 0;
        	for (String s : methodRates) {
    			RateLimiterConfig rlc = buildRateLimiterConfig(s);
    			getMatchlistRL.add(registry.rateLimiter("getMatchlistRL#"+i, rlc));
    	    	i++;
    		}
    	}
	}

	private RateLimiterConfig buildRateLimiterConfig(String rateLimit) {
		String[] rate = rateLimit.split(":");
		Integer permits = Integer.parseInt(rate[0]);
		Long period = Long.parseLong(rate[1]); // seconds
		RateLimiterConfig rlc = RateLimiterConfig.custom()
				.limitForPeriod(permits)
				.limitRefreshPeriod(Duration.ofSeconds(period))
				.timeoutDuration(Duration.ofMillis(5000))
				.build();
		return rlc;
	}
}
