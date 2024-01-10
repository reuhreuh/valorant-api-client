
package net.rrworld.valorant.client.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Match {

    @JsonProperty("matchInfo")
    private MatchInfo matchInfo;
    @JsonProperty("players")
    private List<Player> players = null;
    @JsonProperty("coaches")
    private List<Coach> coaches = null;
    @JsonProperty("teams")
    private List<Team> teams = null;
    @JsonProperty("roundResults")
    private List<RoundResult> roundResults = null;
    /**
     * @since 1.0.12
     */
    @JsonProperty("premierMatchInfo")
    private PremierMatchInfo premierMatchInfo = null;

    public MatchInfo getMatchInfo() {
        return matchInfo;
    }

    public void setMatchInfo(MatchInfo matchInfo) {
        this.matchInfo = matchInfo;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public void setCoaches(List<Coach> coaches) {
        this.coaches = coaches;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<RoundResult> getRoundResults() {
        return roundResults;
    }

    public void setRoundResults(List<RoundResult> roundResults) {
        this.roundResults = roundResults;
    }

	public PremierMatchInfo getPremierMatchInfo() {
		return premierMatchInfo;
	}

	public void setPremierMatchInfo(PremierMatchInfo premierMatchInfo) {
		this.premierMatchInfo = premierMatchInfo;
	}
}
