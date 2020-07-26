package de.hwrberlin.autovermietung.frames;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.cars.Car;
import de.hwrberlin.autovermietung.cars.CarBrand;
import de.hwrberlin.autovermietung.cars.CarClass;
import de.hwrberlin.autovermietung.cars.FuelType;

public class CarsAdminFrame extends MainFrame {

	private static final long serialVersionUID = -7857686873009312944L;

	private JComboBox<Object> combobox_cars;
	
	private JButton button_back, button_car_create, button_car_delete, button_car_detail_view;
	
//	private JTextArea textarea_list_contracts;
	
	public CarsAdminFrame() {
		super(4, "Fahrzeuge", 600, 400);
		
		List<String> cars = new ArrayList<String>();
		
		cars.add("Fahrzeug auswählen...");
		for (Car car : Main.getCarManager().getCars()) {
			cars.add(car.getCarBrand().getName() + " " + car.getModel());
		}
		
		this.combobox_cars = new JComboBox<Object>(cars.toArray());
		this.combobox_cars.setSelectedIndex(0);
		this.combobox_cars.setBounds(300, 50, 250, 25);
		
		this.button_back = new JButton("Zurück");
		this.button_back.setBounds(168, 224, 250, 75);
		this.button_back.addActionListener(this);
		
		this.button_car_detail_view = new JButton("Detailansicht");
		this.button_car_detail_view.setBounds(300, 100, 250, 25);
		this.button_car_detail_view.addActionListener(this);
		
		this.button_car_create = new JButton("Neues Fahrzeug hinzufügen");
		this.button_car_create.setBounds(10, 50, 260, 125);
		this.button_car_create.addActionListener(this);
			
		this.button_car_delete = new JButton("Fahrzeug löschen");
		this.button_car_delete.setBounds(300, 150, 250, 25);
		this.button_car_delete.addActionListener(this);
		
		this.getContentPane().add(this.combobox_cars);
		this.getContentPane().add(this.button_back);
		this.getContentPane().add(this.button_car_detail_view);	
		this.getContentPane().add(this.button_car_create);
		this.getContentPane().add(this.button_car_delete);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.button_back) {
			Main.getFrameManager().openFrameByID(1);
		} else if (event.getSource() == this.button_car_create) {
			this.openCarCreate();
		} else if (event.getSource() == this.button_car_delete) {
			if (this.combobox_cars.getSelectedIndex() == 0) return;
			if (Main.getCarManager().deleteCar(Main.getCarManager().getCarByName(this.combobox_cars.getSelectedItem().toString()))) {
				JOptionPane.showMessageDialog(null, "Das Fahrzeug wurde erfolgreich gelöscht.");
				
				this.updateCarList();
			} else {
				JOptionPane.showMessageDialog(null, "Das Fahrzeug konnte nicht gelöscht werden.");
			}
		} else if (event.getSource() == this.button_car_detail_view) {
			if (this.combobox_cars.getSelectedIndex() == 0) return;
			CarFrame frame = (CarFrame) Main.getFrameManager().openFrameByID(102);
			frame.setCar(Main.getCarManager().getCarByName(this.combobox_cars.getSelectedItem().toString()));
		}
	}
	
	public void openCarCreate() {
		JComboBox<Object> car_brand = new JComboBox<Object>(CarBrand.values());
		
		List<String> classes = new ArrayList<String>();
		
		for (CarClass c : Main.getCarManager().getCarClasses()) {
			classes.add(c.getClassName() + ": " + c.getPrice() + "€");
		}
		
		JComboBox<Object> car_class = new JComboBox<Object>(classes.toArray());
		JTextField plate_number = new JTextField();
		JTextField car_model = new JTextField();
		JComboBox<Object> fuel_type = new JComboBox<Object>(FuelType.values());
		JTextField power = new JTextField();
		JTextField torque = new JTextField();
		JTextField price = new JTextField();
		JTextField mileage = new JTextField();
		JTextField topspeed = new JTextField();
		Object[] message = { "Kennzeichen", plate_number, "Autoklasse", car_class, "Automarke", car_brand, "Modell", car_model, "Kraftstoff", fuel_type, "Leistung", power, "Drehmoment", torque, "Preis", price, "Kilometerstand", mileage, "Höchstgeschwindigkeit", topspeed };

        JOptionPane pane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        pane.createDialog(null, "Fahrzeug hinzufügen").setVisible(true);
        
        if (Integer.valueOf(pane.getValue().toString()) == JOptionPane.CANCEL_OPTION || Integer.valueOf(pane.getValue().toString()) == JOptionPane.NO_OPTION) {
        } else {
			if (Main.getCarManager().searchCar(plate_number.getText()) != null) {
				JOptionPane.showMessageDialog(null, "Dieses Fahrzeug existiert bereits.");
				return;
			}
			
			Car car = new Car(plate_number.getText(), Main.getCarManager().getCarClassByString(car_class.getSelectedItem().toString()).getClassID(), car_brand.getSelectedItem().toString(), car_model.getText(), fuel_type.getSelectedItem().toString(), Integer.valueOf(power.getText()), Integer.valueOf(torque.getText()), Double.valueOf(price.getText()), Integer.valueOf(mileage.getText()), Integer.valueOf(topspeed.getText()));
			Main.getCarManager().addCar(car);
			JOptionPane.showMessageDialog(null, "Fahrzeug wurde hinzugefügt.");
        }
	}
	
	public void updateCarList() {
		this.combobox_cars.removeAllItems();
		
		this.combobox_cars.addItem("Fahrzeug auswählen...");
		for (Car car : Main.getCarManager().getCars()) {
			this.combobox_cars.addItem(car.getCarBrand().getName() + " " + car.getModel());
		}
		
		this.combobox_cars.setSelectedIndex(0);
	}
}
