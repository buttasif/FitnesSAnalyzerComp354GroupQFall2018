package Page_Controllers;

import java.io.IOException;
import java.sql.Connection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import TransactionScripts.ListChallengesTS;
import View_Helpers.ChallengeHelper;
import java.util.List;
import java.util.ArrayList;


@WebServlet("/ListChallenges")
public class ListChallengesPC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static ThreadLocal <Connection> dbConn;
	
	
    
	@Override    
    public void init() {
    	
    	dbConn=new ThreadLocal <Connection>();
    }
  
	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		
		List <ChallengeHelper> listChallenges = new ArrayList<>();
				
		HttpSession session = request.getSession();
		Integer id = (Integer) session.getAttribute("id");
		
		if (id==null) {														//not logged in, cannot view any players
			request.setAttribute("message", "Some thing bad happened. You are  not logged in");
			RequestDispatcher rd=request.getRequestDispatcher("fail.jsp");
			try {
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		else {
			listChallenges=ListChallengesTS.execute(dbConn);
			
			JsonArray jsonChallenges = new JsonArray();
			
			for(ChallengeHelper c : listChallenges) {
				JsonObject jChallenge = new JsonObject();
				jChallenge.addProperty("id", c.getChallengeId());
				jChallenge.addProperty("challenger", c.getChallenger());
				jChallenge.addProperty("challengee", c.getChallengee());
				jChallenge.addProperty("status", c.getChalengeStaus());
				jsonChallenges.add(jChallenge);
			}
			request.setAttribute("challengesList", jsonChallenges);
			RequestDispatcher rd=request.getRequestDispatcher("listChallenges.jsp");
			
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
