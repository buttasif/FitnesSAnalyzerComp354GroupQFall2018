package TransactionScripts;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import View_Helpers.ChallengeHelper;

import Data_GateWays.ChallengeRDG;
import View_Helpers.ChallengeHelper;

public class ListChallengesTS {

	public static List<ChallengeHelper> execute(ThreadLocal <Connection> dbConn) {
		
		ChallengeHelper challengeHelper;
		
		ChallengeRDG challenge;
		int challenger;
		int challengee;
		int status;
		int id;
		
		List<ChallengeHelper> challengeHelperList= new ArrayList<>();
		List <ChallengeRDG> challengeRDGList =ChallengeRDG.findAllChallenges(dbConn);
		
		for (ChallengeRDG c: challengeRDGList) {
			
			challenger=c.getChallenger();
			challengee=c.getChallengee();
			id=c.getChallengeId();
			status=c.getChalengeStaus();
			
			challengeHelper=new ChallengeHelper(challenger,challengee,id,status);
			challengeHelperList.add(challengeHelper);
			
		}
		return challengeHelperList;
	}

}
