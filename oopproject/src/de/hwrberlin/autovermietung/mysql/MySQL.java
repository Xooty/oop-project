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

import javax.swing.JOptionPane;

import de.hwrberlin.autovermietung.Main;
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

		Connection connection = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/", this.user, this.password);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Das Programm konnte keine Verbindung zur Datenbank herstellen!");
			return;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			st = connection.prepareStatement("CREATE DATABASE IF NOT EXISTS " + this.database);
			st.executeUpdate();
			st.close();
			this.closeConnection(connection);
			
			connection = this.openConnection();
			st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS users (user_id INTEGER AUTO_INCREMENT PRIMARY KEY, user_name VARCHAR(50), user_password VARCHAR(50), permissions VARCHAR(50))");
			st.executeUpdate();
			st.close();
			
			st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS cars (car_id INTEGER AUTO_INCREMENT PRIMARY KEY, plate_number VARCHAR(50), class_id INTEGER, car_brand VARCHAR(50), car_model VARCHAR(50), fuel_type VARCHAR(20), power INTEGER, torque INTEGER, price DOUBLE, mileage INTEGER, topspeed INTEGER)");
			st.executeUpdate();
			st.close();
			
			st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS cars_class (class_id INTEGER AUTO_INCREMENT PRIMARY KEY, class_name VARCHAR(50), price DOUBLE)");
			st.executeUpdate();
			st.close();
			
			st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS customers (customer_id INTEGER AUTO_INCREMENT PRIMARY KEY, salutation VARCHAR(10), first_name VARCHAR(50), surname VARCHAR(50), birth_date VARCHAR(50), address VARCHAR(500), email VARCHAR(200), phone VARCHAR(50))");
			st.executeUpdate();
			st.close();
			
			st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS contracts (contract_id INTEGER AUTO_INCREMENT PRIMARY KEY, customer_id INTEGER, car_id INTEGER, employee_id INTEGER, contract_start LONG, contract_end LONG, mileage_start INTEGER, mileage_end INTEGER, car_returned BOOLEAN)");
			st.executeUpdate();
			st.close();
			
			st = connection.prepareStatement("SELECT * FROM users");
			rs = st.executeQuery();
			
			if (!rs.first()) {
				st.close();
				st = connection.prepareStatement("INSERT INTO users (user_name, user_password, permissions) VALUES ('admin', '123', 'ADMIN')");
				st.executeUpdate();
				st.close();
				
				st = connection.prepareStatement("INSERT INTO users (user_name, user_password, permissions) VALUES ('user', '123', 'USER')");
				st.executeUpdate();
				st.close();
			}
			rs.close();
			
			st = connection.prepareStatement("SELECT * FROM cars");
			rs = st.executeQuery();
			
			if (!rs.first()) {
				st.close();
				
				st = connection.prepareStatement("INSERT INTO cars (class_id, plate_number, car_brand, car_model, fuel_type, power, torque, price, mileage, topspeed) VALUES (1, 'B-GT 123', 'smart', 'fortwo', 'SUPER', 52, 160, 21386.9, 0, 155)");
				st.executeUpdate();
				st.close();
				
				st = connection.prepareStatement("INSERT INTO cars (class_id, plate_number, car_brand, car_model, fuel_type, power, torque, price, mileage, topspeed) VALUES (2, 'B-GT 124', 'Volkswagen', 'Golf', 'SUPER_E10', 66, 175, 19880.8, 0, 188)");
				st.executeUpdate();
				st.close();
				
				st = connection.prepareStatement("INSERT INTO cars (class_id, plate_number, car_brand, car_model, fuel_type, power, torque, price, mileage, topspeed) VALUES (3, 'B-GT 125', 'Mercedes-Benz', 'E200', 'SUPER', 143, 175, 19880.8, 0, 188)");
				st.executeUpdate();
				st.close();
				
				st = connection.prepareStatement("INSERT INTO cars (class_id, plate_number, car_brand, car_model, fuel_type, power, torque, price, mileage, topspeed) VALUES (4, 'B-GT 126', 'Volkswagen', 'Touareg', 'DIESEL', 170, 175, 19880.8, 0, 188)");
				st.executeUpdate();
				st.close();
			}
			rs.close();
			
			st = connection.prepareStatement("SELECT * FROM cars_class");
			rs = st.executeQuery();
			
			if (!rs.first()) {
				st.close();
				
				st = connection.prepareStatement("INSERT INTO cars_class (class_name, price) VALUES ('A', 35)"); // smart fortwo
				st.executeUpdate();
				st.close();
				
				st = connection.prepareStatement("INSERT INTO cars_class (class_name, price) VALUES ('E', 60)"); // Volkswagen Golf
				st.executeUpdate();
				st.close();
				
				st = connection.prepareStatement("INSERT INTO cars_class (class_name, price) VALUES ('I', 100)"); // Mercedes-Benz E200
				st.executeUpdate();
				st.close();
				
				st = connection.prepareStatement("INSERT INTO cars_class (class_name, price) VALUES ('J', 130)"); // Volkswagen Touareg
				st.executeUpdate();
				st.close();
			}
			
			st = connection.prepareStatement("SELECT * FROM customers");
			rs = st.executeQuery();
			
			if (!rs.first()) {
				st.close();
				
				st = connection.prepareStatement("INSERT INTO customers (salutation, first_name, surname, birth_date, address, email, phone) VALUES ('Herr', 'Max', 'Mustermann', '01.01.1970', 'Deutschland, Berlin, 13581, Max-Mustermann-Straße 10', 'max.mustermann@example.com', '017591231022')");
				st.executeUpdate();
				st.close();
				
				st = connection.prepareStatement("INSERT INTO customers (salutation, first_name, surname, birth_date, address, email, phone) VALUES ('Herr', 'Hans', 'Wurst', '23.05.1980', 'Deutschland, München, 80689, Hans-Wurst-Weg 2b', 'hans@wurst.de', '017310387234')");
				st.executeUpdate();
				st.close();
				
				st = connection.prepareStatement("INSERT INTO customers (salutation, first_name, surname, birth_date, address, email, phone) VALUES ('Herr', 'Fridolin', 'Müller', '25.03.1996', 'Deutschland, Berlin, 10559, Müller-Meier-Schmidt-Straße 61c', 'fridolin@muellerchen.org', '017591231022')");
				st.executeUpdate();
				st.close();
				
				st = connection.prepareStatement("INSERT INTO customers (salutation, first_name, surname, birth_date, address, email, phone) VALUES ('Herr', 'Jürgen', 'Meier', '28.07.1977', 'Österreich, Wien, 1050, Schönbrunner Str. 5', 'meier.guenther@irgendwas.at', '+43-663 14293418')");
				st.executeUpdate();
				st.close();
				
				st = connection.prepareStatement("INSERT INTO customers (salutation, first_name, surname, birth_date, address, email, phone) VALUES ('Herr', 'Robert', 'Schmitt', '05.12.1950', 'Deutschland, Köln, 50667, Max-Mustermann-Straße 10', '', '01751534628')");
				st.executeUpdate();
				st.close();
				
				st = connection.prepareStatement("INSERT INTO customers (salutation, first_name, surname, birth_date, address, email, phone) VALUES ('Herr', 'Uwe', 'Schmidt', '01.04.1947', 'Deutschland, Berlin, 10115, Uwe-Meme-Straße 57', 'ichbinderuwe@ichbinauchdabei.de', '')");
				st.executeUpdate();
				st.close();
			}

			st = connection.prepareStatement("SELECT * FROM contracts");
			rs = st.executeQuery();
			
			if (!rs.first()) {
				st.close();
				
				st = connection.prepareStatement("INSERT INTO contracts (customer_id, car_id, employee_id, contract_start, contract_end, mileage_start, mileage_end, car_returned) VALUES (1, 2, 2, 1593900000000, 1596146400000, 0, NULL, 0)");
				st.executeUpdate();
				st.close();
				
				st = connection.prepareStatement("INSERT INTO contracts (customer_id, car_id, employee_id, contract_start, contract_end, mileage_start, mileage_end, car_returned) VALUES (3, 3, 1, 1596232800000, 1596405600000, 0, NULL, 0)");
				st.executeUpdate();
				st.close();
				
				st = connection.prepareStatement("INSERT INTO contracts (customer_id, car_id, employee_id, contract_start, contract_end, mileage_start, mileage_end, car_returned) VALUES (2, 1, 1, 1604790000000, 1605394800000, 0, NULL, 0)");
				st.executeUpdate();
				st.close();
			}
			
			JOptionPane.showMessageDialog(null, "Das Programm hat sich mit der Datenbank verbunden!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeRessources(null, st, connection);
		}
	}
	
	public boolean createUser(String user_name, String password, String permission) {
		MySQL sql = Main.getMySQL();
    	Connection connection = sql.openConnection();
    	PreparedStatement st = null;
    	ResultSet rs = null;
    	
    	try {
    		st = connection.prepareStatement("SELECT * FROM users WHERE user_name = ?");
    		st.setString(1, user_name);
    		rs = st.executeQuery();
    		
    		if (rs.first()) {
    			return false;
    		} else {
    			st.close();
    			
    			st = connection.prepareStatement("INSERT INTO users (user_name, user_password, permissions) VALUES (?, ?, ?)");
    			st.setString(1, user_name);
    			st.setString(2, password);
    			st.setString(3, permission);
    			
    			st.executeUpdate();
    			
    			return true;
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		sql.closeRessources(rs, st, connection);
    	}
    	return false;
	}
	
	public User searchUser(String user_name) {
		MySQL sql = Main.getMySQL();
		Connection connection = sql.openConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("SELECT * FROM users WHERE user_name = ?");
			st.setString(1, user_name);
			rs = st.executeQuery();
			
			if (rs.first()) {
				return new User(rs.getInt("user_id"), connection);
			} else {
				JOptionPane.showMessageDialog(null, "Dieser Benutzername existiert nicht.");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.closeRessources(rs, st, connection);
		}
		return null;
	}
	
	public void deleteUser(User user) {
		MySQL sql = Main.getMySQL();
		Connection connection = sql.openConnection();
		PreparedStatement st = null;
		
		try {
			st = connection.prepareStatement("DELETE FROM users WHERE user_id = ? AND user_name = ?");
			st.setInt(1, user.getUserID());
			st.setString(2, user.getUserName());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.closeRessources(null, st, connection);
		}
	}
}
