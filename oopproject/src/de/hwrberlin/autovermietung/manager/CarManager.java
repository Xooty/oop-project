package de.hwrberlin.autovermietung.manager;

import java.util.ArrayList;
import java.util.List;

import de.hwrberlin.autovermietung.cars.Car;

public class CarManager {

	private List<Car> cars;
	
	public CarManager() {
		this.cars = new ArrayList<Car>();
		
		this.setupCars();
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
	
	public void setupCars() {
		
//		this.addCar(new Car().setCarBrand(CarBrand.AUDI).setModel("RS6").setCarType(CarType.SPORT).setID("012394382").setHorsepower(600).setTorque(800).setTopspeed(330).setFuel(60).setFuelType(FuelType.SUPER).setPrice(117500).setMileage(0));
//		this.addCar(new Car().setCarBrand(CarBrand.HYUNDAI).setModel("i30N").setCarType(CarType.SPORT).setID("123456789").setHorsepower(275).setTorque(378).setTopspeed(250).setFuel(45).setFuelType(FuelType.SUPER).setPrice(38000).setMileage(0));
	}
}
