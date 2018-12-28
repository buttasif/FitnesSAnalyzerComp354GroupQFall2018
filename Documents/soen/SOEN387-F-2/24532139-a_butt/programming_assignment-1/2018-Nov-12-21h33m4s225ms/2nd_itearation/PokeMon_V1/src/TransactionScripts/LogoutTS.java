package TransactionScripts;

import java.sql.Connection;

import Data_GateWays.UserRDG;
import View_Helpers.UserHelper;

public class LogoutTS {

	public static UserHelper execute(Integer id, ThreadLocal<Connection> dbConn) {
	
	UserHelper userHelper=null;
	UserRDG user=UserRDG.findByID(id, dbConn);
	
	if (user!=null) {
		userHelper = new UserHelper(user.getuName(),user.getVersion(),user.getId());
	}
	
	return userHelper;	
	
	}
	
}
