package Page_Controllers;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import TransactionScripts.RefuseChallengeTS;


@WebServlet("/RefuseChallenge")
public class RefuseChallengePC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
public static ThreadLocal <Connection> dbConn;
	
	
    
	@Override    
    public void init() {
    	
    	dbConn=new ThreadLocal <Connection>();
    }

private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		
		
		HttpSession session=request.getSession();
		Integer playerID= (Integer)session.getAttribute("id");
		String challengeIdTemp=null;
		Integer challengeId=null; 
		
		String message=null;
		RequestDispatcher rd=null;
		
		challengeIdTemp=request.getParameter("challenge");
		challengeId=Integer.parseInt(challengeIdTemp);
		boolean  success=RefuseChallengeTS.execute(challengeId, playerID, dbConn);
		
		if (success) {
			
			message="Challenge Refused Successfully.";
			request.setAttribute("message", message);
			rd=request.getRequestDispatcher("success.jsp");
			try {
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		}else {
			
			message="Cannot withdra/refuse other people challenges";
			request.setAttribute("message", message);
			rd=request.getRequestDispatcher("fail.jsp");
			try {
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		
	}

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			processRequest(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}


	

}
