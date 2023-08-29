
package net.rrworld.valorant.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {

    @JsonProperty("puuid")
    private String puuid;
    @JsonProperty("gameName")
    private String gameName;
    @JsonProperty("tagLine")
    private String tagLine;
    @JsonProperty("teamId")
    private String teamId;
    @JsonProperty("partyId")
    private String partyId;
    @JsonProperty("characterId")
    private String characterId;
    @JsonProperty("stats")
    private Stats stats;
    @JsonProperty("competitiveTier")
    private Integer competitiveTier;
    @JsonProperty("playerCard")
    private String playerCard;
    @JsonProperty("playerTitle")
    private String playerTitle;
    @JsonProperty("accountLevel")
	private Integer accountLevel;

    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getCharacterId() {
        return characterId;
    }

    public void setCharacterId(String characterId) {
        this.characterId = characterId;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public Integer getCompetitiveTier() {
        return competitiveTier;
    }

    public void setCompetitiveTier(Integer competitiveTier) {
        this.competitiveTier = competitiveTier;
    }

    public String getPlayerCard() {
        return playerCard;
    }

    public void setPlayerCard(String playerCard) {
        this.playerCard = playerCard;
    }

    public String getPlayerTitle() {
        return playerTitle;
    }

    public void setPlayerTitle(String playerTitle) {
        this.playerTitle = playerTitle;
    }

	public Integer getAccountLevel() {
		return accountLevel;
	}

	public void setAccountLevel(Integer accountLevel) {
		this.accountLevel = accountLevel;
	}
}
