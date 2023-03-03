package net.rrworld.valorant.client.ratelimiter;

import java.util.concurrent.TimeUnit;

/**
 * Represent an API rate limit.
 * <p>
 * A limit is defined by its number of <code>permits</code> within a given <code>period</code> of time. 
 * The period time <code>unit</code> can be set too.
 * </p> 
 * @author reuhreuh
 *
 */
public class RateLimit {
	
	private int permits;
	private int period;
	private TimeUnit unit;
	
	/**
	 * Gets the number of permits
	 * 
	 * @return number of permits
	 */
	public int getPermits() {
		return permits;
	}
	
	/**
	 * Sets the number of permits
	 * 
	 * @param permits the number permits
	 */
	public void setPermits(int permits) {
		this.permits = permits;
	}
	
	/**
	 * Gets the time period
	 * 
	 * @return the time period
	 */
	public int getPeriod() {
		return period;
	}
	
	/**
	 * Sets the time period
	 * @param period the time period
	 */
	public void setPeriod(int period) {
		this.period = period;
	}
	
	/**
	 * Gets the time unit
	 * 
	 * @return the time unit
	 */
	public TimeUnit getUnit() {
		return unit;
	}
	
	/**
	 * Gets the time unit
	 * @param unit the time unit
	 */
	public void setUnit(TimeUnit unit) {
		this.unit = unit;
	}
}
