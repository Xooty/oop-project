package de.hwrberlin.autovermietung.users;

import java.util.ArrayList;
import java.util.List;

public class UserManager {

	private List<User> users;
	
	public UserManager() {
		this.users = new ArrayList<User>();
		
		this.loadUsers();
	}
	
	public List<User> getUsers() {
		return this.users;
	}
	
	public User getUser(int id) {
		for (User user : this.users) {
			if (user.getID() == id) return user;
		}
		return null;
	}
	
	public void loadUsers() {
		User alex = new User(0, "alex", "passwort");
		alex.setPermission(Permission.ADMIN);
		this.users.add(alex);
		this.users.add(new User(1, "jassi", "passwort123"));
		this.users.add(new User(2, "marc", "passwort456"));
		this.users.add(new User(3, "niklas", "passwort789"));
	}
}
