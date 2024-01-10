package net.rrworld.valorant.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PremierMatchInfo {

	@JsonProperty("premierSeasonId")
	private String premierSeasonId;
	@JsonProperty("premierEventId")
	private String premierEventId;
	@JsonProperty("tournamentId")
	private String tournamentId;
	@JsonProperty("matchupId")
	private String matchupId;

	public String getPremierSeasonId() {
		return premierSeasonId;
	}
	public void setPremierSeasonId(String premierSeasonId) {
		this.premierSeasonId = premierSeasonId;
	}
	public String getPremierEventId() {
		return premierEventId;
	}
	public void setPremierEventId(String premierEventId) {
		this.premierEventId = premierEventId;
	}
	public String getTournamentId() {
		return tournamentId;
	}
	public void setTournamentId(String tournamentId) {
		this.tournamentId = tournamentId;
	}
	public String getMatchupId() {
		return matchupId;
	}
	public void setMatchupId(String matchupId) {
		this.matchupId = matchupId;
	}	
}
