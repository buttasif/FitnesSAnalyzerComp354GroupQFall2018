package TransactionScripts;

import java.sql.Connection;
import java.util.List;
import java.util.StringTokenizer;
import java.util.ArrayList;
import DTO_PlaceHolders.Cards;
import Data_GateWays.DeckGateway;



public class UploadDeckTS {

	private static final int DECK_SIZE=40;	
	
	public static Integer execute(int playerId, String deck, ThreadLocal<Connection> dbConn){
		
		
		List <String> types= new ArrayList<>(40);
		List <String> names= new ArrayList<>(40);
		List <DeckGateway> deckGateway=new ArrayList<>();
		Integer rowsAffected=null;
		
		extractCardsMetaData(deck, types, names);
		
		if (types.size()>DECK_SIZE||types.size()<DECK_SIZE) {
			return null;
		}
		else {
			rowsAffected=DeckGateway.insertDeck(playerId, types,names,dbConn);
			return rowsAffected;
		}
	}

	private static void extractCardsMetaData(String deck, List<String> types, List<String> names) {
		String MULTIPLE_DELIMITER =" \"\n";
        int count=0;
        
        StringTokenizer dsplit=new StringTokenizer(deck, MULTIPLE_DELIMITER);
        while (dsplit.hasMoreTokens()){
            if (count % 2==0){
                types.add(dsplit.nextToken());
            }
            else{
                names.add(dsplit.nextToken());
            }
            count++;
        }
	}
}
