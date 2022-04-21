package net.rrworld.valorant.client.assets;

import java.time.ZonedDateTime;

/**
 * Provides static data about each act :
 * <ul>
 * <li>identifier</li>
 * <li>label</li>
 * <li>start &amp; end time</li>
 * </ul>
 * 
 * @author reuhreuh
 *
 */
// https://valorant-api.com/v1/seasons
public enum Act {
	
	// Episode 2
	EP2_Act1("97b6e739-44cc-ffa7-49ad-398ba502ceb0", "Ep2 - Act1", "2021-01-12T00:00:00Z", "2021-03-02T00:00:00Z"),
	EP2_Act2("ab57ef51-4e59-da91-cc8d-51a5a2b9b8ff", "Ep2 - Act2", "2021-03-02T00:00:00Z", "2021-04-27T00:00:00Z"),
	EP2_Act3("52e9749a-429b-7060-99fe-4595426a0cf7", "Ep2 - Act3", "2021-04-27T00:00:00Z", "2021-06-22T00:00:00Z"),
	// Episode 3
	EP3_Act1("2a27e5d2-4d30-c9e2-b15a-93b8909a442c", "Ep3 - Act1", "2021-06-22T00:00:00Z", "2021-09-08T00:00:00Z"),
	EP3_Act2("4cb622e1-4244-6da3-7276-8daaf1c01be2", "Ep3 - Act2", "2021-09-08T00:00:00Z", "2021-11-02T00:00:00Z"),
	EP3_Act3("a16955a5-4ad0-f761-5e9e-389df1c892fb", "Ep3 - Act3", "2021-11-02T00:00:00Z", "2022-01-11T00:00:00Z"),
	// Episode 4
	EP4_Act1("573f53ac-41a5-3a7d-d9ce-d6a6298e5704", "Ep4 - Act1", "2022-01-11T00:00:00Z", "2022-03-01T00:00:00Z"),
	EP4_Act2("d929bc38-4ab6-7da4-94f0-ee84f8ac141e", "Ep4 - Act2", "2022-03-01T00:00:00Z", "2022-04-26T00:00:00Z"),
	EP4_Act3("3e47230a-463c-a301-eb7d-67bb60357d4f", "Ep4 - Act3", "2022-04-26T00:00:00Z", "2022-06-21T00:00:00Z");
	
	private String id;
	private String label;
	private ZonedDateTime startTime;
	private ZonedDateTime endTime;
	
	private Act(String id, String label, String startTime, String endTime) {
		this.id = id;
		this.label = label;
		this.startTime = ZonedDateTime.parse(startTime);
		this.endTime = ZonedDateTime.parse(endTime);
	}
	
	public String getId() {
		return this.id;
	}

	public String getLabel() {
		return label;
	}

	public ZonedDateTime getStartTime() {
		return startTime;
	}

	public ZonedDateTime getEndTime() {
		return endTime;
	}
	
	public static Act valueOfId(String id) {
		for(Act a : values()) {
			if(a.getId().equals(id)) {
				return a;
			}
		}
		return null;
	}
}
