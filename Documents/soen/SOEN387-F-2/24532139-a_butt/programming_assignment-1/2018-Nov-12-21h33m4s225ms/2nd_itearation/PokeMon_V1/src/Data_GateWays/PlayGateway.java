package Data_GateWays;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO_PlaceHolders.PlayDTO;

public class PlayGateway {
	
	
	static final String DEFAULT_STATUS="playing";
	static final int DEFAULT_HANDSIZE=0;
	static final int DEFAULT_DECKSIZE=40;
	static final int DEFAULT_DISCARDSIZE=0;
	
	
	int id;
	int handSize;
	int deckSize;
	int discardSize;
	int benchId;
	String status;
	
	
	public PlayGateway(int id, int handSize, int deckSize, int discardSize, int benchId, String status) {
		super();
		this.id = id;
		this.handSize = handSize;
		this.deckSize = deckSize;
		this.discardSize = discardSize;
		this.benchId = benchId;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHandSize() {
		return handSize;
	}

	public void setHandSize(int handSize) {
		this.handSize = handSize;
	}

	public int getDeckSize() {
		return deckSize;
	}

	public void setDeckSize(int deckSize) {
		this.deckSize = deckSize;
	}

	public int getDiscardSize() {
		return discardSize;
	}

	public void setDiscardSize(int discardSize) {
		this.discardSize = discardSize;
	}

	public int getBenchId() {
		return benchId;
	}

	public void setBenchId(int benchId) {
		this.benchId = benchId;
	}
	
	
	public static PlayGateway findhByID(Integer id2, ThreadLocal<Connection> dbConn) {
		PlayGateway playRDG=null;
		Connection con =null;
		ResultSet rs;
		
		try {
			con = connectDB();					//return connection object to connect to the DB
			dbConn.set(con);					
			rs = getResultSetByID(id2, con);		//fetch results from the DB
			if (rs.next()) {
				playRDG = createPlayRDG(rs);			
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
		return playRDG;

	}
	
	private static PlayGateway createPlayRDG(ResultSet rs) throws SQLException {
		
		PlayGateway playRDG =null;

		int id= rs.getInt("ID");
		String stat=rs.getString("status");
		int hsize=rs.getInt("handsize"); 
		int dsize=rs.getInt("decksize");
		int discardize=rs.getInt("discardize");
		int benchid=rs.getInt("discardize");
		
	return playRDG;
	
	}
	
public static void insert(PlayDTO players, ThreadLocal <Connection> dbConn) {
		
		Connection con =null;
		
		
		try {
			
			con = connectDB();
			dbConn.set(con);
			
			insertHelper(players.getBoardId(), players.getPlayerID(), DEFAULT_STATUS, DEFAULT_HANDSIZE, DEFAULT_DECKSIZE, DEFAULT_DISCARDSIZE, players.getBenchId(),con);		
			
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
	


	private static void insertHelper(int id2, int playerid2, String defaultStatus, int defaultHandsize, int defaultDecksize,
		int defaultDiscardsize, int benchId2, Connection con) throws SQLException {
	
		String query = "INSERT INTO play (ID, playid, status, handsize, decksize, discardsize,benchid) VALUES (?,?,?,?,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, id2);
		ps.setInt(2, playerid2);
		ps.setString(3, defaultStatus);

		ps.setInt(4, defaultHandsize);
		ps.setInt(5, defaultDecksize);
		ps.setInt(6, defaultDiscardsize);
		ps.setInt(7, benchId2);
		ps.executeUpdate();
}

	
	public static void updateChallenge(Integer board, Integer player, ThreadLocal<Connection> dbConn) {
		
		Connection con =null;
		
		
		try {
			
			con = connectDB();
			dbConn.set(con);
			updatePlayHelper(board, player, con);
			
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
	
	private static ResultSet getResultSetByID(Integer id2, Connection con) throws SQLException {
		
		String query = "select * from play where ID=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, id2);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	private static void updatePlayHelper(Integer board, Integer player, Connection con) throws SQLException {
		String query = "UPDATE play SET Status = ? WHERE playid=? AND ID=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, player);
		ps.setInt(2, board);
		ps.executeUpdate();
		
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
