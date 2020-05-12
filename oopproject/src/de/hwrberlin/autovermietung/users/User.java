package de.hwrberlin.autovermietung.users;

public class User {

	private int id;
	
	private String user_name, user_password;
	
	private Permission permission;

	public User(int id, String user_name, String password) {
		this.id = id;
		
		this.user_name = user_name;
		
		this.user_password = password;
		
		this.permission = Permission.USER;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getUserName() {
		return this.user_name;
	}
	
	public String getUserPassword() {
		return this.user_password;
	}
	
	public Permission getPermission() {
		return this.permission;
	}
	
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
}
