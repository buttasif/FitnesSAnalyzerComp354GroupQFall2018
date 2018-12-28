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

import Data_GateWays.UserRDG;
import TransactionScripts.LogoutTS;
import Utility.Utility;
import Utility.Validate;
import View_Helpers.UserHelper;


@WebServlet("/Logout")
public class LogutPC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static ThreadLocal <Connection> dbConn;
    
	@Override    
    public void init() {
    	
    	dbConn=new ThreadLocal <Connection>();
    }
	
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		Integer id = (Integer) session.getAttribute("id");
		
		//checking session set					
		if (id==null) {
			
			request.setAttribute("message", "Session not set.");
			RequestDispatcher rd = request.getRequestDispatcher("fail.jsp");
			try {
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else{
			UserHelper userHelper = LogoutTS.execute(id, dbConn); 
	
			if (userHelper!=null) {
				session.invalidate();
				request.setAttribute("message", "Request to logout from user "+userHelper.getuName()+" was successful.");
				RequestDispatcher rd = request.getRequestDispatcher("success.jsp"); 
				try {
					rd.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else{
				request.setAttribute("message", "Some thing has gone  wrong with the logout request.");
				RequestDispatcher rd = request.getRequestDispatcher("fail.jsp");
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
