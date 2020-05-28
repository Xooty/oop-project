package de.hwrberlin.autovermietung.frames;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.cars.Car;

public class MenuFrame extends MainFrame {

	private static final long serialVersionUID = -1617781986656844268L;

	private JComboBox<Object> combobox_cars;
	
	private JButton button_select, button_calendar;
	
	public MenuFrame() {
		super(1, "Hauptmenü", 600, 600);
		
		List<String> cars = new ArrayList<String>();
		
		for (Car car : Main.getCarManager().getCars()) {
			cars.add(car.getCarBrand().getName() + " " + car.getModel());
		}
		
		this.combobox_cars = new JComboBox<Object>(cars.toArray());
		this.combobox_cars.setSelectedIndex(0);
		this.combobox_cars.setBounds(200, 200, 250, 25);

		this.button_select = new JButton("Auswählen");
		this.button_select.setBounds(300, 300, 250, 25);
		this.button_select.addActionListener(this);
		
		this.button_calendar = new JButton("Kalendar");
		this.button_calendar.setBounds(300, 500, 250, 25);
		this.button_calendar.addActionListener(this);
				
		this.add(this.combobox_cars);
		this.add(this.button_select);
		this.add(this.button_calendar);
		
		this.add(new JLabel());
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.button_select) {
			CarFrame frame = (CarFrame) Main.getFrameManager().openFrameByID(3);
			frame.setCar(Main.getCarManager().getCarByName(this.combobox_cars.getSelectedItem().toString()));
		} else if (event.getSource() == this.button_calendar) {
			Main.getFrameManager().openFrameByID(4);
		}
	}
}
