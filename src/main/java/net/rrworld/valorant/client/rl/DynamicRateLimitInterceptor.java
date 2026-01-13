package net.rrworld.valorant.client.rl;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class DynamicRateLimitInterceptor implements ClientHttpRequestInterceptor {
	
	private final DynamicRateLimiterManager rateLimiterManager;
	private final List<String> headerNames;
	private final int maxRetries;
	private final Duration maxWaitTime;

	public DynamicRateLimitInterceptor(List<String> headerNames) {
		this(headerNames, 5, Duration.ofMinutes(1));
	}

	public DynamicRateLimitInterceptor(List<String> headerNames, int maxRetries, Duration maxWaitTime) {
		this.rateLimiterManager = new DynamicRateLimiterManager();
		this.headerNames = new ArrayList<>(headerNames);
		this.maxRetries = maxRetries;
		this.maxWaitTime = maxWaitTime;
	}

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {

		int retries = 0;

		while (retries < maxRetries) {
			// Vérifier si on peut faire la requête
			if (rateLimiterManager.tryAcquire()) {
				// Exécuter la requête
				ClientHttpResponse response = execution.execute(request, body);

				// Mettre à jour les rate limiters à partir des headers de réponse
				for (String headerName : headerNames) {
					String headerValue = response.getHeaders().getFirst(headerName);
					if (headerValue != null) {
						rateLimiterManager.updateFromHeader(headerName, headerValue);
					}
				}

				return response;
			}

			// Calculer le temps d'attente
			Duration waitTime = rateLimiterManager.getMaxWaitTime();

			if (waitTime.compareTo(maxWaitTime) > 0) {
				throw new IOException("Rate limit wait time exceeds maximum allowed: " + waitTime);
			}

			// Attendre avant de réessayer
			try {
				Thread.sleep(waitTime.toMillis());
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				throw new IOException("Rate limiter interrupted", e);
			}

			retries++;
		}

		throw new IOException("Max retries exceeded for rate limiting");
	}

	public DynamicRateLimiterManager getRateLimiterManager() {
		return rateLimiterManager;
	}
}
