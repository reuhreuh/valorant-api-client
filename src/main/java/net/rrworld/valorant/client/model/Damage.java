
package net.rrworld.valorant.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Damage {

    @JsonProperty("receiver")
    private String receiver;
    @JsonProperty("damage")
    private Integer damage;
    @JsonProperty("legshots")
    private Integer legshots;
    @JsonProperty("bodyshots")
    private Integer bodyshots;
    @JsonProperty("headshots")
    private Integer headshots;

    @JsonProperty("receiver")
    public String getReceiver() {
        return receiver;
    }

    @JsonProperty("receiver")
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @JsonProperty("damage")
    public Integer getDamage() {
        return damage;
    }

    @JsonProperty("damage")
    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    @JsonProperty("legshots")
    public Integer getLegshots() {
        return legshots;
    }

    @JsonProperty("legshots")
    public void setLegshots(Integer legshots) {
        this.legshots = legshots;
    }

    @JsonProperty("bodyshots")
    public Integer getBodyshots() {
        return bodyshots;
    }

    @JsonProperty("bodyshots")
    public void setBodyshots(Integer bodyshots) {
        this.bodyshots = bodyshots;
    }

    @JsonProperty("headshots")
    public Integer getHeadshots() {
        return headshots;
    }

    @JsonProperty("headshots")
    public void setHeadshots(Integer headshots) {
        this.headshots = headshots;
    }

}
