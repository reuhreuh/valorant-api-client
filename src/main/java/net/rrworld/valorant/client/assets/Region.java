package net.rrworld.valorant.client.assets;

import net.rrworld.valorant.client.ValorantClient;

/**
 * Represent Riot world regions, used for {@link ValorantClient}
 * 
 * @author reuhreuh
 *
 */
public enum Region {
	/**
	 * Pacific Asia
	 */
	AP("ap"),
	/**
	 * Brazil
	 */
	BR("br"),
	/**
	 * E-Sports
	 */
	ESPORTS("esports"),
	/**
	 * Europe
	 */
	EU("eu"),
	/**
	 * South Korea
	 */
	KR("kr"),
	/**
	 * Latin America
	 */
	LATAM("latam"),
	/**
	 * Near Airport
	 */
	NA("na");
	
	private String value;
	
	private Region(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
