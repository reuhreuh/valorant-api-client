package net.rr_world.valorant.client.assets;

/**
 * Provides static data about each act :
 * <ul>
 * <li>identifier</li>
 * <li>label</li>
 * <li>main color</li>
 * </ul>
 * 
 * @author reuhreuh
 *
 */
public enum Agent {
	
	CYPHER("117ed9e3-49f3-6512-3ccf-0cada7e3823b","Cypher","#B7B7B6"),
	KILLJOY("1e58de9c-4950-5125-93e9-a0aee9f98746","Killjoy","#FADD32"),
	BREACH("5f8d3a7f-467b-97f3-062c-13acf203c006","Breach","#C24C20"),
	BRIMSTONE("9f0d8ba9-4140-b941-57d3-a7ad57c6b417","Brimstone","#2D232E"),
	RAZE("f94c3b30-42be-e959-889c-5aa313dba261","Raze","#FE6547"),
	SAGE("569fdd95-4d10-43ab-ca70-79becc718b46","Sage","#0F1924"),
	VIPER("707eab51-4836-f488-046a-cda6bf494859","Viper","#18AE47"),
	PHOENIX("eb93336a-449b-9c1b-0a54-a891f7921d69","Phoenix","#BD431F"),
	JETT("add6443a-41bd-e414-f6ad-e58d267f4e95","Jett","#87DCF5"),
	SOVA("320b2a48-4d9b-a075-30f1-1f93a9b638fa","Sova","#CFE2E8"),
	OMEN("8e253930-4c05-31dd-1b6c-968525494517","Omen","#3F4D99"),
	REYNA("a3bfb853-43b2-7238-a4f1-ad90e9e46bcc","Reyna","#A6479A"),
	SKYE("6f2a04ca-43e0-be17-7f36-b3908627744d","Skye","#434026"),
	ASTRA("41fb69c1-4189-7b37-f117-bcaf1e96f1bf","Astra","#DD2AA3"),
	KAYO("601dbbe7-43ce-be57-2a40-4abd24953621","KAY/O","#415161"),
	YORU("7f94d92c-4234-0a36-9646-3a87eb8b5c89","Yoru","#011CAF"),
	CHAMBER("22697a3d-45bf-8dd7-4fec-84a9e28c69d7","Chamber","#39A2D7"),
	NEON("bb2a4828-46eb-8cd1-e765-15848195d751","Neon","#125FD8");
	
	private String id;
	private String label;
	private String color;
	
	private Agent(String id, String label, String color) {
		this.id = id;
		this.label = label;
		this.color = color;
	}
	
	public String getId() {
		return this.id;
	}
	public String getColor() {
		return this.color;
	}
	public String getLabel() {
		return this.label;
	}
	
	public static Agent valueOfId(String id) {
		for(Agent a : values()) {
			if(a.getId().equals(id)) {
				return a;
			}
		}
		return null;
	}
}
