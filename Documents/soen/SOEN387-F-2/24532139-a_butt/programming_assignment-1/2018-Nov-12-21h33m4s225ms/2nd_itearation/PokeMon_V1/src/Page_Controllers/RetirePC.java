package Page_Controllers;

import java.io.IOException;
import java.sql.Connection;

import TransactionScripts.RetireTS;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Retire")

public class RetirePC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
public static ThreadLocal <Connection> dbConn;
	
	
    
	@Override    
    public void init() {
    	
    	dbConn=new ThreadLocal <Connection>();
    }


	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
	
		
		
	}

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
