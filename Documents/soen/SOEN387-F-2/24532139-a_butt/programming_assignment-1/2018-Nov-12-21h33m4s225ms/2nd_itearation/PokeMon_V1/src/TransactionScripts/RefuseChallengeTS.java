package TransactionScripts;

import java.sql.Connection;

import Data_GateWays.ChallengeRDG;

public class RefuseChallengeTS {

	final static Integer REFUSE_OWN_CHALLENGE=2;
	final static Integer REFUSE_OTHER_CHALLENGE=1;
	
	public static boolean execute(Integer challengeId, Integer playerID, ThreadLocal<Connection> dbConn) {
		
		boolean answer=false;
		int status=0;
		ChallengeRDG refuseC=null;
		refuseC=ChallengeRDG.findById(challengeId,dbConn);
		
		if (playerID==refuseC.getChallenger() && challengeId==refuseC.getChallengeId()) {
				status=REFUSE_OWN_CHALLENGE;
				ChallengeRDG.updateRefuseChallenge(challengeId, status, dbConn);
				answer= true;
			}
		else {
			if  (playerID==refuseC.getChallengee() && challengeId==refuseC.getChallengeId()) {
				status=REFUSE_OTHER_CHALLENGE;
				ChallengeRDG.updateRefuseChallenge(challengeId, status, dbConn);
				answer=true;
			} 
			else {
				answer=false;
			}
			
		}
		
		return answer;
			
	}

}
