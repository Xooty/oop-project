package de.hwrberlin.autovermietung.cars;

import java.util.ArrayList;
import java.util.List;

public class CarManager {

	private List<Car> cars;
	
	public CarManager() {
		this.cars = new ArrayList<Car>();
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
	
	public Car getCarByID(String id) {
		for (Car car : this.cars) {
			if (car.getID().equals(id)) return car;
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
}
