package de.hwrberlin.autovermietung.frames;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.cars.Car;
import de.hwrberlin.autovermietung.users.Permission;

public class CarFrame extends MainFrame {

	private static final long serialVersionUID = 1723247931375093671L;
	
	private Car car;
	
	private JLabel label_plate_number, label_class, label_car_brand, label_car_model, label_fuel_type, label_power, label_torque, label_price, label_mileage, label_topspeed;
	
	private JButton button_back;

	public CarFrame() {
		super(102, "Auto: ", 350, 450);
		
		this.label_plate_number = new JLabel();
		this.label_class = new JLabel();
		this.label_car_brand = new JLabel();
		this.label_car_model = new JLabel();
		this.label_fuel_type = new JLabel();
		this.label_power = new JLabel();
		this.label_torque = new JLabel();
		this.label_price = new JLabel();
		this.label_mileage = new JLabel();
		this.label_topspeed = new JLabel();
	}

	public void setCar(Car car) {
		this.car = car;
		
		this.setTitle("Auto: " + this.car.getCarBrand().getName() + " " + this.car.getModel());
		
		this.label_plate_number.setText("Kennzeichen: " + car.getPlateNumber());
		this.label_plate_number.setBounds(50, 25, 250, 25);
		
		this.label_class.setText("Klasse: " + Main.getCarManager().getCarClassByID(car.getCarClass()).getClassName() + " - " + Main.getCarManager().getCarClassByID(car.getCarClass()).getPrice() + "€");
		this.label_class.setBounds(50, 50, 250, 25);
		
		this.label_car_brand.setText("Automarke: " + car.getCarBrand().getName());
		this.label_car_brand.setBounds(50, 75, 250, 25);
		
		this.label_car_model.setText("Modell: " + car.getModel());
		this.label_car_model.setBounds(50, 100, 250, 25);
		
		this.label_power.setText("Leistung: " + car.getHorsepower() + " PS");
		this.label_power.setBounds(50, 125, 250, 25);
		
		this.label_torque.setText("Drehmoment: " + car.getTorque() + " Nm");
		this.label_torque.setBounds(50, 150, 250, 25);
		
		this.label_fuel_type.setText("Kraftstoff: " + car.getFuelType().toString().replace("_", " "));
		this.label_fuel_type.setBounds(50, 175, 250, 25);
		
		this.label_price.setText("Preis: " + car.getPrice() + "€");
		this.label_price.setBounds(50, 200, 250, 25);
		
		this.label_mileage.setText("Kilometerstand: " + car.getMileage() + " km");
		this.label_mileage.setBounds(50, 225, 250, 25);
		
		this.label_topspeed.setText("Höchstgeschwindigkeit: " + car.getTopspeed() + " km/h");
		this.label_topspeed.setBounds(50, 250, 250, 25);
		
		this.button_back = new JButton("Zurück");
		this.button_back.setBounds(50, 300, 250, 50);
		this.button_back.addActionListener(this);
		
		this.getContentPane().add(this.label_plate_number);
		this.getContentPane().add(this.label_class);
		this.getContentPane().add(this.label_car_brand);
		this.getContentPane().add(this.label_car_model);
		this.getContentPane().add(this.label_fuel_type);
		this.getContentPane().add(this.label_power);
		this.getContentPane().add(this.label_torque);
		this.getContentPane().add(this.label_price);
		this.getContentPane().add(this.label_mileage);
		this.getContentPane().add(this.label_topspeed);
		this.getContentPane().add(this.button_back);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (Main.getMySQL().getUser().hasPermission(Permission.ADMIN)) {
			Main.getFrameManager().openFrameByID(4);
		} else {
			Main.getFrameManager().openFrameByID(107);
		}
	}
}