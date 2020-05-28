package de.hwrberlin.autovermietung.customers;

public class Customer {

	private String name, surname;
	
	private int age;
	
	private String id;
	
	private String phone;
	
	public Customer(String name, String surname, int age, String id) {
		this.name = name;
		this.surname = surname;
		
		this.age = age;
		
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public String getFullName() {
		return this.name + " " + this.surname;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public String getID() {
		return this.id;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
