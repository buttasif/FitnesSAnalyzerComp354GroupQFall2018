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

import TransactionScripts.UploadDeckTS;

/**
 * Servlet implementation class UploadDeckPC
 */
@WebServlet("/UploadDeck")
public class UploadDeckPC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
public static ThreadLocal <Connection> dbConn;
static final int Deck_Size=40;
static final Integer DECK_UPLOADED=1; 		//in success set attribute sessiondeckupload to 1
    
	@Override    
    public void init() {
    	
    	dbConn=new ThreadLocal <Connection>();
    }
	
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
    	
    	Integer rows=null;
    	String deck=request.getParameter("deck");
		HttpSession session = request.getSession();
		Integer playerId = (Integer) session.getAttribute("id");
		RequestDispatcher rd=null;
		Integer DeckId =null;
		
	
		if (playerId==null) {														//not logged in
			request.setAttribute("message", "Some thing bad happened.");
			rd=request.getRequestDispatcher("fail.jsp");
			try {
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		else{
			rows=UploadDeckTS.execute(playerId,deck,dbConn);
			if (rows==null) {													//Deck Size not good
				request.setAttribute("message", "Deck Size Inaccurate");
				rd=request.getRequestDispatcher("fail.jsp");
				try {
					rd.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
			else {
				request.setAttribute("message", "Deck Upload Successful");		//Deck upload successful
				rd=request.getRequestDispatcher("success.jsp");
				session.setAttribute("deckupload", DECK_UPLOADED);
				try {
					rd.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				} 
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
