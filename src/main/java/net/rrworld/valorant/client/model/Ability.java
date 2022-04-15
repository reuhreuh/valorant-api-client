
package net.rrworld.valorant.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ability {

    @JsonProperty("grenadeEffects")
    private Object grenadeEffects;
    @JsonProperty("ability1Effects")
    private Object ability1Effects;
    @JsonProperty("ability2Effects")
    private Object ability2Effects;
    @JsonProperty("ultimateEffects")
    private Object ultimateEffects;

    public Object getGrenadeEffects() {
        return grenadeEffects;
    }

    public void setGrenadeEffects(Object grenadeEffects) {
        this.grenadeEffects = grenadeEffects;
    }

    public Object getAbility1Effects() {
        return ability1Effects;
    }

    public void setAbility1Effects(Object ability1Effects) {
        this.ability1Effects = ability1Effects;
    }

    public Object getAbility2Effects() {
        return ability2Effects;
    }

    public void setAbility2Effects(Object ability2Effects) {
        this.ability2Effects = ability2Effects;
    }

    public Object getUltimateEffects() {
        return ultimateEffects;
    }

    public void setUltimateEffects(Object ultimateEffects) {
        this.ultimateEffects = ultimateEffects;
    }
}
