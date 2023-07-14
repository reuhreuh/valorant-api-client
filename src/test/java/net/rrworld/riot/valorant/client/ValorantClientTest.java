package net.rrworld.riot.valorant.client;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import net.rrworld.valorant.client.ValorantClient;
import net.rrworld.valorant.client.assets.Act;
import net.rrworld.valorant.client.assets.Agent;
import net.rrworld.valorant.client.assets.Map;
import net.rrworld.valorant.client.assets.Region;
import net.rrworld.valorant.client.assets.Weapon;
import net.rrworld.valorant.client.model.Match;
import net.rrworld.valorant.client.model.Matchlist;
import net.rrworld.valorant.client.model.Player;
import net.rrworld.valorant.client.model.PlayerStat;
import net.rrworld.valorant.client.model.RoundResult;
import net.rrworld.valorant.client.model.Team;
import net.rrworld.valorant.client.ratelimiter.RiotRateLimiter;

public class ValorantClientTest {

	private final Logger LOGGER = LoggerFactory.getLogger(ValorantClientTest.class);
	
	private RestTemplate restTemplate;
	private ValorantClient client;
	private Resource jsonMatch;
	private Resource jsonMatchlist;
	private HttpHeaders headers;

	@BeforeEach
	public void init() throws IOException {
		this.restTemplate = new RestTemplateBuilder().build();
		this.client = new ValorantClient("foo-bar-api", Region.EU, restTemplate);
		this.jsonMatch = new ClassPathResource("match.json");
		this.jsonMatchlist = new ClassPathResource("matchlist.json");
		this.headers = buildRiotHeaders();
	}

	@Test
	public void testGetMatch200() {
		MockRestServiceServer server = MockRestServiceServer.createServer(restTemplate);
		server.expect(requestTo("https://eu.api.riotgames.com/val/match/v1/matches/123")).andRespond(withSuccess(jsonMatch, MediaType.APPLICATION_JSON));
		Match m = client.getMatch("123");
		// Match
		Assertions.assertEquals(2, m.getTeams().size(), "Wrong teams count");
		Assertions.assertEquals(10, m.getPlayers().size(), "Wrong players count");
		Assertions.assertEquals(Map.SPLIT, Map.valueOfPath(m.getMatchInfo().getMapId()), "Expected map was Split");
		Assertions.assertEquals(Act.EP4_Act2, Act.valueOfId(m.getMatchInfo().getSeasonId()), "Expected season was EP4_Act2");
		
		// One player : Skye
		Optional<Player> op = m.getPlayers().stream().filter(p -> Agent.SKYE.getId().equals(p.getCharacterId())).findFirst();
		Assertions.assertTrue(op.isPresent(), "Can't find agent Skye");
		Player skye = op.get();
		Assertions.assertEquals(13, skye.getStats().getKills(), "Wrong number of kills for Skye");
		Assertions.assertEquals(3758, skye.getStats().getScore(), "Wrong score for Skye");
		
		// Team results
		Team t = null;
		if(m.getTeams().get(0).getTeamId().equals(skye.getTeamId())){
			t = m.getTeams().get(0);
		} else {
			t = m.getTeams().get(1);
		}
		Assertions.assertEquals(9, t.getRoundsWon(), "Wrong rounds won for Skye Team");
		Assertions.assertFalse(t.getWon(), "Wrong result for Skye Team");
		
		// Round result
		RoundResult r0 = m.getRoundResults().get(0);
		Assertions.assertEquals(0, r0.getRoundNum(), "Wrong round number");
		Assertions.assertEquals("Eliminated", r0.getRoundResult(), "Wrong round result");
		Assertions.assertEquals("CeremonyDefault", r0.getRoundCeremony(), "Wrong round Ceremony");
		Assertions.assertEquals("Blue", r0.getWinningTeam(), "Wrong winning team");
		Assertions.assertEquals(0, r0.getRoundNum(), "Wrong round number");
		// Player round stats
		Optional<PlayerStat> ops = r0.getPlayerStats().stream().filter(p -> Objects.equals(p.getPuuid(), skye.getPuuid())).findFirst();
		Assertions.assertTrue(ops.isPresent(), "Can't find Skye first round result");
		PlayerStat skyePS = ops.get();
		Assertions.assertEquals(1, skyePS.getKills().size());
		Assertions.assertEquals(Weapon.GHOST, Weapon.valueOfId(skyePS.getKills().get(0).getFinishingDamage().getDamageItem()), "Wrong weapon for Skye kill");
		Assertions.assertEquals(190, skyePS.getScore());
	}
	
	@Test
	public void testGetMatch404() {
		MockRestServiceServer server = MockRestServiceServer.createServer(restTemplate);
		server.expect(requestTo("https://eu.api.riotgames.com/val/match/v1/matches/123")).andRespond(withStatus(HttpStatus.NOT_FOUND).headers(headers));
		Match m = client.getMatch("123");		
		Assertions.assertNull(m, "Match should be null");
	}
	
	@Test
	public void testGetMatch403() {
		MockRestServiceServer server = MockRestServiceServer.createServer(restTemplate);
		server.expect(requestTo("https://eu.api.riotgames.com/val/match/v1/matches/123")).andRespond(withStatus(HttpStatus.FORBIDDEN).headers(headers));
		Match m = client.getMatch("123");		
		Assertions.assertNull(m, "Match should be null");
	}
	
	@Test
	public void testGetMatchlist200() {
		MockRestServiceServer server = MockRestServiceServer.createServer(restTemplate);
		server.expect(requestTo("https://eu.api.riotgames.com/val/match/v1/matchlists/by-puuid/puuid-123")).andRespond(withSuccess(jsonMatchlist, MediaType.APPLICATION_JSON).headers(headers));
		Matchlist ml = client.getMatchlist("puuid-123");
		Assertions.assertNotNull(ml, "Matchlist shouldn't be null");
		Assertions.assertEquals("puuid-123", ml.getPuuid(), "Wrong puuid");
		Assertions.assertEquals(5, ml.getHistory().size(), "5 matches were expected");
		Assertions.assertEquals("dc619288-09f6-4e1c-b48a-313691614425", ml.getHistory().get(0).getMatchId(), "Unexpected matchId");
		Assertions.assertEquals("competitive", ml.getHistory().get(0).getQueueId(), "First match was 'competitive'");
	}
	
	@Test
	public void testGetMatchlist404() {
		MockRestServiceServer server = MockRestServiceServer.createServer(restTemplate);
		server.expect(requestTo("https://eu.api.riotgames.com/val/match/v1/matchlists/by-puuid/puuid-123")).andRespond(withStatus(HttpStatus.NOT_FOUND).headers(headers));
		Matchlist ml = client.getMatchlist("puuid-123");		
		Assertions.assertNull(ml, "Match should be null");
	}
	
	@Test
	public void testGetMatchlist403() {
		MockRestServiceServer server = MockRestServiceServer.createServer(restTemplate);
		server.expect(requestTo("https://eu.api.riotgames.com/val/match/v1/matchlists/by-puuid/puuid-123")).andRespond(withStatus(HttpStatus.FORBIDDEN).headers(headers));
		Matchlist ml = client.getMatchlist("puuid-123");		
		Assertions.assertNull(ml, "Match should be null");
	}
	
	@Test
	public void testRateLimiterSimple() {
		MockRestServiceServer server = MockRestServiceServer.createServer(restTemplate);
		server.expect(ExpectedCount.manyTimes(),requestTo("https://eu.api.riotgames.com/val/match/v1/matches/123")).andRespond(withSuccess(jsonMatch, MediaType.APPLICATION_JSON).headers(headers));
		
		final Long startTime = System.currentTimeMillis();
	    client.getMatch("123");
	    client.getMatch("123");
	    final Long duration = (System.currentTimeMillis() - startTime);

	    // then
	    LOGGER.debug("Elpased time : {}ms", duration);
	    //Assertions.assertTrue(duration >= 10000);
	}
	
	
	@Test
	public void testRateLimiterAdvanced() {
		MockRestServiceServer server = MockRestServiceServer.createServer(restTemplate);
		server.expect(ExpectedCount.manyTimes(),requestTo("https://eu.api.riotgames.com/val/match/v1/matches/123")).andRespond(withSuccess(jsonMatch, MediaType.APPLICATION_JSON).headers(headers));
		
		final Long startTime = System.currentTimeMillis();
	    IntStream.range(0, 500).forEach(i -> {
	    	LOGGER.debug("Processing Match #{}",i);
	        client.getMatch("123");
	    });
	    final Long duration = (System.currentTimeMillis() - startTime);

	    // then
	    LOGGER.debug("Elpased time : {}ms", duration);
	    Assertions.assertTrue(duration >= 10000);
	}
	
	private HttpHeaders buildRiotHeaders() {
		HttpHeaders h = new HttpHeaders();
		// 20 per 1sec 
		// 100 per 2min
		h.put(RiotRateLimiter.APP_RATE_LIMIT_HEADER, List.of("20:1,100:120"));
		h.put(RiotRateLimiter.APP_RATE_LIMITE_COUNT_HEADER, List.of("1:1,1:120"));
		// 60 per 60sec
		h.put(RiotRateLimiter.METHOD_RATE_LIMIT_HEADER, List.of("60:60"));
		h.put(RiotRateLimiter.METHOD_RATE_LIMIT_COUNT_HEADER, List.of("1:60"));
		return h;
	}
}
