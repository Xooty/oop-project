package de.hwrberlin.autovermietung.frames;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.cars.Car;
import de.hwrberlin.autovermietung.users.Permission;

public class MenuFrame extends MainFrame {

	private static final long serialVersionUID = -1617781986656844268L;

	private JComboBox<Object> combobox_cars;
	
	private JButton button_select, button_calendar, button_change_password;
	
	public MenuFrame() {
		super(1, Permission.ADMIN, "Hauptmenü", 600, 600);
		
		List<String> cars = new ArrayList<String>();
		
		for (Car car : Main.getCarManager().getCars()) {
			cars.add(car.getCarBrand().getName() + " " + car.getModel());
		}
		
		this.combobox_cars = new JComboBox<Object>(cars.toArray());
		this.combobox_cars.setSelectedIndex(0);
		this.combobox_cars.setBounds(300, 250, 250, 25);

		this.button_select = new JButton("Auswählen");
		this.button_select.setBounds(300, 300, 250, 25);
		this.button_select.addActionListener(this);
		
		this.button_calendar = new JButton("Kalender");
		this.button_calendar.setBounds(300, 500, 250, 25);
		this.button_calendar.addActionListener(this);
		
		this.button_change_password = new JButton("Passwort ändern");
		this.button_change_password.setBounds(100, 100, 250, 25);
		this.button_change_password.addActionListener(this);
				
		this.add(this.combobox_cars);
		this.add(this.button_select);
		this.add(this.button_calendar);
		this.add(this.button_change_password);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.button_select) {
			CarFrame frame = (CarFrame) Main.getFrameManager().openFrameByID(102);
			frame.setCar(Main.getCarManager().getCarByName(this.combobox_cars.getSelectedItem().toString()));
		} else if (event.getSource() == this.button_calendar) {
			Main.getFrameManager().openFrameByID(3);
		} else if (event.getSource() == this.button_change_password) {
			Main.getMySQL().getUser().setUserPassword("123456");
		}
	}
}
