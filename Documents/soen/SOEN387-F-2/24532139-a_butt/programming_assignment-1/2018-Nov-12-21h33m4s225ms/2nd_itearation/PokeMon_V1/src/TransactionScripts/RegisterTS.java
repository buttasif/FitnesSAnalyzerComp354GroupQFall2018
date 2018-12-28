package TransactionScripts;

import java.sql.Connection;

import Utility.Validate;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Data_GateWays.UserRDG;
import Utility.Validate;
import Data_GateWays.UserRDG;

public class RegisterTS {

	public static UserRDG execute(String user, String pass, ThreadLocal<Connection> dbConn) {
		
		UserRDG uRDG=null;
		uRDG = UserRDG.findByName(user, dbConn);
		return uRDG;
	}
}
