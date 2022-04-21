package net.rrworld.valorant.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class History {

	@JsonProperty("matchId")
	public String matchId;
	@JsonProperty("gameStartTimeMillis")
	public Long gameStartTimeMillis;
	@JsonProperty("queueId")
	public String queueId;

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public Long getGameStartTimeMillis() {
		return gameStartTimeMillis;
	}

	public void setGameStartTimeMillis(Long gameStartTimeMillis) {
		this.gameStartTimeMillis = gameStartTimeMillis;
	}

	public String getQueueId() {
		return queueId;
	}

	public void setQueueId(String queueId) {
		this.queueId = queueId;
	}
}