package Data_GateWays;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardGateway {
	
	private Integer id;
	private List<Integer> players = null;
	private List<PlayGateway> play = null;
	
	
	public BoardGateway(Integer id, List<Integer> players, List<PlayGateway> play) {
		super();
		this.id = id;
		this.players = players;
		this.play = play;
	}

	public Integer getId() {
	return id;
	}

	public void setId(Integer id) {
	this.id = id;
	}

	public List<Integer> getPlayers() {
	return players;
	}

	public void setPlayers(List<Integer> players) {
	this.players = players;
	}

	
	public List<PlayGateway> getPlay() {
	return play;
	}

	public void setPlay(List<PlayGateway> play) {
	this.play = play;
	}

	
	
	
	public static void insert(Integer boardid, Integer challenger1, Integer challengee1,ThreadLocal <Connection> dbConn ) {
		
		
		BoardGateway board=null;
		Connection con =null;
		
		try {
			
			con = connectDB();
			dbConn.set(con);
			insertHelper(boardid, challenger1, challengee1, con);
			
			
			
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
	

	public static BoardGateway findBoardByPlayers(Integer boardid, ThreadLocal <Connection> dbConn) {
		
		BoardGateway board=null;
		Connection con =null;
		ResultSet rs;
		
		try {
			con = connectDB();					//return connection object to connect to the DB
			dbConn.set(con);					
			rs = getResultSetByID(boardid,con);		//fetch results from the DB
			if (rs.next()) {
				board = createBoardRDG(rs);			
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
		return board;
		
		
		
		
	}

	private static BoardGateway createBoardRDG(ResultSet rs) throws SQLException {
		
		BoardGateway boardRDG=null;
		
		List <Integer> plyerList=new ArrayList<>();
		
		int id= rs.getInt("ID");
		int p1=rs.getInt("player1");
		int p2=rs.getInt("player2");
	
		boardRDG=new BoardGateway(id, plyerList,null);
		
	return boardRDG;
	
	}

	private static ResultSet getResultSetByID(Integer board, Connection con) throws SQLException {
		
		String query = "select * from board where ID=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, board);
		
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	private static void insertHelper(Integer boardid, Integer challenger1, Integer challengee1, Connection con) throws SQLException {
		
		String query = "INSERT INTO board (ID, player1, player2) VALUES (?,?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, boardid);
		ps.setInt(2, challenger1);
		ps.setInt(3, challengee1);
		
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
