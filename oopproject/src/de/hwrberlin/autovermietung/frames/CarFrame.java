package de.hwrberlin.autovermietung.frames;

import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import de.hwrberlin.autovermietung.cars.Car;
import de.hwrberlin.autovermietung.users.Permission;

public class CarFrame extends MainFrame {

	private static final long serialVersionUID = 1723247931375093671L;
	
	private Car car;
	
	private JLabel label_power;

	public CarFrame() {
		super(102, Permission.USER, "Auto: ", 450, 450);
	}

	public void setCar(Car car) {
		this.car = car;
		
		this.setTitle("Auto: " + this.car.getCarBrand().getName() + " " + this.car.getModel());
		
		this.label_power = new JLabel("Leistung: " + car.getHorsepower() + " kW (" + (car.getHorsepower() * 1.35962) + " PS)");
		this.label_power.setBounds(50, 50, 250, 25);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
	}
}
