package de.hwrberlin.autovermietung.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.cars.Car;
import de.hwrberlin.autovermietung.cars.CarClass;
import de.hwrberlin.autovermietung.mysql.MySQL;

public class CarManager {

	private List<Car> cars;
	
	private List<CarClass> car_classes;
	
	public CarManager() {
		this.cars = new ArrayList<Car>();
		this.car_classes = new ArrayList<CarClass>();
		
		MySQL sql = Main.getMySQL();
		Connection connection = sql.openConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("SELECT * FROM cars_class");
			rs = st.executeQuery();
			
			while (rs.next()) {
				this.car_classes.add(new CarClass(rs.getInt("class_id"), rs.getString("class_name"), rs.getDouble("price")));
			}
			
			rs.close();
			st.close();
			
			st = connection.prepareStatement("SELECT car_id FROM cars");
			rs = st.executeQuery();
			
			while (rs.next()) {
				this.cars.add(new Car(rs.getInt("car_id"), connection));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.closeRessources(rs, st, connection);
		}
	}
	
	public List<Car> getCars() {
		return this.cars;
	}
	
	public void addCar(Car car) {
		this.cars.add(car);
	}
	
	public void removeCar(Car car) {
		this.cars.remove(car);
	}
	
	public Car getCarByPlateNumber(String plate_number) {
		for (Car car : this.cars) {
			if (car.getPlateNumber().equalsIgnoreCase(plate_number)) return car;
		}
		return null;
	}
	
	public Car getCarByName(String name) {
		for (Car car : this.cars) {
			String car_name = car.getCarBrand().getName() + " " + car.getModel();
			if (car_name.equalsIgnoreCase(name)) return car;
		}
		return null;
	}
	
	public Car getCarByID(int car_id) {
		for (Car car : this.cars) {
			if (car.getCarID() == car_id) return car;
		}
		return null;
	}
	
	public List<CarClass> getCarClasses() {
		return this.car_classes;
	}
	
	public void addCarClass(CarClass car_class) {
		this.car_classes.add(car_class);
	}
	
	public void removeCarClass(CarClass car_class) {
		this.car_classes.remove(car_class);
	}
	
	public CarClass getCarClassByString(String s) {
		for (CarClass c : this.car_classes) {
			String class_name = c.getClassName() + ": " + c.getPrice() + "€";
			if (class_name.equalsIgnoreCase(s)) return c;
		}
		return null;
	}
	
	public CarClass getCarClassByID(int class_id) {
		for (CarClass c : this.car_classes) {
			if (class_id == c.getClassID()) return c;
		}
		return null;
	}
	
	public Car searchCar(String plate_number) {
		MySQL sql = Main.getMySQL();
		Connection connection = sql.openConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("SELECT * FROM cars WHERE plate_number = ?");
			st.setString(1, plate_number);
			rs = st.executeQuery();
			
			if (rs.first()) {
				return new Car(rs.getString("plate_number"), connection);
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
	
	public boolean deleteCar(Car car) {
		MySQL sql = Main.getMySQL();
		Connection connection = sql.openConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("DELETE FROM cars WHERE car_id = ?");
			st.setInt(1, car.getCarID());
			st.executeUpdate();
			
			Main.getCarManager().removeCar(car);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.closeRessources(rs, st, connection);
		}
		return false;
	}
}
