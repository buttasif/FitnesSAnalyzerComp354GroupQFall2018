package DTO_PlaceHolders;

public class PlayDTO {

	
	int boardId;
	int PlayerID;
	int handSize;
	int deckSize;
	int discardSize;
	int benchId;
	String status=null;
	
	
	public PlayDTO(int boardId, int playerID, int handSize, int deckSize, int discardSize, int benchId) {
		super();
		this.boardId = boardId;
		PlayerID = playerID;
		this.handSize = handSize;
		this.deckSize = deckSize;
		this.discardSize = discardSize;
		this.benchId = benchId;
		this.status="playing";
	}
	public PlayDTO(Integer boardID2, Integer player1) {
		this.boardId=boardID2;
		this.PlayerID=player1;
		this.benchId=boardID2;
		this.status="playing";
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public int getPlayerID() {
		return PlayerID;
	}
	public void setPlayerID(int playerID) {
		PlayerID = playerID;
	}
	public int getHandSize() {
		return handSize;
	}
	public void setHandSize(int handSize) {
		this.handSize = handSize;
	}
	public int getDeckSize() {
		return deckSize;
	}
	public void setDeckSize(int deckSize) {
		this.deckSize = deckSize;
	}
	public int getDiscardSize() {
		return discardSize;
	}
	public void setDiscardSize(int discardSize) {
		this.discardSize = discardSize;
	}
	public int getBenchId() {
		return benchId;
	}
	public void setBenchId(int benchId) {
		this.benchId = benchId;
	}
	
	
	
}
