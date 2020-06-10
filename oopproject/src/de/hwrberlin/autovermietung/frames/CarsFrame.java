package de.hwrberlin.autovermietung.frames;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.cars.Car;
import de.hwrberlin.autovermietung.users.Permission;

public class CarsFrame extends MainFrame {

	private static final long serialVersionUID = -7857686873009312944L;

	private JComboBox<Object> combobox_cars;
	
	private JButton button_detail_view;
	
	private JTextArea textarea_list_contracts;
	
	public CarsFrame() {
		super(107, Permission.USER, "Fahrzeuge", 600, 600);
	}
	
	public void setup() {
		if (Main.getMySQL().getUser().hasPermission(Permission.ADMIN)) {
			
		} else {
			List<String> cars = new ArrayList<String>();
			
			cars.add("Fahrzeug auswählen...");
			for (Car car : Main.getCarManager().getCars()) {
				cars.add(car.getCarBrand().getName() + " " + car.getModel());
			}
			
			this.combobox_cars = new JComboBox<Object>(cars.toArray());
			this.combobox_cars.setSelectedIndex(0);
			this.combobox_cars.setBounds(25, 25, 250, 25);
			
			this.button_detail_view = new JButton("Detailansicht");
			
			this.add(this.combobox_cars);
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
	}
}
