package View_Helpers;

import View_Helpers.UserHelper;
public class UserHelper {

	String uName;
	
	Integer version;
	Integer id;
	
	public UserHelper(String uName, Integer version, Integer id) {
		super();
		this.uName = uName;
		this.version = version;
		this.id = id;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}

	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
