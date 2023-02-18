
package net.rrworld.valorant.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DefusePlayerLocation {

    @JsonProperty("puuid")
    private String puuid;
    @JsonProperty("viewRadians")
    private Double viewRadians;
    @JsonProperty("location")
    private Location location;

    @JsonProperty("puuid")
    public String getPuuid() {
        return puuid;
    }

    @JsonProperty("puuid")
    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    @JsonProperty("viewRadians")
    public Double getViewRadians() {
        return viewRadians;
    }

    @JsonProperty("viewRadians")
    public void setViewRadians(Double viewRadians) {
        this.viewRadians = viewRadians;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
    }

}
