package net.rrworld.valorant.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import net.rrworld.valorant.client.assets.Region;
import net.rrworld.valorant.client.model.Match;

/**
 * Simple Valorant client, using official Riot API. It provides:
 * <p>
 * VAL-MATCH-V1
 * <ul>
 * <li>/val/match/v1/matches/{matchId}</li>
 * <li>/val/match/v1/matchlists/by-puuid/{puuid}</li>
 * </ul>
 * </p>
 * @author reuhreuh
 */
public class ValorantClient {

	private final Logger LOGGER = LoggerFactory.getLogger(ValorantClient.class);
	
	private static final String API_KEY_HEADER = "X-Riot-Token";
	private static final String MATCH_URL = "https://%s.api.riotgames.com/val/match/v1/matches/%s";
	
	private String apiKey;
	private String region;
	private RestTemplate restClient;
	
	/**
	 * Create a new Valorant API client, using given API Key, for a given region (API requires to select one)
	 * <p>
	 * A {@code RestTemplate} instance will be built, with a default configuration.
	 * </p>
	 * @param apiKey the Riot API key
	 * @param region the target region
	 */
	public ValorantClient(final String apiKey, final Region region) {
		this(apiKey, region, new RestTemplateBuilder().build());
	}
	
	/**
	 * Create a new Valorant API client, using given API key, for a given region, and a configured ready to use RestTemplate client.
	 * 
	 * @param apiKey the Riot API key
	 * @param region the target region
	 * @param restClient configured http client
	 */
	public ValorantClient(final String apiKey, final Region region, final RestTemplate restClient) {
		this.apiKey = apiKey;
		this.region = region.name().toLowerCase();
		this.restClient = restClient;
	}
	
	
	
	/**
	 * Get a Match using VAL-MATCH-V1 API
	 * @see <a href='https://developer.riotgames.com/apis#val-match-v1/GET_getMatch'>https://developer.riotgames.com/apis#val-match-v1/GET_getMatch</a>
	 * @param matchId the match identifier 
	 * @return the {@code MatchDto} or null if there is any HTTP error
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
				LOGGER.warn("Match {} not found on Riot API. HTTP response code : {}", matchId, response.getStatusCodeValue());
			}
		} catch (RestClientException e) {
			LOGGER.error("Error while calling Riot API for match {} : {}", matchId, e.getMessage());
		}
		return m;
	}
}
