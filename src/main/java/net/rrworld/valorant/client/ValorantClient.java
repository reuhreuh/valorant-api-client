package net.rrworld.valorant.client;

import java.util.Arrays;
import java.util.List;

import org.junit.platform.commons.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import net.rrworld.valorant.client.assets.Region;
import net.rrworld.valorant.client.model.Match;
import net.rrworld.valorant.client.model.Matchlist;
import net.rrworld.valorant.client.ratelimiter.RiotRateLimiter;

/**
 * Simple Valorant client, using official Riot API. It provides:
 * <p>
 * VAL-MATCH-V1
 * </p>
 * <ul>
 * <li>/val/match/v1/matches/{matchId}</li>
 * <li>/val/match/v1/matchlists/by-puuid/{puuid}</li>
 * </ul>
 * 
 * @author reuhreuh
 */
public class ValorantClient {

	private final Logger LOGGER = LoggerFactory.getLogger(ValorantClient.class);

	/**
	 * API Key request header
	 */
	public static final String API_KEY_HEADER = "X-Riot-Token";
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
	
	private static final String MATCH_URL = "https://%s.api.riotgames.com/val/match/v1/matches/%s";
	private static final String MATCH_LIST_URL = "https://%s.api.riotgames.com/val/match/v1/matchlists/by-puuid/%s";

	private String apiKey;
	private String region;
	private RestTemplate restClient;

	/**
	 * Create a new Valorant API client, using given API Key, for a given region
	 * (API requires to select one)
	 * <p>
	 * A {@code RestTemplate} instance will be built, with a default configuration.
	 * </p>
	 * 
	 * @param apiKey the Riot API key
	 * @param region the target region
	 */
	public ValorantClient(final String apiKey, final Region region) {
		this(apiKey, region, new RestTemplateBuilder().build());
	}

	/**
	 * Create a new Valorant API client, using given API key, for a given region,
	 * and a configured ready to use RestTemplate client.
	 * 
	 * @param apiKey     the Riot API key
	 * @param region     the target region
	 * @param restClient configured http client
	 */
	public ValorantClient(final String apiKey, final Region region, final RestTemplate restClient) {
		this.apiKey = apiKey;
		this.region = region.name().toLowerCase();
		this.restClient = restClient;
	}

	/**
	 * Get a Match using VAL-MATCH-V1 API
	 * 
	 * @see <a href=
	 *      'https://developer.riotgames.com/apis#val-match-v1/GET_getMatch'>https://developer.riotgames.com/apis#val-match-v1/GET_getMatch</a>
	 * @param matchId the match identifier
	 * @return the match DTO or null if there is any HTTP error
	 */
	public Match getMatch(final String matchId) {
		return RiotRateLimiter.getInstance().getMatch(() -> getMatchInternal(matchId));
	}

	private Match getMatchInternal(final String matchId) {
		LOGGER.info("Retrieving match {} from Riot API", matchId);
		String url = String.format(MATCH_URL, region, matchId);
		Match m = null;
		ResponseEntity<Match> response = null;
		HttpHeaders responseHeaders = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add(API_KEY_HEADER, apiKey);
			HttpEntity<String> entity = new HttpEntity<>(headers);
			response = restClient.exchange(url, HttpMethod.GET, entity, Match.class);
			responseHeaders = response.getHeaders();
			if (HttpStatus.OK == response.getStatusCode()) {
				LOGGER.info("Match {} found on Riot API", matchId);
				m = response.getBody();
			} else {
				LOGGER.warn("Match {} not found on Riot API. HTTP response code : {}", matchId,
						response.getStatusCode().value());
			}
		} catch (RestClientResponseException e) {
			responseHeaders = e.getResponseHeaders();
			LOGGER.warn("Match {} not found on Riot API. HTTP response code : {}", matchId, e.getRawStatusCode());
		} catch (RestClientException rce) {
			LOGGER.error("Error while calling Riot API for getMatch({}) : {}", matchId, rce.getMessage());
		} finally {
			ExtractedLimits el = rateLimitsExtractor(responseHeaders);
			RiotRateLimiter.getInstance().updateGetMatchLimits(el.getAppLimits(), el.getMethodLimits());
		}
		return m;
	}

	/**
	 * Get match list history for a given player, identified by its
	 * <code>puuid</code>
	 * 
	 * @see <a href=
	 *      'https://developer.riotgames.com/apis#val-match-v1/GET_getMatchlist'>https://developer.riotgames.com/apis#val-match-v1/GET_getMatchlist</a>
	 * @param playerPuuid the player identifier
	 * @return the match list history DTO or <code>null</code>, in case of error.
	 */
	public Matchlist getMatchlist(final String playerPuuid) {
		LOGGER.info("Retrieving match list for player {} from Riot API", playerPuuid);
		String url = String.format(MATCH_LIST_URL, region, playerPuuid);
		Matchlist ml = null;
		ResponseEntity<Matchlist> response = null;
		HttpHeaders responseHeaders = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add(API_KEY_HEADER, apiKey);
			HttpEntity<String> entity = new HttpEntity<>(headers);
			response = restClient.exchange(url, HttpMethod.GET, entity, Matchlist.class);
			responseHeaders = response.getHeaders();
			if (HttpStatus.OK == response.getStatusCode()) {
				LOGGER.info("Matchlist found for player {} on Riot API", playerPuuid);
				ml = response.getBody();
			} else {
				LOGGER.warn("Matchlist not found for player {} on Riot API. HTTP response code : {}", playerPuuid,
						response.getStatusCode().value());
			}
		} catch(RestClientResponseException e) {
			responseHeaders = e.getResponseHeaders();
			LOGGER.warn("Matchlist not found for player {} on Riot API. HTTP response code : {}", playerPuuid, e.getRawStatusCode());
		} catch (RestClientException e) {
			LOGGER.error("Error while calling Riot API for getMatchlist({}) : {}", playerPuuid, e.getMessage());
		} finally {
			ExtractedLimits el = rateLimitsExtractor(responseHeaders);
			RiotRateLimiter.getInstance().updateGetMatchListLimits(el.getAppLimits(), el.getMethodLimits());
		}
		return ml;
	}

	private ExtractedLimits rateLimitsExtractor(HttpHeaders headers) {
		String appLimits = headers.getFirst(APP_RATE_LIMIT_HEADER);
		String methodLimits = headers.getFirst(METHOD_RATE_LIMIT_HEADER);
		LOGGER.debug("App limits : {}", appLimits);
		LOGGER.debug("Method limits : {}", methodLimits);
		ExtractedLimits el = new ExtractedLimits();
		if(StringUtils.isNotBlank(appLimits)) {
			el.setAppLimits(Arrays.asList(appLimits.split(",")));
		}
		if(StringUtils.isNotBlank(methodLimits)) {
			el.setMethodLimits(Arrays.asList(methodLimits.split(",")));
		}
		return el;
	}
	
	// Inner 
	private static class ExtractedLimits {
		List<String> appLimits;
		List<String> methodLimits;
		public List<String> getAppLimits() {
			return appLimits;
		}
		public void setAppLimits(List<String> appLimits) {
			this.appLimits = appLimits;
		}
		public List<String> getMethodLimits() {
			return methodLimits;
		}
		public void setMethodLimits(List<String> methodLimits) {
			this.methodLimits = methodLimits;
		}
	}
}
