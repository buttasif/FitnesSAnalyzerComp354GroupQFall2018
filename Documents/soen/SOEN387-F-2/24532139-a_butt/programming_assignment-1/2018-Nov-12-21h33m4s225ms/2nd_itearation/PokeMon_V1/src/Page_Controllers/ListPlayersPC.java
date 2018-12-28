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

import DTO_PlaceHolders.UserNameDTO;
import TransactionScripts.ListPlayersTS;
import View_Helpers.UserHelper;

/**
 * Servlet implementation class ListPlayersPC
 */
@WebServlet("/ListPlayers")
public class ListPlayersPC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
public static ThreadLocal <Connection> dbConn;
    
	@Override    
    public void init() {
    	
    	dbConn=new ThreadLocal <Connection>();
    }
 
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		Integer id = (Integer) session.getAttribute("id");
		List <UserNameDTO> list;	
		if (id==null) {														//not logged in, cannot view any players
			request.setAttribute("message", "Some thing bad happened.");
			RequestDispatcher rd=request.getRequestDispatcher("fail.jsp");
			try {
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		else{
			list = ListPlayersTS.execute(dbConn);
			if (list!=null) {
				request.setAttribute("players", list);
				RequestDispatcher rd=request.getRequestDispatcher("players.jsp");
				try {
					rd.forward(request, response);
				} catch (Exception e) {
						e.printStackTrace();
				} 
			}
			else {
				request.setAttribute("message", "Some thing bad happened.");
				RequestDispatcher rd=request.getRequestDispatcher("fail.jsp");
				try {
					rd.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
		
	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
