package de.hwrberlin.autovermietung.cars;

import java.util.ArrayList;
import java.util.List;

import de.hwrberlin.autovermietung.humans.Human;

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
	
	private Human current_human;
	private List<Human> previous_humans;
	
	public Car() {
		this.damages = new ArrayList<String>();
		
		this.current_human = null;
		this.previous_humans = new ArrayList<Human>();
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
	
	public Human getCurrentHuman() {
		return this.current_human;
	}
	
	public void setCurrentHuman(Human current_human) {
		this.current_human = current_human;
	}
	
	public List<Human> getPreviousHumans() {
		return this.previous_humans;
	}
	
	public void addPreviousHuman(Human human) {
		this.previous_humans.add(human);
	}
	
	public void removePreviousHuman(Human human) {
		this.previous_humans.remove(human);
	}
}
