package de.hwrberlin.autovermietung.frames;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.cars.Car;

public class MenuFrame extends MainFrame {

	private static final long serialVersionUID = -1617781986656844268L;

	private JComboBox<Object> combobox_cars;
	
	public MenuFrame() {
		super(1, "Hauptmenü", 600, 600);
		
		List<String> cars = new ArrayList<String>();
		
		for (Car car : Main.getCarManager().getCars()) {
			cars.add(car.getCarBrand().getName() + " " + car.getModel());
		}
		
		this.combobox_cars = new JComboBox<Object>(cars.toArray());
		this.combobox_cars.setSelectedIndex(0);
		this.combobox_cars.setBounds(200, 200, 250, 25);
		
		this.add(this.combobox_cars);
		
		this.add(new JLabel());
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
	}
}
