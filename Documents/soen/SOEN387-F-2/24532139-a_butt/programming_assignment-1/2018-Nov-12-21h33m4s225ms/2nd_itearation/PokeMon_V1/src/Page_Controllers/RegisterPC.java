package Page_Controllers;

import java.io.IOException;
import java.sql.Connection;
import TransactionScripts.RegisterTS;
import Utility.Validate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Data_GateWays.UserRDG;


@WebServlet("/Register")
public class RegisterPC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  	public static ThreadLocal <Connection> dbConn;
    
	@Override    
    public void init() {
    	dbConn=new ThreadLocal <Connection>();
    }

	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		
		RequestDispatcher rd=null;
		String user=request.getParameter("user");
		String pass=request.getParameter("pass");
		String message;
		Integer rowsAffected=null;
		UserRDG uRDG;
			
		if ((user.equalsIgnoreCase(""))||(pass.equalsIgnoreCase(""))) {
			
			message = "Bad or No Input";
			callFailPage(request, response, message, rd);
			
		}
		
		uRDG=RegisterTS.execute(user, pass, dbConn);
		
		
		if (!(uRDG==null)) {									//handle duplicate & no input registrations
			
			message= "Already Registered";
			
			callFailPage(request, response, message,rd); 
		}
		else {
			uRDG =new UserRDG(user, pass);														 //create new UserRDG and Insert into SQL			
			rowsAffected = uRDG.insert(dbConn);
			uRDG = UserRDG.findByName(user, dbConn);
			
			if (rowsAffected!=null) {															//if query successful, 1 row will be affected, call success view
				
				message="User was successfully registerd";
				
				callSuccessPage(request, response, message, uRDG, rd); 
			}
			else {																			//if query unsuccessful, number of rows affected will be 0
				message="Something went wrong, registration was unsuccessfull";
				callFailPage(request, response, message,rd); 	
			}	
		}
	
	}

	private void callSuccessPage(HttpServletRequest request, HttpServletResponse response, String message,
			UserRDG uRDG,RequestDispatcher rd) {
		
		request.setAttribute("message", message);
		int uID = uRDG.getId();																		//get the user ID 
		HttpSession session = request.getSession();
		session.setAttribute("id", uID);
		session.setAttribute("viewdeckcount", 1);
		rd=request.getRequestDispatcher("success.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void callFailPage(HttpServletRequest request, HttpServletResponse response, String message, RequestDispatcher rd) {
		
		request.setAttribute("message", message);
		rd=request.getRequestDispatcher("fail.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	
	
}
