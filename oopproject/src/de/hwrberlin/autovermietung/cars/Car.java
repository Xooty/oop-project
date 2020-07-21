package de.hwrberlin.autovermietung.cars;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.contract.Contract;
import de.hwrberlin.autovermietung.customers.Customer;
import de.hwrberlin.autovermietung.mysql.MySQL;

public class Car {

	private int car_id;
	
	private String plate_number;
	
	private int class_id;
	
	private CarBrand car_brand;
	
	private String model;
	
	private CarType car_type;
	
	private String id;
	
	private double price;
	
	private long mileage;
	
	private int power;
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
	
	public Car(int car_id) {
		this.car_id = car_id;
		
		MySQL sql = Main.getMySQL();
		Connection connection = sql.openConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("SELECT * FROM cars WHERE car_id = ?");
			st.setInt(1, this.car_id);
			
			rs = st.executeQuery();
			
			rs.first();
			
			this.plate_number = rs.getString("plate_number");
			this.class_id = rs.getInt("class_id");
			this.car_brand = CarBrand.valueOf(rs.getString("car_brand"));
			this.model = rs.getString("car_model");
			this.power = rs.getInt("power");
			this.torque = rs.getInt("torque");
			this.price = rs.getDouble("price");
			this.topspeed = rs.getInt("topspeed");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.closeRessources(rs, st, connection);
		}
	}
	
	public int getCarID() {
		return this.car_id;
	}
	
	public String getPlateNumber() {
		return this.plate_number;
	}
	
	public int getClassID() {
		return this.class_id;
	}
	
	public void setClassID(int class_id) {
		this.class_id = class_id;
	}
	
	public CarBrand getCarBrand() {
		return this.car_brand;
	}
	
	public void setCarBrand(CarBrand car_brand) {
		this.car_brand = car_brand;
	}
	
	public String getModel() {
		return this.model;
	}
	
	public void setModel(String model) {
		this.model = model;
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
	
	public void setID(String id) {
		this.id = id;
	}
	
	public int getHorsepower() {
		return this.power;
	}
	
	public void setHorsepower(int horsepower) {
		this.power = horsepower;
	}
	
	public int getTorque() {
		return this.torque;
	}
	
	public void setTorque(int torque) {
		this.torque = torque;
	}
	
	public int getTopspeed() {
		return this.topspeed;
	}
	
	public void setTopspeed(int topspeed) {
		this.topspeed = topspeed;
	}
	
	public int getFuel() {
		return this.fuel;
	}
	
	public void setFuel(int fuel) {
		this.fuel = fuel;
	}
	
	public FuelType getFuelType() {
		return this.fuel_type;
	}
	
	public void setFuelType(FuelType fuel_type) {
		this.fuel_type = fuel_type;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public long getMileage() {
		return this.mileage;
	}
	
	public void setMileage(long mileage) {
		this.mileage = mileage;
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