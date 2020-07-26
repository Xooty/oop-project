package de.hwrberlin.autovermietung.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.customers.Customer;
import de.hwrberlin.autovermietung.mysql.MySQL;

public class CustomerManager {

	private List<Customer> customers;
	
	public CustomerManager() {
		this.customers = new ArrayList<Customer>();
	}
	
	public List<Customer> getCustomers() {
		return this.customers;
	}
	
	public void addCustomer(Customer customer) {
		this.customers.add(customer);
	}
	
	public void removeCustomer(Customer customer) {
		this.customers.remove(customer);
	}
	
	public Customer searchCustomer(String first_name, String surname) {
		MySQL sql = Main.getMySQL();
		Connection connection = sql.openConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("SELECT * FROM customers WHERE first_name = ? AND surname = ?");
			st.setString(1, first_name);
			st.setString(2, surname);
			rs = st.executeQuery();
			
			if (rs.first()) {
				return new Customer(rs.getInt("customer_id"), connection);
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.closeRessources(rs, st, connection);
		}
		return null;
	}
	
	public Customer searchCustomer(int customer_id) {
		MySQL sql = Main.getMySQL();
		Connection connection = sql.openConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("SELECT * FROM customers WHERE customer_id = ?");
			st.setInt(1, customer_id);
			rs = st.executeQuery();
			
			if (rs.first()) {
				return new Customer(customer_id, connection);
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.closeRessources(rs, st, connection);
		}
		return null;
	}
	
	public boolean deleteCustomer(Customer customer) {
		MySQL sql = Main.getMySQL();
		Connection connection = sql.openConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("DELETE FROM customers WHERE customer_id = ?");
			st.setInt(1, customer.getCustomerID());
			st.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.closeRessources(rs, st, connection);
		}
		return false;
	}
}
