package Page_Controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import TransactionScripts.ChallengePlayerTS;


/**
 * Servlet implementation class ChallengePlayerPC
 */
@WebServlet("/ChallengePlayer")
public class ChallengePlayerPC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
  	public static ThreadLocal <Connection> dbConn;
    
	@Override    
    public void init() {
    	dbConn=new ThreadLocal <Connection>();
    }
	
static final Integer CHALLENGE_SUCCESS=1;
	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session=request.getSession();
		String message=null;
		RequestDispatcher rd=null;
		Integer challenger=(Integer)session.getAttribute("id");
		Integer success=null;
		Integer deckuploaded=(Integer) session.getAttribute("deckupload");
		
		if (challenger==null) {
			message="not logged in, Challenge Failed";
			callfail(request,response,message,rd);
			}
		else {
			String temp=request.getParameter("player");
			Integer challengee=Integer.parseInt(temp); 
			success=ChallengePlayerTS.execute(challenger,challengee,dbConn);
			if (deckuploaded==null) {
				message="No Deck, Challenge Failed";
				callfail(request,response,message,rd);
			}
			else if (challenger==challengee) {
				message="challenger and challengee are same, Challenge Failed";
				callfail(request,response,message,rd);
			}
			else {
			success=ChallengePlayerTS.execute(challenger,challengee,dbConn);
			}
		
		}
		
			
		if (CHALLENGE_SUCCESS==success) {
			message="Challenge Registered.";
			callsuccess(request, response, message,rd); 
		}
		else {
			message="invalid ID, Challenge Failed";
			callfail(request, response, message,rd); 
		}
		
}

	private void callfail(HttpServletRequest request, HttpServletResponse response, String message, RequestDispatcher rd) {
				
		message="Challenge Failed to Register.";
		request.setAttribute("message", message);
		rd=request.getRequestDispatcher("fail.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void callsuccess(HttpServletRequest request, HttpServletResponse response, String message, RequestDispatcher rd) {
		
		request.setAttribute("message", message);
		rd=request.getRequestDispatcher("success.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}




}
