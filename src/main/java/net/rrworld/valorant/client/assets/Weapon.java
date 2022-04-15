package net.rrworld.valorant.client.assets;

/**
 * Provides static data about each weapon :
 * <ul>
 * <li>identifier</li>
 * <li>label</li>
 * </ul>
 * 
 * @author reuhreuh
 *
 */
// https://valorant-api.com/v1/weapons
public enum Weapon {

	MELEE("2f59173c-4bed-b6c3-2191-dea9b58be9c7", "Melee"),
	// Pistols
	CLASSIC("29a0cfab-485b-f5d5-779a-b59f85e204a8", "Classic"),
	GHOST("1baa85b4-4c70-1284-64bb-6481dfc3bb4e", "Ghost"),
	SHORTY("42da8ccc-40d5-affc-beec-15aa47b42eda", "Shorty"),
	SHERIFF("e336c6b8-418d-9340-d77f-7a9e4cfe0702", "Sheriff"),
	FRENZY("44d4e95c-4157-0037-81b2-17841bf2e8e3", "Frenzy"),
	// SMG
	STINGER("f7e1b454-4ad4-1063-ec0a-159e56b58941", "Stinger"),
	SPECTRE("462080d1-4035-2937-7c09-27aa2a5c27a7", "Spectre"),	
	// Shotguns
	BUCKY("910be174-449b-c412-ab22-d0873436b21b", "Bucky"),
	JUDGE("ec845bf4-4f79-ddda-a3da-0db3774b2794", "Judge"),
	// Rifle
	BULLDOG("ae3de142-4d85-2547-dd26-4e90bed35cf7", "Bulldog"),
	VANDAL("9c82e19d-4575-0200-1a81-3eacf00cf872", "Vandal"),
	PHANTOM("ee8e8d15-496b-07ac-e5f6-8fae5d4c7b1a", "Phantom"),
	GUARDIAN("4ade7faa-4cf1-8376-95ef-39884480959b", "Guardian"),
	// Batteuses
	ARES("55d8a0f4-4274-ca67-fe2c-06ab45efdf58", "Ares"),
	ODIN("63e6c2b6-4a8e-869c-3d4c-e38355226584", "Odin"),
	// Snipers
	MARSHALL("c4883e50-4494-202c-3ec3-6b8a9284f00b", "Marshall"),
	OPERATOR("a03b24d3-4319-996d-0f8c-94bbfba1dfc7", "Operator");
	
	private String id;
	private String label;
	
	private Weapon(String id, String label) {
		this.id = id;
		this.label = label;
	}

	public String getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}
	
	public static Weapon valueOfId(String id) {
		for(Weapon w : values()) {
			if(w.getId().equalsIgnoreCase(id)) {
				return w;
			}
		}
		return null;
	}
}
