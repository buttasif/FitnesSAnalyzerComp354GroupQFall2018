package TransactionScripts;

import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import DTO_PlaceHolders.UserNameDTO;
import Data_GateWays.UserRDG;
import View_Helpers.UserHelper;

public class ListPlayersTS {

	public static List<UserNameDTO> execute(ThreadLocal<Connection> dbConn) {
		
		UserNameDTO names;
		ArrayList <UserNameDTO> listNameDTO=new ArrayList<>();
		List <UserRDG> listuRDG = UserRDG.findAll(dbConn);
		
		
		for (UserRDG u: listuRDG) {
			names=new UserNameDTO(u.getuName(), u.getId());
			listNameDTO.add(names);
		}
		return listNameDTO;
	}
}
