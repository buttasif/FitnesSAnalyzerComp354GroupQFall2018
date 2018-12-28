package TransactionScripts;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import Data_GateWays.ChallengeRDG;
import Data_GateWays.GamesRDG;
import Data_GateWays.PlayGateway;
import DTO_PlaceHolders.PlayDTO;
import Data_GateWays.BoardGateway;

public class AcceptChallengeTS {

	public static boolean execute(Integer challenge, Integer challengeeId, ThreadLocal<Connection> dbConn) {
	
		Integer boardID=null;
		Integer player1=null;
		Integer player2=null;
		Integer player1deck=null;
		Integer player2deck=null;
	
		BoardGateway board=null;
		PlayDTO player1DTO=null;
		PlayDTO player2DTO=null;
		
		List <PlayDTO> playersList=new ArrayList();
		
		
		
		boolean accepted=false;
		ChallengeRDG acceptC=null;
		acceptC=ChallengeRDG.findById(challenge,dbConn);
		
		if ( (challengeeId!=acceptC.getChallengee()) || (challengeeId==acceptC.getChallenger()) ){	//cannot accept someone else challenge
			return false;
		}
		
		else { 			
			ChallengeRDG.updateChallenge(challenge,dbConn);
			GamesRDG.insert(acceptC.getChallenger(), challengeeId, dbConn);
			
			boardID=acceptC.getChallengeId();
			player1=acceptC.getChallenger();
			player2=acceptC.getChallengee();
			player1deck=acceptC.getChallenger();
			player2deck=acceptC.getChallengee();
			
			BoardGateway.insert(boardID, acceptC.getChallenger(),acceptC.getChallengee(),dbConn);
			board=BoardGateway.findBoardByPlayers(boardID, dbConn);
									
			createPlayerDTOs(boardID, player1, player2);
			
			
			player1DTO=new PlayDTO(boardID, player1);
			player2DTO=new PlayDTO(boardID, player2);
			
			PlayGateway.insert(player1DTO, dbConn);
			PlayGateway.insert(player2DTO, dbConn);
			
			
			return true;
		}
		
	}

	private static void createPlayerDTOs(Integer boardID, Integer player1, Integer player2) {
		PlayDTO player1DTO;
		PlayDTO player2DTO;
		player1DTO=new PlayDTO(boardID, player1);
		player2DTO=new PlayDTO(boardID, player2);
	}

	
	

	
	
}
