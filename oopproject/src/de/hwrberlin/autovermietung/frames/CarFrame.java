package de.hwrberlin.autovermietung.frames;

import java.awt.event.ActionEvent;

import de.hwrberlin.autovermietung.cars.Car;
import de.hwrberlin.autovermietung.users.Permission;

public class CarFrame  extends MainFrame {

	private static final long serialVersionUID = 1723247931375093671L;
	
	private Car car;

	public CarFrame() {
		super(102, Permission.USER, "Auto: ", 450, 450);
	}

	public void setCar(Car car) {
		this.car = car;
		
		this.setTitle("Auto: " + this.car.getCarBrand().getName() + " " + this.car.getModel());
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
	}
}
