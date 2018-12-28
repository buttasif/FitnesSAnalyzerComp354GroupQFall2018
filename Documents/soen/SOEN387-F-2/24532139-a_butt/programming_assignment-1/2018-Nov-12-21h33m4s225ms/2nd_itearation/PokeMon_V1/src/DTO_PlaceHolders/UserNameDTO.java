package DTO_PlaceHolders;

public class UserNameDTO {
	
	String name;
	Integer id;
	
	
	public UserNameDTO(String name, Integer id) {
		super();
		this.name = name;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	

}
