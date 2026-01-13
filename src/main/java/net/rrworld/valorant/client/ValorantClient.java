package net.rrworld.valorant.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import net.rrworld.valorant.client.assets.Region;
import net.rrworld.valorant.client.model.Match;
import net.rrworld.valorant.client.model.Matchlist;
import net.rrworld.valorant.client.rl.RestTemplateConfig;

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

	private static final String API_KEY_HEADER = "X-Riot-Token";
	private static final String MATCH_URL = "https://%s.api.riotgames.com/val/match/v1/matches/%s";
	private static final String MATCH_LIST_URL = "https://%s.api.riotgames.com/val/match/v1/matchlists/by-puuid/%s";
	
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

	private String apiKey;
	private String region;
	private RestTemplate restClient;

	/**
	 * Create a new Valorant API client, using given API Key, for a given region
	 * (API requires to select one)
	 * <p>
	 * A {@code RestTemplate} instance will be built, with automatic rate-limiter configuration.
	 * </p>
	 * 
	 * @param apiKey the Riot API key
	 * @param region the target region
	 */
	public ValorantClient(final String apiKey, final Region region) {
		this.apiKey = apiKey;
		this.region = region.name().toLowerCase();
		this.restClient = RestTemplateConfig.createRateLimitedRestTemplate();
	}

	/**
	 * Create a new Valorant API client, using given API key, for a given region,
	 * and an RestTemplate client.
	 * <p>This constructor assumes that your <code>restClient</code> takes in charge rate limiting</p>
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
		LOGGER.info("Retrieving match {} from Riot API", matchId);
		String url = String.format(MATCH_URL, region, matchId);
		Match m = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add(API_KEY_HEADER, apiKey);
			HttpEntity<String> entity = new HttpEntity<>(headers);
			ResponseEntity<Match> response = restClient.exchange(url, HttpMethod.GET, entity, Match.class);
			if (HttpStatus.OK == response.getStatusCode()) {
				LOGGER.info("Match {} found on Riot API", matchId);
				m = response.getBody();
			} else {
				LOGGER.warn("Match {} not found on Riot API. HTTP response code : {}", matchId,
						response.getStatusCode().value());
			}
		} catch (RestClientException e) {
			LOGGER.error("Error while calling Riot API for getMatch({}) : {}", matchId, e.getMessage());
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
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add(API_KEY_HEADER, apiKey);
			HttpEntity<String> entity = new HttpEntity<>(headers);
			ResponseEntity<Matchlist> response = restClient.exchange(url, HttpMethod.GET, entity, Matchlist.class);
			if (HttpStatus.OK == response.getStatusCode()) {
				LOGGER.info("Matchlist found for player {} on Riot API", playerPuuid);
				ml = response.getBody();
			} else {
				LOGGER.warn("Matchlist not found for player {} on Riot API. HTTP response code : {}", playerPuuid,
						response.getStatusCode().value());
			}
		} catch (RestClientException e) {
			LOGGER.error("Error while calling Riot API for getMatchlist({}) : {}", playerPuuid, e.getMessage());
		}
		return ml;
	}
}
