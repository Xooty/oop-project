package de.hwrberlin.autovermietung.cars;

public class CarClass {

	private int class_id;
	
	private String class_name;
	
	private double price;
	
	public CarClass(int class_id, String class_name, double price) {
		this.class_id = class_id;
		this.class_name = class_name;
		this.price = price;
	}
	
	public int getClassID() {
		return this.class_id;
	}
	
	public String getClassName() {
		return this.class_name;
	}
	
	public double getPrice() {
		return this.price;
	}
}
