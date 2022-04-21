
package net.rrworld.valorant.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchInfo {

    @JsonProperty("matchId")
    private String matchId;
    @JsonProperty("mapId")
    private String mapId;
    @JsonProperty("gameVersion")
    private String gameVersion;
    @JsonProperty("gameLengthMillis")
    private Integer gameLengthMillis;
    @JsonProperty("gameStartMillis")
    private Long gameStartMillis;
    @JsonProperty("provisioningFlowId")
    private String provisioningFlowId;
    @JsonProperty("isCompleted")
    private Boolean isCompleted;
    @JsonProperty("customGameName")
    private String customGameName;
    @JsonProperty("queueId")
    private String queueId;
    @JsonProperty("gameMode")
    private String gameMode;
    @JsonProperty("isRanked")
    private Boolean isRanked;
    @JsonProperty("seasonId")
    private String seasonId;

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    public Integer getGameLengthMillis() {
        return gameLengthMillis;
    }

    public void setGameLengthMillis(Integer gameLengthMillis) {
        this.gameLengthMillis = gameLengthMillis;
    }

    public Long getGameStartMillis() {
        return gameStartMillis;
    }

    public void setGameStartMillis(Long gameStartMillis) {
        this.gameStartMillis = gameStartMillis;
    }

    public String getProvisioningFlowId() {
        return provisioningFlowId;
    }

    public void setProvisioningFlowId(String provisioningFlowId) {
        this.provisioningFlowId = provisioningFlowId;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String getCustomGameName() {
        return customGameName;
    }

    public void setCustomGameName(String customGameName) {
        this.customGameName = customGameName;
    }

    public String getQueueId() {
        return queueId;
    }

    public void setQueueId(String queueId) {
        this.queueId = queueId;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public Boolean getIsRanked() {
        return isRanked;
    }

    public void setIsRanked(Boolean isRanked) {
        this.isRanked = isRanked;
    }

    public String getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(String seasonId) {
        this.seasonId = seasonId;
    }
}
