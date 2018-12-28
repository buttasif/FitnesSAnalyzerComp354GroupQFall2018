package Utility;

public class Validate {

	public static boolean validataeInput(int input) {
		return false;
		
	}

	public static boolean inputValid(String user, String pass) {
		if (user.equalsIgnoreCase("")||pass.equalsIgnoreCase("")) {		
			return false;
		}
		else {
			return true;
		}
	}
	
	
}
