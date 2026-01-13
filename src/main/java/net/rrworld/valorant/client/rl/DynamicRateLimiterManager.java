package net.rrworld.valorant.client.rl;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DynamicRateLimiterManager {
    private final Map<String, RateLimiter> rateLimiters;
    private final Lock lock;

    public DynamicRateLimiterManager() {
        this.rateLimiters = new ConcurrentHashMap<>();
        this.lock = new ReentrantLock();
    }

    /**
     * Update rate limiters from response header.
     */
    public void updateFromHeader(String headerName, String headerValue) {
        if (headerValue == null || headerValue.trim().isEmpty()) {
            return;
        }

        lock.lock();
        try {
            // Parser le header : format "requests:seconds,requests:seconds,..."
            Pattern pattern = Pattern.compile("(\\d+):(\\d+)");
            Matcher matcher = pattern.matcher(headerValue);

            int index = 0;
            while (matcher.find()) {
                int requests = Integer.parseInt(matcher.group(1));
                int seconds = Integer.parseInt(matcher.group(2));

                String key = headerName + "_" + index;
                
                // Créer ou mettre à jour le rate limiter
                RateLimiter existing = rateLimiters.get(key);
                if (existing == null || 
                    existing.getMaxRequests() != requests || 
                    !existing.getPeriod().equals(Duration.ofSeconds(seconds))) {
                    
                    rateLimiters.put(key, new RateLimiter(requests, Duration.ofSeconds(seconds)));
                }

                index++;
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Tries to get acquire a permet on ALL rate limiters.
     * returns true only if they all allow the query
     */
    public boolean tryAcquire() {
        lock.lock();
        try {
            for (RateLimiter limiter : rateLimiters.values()) {
                if (!limiter.tryAcquire()) {
                    return false;
                }
            }
            return true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Compute the maximum waiting time among all limiters.
     */
    public Duration getMaxWaitTime() {
        lock.lock();
        try {
            Duration maxWait = Duration.ZERO;
            for (RateLimiter limiter : rateLimiters.values()) {
                Duration wait = limiter.getWaitTime();
                if (wait.compareTo(maxWait) > 0) {
                    maxWait = wait;
                }
            }
            return maxWait;
        } finally {
            lock.unlock();
        }
    }

    public int getLimiterCount() {
        return rateLimiters.size();
    }
}
