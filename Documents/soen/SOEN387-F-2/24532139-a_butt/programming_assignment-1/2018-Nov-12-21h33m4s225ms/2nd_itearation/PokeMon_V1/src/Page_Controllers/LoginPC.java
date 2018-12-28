package Page_Controllers;

import View_Helpers.UserHelper;
import java.io.IOException;
import java.sql.Connection;

import TransactionScripts.LoginTS;
import Utility.Utility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Data_GateWays.UserRDG;


/**
 * Servlet implementation class LoginPC
 */
@WebServlet("/Login")
public class LoginPC extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static ThreadLocal <Connection> dbConn;
       
	@Override    
    public void init() {
    	
    	dbConn=new ThreadLocal <Connection>();
    }


	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		
		String user=request.getParameter("user");
		String pass=request.getParameter("pass");
		HttpSession session=null;
		
		UserHelper userHelper=LoginTS.execute(user, pass, dbConn);

		//if no user is found (userHelper is null) or password is bad then send to the failed view
		if (userHelper==null){
			request.setAttribute("message", "Some thing bad happened. User " +user+" does not exist or password incorrect.");
			RequestDispatcher rd=request.getRequestDispatcher("fail.jsp");
			try {
				rd.forward(request, response);
			} catch (Exception e) {
				    e.printStackTrace();
			}
		}
		else {
			
			int uID = userHelper.getId();		//get the user ID from userHelper
			session = request.getSession();
			session.setAttribute("id", uID);
			session.setAttribute("viewdeckcount", 1);
			request.setAttribute("message", "Login from user: "+userHelper.getuName()+" was successful.");
			RequestDispatcher rd=request.getRequestDispatcher("success.jsp");
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
