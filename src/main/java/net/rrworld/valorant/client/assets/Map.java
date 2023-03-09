package net.rrworld.valorant.client.assets;

/**
 * Provides static data about each map :
 * <ul>
 * <li>identifier</li>
 * <li>label</li>
 * <li>in-game path</li>
 * </ul>
 * 
 * @author reuhreuh
 *
 */
// https://valorant-api.com/v1/maps
public enum Map {

	/**
	 * de_dust2 look alike, electric doors
	 */
	ASCENT("7eaecc1b-4337-bbf6-6ab9-04b8f06b3319", "Ascent","/Game/Maps/Ascent/Ascent",0.00007, -0.00007, 0.813895, 0.573242),
	/**
	 * 2 Tps
	 */
	BIND("2c9d57ec-4431-9c5e-2939-8f9ef6dd5cba", "Bind", "/Game/Maps/Duality/Duality", 0.000059, -0.000059, 0.576941, 0.967566),
	/**
	 * Very wide map
	 */
	BREEZE("2fb9a4fd-47b8-4e7d-a969-74b4046ebd53", "Breeze", "/Game/Maps/Foxtrot/Foxtrot", 0.00007, -0.00007, 0.465123, 0.833078),
	/**
	 * Attacker have 2 spawns, defenders start from center
	 */
	FRACTURE("b529448b-4d60-346e-e89e-00a4c527a405", "Fracture", "/Game/Maps/Canyon/Canyon", 0.000078,-0.000078, 0.556952, 1.155886),
	/**
	 * 3 BS A,B,C
	 */
	HAVEN("2bee0dc9-4ffe-519b-1cbd-7fbe763a6047", "Haven", "/Game/Maps/Triad/Triad", 0.000075, -0.000075, 1.09345, 0.642728),
	/**
	 * Zip lines
	 */
	ICEBOX("e2ad5c54-4114-a870-9641-8ea21279579a", "Icebox", "/Game/Maps/Port/Port", 0.000072, -0.000072, 0.460214, 0.304687),
	/**
	 * In India, with rotating doors and 3 BS A,B,C
	 * @since 1.0.6
	 */
	LOTUS("2fe4ed3a-450a-948b-6d6b-e89a78e680a9", "Lotus", "/Game/Maps/Jam/Jam", 0.000072, -0.000072, 0.454789, 0.917752),
	/**
	 * Underwater world, Lisboa lookalike
	 * @since 1.0.3
	 */
	PEARL("fd267378-4d1d-484f-ff52-77821ed10dc2", "Pearl","/Game/Maps/Pitt/Pitt", 0.000078, -0.000078, 0.480469, 0.916016),
	/**
	 * Close range fights
	 */
	SPLIT("d960549e-485c-e861-8d71-aa9d1aed12a2", "Split","/Game/Maps/Bonsai/Bonsai",0.000078,-0.000078,0.842188,0.697578),
	/**
	 * For internal usage
	 */
	NONE("NONE", "None","NONE", 0d,0d,0d,0d);

	private String id;
	private String label;
	private String path;
	private Double xMultiplier;
	private Double yMultiplier;
	private Double xScalarToAdd;
	private Double yScalarToAdd;

	private Map(String id, String label, String path, Double xMultiplier,  Double yMultiplier,  Double xScalarToAdd, Double yScalarToAdd) {
		this.id = id;
		this.label = label;
		this.path = path;
	}

	public String getId() {
		return this.id;
	}

	public String getLabel() {
		return label;
	}

	public String getPath() {
		return path;
	}
	public Double getxMultiplier() {
		return xMultiplier;
	}

	public Double getyMultiplier() {
		return yMultiplier;
	}

	public Double getxScalarToAdd() {
		return xScalarToAdd;
	}

	public Double getyScalarToAdd() {
		return yScalarToAdd;
	}
	
	public static Map valueOfId(String id) {
		for (Map a : values()) {
			if (a.getId().equals(id)) {
				return a;
			}
		}
		return NONE;
	}

	public static Map valueOfPath(String path) {
		for (Map a : values()) {
			if (a.getPath().equalsIgnoreCase(path)) {
				return a;
			}
		}
		return NONE;
	}
}
