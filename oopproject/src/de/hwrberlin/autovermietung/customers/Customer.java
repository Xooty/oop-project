package de.hwrberlin.autovermietung.customers;

import java.util.Calendar;
import java.util.List;

import de.hwrberlin.autovermietung.contract.Contract;

public class Customer {

	private int customer_id;
	
	private String first_name, surname;
	
	private Calendar birth_date;
	
	private String address;
	
	private String phone, email;
	
	private List<Contract> current_contracts, last_contracts;
	
	public Customer(int customer_id, String first_name, String surname, Calendar birth_date) {
		this.customer_id = customer_id;
		
		this.first_name = first_name;
		this.surname = surname;
		
		this.birth_date = birth_date;
	}
	
	public int getCustomerID() {
		return this.customer_id;
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
		return this.birth_date;
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
	
	public List<Contract> getCurrentContracts() {
		return this.current_contracts;
	}
	
	public void setCurrentContracts(List<Contract> current_contracts) {
		this.current_contracts = current_contracts;
	}
	
	public List<Contract> getLastContracts() {
		return this.last_contracts;
	}
	
	public void setLastContracts(List<Contract> last_contracts) {
		this.last_contracts = last_contracts;
	}
}
