package Utility;

import org.apache.catalina.User;

public class Utility {

	public static boolean validateLogin(String userPass, String dbPass){
		if (userPass.equalsIgnoreCase(dbPass)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean inputValid(String input) {
		
		if (input==null) {
			return false;
		}
		else {
			return true;
		}
	}
	
}

