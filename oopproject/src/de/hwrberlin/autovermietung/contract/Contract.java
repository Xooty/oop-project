package de.hwrberlin.autovermietung.contract;

import de.hwrberlin.autovermietung.cars.Car;
import de.hwrberlin.autovermietung.customers.Customer;

public class Contract {

	private int id;
	
	private Customer human;
	
	private Car car;
	
	private long time_start, time_end;
	
	private long mileage_start, mileage_end;
	
	private boolean insurance;
	
	public Contract(int id, Customer human, Car car) {
		this.id = id;
		
		this.human = human;
		
		this.car = car;
	}
	
	public int getID() {
		return this.id;
	}
	
	public Customer getHuman() {
		return this.human;
	}
	
	public Car getCar() {
		return this.car;
	}
	
	public long getTimeStart() {
		return this.time_start;
	}
	
	public void setTimeStart(long time) {
		this.time_start = time;
	}
	
	public long getTimeEnd() {
		return this.time_end;
	}
	
	public void setEndTime(long time) {
		this.time_end = time;
	}
	
	public void addTimeEnd(long time) {
		this.time_end += time;
	}
	
	public long getTimeLeft() {
		return this.time_end - this.time_start;
	}
	
	public long getMileageStart() {
		return this.mileage_start;
	}
	
	public void setMileageStart(long mileage) {
		this.mileage_start = mileage;
	}
	
	public long getMileageEnd() {
		return this.mileage_end;
	}
	
	public void setMileageEnd(long mileage) {
		this.mileage_end = mileage;
	}
	
	public void addMileageEnd(long mileage) {
		this.mileage_end += mileage;
	}
	
	public long getMileageDifference() {
		return this.mileage_end - this.mileage_start;
	}
	
	public boolean hasInsurance() {
		return this.insurance;
	}
	
	public void setInsurance(boolean insurance) {
		this.insurance = insurance;
	}
}