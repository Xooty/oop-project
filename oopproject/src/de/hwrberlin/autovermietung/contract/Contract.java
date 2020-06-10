package de.hwrberlin.autovermietung.contract;

import de.hwrberlin.autovermietung.cars.Car;
import de.hwrberlin.autovermietung.customers.Customer;

public class Contract {

	private int contract_id;
	
	private int worker_id;
	
	private Customer customer;
	
	private Car car;
	
	private long time_start, time_end;
	
	private long mileage_start, mileage_end;
	
	private boolean insurance;
	
	public Contract(int contract_id, int worker_id, Customer customer, Car car) {
		this.contract_id = contract_id;
		
		this.worker_id = worker_id;
		
		this.customer = customer;
		
		this.car = car;
	}
	
	public int getContractID() {
		return this.contract_id;
	}
	
	public int getWorkerID() {
		return this.worker_id;
	}
	
	public Customer getCustomer() {
		return this.customer;
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