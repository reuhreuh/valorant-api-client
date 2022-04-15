package net.rrworld.riot.valorant.client;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import net.rrworld.valorant.client.ValorantClient;
import net.rrworld.valorant.client.assets.Act;
import net.rrworld.valorant.client.assets.Agent;
import net.rrworld.valorant.client.assets.Map;
import net.rrworld.valorant.client.assets.Region;
import net.rrworld.valorant.client.assets.Weapon;
import net.rrworld.valorant.client.model.Match;
import net.rrworld.valorant.client.model.Player;
import net.rrworld.valorant.client.model.PlayerStat;
import net.rrworld.valorant.client.model.RoundResult;
import net.rrworld.valorant.client.model.Team;

public class ValorantClientTest {
	
	private RestTemplate restTemplate;
	private ValorantClient client;
	private Resource jsonMatch;

	@BeforeEach
	public void init() throws IOException {
		this.restTemplate = new RestTemplateBuilder().build();
		this.client = new ValorantClient("foo-bar-api", Region.EU, restTemplate);
		this.jsonMatch = new ClassPathResource("match.json");
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
		Assertions.assertEquals(Act.EP4_Act2, Act.valueOfId(m.getMatchInfo().getSeasonId()), "Expected season was");
		
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
		server.expect(requestTo("https://eu.api.riotgames.com/val/match/v1/matches/123")).andRespond(withStatus(HttpStatus.NOT_FOUND));
		Match m = client.getMatch("123");		
		Assertions.assertNull(m, "Match should be null");
	}
	
	@Test
	public void testGetMatch403() {
		MockRestServiceServer server = MockRestServiceServer.createServer(restTemplate);
		server.expect(requestTo("https://eu.api.riotgames.com/val/match/v1/matches/123")).andRespond(withStatus(HttpStatus.FORBIDDEN));
		Match m = client.getMatch("123");		
		Assertions.assertNull(m, "Match should be null");
	}
}
