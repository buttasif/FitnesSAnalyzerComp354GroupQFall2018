package Data_GateWays;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class DeckGateway {
	
	Integer id;
	Integer playerId;
	String type;
	String name;
	
	static final int First=1;	
	
	public DeckGateway(Integer playerId, Integer id, String type, String name) {
		super();
		this.id = id;
		this.playerId = playerId;
		this.type = type;
		this.name = name;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getPlayerId() {
		return playerId;
	}


	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	public static Integer insertDeck(int playerId, List <String> types, List <String> names,ThreadLocal <Connection> dbConn) {
		
		List<DeckGateway> listDeckGateway=new ArrayList<>();
		DeckGateway dGateway=null;
		Connection con =null;
		int rowsAffected=0;
		
		try {
			
			con = connectDB();
			dbConn.set(con);
			for (int i=0; i<types.size();i++) {
				insertHelper(playerId, types.get(i),names.get(i),con);
				rowsAffected++;
			}
			
		}catch (Exception e) {
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

public static DeckGateway findDeckById(int playerId, int order, ThreadLocal <Connection> dbConn){
		
		DeckGateway deckRDG=null;
		Connection con =null;
		ResultSet rs;

		try {
			con = connectDB();									//return connection object to connect to the DB
			dbConn.set(con);					
			if (order!=First) {
				rs = getResultSetById(con, playerId, order);			//fetch results from the DB
			}
			else {
				rs=getFirstResultSetById(con, playerId);
			}
			while (rs.next()) {
				
				deckRDG=createDeckRDG(rs);
				
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
		return deckRDG;
	}	
	




public static List <DeckGateway> findFullDeckById(int playerId, ThreadLocal <Connection> dbConn){
	
	DeckGateway deckRDG=null;
	Connection con =null;
	ResultSet rs;
	List <DeckGateway> deckRDGList = new ArrayList<>();		

	try {
		con = connectDB();					//return connection object to connect to the DB
		dbConn.set(con);					
		rs = getAllResultSetById(con, playerId);			//fetch results from the DB
		
		while (rs.next()) {
			
			deckRDG=createDeckRDG(rs);
			deckRDGList.add(deckRDG);			//create a list of users from result sets
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
	return deckRDGList;
}

private static DeckGateway createDeckRDG(ResultSet rs) throws SQLException {

	DeckGateway deckRDG=null;	

	int playerId= rs.getInt("PlayerId");
	String type=rs.getString("Type");
	String name=rs.getString("Name");
	int order= rs.getInt("ID");

	deckRDG = new DeckGateway(playerId, order, type, name);

	return deckRDG;
	
}


private static ResultSet getResultSetById(Connection con, int playerId2, int order) throws SQLException {
	String query = "select * from deck where PlayerId=? AND ID=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, playerId2);
		ps.setInt(2, order);
		ResultSet rs = ps.executeQuery();
		return rs;
}

private static ResultSet getAllResultSetById(Connection con, int playerId2) throws SQLException {
	String query = "select * from deck where PlayerId=? ORDER BY ID;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, playerId2);
		ResultSet rs = ps.executeQuery();
		return rs;
}


private static void insertHelper(int playerId, String type, String name, Connection con) throws SQLException {
	
	String query = "INSERT INTO deck (PlayerID, Type, Name) VALUES (?,?,?)";
	PreparedStatement ps = con.prepareStatement(query);
	ps.setInt(1, playerId);
	ps.setString(2, type);
	ps.setString(3, name);
	ps.executeUpdate();
	
	}

private static ResultSet getFirstResultSetById(Connection con, int playerId2) throws SQLException {
	String query = "select * from deck where PlayerId=? ORDER BY ID ASC LIMIT 1;";
	PreparedStatement ps = con.prepareStatement(query);
	ps.setInt(1, playerId2);
	ResultSet rs = ps.executeQuery();
	return rs;
}

private static Connection connectDB() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
	Connection con;
	Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

	con = DriverManager.getConnection("jdbc:mysql://localhost/asif_b?"
			+"user=asif_b&password=rialvani&characterEncoding=UTF-8&useUnicode=true&useJDBCCompliantTimezoneShift="
			+ "true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true");
	return con;
}

}
