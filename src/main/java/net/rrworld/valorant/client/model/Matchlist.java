package net.rrworld.valorant.client.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Matchlist {

	@JsonProperty("puuid")
	public String puuid;
	@JsonProperty("history")
	public List<History> history = null;

	public String getPuuid() {
		return puuid;
	}
	public void setPuuid(String puuid) {
		this.puuid = puuid;
	}
	public List<History> getHistory() {
		return history;
	}
	public void setHistory(List<History> history) {
		this.history = history;
	}
}