package net.rrworld.valorant.client.ratelimiter;

import java.time.Duration;
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
	
	// Method rate limiters
	private RateLimiter getMatchShortRL;
	private RateLimiter getMatchLongRL;
	
	
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
    	Supplier<Match> m1 = RateLimiter.decorateSupplier(this.appShortRL, getMatchMethod);
    	Supplier<Match> m2 = RateLimiter.decorateSupplier(this.appLongRL, m1);
    	return m2.get();
    }
    
    public void updateMatchRates() {
    }
	
}
