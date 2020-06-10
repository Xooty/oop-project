package de.hwrberlin.autovermietung.cars;

import java.util.ArrayList;
import java.util.List;

import de.hwrberlin.autovermietung.contract.Contract;
import de.hwrberlin.autovermietung.customers.Customer;

public class Car {

	private CarBrand car_brand;
	
	private String model;
	
	private CarType car_type;
	
	private String id;
	
	private double price;
	
	private long mileage;
	
	private int horsepower;
	private int torque;
	private int topspeed;
	
	private int fuel;
	private FuelType fuel_type;
	
	private List<String> damages;
	
	private Customer current_customer;
	private List<Customer> previous_customers;
	
	private boolean occupied;
	
	private List<Contract> contracts;
	
	public Car() {
		this.damages = new ArrayList<String>();
		
		this.current_customer = null;
		this.previous_customers = new ArrayList<Customer>();
		
		this.occupied = false;
		
		this.contracts = new ArrayList<Contract>();
	}
	
	public CarBrand getCarBrand() {
		return this.car_brand;
	}
	
	public Car setCarBrand(CarBrand car_brand) {
		this.car_brand = car_brand;
		return this;
	}
	
	public String getModel() {
		return this.model;
	}
	
	public Car setModel(String model) {
		this.model = model;
		return this;
	}
	
	public CarType getCarType() {
		return this.car_type;
	}
	
	public Car setCarType(CarType car_type) {
		this.car_type = car_type;
		return this;
	}
	
	public String getID() {
		return this.id;
	}
	
	public Car setID(String id) {
		this.id = id;
		return this;
	}
	
	public int getHorsepower() {
		return this.horsepower;
	}
	
	public Car setHorsepower(int horsepower) {
		this.horsepower = horsepower;
		return this;
	}
	
	public int getTorque() {
		return this.torque;
	}
	
	public Car setTorque(int torque) {
		this.torque = torque;
		return this;
	}
	
	public int getTopspeed() {
		return this.topspeed;
	}
	
	public Car setTopspeed(int topspeed) {
		this.topspeed = topspeed;
		return this;
	}
	
	public int getFuel() {
		return this.fuel;
	}
	
	public Car setFuel(int fuel) {
		this.fuel = fuel;
		return this;
	}
	
	public FuelType getFuelType() {
		return this.fuel_type;
	}
	
	public Car setFuelType(FuelType fuel_type) {
		this.fuel_type = fuel_type;
		return this;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public Car setPrice(double price) {
		this.price = price;
		return this;
	}
	
	public long getMileage() {
		return this.mileage;
	}
	
	public Car setMileage(long mileage) {
		this.mileage = mileage;
		return this;
	}
	
	public void addMileage(long mileage) {
		this.mileage += mileage;
	}
	
	public List<String> getDamages() {
		return this.damages;
	}
	
	public void setDamages(List<String> damages) {
		this.damages = damages;
	}
	
	public void addDamage(String damage) {
		this.damages.add(damage);
	}
	
	public void removeDamage(String damage) {
		this.damages.remove(damage);
	}
	
	public Customer getCurrentCustomer() {
		return this.current_customer;
	}
	
	public void setCurrentCustomer(Customer current_customer) {
		this.current_customer = current_customer;
	}
	
	public List<Customer> getPreviousCustomers() {
		return this.previous_customers;
	}
	
	public void addPreviousCustomer(Customer customer) {
		this.previous_customers.add(customer);
	}
	
	public void removePreviousCustomer(Customer customer) {
		this.previous_customers.remove(customer);
	}
	
	public boolean isOccupied() {
		return this.occupied;
	}
	
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
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
	
	public void removeContract(Contract contract) {
		this.contracts.remove(contract);
	}
}