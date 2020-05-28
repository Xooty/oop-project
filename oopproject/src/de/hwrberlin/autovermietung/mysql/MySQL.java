package de.hwrberlin.autovermietung.mysql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.hwrberlin.autovermietung.users.User;

public class MySQL {

	private String host;
	private String database;
	private String user;
	private String password;
	private String port;

	private File file;
	
	private User user_application;

	public MySQL(String path) { // Path für Desktop: System.getProperty("user.home") + /Desktop/Autovermietung/mysql.txt"

		this.file = new File(path);
		if (!this.file.exists()) {
			
			this.file.mkdir(); 
			this.file = new File(path + "mysql.txt");
			
			PrintWriter writer = null;
			try {
				this.file.createNewFile();
				
				writer = new PrintWriter(new FileOutputStream(this.file, true));

				String separator = System.getProperty("line.separator");
				
				writer.write("host: localhost" + separator);
				writer.write("database: autovermietung" + separator);
				writer.write("user: root" + separator);
				writer.write("password: 12345" + separator);
				writer.write("port: 3306");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (writer != null) {
					writer.flush();
					writer.close();
		        }
			}
		}
		
		this.file = new File(path + "mysql.txt");
		
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(this.file));
			
			String line = reader.readLine();
			
			while (line != null) {
				String[] args = line.split(": ");

				switch (args[0]) {
					case "host": this.host = args[1];
					case "database": this.database = args[1];
					case "user": this.user = args[1];
					case "password": this.password = args[1];
					case "port": this.port = args[1];
				}
				
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.initTables();
	}
	
	public User setUser(User user) {
		this.user_application = user;
		return user;
	}
	
	public User getUser() {
		return this.user_application;
	}

	public Connection openConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.user, this.password);
			return conn;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void queryUpdate(String query) {
		Connection conn = this.openConnection();
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(query);
			st.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Failed to send query update '" + query + "'");
		} finally {
			this.closeRessources(null, st, conn);
		}
	}

	public void closeRessources(ResultSet rs, PreparedStatement st, Connection connection) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) { }
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) { }
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) { }
		}
	}

	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void initTables() {

		Connection connection = this.openConnection();
		PreparedStatement st = null;

		try {
			st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS users (user_id INTEGER AUTO_INCREMENT PRIMARY KEY, user_name VARCHAR(50), user_password VARCHAR(50), permissions VARCHAR(50))");
			st.executeUpdate();
			st.close();
			st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS cars (car_id INTEGER AUTO_INCREMENT PRIMARY KEY, car_brand VARCHAR(50), car_model VARCHAR(50), horsepower INTEGER)");
			st.executeUpdate();
			st.close();
			st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS customers (customer_id INTEGER AUTO_INCREMENT PRIMARY KEY, first_name VARCHAR(50), surname VARCHAR(50), age INTEGER)");
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeRessources(null, st, connection);
		}
	}
}
