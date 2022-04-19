
package net.rr_world.valorant.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AbilityCasts {

    @JsonProperty("grenadeCasts")
    private Integer grenadeCasts;
    @JsonProperty("ability1Casts")
    private Integer ability1Casts;
    @JsonProperty("ability2Casts")
    private Integer ability2Casts;
    @JsonProperty("ultimateCasts")
    private Integer ultimateCasts;

    public Integer getGrenadeCasts() {
        return grenadeCasts;
    }

    public void setGrenadeCasts(Integer grenadeCasts) {
        this.grenadeCasts = grenadeCasts;
    }

    public Integer getAbility1Casts() {
        return ability1Casts;
    }

    public void setAbility1Casts(Integer ability1Casts) {
        this.ability1Casts = ability1Casts;
    }

    public Integer getAbility2Casts() {
        return ability2Casts;
    }

    public void setAbility2Casts(Integer ability2Casts) {
        this.ability2Casts = ability2Casts;
    }

    public Integer getUltimateCasts() {
        return ultimateCasts;
    }

    public void setUltimateCasts(Integer ultimateCasts) {
        this.ultimateCasts = ultimateCasts;
    }
}
