package de.hwrberlin.autovermietung.cars;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.contract.Contract;
import de.hwrberlin.autovermietung.mysql.MySQL;

public class Car {

	private int car_id;
	
	private String plate_number;
	
	private int car_class;
	
	private CarBrand car_brand;
	
	private String model;
	
	private double price;
	
	private int mileage;
	private int power;
	private int torque;
	private int topspeed;
	
	private FuelType fuel_type;
	
	private List<String> damages;
	
	private boolean occupied;
	
	private List<Contract> contracts;
	
	public Car(String plate_number, int class_id, String car_brand, String car_model, String fuel_type, int power, int torque, double price, int mileage, int topspeed) {
		
		this.occupied = false;
		this.contracts = new ArrayList<Contract>();
		
		this.plate_number = plate_number;
		this.car_class = class_id;
		this.car_brand = CarBrand.valueOf(car_brand);
		this.model = car_model;
		this.fuel_type = FuelType.valueOf(fuel_type);
		this.power = power;
		this.torque = torque;
		this.price = price;
		this.mileage = mileage;
		this.topspeed = topspeed;
		
		MySQL sql = Main.getMySQL();
		Connection connection = sql.openConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("INSERT INTO cars (plate_number, class_id, car_brand, car_model, fuel_type, power, torque, price, mileage, topspeed) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			st.setString(1, this.plate_number);
			st.setInt(2, this.car_class);
			st.setString(3, this.car_brand.getName());
			st.setString(4, this.model);
			st.setString(5, this.fuel_type.toString());
			st.setInt(6, this.power);
			st.setInt(7, this.torque);
			st.setDouble(8, this.price);
			st.setInt(9, this.mileage);
			st.setInt(10, this.topspeed);
			
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.closeRessources(rs, st, connection);
		}
	}
	
	public Car(String plate_number, Connection connection) {
		this.plate_number = plate_number;
		
		this.contracts = new ArrayList<Contract>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("SELECT * FROM cars WHERE plate_number = ?");
			st.setString(1, this.plate_number);
			
			rs = st.executeQuery();
			
			rs.first();
			
			this.car_id = rs.getInt("car_id");
			this.car_class = rs.getInt("class_id");
			this.car_brand = CarBrand.valueOf(rs.getString("car_brand").toUpperCase().replace("-", "_"));
			this.model = rs.getString("car_model");
			this.fuel_type = FuelType.valueOf(rs.getString("fuel_type"));
			this.power = rs.getInt("power");
			this.torque = rs.getInt("torque");
			this.price = rs.getDouble("price");
			this.mileage = rs.getInt("mileage");
			this.topspeed = rs.getInt("topspeed");
			
			rs.close();
			st.close();
			
			st = connection.prepareStatement("SELECT * FROM contracts WHERE car_id = ? AND car_returned = FALSE");
			st.setInt(1, this.car_id);
			
			rs = st.executeQuery();
			
			while (rs.next()) {
				if (System.currentTimeMillis() < rs.getLong("contract_end") && System.currentTimeMillis() > rs.getLong("contract_start")) {
					this.occupied = true;
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Car(int car_id, Connection connection) {
		this.car_id = car_id;
		
		this.contracts = new ArrayList<Contract>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("SELECT * FROM cars WHERE car_id = ?");
			st.setInt(1, this.car_id);
			
			rs = st.executeQuery();
			
			rs.first();
			
			this.plate_number = rs.getString("plate_number");
			this.car_class = rs.getInt("class_id");
			this.car_brand = CarBrand.valueOf(rs.getString("car_brand").toUpperCase().replace("-", "_"));
			this.model = rs.getString("car_model");
			this.fuel_type = FuelType.valueOf(rs.getString("fuel_type"));
			this.power = rs.getInt("power");
			this.torque = rs.getInt("torque");
			this.price = rs.getDouble("price");
			this.mileage = rs.getInt("mileage");
			this.topspeed = rs.getInt("topspeed");
			
			rs.close();
			st.close();
			
			st = connection.prepareStatement("SELECT * FROM contracts WHERE car_id = ? AND car_returned = FALSE");
			st.setInt(1, this.car_id);
			
			rs = st.executeQuery();
			
			while (rs.next()) {
				if (System.currentTimeMillis() < rs.getLong("contract_end") && System.currentTimeMillis() > rs.getLong("contract_start")) {
					this.occupied = true;
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getCarID() {
		return this.car_id;
	}
	
	public String getPlateNumber() {
		return this.plate_number;
	}
	
	public int getCarClass() {
		return this.car_class;
	}
	
	public void setCarClass(int car_class) {
		this.car_class = car_class;
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
	
	public int getMileage() {
		return this.mileage;
	}
	
	public void setMileage(int mileage) {
		this.mileage = mileage;
		
		MySQL sql = Main.getMySQL();
		Connection connection = sql.openConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("UPDATE cars SET mileage = ? WHERE car_id = ?");
			st.setInt(1, this.mileage);
			st.setInt(2, this.car_id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.closeRessources(rs, st, connection);
		}
	}
	
	public void addMileage(int mileage) {
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
	
	public void loadContracts() {
		MySQL sql = Main.getMySQL();
		Connection connection = sql.openConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("SELECT contract_id FROM contracts WHERE car_id = ?");
			st.setInt(1, this.car_id);
			
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