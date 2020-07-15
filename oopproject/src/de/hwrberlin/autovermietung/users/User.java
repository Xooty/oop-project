package de.hwrberlin.autovermietung.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.mysql.MySQL;

public class User {

	private int user_id;
	
	private String user_name, user_password;
	
	private Permission permission;
	
	private long last_login;

	public User(int user_id) {
		this.user_id = user_id;
		
		MySQL mysql = Main.getMySQL();
		Connection connection = mysql.openConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("SELECT * FROM users WHERE user_id = ?");
			st.setInt(1, this.user_id);
			
			rs = st.executeQuery();

			rs.first();
			
			this.user_name = rs.getString("user_name");
			this.user_password = rs.getString("user_password");
			this.permission = Permission.valueOf(rs.getString("permissions").toUpperCase());
//			this.last_login = rs.getInt("last_login");
		} catch (NullPointerException | SQLException e) {
			System.err.println("Der User konnte nicht geladen werden.");
			e.printStackTrace();
			mysql.closeRessources(rs, st, connection);
			return;
		} finally {
			mysql.closeRessources(rs, st, connection);
		}
		
//		Main.getMySQL().queryUpdate("UPDATE users SET last_login = " + System.currentTimeMillis() + " WHERE user_id = " + this.user_id);
	}
	
	public int getUserID() {
		return this.user_id;
	}
	
	public String getUserName() {
		return this.user_name;
	}
	
	public void setUserName(String user_name) {
		this.user_name = user_name;
		
		MySQL mysql = Main.getMySQL();
		Connection connection = mysql.openConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("UPDATE users SET user_name = ? WHERE user_id = ?");
			st.setString(1, this.user_password);
			st.setInt(2, this.user_id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysql.closeRessources(rs, st, connection);
		}
	}
	
	public String getUserPassword() {
		return this.user_password;
	}
	
	public void setUserPassword(String password) {
		this.user_password = password;
		
		MySQL mysql = Main.getMySQL();
		Connection connection = mysql.openConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("UPDATE users SET user_password = ? WHERE user_id = ? AND user_name = ?");
			st.setString(1, this.user_password);
			st.setInt(2, this.user_id);
			st.setString(3, this.user_name);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysql.closeRessources(rs, st, connection);
		}
	}
	
	public boolean hasPermission(Permission permission) {
		if (this.permission.ordinal() <= permission.ordinal()) {
			return true;
		}
		return false;
	}
	
	public Permission getPermission() {
		return this.permission;
	}
	
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	
	public long getLastLogin() {
		return this.last_login;
	}
	
	public void updateUser(String user_name, String password, Permission permission) {
		this.user_name = user_name;
		this.user_password = password;
		this.permission = permission;
		
		MySQL mysql = Main.getMySQL();
		Connection connection = mysql.openConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("UPDATE users SET user_name = ?, user_password = ?, permissions = ? WHERE user_id = ?");
			st.setString(1, this.user_name);
			st.setString(2, this.user_password);
			st.setString(3, this.permission.toString().toUpperCase());
			st.setInt(4, this.user_id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysql.closeRessources(rs, st, connection);
		}
	}
}
