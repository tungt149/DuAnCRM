package cybersoft.java12.dto;

import cybersoft.java12.model.User;

public class UserDTO {
	/* Data Transfer Object */
	private int id;
	private String email;
	private String password;
	private String name;
	private String address;
	private String phone;
	private int roleId;


	public UserDTO(int id, String email, String password, String name, String address, String phone, int roleId) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.roleId = roleId;
	}
	
	public UserDTO(User user) {
		this.setId(user.getId());
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.name = user.getName();
		this.address = user.getAddress();
		this.phone = user.getPhone();
		this.roleId = user.getRole().getId();
	}

	public UserDTO() {

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
