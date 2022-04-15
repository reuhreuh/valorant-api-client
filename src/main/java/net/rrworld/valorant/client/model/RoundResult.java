
package net.rrworld.valorant.client.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoundResult {

    @JsonProperty("roundNum")
    private Integer roundNum;
    @JsonProperty("roundResult")
    private String roundResult;
    @JsonProperty("roundCeremony")
    private String roundCeremony;
    @JsonProperty("winningTeam")
    private String winningTeam;
    @JsonProperty("bombPlanter")
    private String bombPlanter;
    @JsonProperty("bombDefuser")
    private Object bombDefuser;
    @JsonProperty("plantRoundTime")
    private Integer plantRoundTime;
    @JsonProperty("plantPlayerLocations")
    private List<PlantPlayerLocation> plantPlayerLocations = null;
    @JsonProperty("plantLocation")
    private PlantLocation plantLocation;
    @JsonProperty("plantSite")
    private String plantSite;
    @JsonProperty("defuseRoundTime")
    private Integer defuseRoundTime;
    @JsonProperty("defusePlayerLocations")
    private Object defusePlayerLocations;
    @JsonProperty("defuseLocation")
    private DefuseLocation defuseLocation;
    @JsonProperty("playerStats")
    private List<PlayerStat> playerStats = null;
    @JsonProperty("roundResultCode")
    private String roundResultCode;

    public Integer getRoundNum() {
        return roundNum;
    }

    public void setRoundNum(Integer roundNum) {
        this.roundNum = roundNum;
    }

    public String getRoundResult() {
        return roundResult;
    }

    public void setRoundResult(String roundResult) {
        this.roundResult = roundResult;
    }

    public String getRoundCeremony() {
        return roundCeremony;
    }

    public void setRoundCeremony(String roundCeremony) {
        this.roundCeremony = roundCeremony;
    }

    public String getWinningTeam() {
        return winningTeam;
    }

    public void setWinningTeam(String winningTeam) {
        this.winningTeam = winningTeam;
    }

    public String getBombPlanter() {
        return bombPlanter;
    }

    public void setBombPlanter(String bombPlanter) {
        this.bombPlanter = bombPlanter;
    }

    public Object getBombDefuser() {
        return bombDefuser;
    }

    public void setBombDefuser(Object bombDefuser) {
        this.bombDefuser = bombDefuser;
    }

    public Integer getPlantRoundTime() {
        return plantRoundTime;
    }

    public void setPlantRoundTime(Integer plantRoundTime) {
        this.plantRoundTime = plantRoundTime;
    }

    public List<PlantPlayerLocation> getPlantPlayerLocations() {
        return plantPlayerLocations;
    }

    public void setPlantPlayerLocations(List<PlantPlayerLocation> plantPlayerLocations) {
        this.plantPlayerLocations = plantPlayerLocations;
    }

    public PlantLocation getPlantLocation() {
        return plantLocation;
    }

    public void setPlantLocation(PlantLocation plantLocation) {
        this.plantLocation = plantLocation;
    }

    public String getPlantSite() {
        return plantSite;
    }

    public void setPlantSite(String plantSite) {
        this.plantSite = plantSite;
    }

    public Integer getDefuseRoundTime() {
        return defuseRoundTime;
    }

    public void setDefuseRoundTime(Integer defuseRoundTime) {
        this.defuseRoundTime = defuseRoundTime;
    }

    public Object getDefusePlayerLocations() {
        return defusePlayerLocations;
    }

    public void setDefusePlayerLocations(Object defusePlayerLocations) {
        this.defusePlayerLocations = defusePlayerLocations;
    }

    public DefuseLocation getDefuseLocation() {
        return defuseLocation;
    }

    public void setDefuseLocation(DefuseLocation defuseLocation) {
        this.defuseLocation = defuseLocation;
    }

    public List<PlayerStat> getPlayerStats() {
        return playerStats;
    }


    public void setPlayerStats(List<PlayerStat> playerStats) {
        this.playerStats = playerStats;
    }

    public String getRoundResultCode() {
        return roundResultCode;
    }

    public void setRoundResultCode(String roundResultCode) {
        this.roundResultCode = roundResultCode;
    }
}
