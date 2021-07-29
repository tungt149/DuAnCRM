package cybersoft.java12.dto;

import cybersoft.java12.model.Role;

public class RoleDTO {
	private int id;
	private String name;
	private String description;
	
	public RoleDTO(Role role) {
		this.id = role.getId();
		this.name = role.getName();
		this.description = role.getDescription();
	}
	
	public RoleDTO(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;	
	}
	
	public RoleDTO() {
		
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}


