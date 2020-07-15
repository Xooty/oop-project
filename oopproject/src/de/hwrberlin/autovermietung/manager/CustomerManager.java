package de.hwrberlin.autovermietung.manager;

import java.util.ArrayList;
import java.util.List;

import de.hwrberlin.autovermietung.customers.Customer;

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
}
