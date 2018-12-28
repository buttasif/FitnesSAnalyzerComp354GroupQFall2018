package Data_GateWays;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChallengeRDG {

	
	Integer challenger;
	Integer challengee;
	Integer challengeId;
	Integer chalengeStaus;
	
	static final int STATUS_NEW_CHALLENGE=0;
	static final int STATUS_CHALLENGE_ACCEPTED=3;
	
	public ChallengeRDG(Integer challenger, Integer challengee, Integer challengeId, Integer chalengeStaus) {
		super();
		this.challenger = challenger;
		this.challengee = challengee;
		this.challengeId = challengeId;
		this.chalengeStaus = chalengeStaus;
	}


	public Integer getChallenger() {
		return challenger;
	}


	public void setChallenger(Integer challenger) {
		this.challenger = challenger;
	}


	public Integer getChallengee() {
		return challengee;
	}


	public void setChallengee(Integer challengee) {
		this.challengee = challengee;
	}


	public Integer getChallengeId() {
		return challengeId;
	}


	public void setChallengeId(Integer challengeId) {
		this.challengeId = challengeId;
	}


	public Integer getChalengeStaus() {
		return chalengeStaus;
	}


	public void setChalengeStaus(Integer chalengeStaus) {
		this.chalengeStaus = chalengeStaus;
	}

	
		
	

	public static List <ChallengeRDG> findAllChallenges(ThreadLocal <Connection> dbConn) {
		
		List <ChallengeRDG> challengesList = new ArrayList<>(); 
		Connection con =null;
		ResultSet rs;
		ChallengeRDG challengeRDG;
		
		try {
			con = connectDB();					//return connection object to connect to the DB
			dbConn.set(con);					
			rs = getAllResultSet(con);			//fetch all challenges from the DB
			
			while (rs.next()) {
				
				challengeRDG=createChallengeRDG(rs);
				challengesList.add(challengeRDG);		//create a list of challenges from result sets
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
		
		return challengesList;
	}


	
	

	private static ChallengeRDG createChallengeRDG(ResultSet rs) throws SQLException {
		ChallengeRDG challenge=null;
		
		int id= rs.getInt("ID");
		int challenger=rs.getInt("challenger");
		int challengee=rs.getInt("challengee");
		int status=rs.getInt("status");
		challenge = new ChallengeRDG(challenger, challengee, id, status);
		return challenge;
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

	private static ResultSet getAllResultSet(Connection con) throws SQLException  {
		
		String query = "select * from Challenges;";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
public static Integer insert(int challenger, int challengee, ThreadLocal <Connection> dbConn) {
		
		Connection con =null;
		Integer rowsAffected=null;
		
		try {
			
			con = connectDB();
			dbConn.set(con);
			rowsAffected = insertHelper(challenger, challengee, con);
			
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


private static Integer insertHelper(int challenger, int challengee, Connection con) throws SQLException {
	int rowsAffected;
	String query = "INSERT INTO Challenges (challenger, challengee, status) VALUES (?,?,?)";
	PreparedStatement ps = con.prepareStatement(query);
	ps.setInt(1, challenger);
	ps.setInt(2, challengee);
	ps.setInt(3, STATUS_NEW_CHALLENGE);
	rowsAffected=ps.executeUpdate();
	return rowsAffected;
}


public static ChallengeRDG findById(Integer challenge, ThreadLocal<Connection> dbConn) {
	
	ChallengeRDG cRDG=null;
	Connection con =null;
	ResultSet rs;
	
	try {
		con = connectDB();					//return connection object to connect to the DB
		dbConn.set(con);					
		rs = getResultSetByID(challenge, con);				//fetch results from the DB
		if (rs.next()) {
			cRDG = createChallengeRDG(rs);			
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
	return cRDG;
	
}


private static ResultSet getResultSetByID(Integer challenge, Connection con) throws SQLException {
	String query = "select * from Challenges where ID=?;";
	PreparedStatement ps = con.prepareStatement(query);
	ps.setInt(1, challenge);
	ResultSet rs = ps.executeQuery();
	return rs;
}


public static void updateChallenge(Integer challenge, ThreadLocal<Connection> dbConn) {
	
	Connection con =null;
	
	
	try {
		
		con = connectDB();
		dbConn.set(con);
		updateChallengeHelper(challenge, con);
		
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


private static void updateChallengeHelper(Integer challenge, Connection con) throws SQLException {
	String query = "UPDATE Challenges SET Status = ? WHERE ID=?;";
	PreparedStatement ps = con.prepareStatement(query);
	ps.setInt(1, STATUS_CHALLENGE_ACCEPTED);
	ps.setInt(2, challenge);
	ps.executeUpdate();
	
}


public static void updateRefuseChallenge(Integer challengeId, int status, ThreadLocal<Connection> dbConn) {
	
Connection con =null;
	
	
	try {
		
		con = connectDB();
		dbConn.set(con);
		updateRefuseChallengeHelper(challengeId, status,con);
		
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


private static void updateRefuseChallengeHelper(Integer challengeId, int status, Connection con) throws SQLException {
	String query = "UPDATE Challenges SET Status = ? WHERE ID=?;";
	PreparedStatement ps = con.prepareStatement(query);
	ps.setInt(1, status);
	ps.setInt(2, challengeId);
	ps.executeUpdate();
	
}

}


