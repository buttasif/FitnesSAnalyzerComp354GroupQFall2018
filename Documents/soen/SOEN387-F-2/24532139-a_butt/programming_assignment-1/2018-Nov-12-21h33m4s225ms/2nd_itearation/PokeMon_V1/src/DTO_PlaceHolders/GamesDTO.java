package DTO_PlaceHolders;

public class GamesDTO {

	int gameId;
	int [] players = new int [2];
	
	
	public GamesDTO(int gameId, int[] players) {
		super();
		this.gameId = gameId;
		this.players = players;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public int[] getPlayers() {
		return players;
	}
	public void setPlayers(int[] players) {
		this.players = players;
	}
	
	
	
	
}
