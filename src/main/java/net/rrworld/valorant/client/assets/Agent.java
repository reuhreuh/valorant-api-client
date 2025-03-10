package net.rrworld.valorant.client.assets;

/**
 * Provides static data about each agent :
 * <ul>
 * <li>identifier</li>
 * <li>label</li>
 * <li>role</li>
 * <li>main color</li>
 * </ul>
 * 
 * @author reuhreuh
 *
 */
public enum Agent {
	
	/**
	 * Sentinel, reveals
	 */
	CYPHER("117ed9e3-49f3-6512-3ccf-0cada7e3823b","Cypher","Sentinel","#B7B7B6"),
	/**
	 * Sentinel, alarm bots, nano nades
	 */
	KILLJOY("1e58de9c-4950-5125-93e9-a0aee9f98746","Killjoy","Sentinel","#FADD32"),
	/**
	 * Best agent
	 */
	BREACH("5f8d3a7f-467b-97f3-062c-13acf203c006","Breach","Initiator","#C24C20"),
	/**
	 * Initiator, smokes
	 */
	BRIMSTONE("9f0d8ba9-4140-b941-57d3-a7ad57c6b417","Brimstone","Controller","#2D232E"),
	/**
	 * Duelist, nade, C4, bazooka
	 */
	RAZE("f94c3b30-42be-e959-889c-5aa313dba261","Raze","Duelist","#FE6547"),
	/**
	 * Sentinel, heals, slows, walls
	 */
	SAGE("569fdd95-4d10-43ab-ca70-79becc718b46","Sage","Sentinel","#0F1924"),
	/**
	 * Controller, smokes, poisons, wall
	 */
	VIPER("707eab51-4836-f488-046a-cda6bf494859","Viper","Controller","#18AE47"),
	/**
	 * Duelist, flashes, molly
	 */
	PHOENIX("eb93336a-449b-9c1b-0a54-a891f7921d69","Phoenix","Duelist","#BD431F"),
	/**
	 * Initiator, dashes, smokes
	 */
	JETT("add6443a-41bd-e414-f6ad-e58d267f4e95","Jett","Duelist","#87DCF5"),
	/**
	 * Initiator, reveal
	 */
	SOVA("320b2a48-4d9b-a075-30f1-1f93a9b638fa","Sova","Initiator","#CFE2E8"),
	/**
	 * Controller, smokes, TP
	 */
	OMEN("8e253930-4c05-31dd-1b6c-968525494517","Omen","Controller","#3F4D99"),
	/**
	 * Duelist, flashes, heal, dimiss
	 */
	REYNA("a3bfb853-43b2-7238-a4f1-ad90e9e46bcc","Reyna","Duelist","#A6479A"),
	/**
	 * Initiator, flashes, reveal
	 */
	SKYE("6f2a04ca-43e0-be17-7f36-b3908627744d","Skye","Initiator","#434026"),
	/**
	 * Controller, Smokes, stuns, etc..
	 */
	ASTRA("41fb69c1-4189-7b37-f117-bcaf1e96f1bf","Astra","Controller","#DD2AA3"),
	/**
	 * Initiator, disables abilities, flashes
	 */
	KAYO("601dbbe7-43ce-be57-2a40-4abd24953621","KAY/O","Initiator","#415161"),
	/**
	 * Duelist, flashes, TPs
	 */
	YORU("7f94d92c-4234-0a36-9646-3a87eb8b5c89","Yoru","Duelist","#011CAF"),
	/**
	 * Sentinel, TPs, special weapons
	 */
	CHAMBER("22697a3d-45bf-8dd7-4fec-84a9e28c69d7","Chamber","Sentinel","#39A2D7"),
	/**
	 * Duelist, runs fast, stuns
	 */
	NEON("bb2a4828-46eb-8cd1-e765-15848195d751","Neon","Duelist","#125FD8"),
	/**
	 * Initiator, reveal, block, since Episode 4 - Act 3
	 * @since 1.0.1
	 */
	FADE("dade69b4-4f5a-8528-247b-219e5a1facd6","Fade","Initiator","#2e4f78"),
	/**
	 * Controller, smoke, wall, wave. since Episode 5 - Act 3
	 * @since 1.0.4
	 */
	HARBOR("95b78ed7-4637-86d9-7e41-71ba8c293152","Harbor","Controller","#327766"),
	/**
	 * Initiator, molly, splash flash, wingman bot. Since Episode 6 - Act 2
	 * @since 1.0.8
	 */
	GEKKO("e370fa57-4757-3604-3648-499e1f642d3f","Gekko","Initiator","#C7F559"),
	/**
	 * Sentinel, walls and traps.
	 * @since 1.0.9
	 */
	DEADLOCK("cc8b64c8-4b25-4ff9-6e7f-37b4da43d235","Deadlock","Sentinel","#99918D"),
	/**
	 * Duelist with initiator abilities (wall, decay)
	 * @since 1.0.11
	 */
	ISO("0e38b510-41a8-5780-5e8f-568b2a4f2d6c", "Iso", "Duelist","#3C1279"),
	/**
	 * Controller, can use smokes while dead. Since Episode 8 - Act 2
	 * @since 1.0.14
	 */
	CLOVE("1dbf2edd-4729-0984-3115-daa5eed44993", "Clove", "Controller","#B270A4"),
	/**
	 * Sentinel, with trap, flash, molly and able to disable primary weapon. Since Episode 9 - Act 2
	 * @since 1.0.16
	 */
	VYSE("efba5359-4016-a1e5-7626-b1ae76895940", "Vyse", "Sentinel","#4F5877"),
	/**
	 * Initiator with stun, damages, intel, and disable abilities
	 * @since 1.0.17
	 */
	TEJO("b444168c-4e35-8076-db47-ef9bf368f384", "Tejo", "Initiator","#6B4210"),
	/**
	 * Duelist with TP back, dash and new stun. with S25 // Act 2.
	 * @since 1.0.18
	 */
	WAYLAY("df1cb487-4902-002e-5c17-d28e83e78588", "Waylay", "Duelist","#F0B469");
	
	
	private String id;
	private String label;
	private String color;
	private String role;
	
	private Agent(String id, String label, String role, String color) {
		this.id = id;
		this.label = label;
		this.role = role;
		this.color = color;
	}
	
	/**
	 * Get Riot API unique identifier
	 * @return the UUID
	 */
	public String getId() {
		return this.id;
	}
	/**
	 * Get main color
	 * @return the color
	 */
	public String getColor() {
		return this.color;
	}
	/**
	 * Get agent role (Initiator, Sentinel, Duelist, Controller, Initiator)
	 * 
	 * @return the role
	 */
	public String getRole() {
		return this.role;
	}
	/**
	 * Get agents Name
	 * @return the agent name
	 */
	public String getLabel() {
		return this.label;
	}
	
	public static Agent valueOfId(String id) {
		for(Agent a : values()) {
			if(a.getId().equalsIgnoreCase(id)) {
				return a;
			}
		}
		return null;
	}
}
