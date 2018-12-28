package TransactionScripts;

import View_Helpers.UserHelper;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Data_GateWays.UserRDG;
import Utility.Utility;

public class LoginTS {

	
	public static UserHelper execute(String user, String pass, ThreadLocal<Connection> dbConn) {
		
		UserHelper userHelper=null;
		UserRDG uRDG=UserRDG.findByName(user, dbConn);
		if (uRDG==null|| (!(Utility.validateLogin(uRDG.getPassword(), pass)))) {
			return userHelper;
		}
		else {
			userHelper=new UserHelper(user, uRDG.getVersion(),uRDG.getId());
			return userHelper;
		}
	}
	
}
