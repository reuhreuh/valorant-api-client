package net.rrworld.valorant.client.rl;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Rate limiter individuel basé sur un algorithme de token bucket
 */
class RateLimiter {
	
    private final int maxRequests;
    private final Duration period;
    private final LinkedList<Instant> requestTimestamps;
    private final Lock lock;

    public RateLimiter(int maxRequests, Duration period) {
        this.maxRequests = maxRequests;
        this.period = period;
        this.requestTimestamps = new LinkedList<>();
        this.lock = new ReentrantLock();
    }

    /**
     * Tente d'acquérir un permit. Retourne true si autorisé, false sinon.
     * Nettoie automatiquement les timestamps expirés.
     */
    public boolean tryAcquire() {
        lock.lock();
        try {
            Instant now = Instant.now();
            Instant cutoff = now.minus(period);

            // Nettoyer les timestamps expirés
            while (!requestTimestamps.isEmpty() && requestTimestamps.getFirst().isBefore(cutoff)) {
                requestTimestamps.removeFirst();
            }

            // Vérifier si on peut faire la requête
            if (requestTimestamps.size() < maxRequests) {
                requestTimestamps.add(now);
                return true;
            }

            return false;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Calcule le temps d'attente nécessaire avant la prochaine requête autorisée
     */
    public Duration getWaitTime() {
        lock.lock();
        try {
            if (requestTimestamps.size() < maxRequests) {
                return Duration.ZERO;
            }

            Instant oldest = requestTimestamps.getFirst();
            Instant nextAvailable = oldest.plus(period);
            Duration waitTime = Duration.between(Instant.now(), nextAvailable);

            return waitTime.isNegative() ? Duration.ZERO : waitTime;
        } finally {
            lock.unlock();
        }
    }

    public int getMaxRequests() {
        return maxRequests;
    }

    public Duration getPeriod() {
        return period;
    }
}