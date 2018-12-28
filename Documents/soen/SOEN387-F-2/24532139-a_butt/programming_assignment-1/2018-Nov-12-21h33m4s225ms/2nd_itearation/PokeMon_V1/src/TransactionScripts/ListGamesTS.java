package TransactionScripts;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import DTO_PlaceHolders.GamesDTO;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import Data_GateWays.GamesRDG;

public class ListGamesTS {

	static final int PLAYER_1=0;
	static final int PLAYER_2=1;

	
	public static List<String> execute(ThreadLocal<Connection> dbConn) {
		
	ObjectMapper mapper = new ObjectMapper ();
	String jsonString=null;
	
	List<String> jsonStrings=new ArrayList<>();
	
		List <GamesRDG> listGameRDG=new ArrayList<>();
		List <GamesDTO> listGameDTO=new ArrayList<>();
		GamesDTO gameDTO=null;
				
		int [] players = new int [2];
				
		listGameRDG=GamesRDG.findAllGames(dbConn);
		
		for(GamesRDG g: listGameRDG) {
			
			players[PLAYER_1]=g.getChallenger();
			players[PLAYER_2]=g.getChallengee();
			
			gameDTO=new GamesDTO(g.getGameId(),players);
			
			
			try {
				jsonString=mapper.writeValueAsString(gameDTO);
				jsonStrings.add(jsonString);
			} catch (JsonGenerationException e) {
					e.printStackTrace();
			} catch (JsonMappingException e) {
					e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
				
		return jsonStrings;
	}

	
	
}
