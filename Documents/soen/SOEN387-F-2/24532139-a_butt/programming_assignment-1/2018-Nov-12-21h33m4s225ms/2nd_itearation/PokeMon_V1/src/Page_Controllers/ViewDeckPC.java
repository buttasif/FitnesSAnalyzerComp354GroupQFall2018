package Page_Controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import DTO_PlaceHolders.Cards;

import TransactionScripts.ViewDeckTS;

@WebServlet("/ViewDeck")

public class ViewDeckPC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	

public static ThreadLocal <Connection> dbConn;
    
	@Override    
    public void init() {
    	
    	dbConn=new ThreadLocal <Connection>();
    }
 
	static final int DECK_SIZE=40;
	static final int DECK_UPLOADED=1;
	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		Integer playerId = (Integer) session.getAttribute("id");
		Cards cardDTO=null;
		RequestDispatcher rd=null;
		Integer viewdeckcount;
		String message=null;
		viewdeckcount=(Integer) session.getAttribute("viewdeckcount");
		List<Cards> cardList=null;
		
		
		
		cardList=ViewDeckTS.execute(playerId,viewdeckcount,dbConn);
		
		
		if (!(cardList.isEmpty())) {
			message="success";
			calldecksucces(request, message,cardList,rd,response,session);
		}
		else {
			calldeckfail(request, message,rd,response);
			
		}
		
	}



	private void calldeckfail(HttpServletRequest request, String message, RequestDispatcher rd,
			HttpServletResponse response) {
		request.setAttribute("message", message);
		rd=request.getRequestDispatcher("fail.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}



	private void calldecksucces(HttpServletRequest request, String message, List<Cards> cardList, RequestDispatcher rd,
			HttpServletResponse response,HttpSession session ) {
		rd=request.getRequestDispatcher("deckSuccess.jsp");
		request.setAttribute("message", message);
		request.setAttribute("card", cardList);
		session.setAttribute("deckupload", DECK_UPLOADED);
		try {
			rd.forward(request, response);
		} catch (Exception e) {
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
