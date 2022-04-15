package net.rrworld.valorant.client.assets;

/**
 * Provides static data about each act :
 * <ul>
 * <li>identifier</li>
 * <li>label</li>
 * <li>in game path</li>
 * </ul>
 * 
 * @author reuhreuh
 *
 */
// https://valorant-api.com/v1/maps
public enum Map {

	ASCENT("7eaecc1b-4337-bbf6-6ab9-04b8f06b3319", "Ascent","/Game/Maps/Ascent/Ascent"),
	BIND("2c9d57ec-4431-9c5e-2939-8f9ef6dd5cba", "Bind","/Game/Maps/Duality/Duality"),
	BREEZE("2fb9a4fd-47b8-4e7d-a969-74b4046ebd53", "Breeze","/Game/Maps/Foxtrot/Foxtrot"),
	HAVEN("2bee0dc9-4ffe-519b-1cbd-7fbe763a6047", "Haven","/Game/Maps/Triad/Triad"),
	ICEBOX("e2ad5c54-4114-a870-9641-8ea21279579a", "Icebox","/Game/Maps/Port/Port"),
	SPLIT("d960549e-485c-e861-8d71-aa9d1aed12a2", "Split","/Game/Maps/Bonsai/Bonsai"),
	FRACTURE("b529448b-4d60-346e-e89e-00a4c527a405", "Fracture","/Game/Maps/Canyon/Canyon"), 
	NONE("NONE", "None","NONE");

	private String id;
	private String label;
	private String path;

	private Map(String id, String label, String path) {
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
