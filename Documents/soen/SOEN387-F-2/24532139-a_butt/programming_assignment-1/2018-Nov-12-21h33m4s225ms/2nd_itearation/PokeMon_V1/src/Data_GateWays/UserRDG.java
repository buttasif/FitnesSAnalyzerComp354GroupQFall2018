package Data_GateWays;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRDG {

	private static final int DEFAULT_VER=1; 
	
	String uName;
	String password;
	Integer version;
	Integer id;
	ThreadLocal <Connection> conn;
	
	
	public UserRDG(String uName, String password, int version, int id) {
		super();
		this.uName = uName;
		this.password = password;
		version = DEFAULT_VER;
		this.id = id;
	}
	
	public UserRDG(String uName, String password, ThreadLocal <Connection> conn) {
		this.uName = uName;
		this.password = password;
		this.conn = conn;
		version=DEFAULT_VER;;
	}
	
	
	public UserRDG(Integer id2, String name, String pass) {
		this.uName=name;
		this.password=pass;
		this.id=id2;
	}
	
	public UserRDG(String name, String pass) {
		this(null, name, pass);
	}
	
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

// DB Interaction methods
	
	public static UserRDG findByName(String uName, ThreadLocal <Connection> dbConn){
		
		UserRDG user=null;
		Connection con =null;
		ResultSet rs;
		
		try {
			con = connectDB();					//return connection object to connect to the DB
			dbConn.set(con);					
			rs = getResultSet(con, uName);		//fetch results from the DB
			if (rs.next()) {
				user = createUserRDG(rs);			//create an userRDG from the data obtained in result sets
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			dbConn.remove();
			try {
				con.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	public Integer insert(ThreadLocal <Connection> dbConn) {
		
		Connection con =null;
		Integer rowsAffected=null;
		
		try {
			
			con = connectDB();
			dbConn.set(con);
			rowsAffected = insertHelper(this.getuName(), this.getPassword(), con);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			dbConn.remove();
			try {
				con.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return rowsAffected;
	}
	
	
//Helper Methods	

	private static UserRDG createUserRDG(ResultSet rs) throws SQLException {
			
			UserRDG user=null;
			int id= rs.getInt("ID");
			String name=rs.getString("UName");
			String pass=rs.getString("Password");
			user = new UserRDG(id,name,pass);
			
		return user;
	}

	private static ResultSet getResultSet(Connection con, String uName) throws SQLException {
		String query = "select * from Users where UName=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, uName);
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	private static Connection connectDB()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection con;
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

		con = DriverManager.getConnection("jdbc:mysql://localhost/asif_b?"
				+"user=asif_b&password=rialvani&characterEncoding=UTF-8&useUnicode=true&useJDBCCompliantTimezoneShift="
				+ "true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true");
		return con;
	}
	

	private static int insertHelper(String user, String pass, Connection con) throws SQLException {
		int rowsAffected;
		String query = "INSERT INTO Users (UName, Password, Version) VALUES (?,?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, user);
		ps.setString(2, pass);
		ps.setInt(3, DEFAULT_VER);
		rowsAffected=ps.executeUpdate();
		return rowsAffected;
	}


	private static ResultSet getResultSetByID(int id2, Connection con) throws SQLException {
		String query = "select * from Users where ID=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, id2);
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	public static UserRDG findByID(Integer id2, ThreadLocal<Connection> dbConn) {
		UserRDG user=null;
		Connection con =null;
		ResultSet rs;
		
		try {
			con = connectDB();					//return connection object to connect to the DB
			dbConn.set(con);					
			rs = getResultSetByID(id2, con);		//fetch results from the DB
			if (rs.next()) {
				user = createUserRDG(rs);			//create an userRDG from the data obtained in result sets
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			dbConn.remove();
			try {
				con.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;

	}

	
	public static List<UserRDG> findAll(ThreadLocal<Connection> dbConn) {
		
		List <UserRDG> userRDGList = new ArrayList<>(); 
		Connection con =null;
		ResultSet rs;
		UserRDG userRDG;
		
		try {
			con = connectDB();					//return connection object to connect to the DB
			dbConn.set(con);					
			rs = getAllResultSet(con);			//fetch results from the DB
			
			while (rs.next()) {
				
				userRDG=createUserRDG(rs);
				userRDGList.add(userRDG);		//create a list of users from result sets
			}
					
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			dbConn.remove();
			try {
				con.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return userRDGList;
	}

	private static ResultSet getAllResultSet(Connection con) throws SQLException {
		
		String query = "select * from Users;";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
}
