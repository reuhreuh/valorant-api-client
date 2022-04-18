
package net.rrworld.valorant.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ability {

    @JsonProperty("grenadeEffects")
    private String grenadeEffects;
    @JsonProperty("ability1Effects")
    private String ability1Effects;
    @JsonProperty("ability2Effects")
    private String ability2Effects;
    @JsonProperty("ultimateEffects")
    private String ultimateEffects;

    public Object getGrenadeEffects() {
        return grenadeEffects;
    }

    public void setGrenadeEffects(String grenadeEffects) {
        this.grenadeEffects = grenadeEffects;
    }

    public Object getAbility1Effects() {
        return ability1Effects;
    }

    public void setAbility1Effects(String ability1Effects) {
        this.ability1Effects = ability1Effects;
    }

    public Object getAbility2Effects() {
        return ability2Effects;
    }

    public void setAbility2Effects(String ability2Effects) {
        this.ability2Effects = ability2Effects;
    }

    public Object getUltimateEffects() {
        return ultimateEffects;
    }

    public void setUltimateEffects(String ultimateEffects) {
        this.ultimateEffects = ultimateEffects;
    }
}
