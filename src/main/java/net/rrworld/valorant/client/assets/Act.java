package net.rrworld.valorant.client.assets;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Comparator;

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
	EP4_Act3("3e47230a-463c-a301-eb7d-67bb60357d4f", "Ep4 - Act3", "2022-04-26T00:00:00Z", "2022-06-21T00:00:00Z"),
	// Episode 5
	EP5_Act1("67e373c7-48f7-b422-641b-079ace30b427", "Ep5 - Act1", "2022-06-21T00:00:00Z", "2022-08-23T00:00:00Z"),
	EP5_Act2("7a85de9a-4032-61a9-61d8-f4aa2b4a84b6", "Ep5 - Act2", "2022-08-23T00:00:00Z", "2022-10-18T00:00:00Z"),
	EP5_Act3("aca29595-40e4-01f5-3f35-b1b3d304c96e", "Ep5 - Act3", "2022-10-18T00:00:00Z", "2023-01-10T00:00:00Z"),
	// Episode 6
	EP6_Act1("9c91a445-4f78-1baa-a3ea-8f8aadf4914d", "Ep6 - Act1", "2023-01-10T00:00:00Z", "2023-03-07T00:00:00Z"),
	EP6_Act2("34093c29-4306-43de-452f-3f944bde22be", "Ep6 - Act2", "2023-03-07T00:00:00Z", "2023-04-25T00:00:00Z"),
	EP6_Act3("2de5423b-4aad-02ad-8d9b-c0a931958861", "Ep6 - Act3", "2023-04-25T00:00:00Z", "2023-06-27T00:00:00Z"),
	// Episode 7
	EP7_Act1("0981a882-4e7d-371a-70c4-c3b4f46c504a", "Ep7 - Act1", "2023-06-27T00:00:00Z", "2023-08-29T00:00:00Z"),
	EP7_Act2("03dfd004-45d4-ebfd-ab0a-948ce780dac4", "Ep7 - Act2", "2023-08-29T00:00:00Z", "2023-10-31T00:00:00Z"),
	EP7_Act3("4401f9fd-4170-2e4c-4bc3-f3b4d7d150d1", "Ep7 - Act3", "2023-10-31T00:00:00Z", "2024-01-09T00:00:00Z"),
	// Episode 8
	EP8_Act1("ec876e6c-43e8-fa63-ffc1-2e8d4db25525", "Ep8 - Act1", "2024-01-10T00:00:00Z", "2024-03-05T00:00:00Z"),
	EP8_Act2("22d10d66-4d2a-a340-6c54-408c7bd53807", "Ep8 - Act2", "2024-03-06T00:00:00Z", "2024-04-30T00:00:00Z"),
	EP8_Act3("4539cac3-47ae-90e5-3d01-b3812ca3274e", "Ep8 - Act3", "2024-05-01T00:00:00Z", "2024-06-25T00:00:00Z"),
	// Episode 9
	EP9_Act1("", "Ep9 - Act1", "2024-06-25T00:00:00Z", ""),
	EP9_Act2("", "Ep9 - Act2", "", ""),
	EP9_Act3("", "Ep9 - Act3", "", "");
	
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
	
	/**
	 * Gets <code>Act</code> from a give Riot identifier (uuid)
	 * @param id the Riot identifier
	 * @return corresponding <code>Act</code>, or <code>null</code> if it does not exist
	 */
	public static Act valueOfId(String id) {
		for(Act a : values()) {
			if(a.getId().equals(id)) {
				return a;
			}
		}
		return null;
	}
	
	/**
	 * Returns an array with <code>Act</code> sorted in anti chronological order
	 * @return sorted <code>Act</code> array
	 */
	public static Act[] recentFirst() {
		Act[] res = values();
		Arrays.sort(res, new Comparator<Act>() {
			@Override
			public int compare(Act a1, Act a2) {
				return a2.getStartTime().compareTo(a1.getStartTime());
			}
		});
		return res;
	}
}
