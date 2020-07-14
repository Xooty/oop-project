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
			st = connection.prepareStatement("CREATE DATABASE IF NOT EXISTS autovermietung");
			st.executeUpdate();
			st.close();
			this.closeConnection(connection);
			
			connection = this.openConnection();
			st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS users (user_id INTEGER AUTO_INCREMENT PRIMARY KEY, user_name VARCHAR(50), user_password VARCHAR(50), permissions VARCHAR(50), last_login TIMESTAMP)");
			st.executeUpdate();
			st.close();
			
			st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS cars (car_id INTEGER AUTO_INCREMENT PRIMARY KEY, class_id INTEGER, car_brand VARCHAR(50), car_model VARCHAR(50), power INTEGER, torque INTEGER, price DOUBLE, mileage INTEGER, topspeed INTEGER)");
			st.executeUpdate();
			st.close();
			
			st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS cars_class (class_id INTEGER AUTO_INCREMENT PRIMARY KEY, class_name VARCHAR(10), price DOUBLE)");
			st.executeUpdate();
			st.close();
			
			st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS customers (customer_id INTEGER AUTO_INCREMENT PRIMARY KEY, first_name VARCHAR(50), surname VARCHAR(50), birth_date VARCHAR(20), country VARCHAR(50), city VARCHAR(50), zip_code VARCHAR(20), street VARCHAR(200), email VARCHAR(200), phone VARCHAR(50))");
			st.executeUpdate();
			st.close();
			
			st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS contracts (contract_id INTEGER AUTO_INCREMENT PRIMARY KEY, customer_id INTEGER, car_id INTEGER, employee_id INTEGER)");
			st.executeUpdate();
			st.close();
			
			st = connection.prepareStatement("SELECT * FROM users");
			rs = st.executeQuery();
			
			if (!rs.first()) {
				st.close();
				st = connection.prepareStatement("INSERT INTO users (user_name, user_password, permissions) VALUES ('alex', '123', 'ADMIN')");
				st.executeUpdate();
				st.close();
				
				st = connection.prepareStatement("INSERT INTO users (user_name, user_password, permissions) VALUES ('jassii', '123', 'USER')");
				st.executeUpdate();
				st.close();
				
				st = connection.prepareStatement("INSERT INTO users (user_name, user_password, permissions) VALUES ('marc', '123', 'USER')");
				st.executeUpdate();
				st.close();
				
				st = connection.prepareStatement("INSERT INTO users (user_name, user_password, permissions) VALUES ('niklas', '123', 'USER')");
				st.executeUpdate();
				st.close();
			}
			rs.close();
			
			st = connection.prepareStatement("SELECT * FROM cars");
			rs = st.executeQuery();
			
			if (!rs.first()) {
				st.close();
				
				st = connection.prepareStatement("INSERT INTO cars (class_id, car_brand, car_model, power, torque, price, mileage, topspeed) VALUES (1, 'smart', 'fortwo', 52, 160, 21386.9, 0, 155)");
				st.executeUpdate();
				st.close();
				
				st = connection.prepareStatement("INSERT INTO cars (class_id, car_brand, car_model, power, torque, price, mileage, topspeed) VALUES (2, 'Volkswagen', 'Golf', 66, 175, 19880.8, 188)");
				st.executeUpdate();
				st.close();
				
				st = connection.prepareStatement("INSERT INTO cars (class_id, car_brand, car_model, power, torque, price, mileage, topspeed) VALUES (3, 'Mercedes-Benz', 'E200', 143)");
				st.executeUpdate();
				st.close();
				
				st = connection.prepareStatement("INSERT INTO cars (class_id, car_brand, car_model, power, torque, price, mileage, topspeed) VALUES (4, 'Volkswagen', 'Touareg', 170)");
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
				
				st = connection.prepareStatement("INSERT INTO cars_class (class_name, price) VALUES ('I', 100)");
				st.executeUpdate();
				st.close();
				
				st = connection.prepareStatement("INSERT INTO cars_class (class_name, price) VALUES ('J', 130)");
				st.executeUpdate();
				st.close();
			}
			
			st = connection.prepareStatement("SELECT * FROM customers");
			rs = st.executeQuery();
			
			if (!rs.first()) {
				st.close();
				
				st = connection.prepareStatement("INSERT INTO customers (first_name, surname, birth_date, country, city, zip_code, street, email, phone) VALUES ('Max', 'Mustermann', '01.01.1970', 'Deutschland', 'Berlin', '13581', 'Max-Mustermann-Straße 10', 'max.mustermann@example.com', '017591231022')");
				st.executeUpdate();
				st.close();
				
				st = connection.prepareStatement("INSERT INTO customers (first_name, surname, birth_date, country, city, zip_code, street, email, phone) VALUES ('Hans', 'Wurst', '23.05.1980', 'Deutschland', 'München', '80689', 'Hans-Wurst-Weg 2b', 'hans@wurst.de', '017310387234')");
				st.executeUpdate();
				st.close();
				
				st = connection.prepareStatement("INSERT INTO customers (first_name, surname, birth_date, country, city, zip_code, street, email, phone) VALUES ('Fridolin', 'Müller', '25.03.1996', 'Deutschland', 'Berlin', '10559', 'Müller-Meier-Schmidt-Straße 61c', 'fridolin@muellerchen.org', '017591231022')");
				st.executeUpdate();
				st.close();
				
				st = connection.prepareStatement("INSERT INTO customers (first_name, surname, birth_date, country, city, zip_code, street, email, phone) VALUES ('Jürgen', 'Meier', '28.07.1977', 'Österreich', 'Wien', '1050', 'Schönbrunner Str. 5', 'meier.guenther@irgendwas.at', '+43-663 14293418')");
				st.executeUpdate();
				st.close();
				
				st = connection.prepareStatement("INSERT INTO customers (first_name, surname, birth_date, country, city, zip_code, street, email, phone) VALUES ('Robert', 'Schmitt', '05.12.1950', 'Deutschland', 'Köln', '50667', 'Max-Mustermann-Straße 10', '', '01751534628')");
				st.executeUpdate();
				st.close();
				
				st = connection.prepareStatement("INSERT INTO customers (first_name, surname, birth_date, country, city, zip_code, street, email, phone) VALUES ('Uwe', 'Schmidt', '01.04.1947', 'Deutschland', 'Berlin', '10115', 'Uwe-Meme-Straße 57', 'ichbinderuwe@ichbinauchdabei.de', '')");
				st.executeUpdate();
				st.close();
			}
			rs.close();
			
			JOptionPane.showMessageDialog(null, "Das Programm hat sich mit der Datenbank verbunden!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeRessources(null, st, connection);
		}
	}
}
