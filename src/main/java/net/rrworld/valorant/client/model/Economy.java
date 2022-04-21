
package net.rrworld.valorant.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Economy {

    @JsonProperty("loadoutValue")
    private Integer loadoutValue;
    @JsonProperty("weapon")
    private String weapon;
    @JsonProperty("armor")
    private String armor;
    @JsonProperty("remaining")
    private Integer remaining;
    @JsonProperty("spent")
    private Integer spent;

    public Integer getLoadoutValue() {
        return loadoutValue;
    }

    public void setLoadoutValue(Integer loadoutValue) {
        this.loadoutValue = loadoutValue;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getArmor() {
        return armor;
    }

    public void setArmor(String armor) {
        this.armor = armor;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    public Integer getSpent() {
        return spent;
    }

    public void setSpent(Integer spent) {
        this.spent = spent;
    }
}
