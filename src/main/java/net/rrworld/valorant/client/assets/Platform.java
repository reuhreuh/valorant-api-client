package net.rrworld.valorant.client.assets;

/**
 * Existing Valorant supported platform (PC and Console)
 */
public enum Platform {
	
	/**
	 * PC
	 */
	PC("pc"),
	/**
	 * Console
	 */
	CONSOLE("console");
	
	private String value;
	
	private Platform(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
