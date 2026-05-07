package net.rrworld.valorant.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

import net.rrworld.valorant.client.assets.Region;
import net.rrworld.valorant.client.model.Match;
import net.rrworld.valorant.client.model.Matchlist;

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
	private static final String RIOT_URL = "https://%s.api.riotgames.com";
	private static final String MATCH_URL = "/val/match/v1/matches/%s";
	private static final String MATCH_LIST_URL = "/val/match/v1/matchlists/by-puuid/%s";

	private RestClient restClient;

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
		this(apiKey, region, RestClient.builder());
	}
	
	public ValorantClient(final String apiKey, final Region region, RestClient.Builder builder) {
		this.restClient = builder
							.baseUrl(String.format(RIOT_URL, region.name().toLowerCase()))
							.defaultHeader(API_KEY_HEADER, apiKey)
							.defaultStatusHandler(HttpStatusCode::is5xxServerError, (request, response) -> {
								LOGGER.error("Server error {} while calling Riot API {} {}", response.getStatusCode().value(), request.getMethod(), request.getURI().toString());
						    })
							.defaultStatusHandler(HttpStatusCode::is4xxClientError, (request, response) -> {
								LOGGER.error("Client error {} while calling Riot API {} {}", response.getStatusCode().value(), request.getMethod(), request.getURI().toString());
						    })
							.defaultStatusHandler(HttpStatusCode::is2xxSuccessful, (request, response) -> {
								LOGGER.info("Riot API response OK for : {}", request.getURI().toString());
						    })
							.build();
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
		String url = String.format(MATCH_URL, matchId);
		Match m = null;
		m = restClient
				.get()
				.uri(url)
				.retrieve()
				.body(Match.class);
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
		String url = String.format(MATCH_LIST_URL, playerPuuid);
		Matchlist ml = null;
		ml = restClient
				.get()
				.uri(url)
				.retrieve()
				.body(Matchlist.class);
		return ml;
	}
}
