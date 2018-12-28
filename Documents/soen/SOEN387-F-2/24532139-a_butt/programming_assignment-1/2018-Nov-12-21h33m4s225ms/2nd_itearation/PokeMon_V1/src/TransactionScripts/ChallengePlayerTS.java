package TransactionScripts;

import java.sql.Connection;
import View_Helpers.ChallengeHelper;
import Data_GateWays.ChallengeRDG;
import Data_GateWays.UserRDG;
import Data_GateWays.UserRDG;


public class ChallengePlayerTS {

	public static int execute(Integer challenger, Integer challengee, ThreadLocal<Connection> dbConn) {
		
		int rowsAffected;
		UserRDG u=UserRDG.findByID(challengee, dbConn);
		
		if (u==null||u.getId()==null) {
			rowsAffected=-1;
		}
		else {
		
		rowsAffected=ChallengeRDG.insert(challenger, challengee, dbConn);	
		
		}
		
		return rowsAffected;
		
	}

	

}
