package DTO_PlaceHolders;

public class Cards {

	private String type;
	private String name;
	private int id;
	private int playerId;
	
	public Cards(String type, String name, int id, int playerId) {
		super();
		this.type = type;
		this.name = name;
		this.id = id;
		this.playerId = playerId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	
	
}
