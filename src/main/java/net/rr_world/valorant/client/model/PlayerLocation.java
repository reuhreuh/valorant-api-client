
package net.rr_world.valorant.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerLocation {

    @JsonProperty("puuid")
    private String puuid;
    @JsonProperty("viewRadians")
    private Double viewRadians;
    @JsonProperty("location")
    private Location location;

    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public Double getViewRadians() {
        return viewRadians;
    }

    public void setViewRadians(Double viewRadians) {
        this.viewRadians = viewRadians;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
