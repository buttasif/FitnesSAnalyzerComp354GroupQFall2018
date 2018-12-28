package TransactionScripts;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import DTO_PlaceHolders.Cards;
import Data_GateWays.DeckGateway;
public class ViewDeckTS {


	public static List <Cards> execute(Integer playerId, Integer vewdeckcount, ThreadLocal<Connection> dbConn) {
		
		List<Cards> listCards=new ArrayList<>();
		DeckGateway deckRDG=null;
		Cards cards=null;
		List <DeckGateway> deckListRDG=null;
		
		deckListRDG=DeckGateway.findFullDeckById(playerId, dbConn);
		
		
		
		for (int i=0;i<deckListRDG.size();i++) {
				deckRDG=deckListRDG.get(i);
				cards=new Cards(deckRDG.getType(),deckRDG.getName(),deckRDG.getId(),deckRDG.getPlayerId());
				listCards.add(cards);
		}
		return listCards;
		
	}
}
