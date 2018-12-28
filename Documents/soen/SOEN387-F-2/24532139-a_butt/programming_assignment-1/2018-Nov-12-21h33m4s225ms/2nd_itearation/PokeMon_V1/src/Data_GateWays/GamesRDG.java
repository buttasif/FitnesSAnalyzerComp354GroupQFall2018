package Data_GateWays;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GamesRDG {
	
	int gameId;
	int challenger;
	int challengee;
	
	public GamesRDG(int gameId, int challenger, int challengee) {
		super();
		this.gameId = gameId;
		this.challenger = challenger;
		this.challengee = challengee;
	}
	
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public int getChallenger() {
		return challenger;
	}
	public void setChallenger(int challenger) {
		this.challenger = challenger;
	}
	public int getChallengee() {
		return challengee;
	}
	public void setChallengee(int challengee) {
		this.challengee = challengee;
	}
	
	
	
public static List <GamesRDG> findAllGames(ThreadLocal <Connection> dbConn) {
		
		List <GamesRDG> gamesList = new ArrayList<>(); 
		Connection con =null;
		ResultSet rs;
		GamesRDG gameRDG=null;
		
		try {
			con = connectDB();					//return connection object to connect to the DB
			dbConn.set(con);					
			rs = getAllResultSet(con);				//fetch all challenges from the DB
						
			while(rs.next()) {
			
			gameRDG=createGamesRDG(rs,gameRDG);
			gamesList.add(gameRDG);		//create a list
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
		
		return gamesList;
	}
	
public static void insert(int challenger1, int challengee1, ThreadLocal <Connection> dbConn) {
	
	Connection con =null;
		
	try {
		
		con = connectDB();
		dbConn.set(con);
		insertHelper(challenger1, challengee1, con);
		
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
	
}	

	private static void insertHelper(int challenger2, int challengee2, Connection con) throws SQLException {
	
		String query = "INSERT INTO Games (challenger, challengee) VALUES (?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, challenger2);
		ps.setInt(2, challengee2);
		
		ps.executeUpdate();

	
}

	
	private static ResultSet getAllResultSet(Connection con) throws SQLException {
		String query = "select * from Games;";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	
}

	private static GamesRDG createGamesRDG(ResultSet rs, GamesRDG gameRDG) throws SQLException {
			
		int id= rs.getInt("ID");
		int challenger=rs.getInt("Challenger");
		int challengee=rs.getInt("Challengee");
		gameRDG = new GamesRDG(id, challenger, challengee);
		
		return gameRDG;
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

}
