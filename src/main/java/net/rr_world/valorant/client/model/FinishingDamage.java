
package net.rr_world.valorant.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FinishingDamage {

    @JsonProperty("damageType")
    private String damageType;
    @JsonProperty("damageItem")
    private String damageItem;
    @JsonProperty("isSecondaryFireMode")
    private Boolean isSecondaryFireMode;

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public String getDamageItem() {
        return damageItem;
    }

    public void setDamageItem(String damageItem) {
        this.damageItem = damageItem;
    }

    public Boolean getIsSecondaryFireMode() {
        return isSecondaryFireMode;
    }

    public void setIsSecondaryFireMode(Boolean isSecondaryFireMode) {
        this.isSecondaryFireMode = isSecondaryFireMode;
    }
}
