package Page_Controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DTO_PlaceHolders.GamesDTO;
import TransactionScripts.ListChallengesTS;
import TransactionScripts.ListGamesTS;
import View_Helpers.ChallengeHelper;

@WebServlet(name = "ListGames", urlPatterns = { "/ListGames" })
public class ListGamesPC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
public static ThreadLocal <Connection> dbConn;
	
	RequestDispatcher rd;
    
	@Override    
    public void init() {
    	
    	dbConn=new ThreadLocal <Connection>();
    }
	
	private void process(HttpServletRequest request, HttpServletResponse response) {
		
		List <String> gameList=new ArrayList<>();
		gameList=ListGamesTS.execute(dbConn);
		
		request.setAttribute("games", gameList);
		rd=request.getRequestDispatcher("listGames.jsp");
		
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		process(request, response);
	}

	

}
