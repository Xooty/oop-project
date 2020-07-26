package de.hwrberlin.autovermietung.customers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.contract.Contract;
import de.hwrberlin.autovermietung.mysql.MySQL;

public class Customer {

	private int customer_id;
	
	private String salutation, first_name, surname;
	
	private String birth_date;
	
	private String address;
	
	private String phone, email;
	
	private List<Contract> contracts;
	
	public Customer(String salutation, String first_name, String surname, String birth_date, String address, String phone, String email) {
		
		this.contracts = new ArrayList<Contract>();
		
		this.salutation = salutation;
		this.first_name = first_name;
		this.surname = surname;
		this.birth_date = birth_date;
		this.address = address;
		this.phone = phone;
		this.email = email;
		
		MySQL sql = Main.getMySQL();
		Connection connection = sql.openConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("INSERT INTO customers (salutation, first_name, surname, birth_date, address, phone, email) VALUES (?, ?, ?, ?, ?, ?, ?)");
			st.setString(1, this.salutation);
			st.setString(2, this.first_name);
			st.setString(3, this.surname);
			st.setString(4, this.birth_date);
			st.setString(5, this.address);
			st.setString(6, this.phone);
			st.setString(7, this.email);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.closeRessources(rs, st, connection);
		}
	}
	
	public Customer(int customer_id, Connection connection) {
		
		this.contracts = new ArrayList<Contract>();
		
		this.customer_id = customer_id;
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("SELECT * FROM customers WHERE customer_id = ?");
			st.setInt(1, this.customer_id);
			
			rs = st.executeQuery();
			
			rs.first();
			
			this.salutation = rs.getString("salutation");
			this.first_name = rs.getString("first_name");
			this.surname = rs.getString("surname");
			this.birth_date = rs.getString("birth_date");
			this.address = rs.getString("address");
			this.phone = rs.getString("phone");
			this.email = rs.getString("email");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getCustomerID() {
		return this.customer_id;
	}
	
	public String getSalutation() {
		return this.salutation;
	}
	
	public String getFirstName() {
		return this.first_name;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public String getFullName() {
		return this.first_name + " " + this.surname;
	}
	
	public Calendar getBirthDate() {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    	format.format(new Date());
    	Date date = null;
    	try {
			date = format.parse(this.birth_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Contract> getContracts() {
		return this.contracts;
	}
	
	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}
	
	public void addContract(Contract contract) {
		this.contracts.add(contract);
	}
	
	public void loadContracts() {
		MySQL sql = Main.getMySQL();
		Connection connection = sql.openConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("SELECT contract_id FROM contracts WHERE customer_id = ?");
			st.setInt(1, this.customer_id);
			
			rs = st.executeQuery();
			
			while (rs.next()) {
				this.addContract(new Contract(rs.getInt("contract_id"), connection));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.closeRessources(rs, st, connection);
		}
		
		for (Contract contract : this.contracts) {
			if (System.currentTimeMillis() > contract.getContractEnd() && contract.isCarReturned()) this.contracts.remove(contract);
		}
	}
}
