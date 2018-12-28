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


import TransactionScripts.AcceptChallengeTS;

/**
 * Servlet implementation class Accept_ChallengePC
 */
@WebServlet("/AcceptChallenge")
public class Accept_ChallengePC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
public static ThreadLocal <Connection> dbConn;
	
	
    
	@Override    
    public void init() {
    	
    	dbConn=new ThreadLocal <Connection>();
    }

	

	private static void processRequest(HttpServletRequest request, HttpServletResponse response){
		
		boolean accepted;
		HttpSession session=request.getSession();
		Integer challengeeId= (Integer)session.getAttribute("id");
		String challengeTemp=null;
		Integer challenge=null; 
		String message=null;
		RequestDispatcher rd=null;
		challengeTemp=request.getParameter("challenge");
		challenge=Integer.parseInt(challengeTemp);
		
		accepted=AcceptChallengeTS.execute(challenge,challengeeId, dbConn);	
		
		if (!accepted) {
			
			message="Challenger same as Challengee Or Accepting Another Person Challenge.";
			request.setAttribute("message", message);
			rd=request.getRequestDispatcher("fail.jsp");
			try {
				rd.forward(request, response);
			} catch (Exception e) {
				
				e.printStackTrace();
			} 
			
		}
		else {
			
			message="Challenge Accepted Successfully.";
			request.setAttribute("message", message);
			rd=request.getRequestDispatcher("success.jsp");
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
