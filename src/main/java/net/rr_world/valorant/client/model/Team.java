
package net.rr_world.valorant.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Team {

    @JsonProperty("teamId")
    private String teamId;
    @JsonProperty("won")
    private Boolean won;
    @JsonProperty("roundsPlayed")
    private Integer roundsPlayed;
    @JsonProperty("roundsWon")
    private Integer roundsWon;
    @JsonProperty("numPoints")
    private Integer numPoints;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public Boolean getWon() {
        return won;
    }

    public void setWon(Boolean won) {
        this.won = won;
    }

    public Integer getRoundsPlayed() {
        return roundsPlayed;
    }

    public void setRoundsPlayed(Integer roundsPlayed) {
        this.roundsPlayed = roundsPlayed;
    }

    public Integer getRoundsWon() {
        return roundsWon;
    }

    public void setRoundsWon(Integer roundsWon) {
        this.roundsWon = roundsWon;
    }

    public Integer getNumPoints() {
        return numPoints;
    }

    public void setNumPoints(Integer numPoints) {
        this.numPoints = numPoints;
    }
}
