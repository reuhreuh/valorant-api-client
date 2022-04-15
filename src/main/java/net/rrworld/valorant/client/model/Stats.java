
package net.rrworld.valorant.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stats {

    @JsonProperty("score")
    private Integer score;
    @JsonProperty("roundsPlayed")
    private Integer roundsPlayed;
    @JsonProperty("kills")
    private Integer kills;
    @JsonProperty("deaths")
    private Integer deaths;
    @JsonProperty("assists")
    private Integer assists;
    @JsonProperty("playtimeMillis")
    private Integer playtimeMillis;
    @JsonProperty("abilityCasts")
    private AbilityCasts abilityCasts;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getRoundsPlayed() {
        return roundsPlayed;
    }

    public void setRoundsPlayed(Integer roundsPlayed) {
        this.roundsPlayed = roundsPlayed;
    }

    public Integer getKills() {
        return kills;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public Integer getPlaytimeMillis() {
        return playtimeMillis;
    }

    public void setPlaytimeMillis(Integer playtimeMillis) {
        this.playtimeMillis = playtimeMillis;
    }

    public AbilityCasts getAbilityCasts() {
        return abilityCasts;
    }

    public void setAbilityCasts(AbilityCasts abilityCasts) {
        this.abilityCasts = abilityCasts;
    }
}
