package net.rrworld.valorant.client.rl;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import net.rrworld.valorant.client.ValorantClient;

public class RestTemplateConfig {
	
    public static RestTemplate createRateLimitedRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        
        List<String> rateLimitHeaders = Arrays.asList(
            ValorantClient.APP_RATE_LIMIT_HEADER,
            ValorantClient.METHOD_RATE_LIMIT_HEADER
        );
        
        DynamicRateLimitInterceptor interceptor = 
            new DynamicRateLimitInterceptor(rateLimitHeaders, 5, Duration.ofMinutes(1));
        
        restTemplate.getInterceptors().add(interceptor);
        
        return restTemplate;
    }
}
