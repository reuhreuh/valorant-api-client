
package net.rr_world.valorant.client.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Kill {

    @JsonProperty("timeSinceGameStartMillis")
    private Integer timeSinceGameStartMillis;
    @JsonProperty("timeSinceRoundStartMillis")
    private Integer timeSinceRoundStartMillis;
    @JsonProperty("killer")
    private String killer;
    @JsonProperty("victim")
    private String victim;
    @JsonProperty("victimLocation")
    private VictimLocation victimLocation;
    @JsonProperty("assistants")
    private List<String> assistants = null;
    @JsonProperty("playerLocations")
    private List<PlayerLocation> playerLocations = null;
    @JsonProperty("finishingDamage")
    private FinishingDamage finishingDamage;

    public Integer getTimeSinceGameStartMillis() {
        return timeSinceGameStartMillis;
    }

    public void setTimeSinceGameStartMillis(Integer timeSinceGameStartMillis) {
        this.timeSinceGameStartMillis = timeSinceGameStartMillis;
    }

    public Integer getTimeSinceRoundStartMillis() {
        return timeSinceRoundStartMillis;
    }

    public void setTimeSinceRoundStartMillis(Integer timeSinceRoundStartMillis) {
        this.timeSinceRoundStartMillis = timeSinceRoundStartMillis;
    }

    public String getKiller() {
        return killer;
    }

    public void setKiller(String killer) {
        this.killer = killer;
    }

    public String getVictim() {
        return victim;
    }

    public void setVictim(String victim) {
        this.victim = victim;
    }

    public VictimLocation getVictimLocation() {
        return victimLocation;
    }

    public void setVictimLocation(VictimLocation victimLocation) {
        this.victimLocation = victimLocation;
    }

    public List<String> getAssistants() {
        return assistants;
    }

    public void setAssistants(List<String> assistants) {
        this.assistants = assistants;
    }

    public List<PlayerLocation> getPlayerLocations() {
        return playerLocations;
    }

    public void setPlayerLocations(List<PlayerLocation> playerLocations) {
        this.playerLocations = playerLocations;
    }

    public FinishingDamage getFinishingDamage() {
        return finishingDamage;
    }

    public void setFinishingDamage(FinishingDamage finishingDamage) {
        this.finishingDamage = finishingDamage;
    }
}
