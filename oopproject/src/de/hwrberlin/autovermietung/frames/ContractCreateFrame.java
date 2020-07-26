package de.hwrberlin.autovermietung.frames;

import java.awt.event.ActionEvent;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.cars.Car;
import de.hwrberlin.autovermietung.cars.CarClass;
import de.hwrberlin.autovermietung.contract.Contract;
import de.hwrberlin.autovermietung.customers.Customer;
import de.hwrberlin.autovermietung.exceptions.CarTakenException;
import de.hwrberlin.autovermietung.other.Util;
import de.hwrberlin.autovermietung.users.Permission;

public class ContractCreateFrame extends MainFrame {

	private static final long serialVersionUID = -344819132138234335L;

	private JLabel label_contract_start, label_contract_end;
	
	private JTextField textfield_customer_name, textfield_plate_number, textfield_contract_start, textfield_contract_end;

	private JButton button_back, button_contract_search_customer, button_contract_search_car, button_contract_create, button_contract_price, button_calendar;
	
	private JTextArea textarea_customer_info, textarea_car_info;
	
	private Customer customer;
	
	private Car car;
	
	public ContractCreateFrame() {
		super(108, "Auftrag anlegen", 700, 850);

		this.textfield_customer_name = new JTextField("Kundennamen eintragen...");
		this.textfield_customer_name.setBounds(50, 50, 250, 25);
		
		this.textfield_plate_number = new JTextField("Kennzeichen eintragen...");
		this.textfield_plate_number.setBounds(50, 300, 250, 25);

		this.button_back = new JButton("Zurück");
		this.button_back.setBounds(50, 700, 250, 50);
		this.button_back.addActionListener(this);
		
		this.button_contract_price = new JButton("Preis: 0€");
		this.button_contract_price.setBounds(50, 550, 200, 50);
		this.button_contract_price.addActionListener(this);

		this.button_contract_search_customer = new JButton("Kunden suchen");
		this.button_contract_search_customer.setBounds(50, 75, 250, 50);
		this.button_contract_search_customer.addActionListener(this);
		
		this.button_contract_search_car = new JButton("Fahrzeug suchen");
		this.button_contract_search_car.setBounds(50, 325, 250, 50);
		this.button_contract_search_car.addActionListener(this);
		
		this.button_contract_create = new JButton("Auftrag erstellen");
		this.button_contract_create.setBounds(400, 700, 250, 50);
		this.button_contract_create.addActionListener(this);
		
		this.button_calendar = new JButton("Kalender (Keine Funktion)");
		this.button_calendar.setBounds(460, 563, 190, 37);
		this.button_calendar.addActionListener(this);
		
		this.textarea_car_info = new JTextArea();
		this.textarea_car_info.setBounds(50, 400, 600, 100);
		
		this.textarea_customer_info = new JTextArea();
		this.textarea_customer_info.setBounds(50, 150, 600, 100);
		
		JScrollPane scroll_pane_1 = new JScrollPane(this.textarea_customer_info);
		scroll_pane_1.setBounds(50, 150, 600, 100);
		
		JScrollPane scroll_pane_2 = new JScrollPane(this.textarea_car_info);
		scroll_pane_2.setBounds(50, 400, 600, 100);
		
		this.label_contract_start = new JLabel("Auftragsbeginn");
		this.label_contract_start.setBounds(275, 550, 100, 25);
		
		this.label_contract_end = new JLabel("Auftragsende");
		this.label_contract_end.setBounds(275, 585, 100, 25);
		
		this.textfield_contract_start = new JTextField("01.01.2020");
		this.textfield_contract_start.setBounds(375, 550, 75, 25);
		
		this.textfield_contract_end = new JTextField("31.12.2020");
		this.textfield_contract_end.setBounds(375, 585, 75, 25);

		this.getContentPane().add(this.textfield_customer_name);
		this.getContentPane().add(this.textfield_plate_number);
		this.getContentPane().add(this.button_back);
		this.getContentPane().add(this.button_contract_search_customer);
		this.getContentPane().add(this.button_contract_search_car);
		this.getContentPane().add(this.button_contract_create);
		this.getContentPane().add(this.button_contract_price);
		this.getContentPane().add(this.button_calendar);
		this.getContentPane().add(this.textfield_contract_start);
		this.getContentPane().add(this.textfield_contract_end);
		this.getContentPane().add(this.label_contract_start);
		this.getContentPane().add(this.label_contract_end);
		this.getContentPane().add(scroll_pane_1);
		this.getContentPane().add(scroll_pane_2);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.button_back) {
			Main.getFrameManager().openFrameByID(105);
			this.resetTexts();
		} else if (event.getSource() == this.button_contract_search_customer) {
			if (this.textfield_customer_name.getText().equals("") || this.textfield_customer_name.getText().equals("Kundennamen eintragen...")) {
				JOptionPane.showMessageDialog(null, "Sie müssen einen Namen eingeben.");
			} else {
				String[] args = this.textfield_customer_name.getText().split(" ");
				if (args.length > 1) {
					this.customer = Main.getCustomerManager().searchCustomer(args[0], args[1]);
					if (this.customer != null) {
						String s = "Kunden-ID: " + this.customer.getCustomerID() + "\n"
								+ "Kundenname: " + this.customer.getSalutation() + " " + this.customer.getFullName() + "\n"
								+ "Geburtsdatum: " + Util.convertDateLongToString(this.customer.getBirthDate().getTimeInMillis()) + "\n"
								+ "Adresse: " + this.customer.getAddress() + "\n"
								+ "Telefon: " + this.customer.getPhone() + "\n"
								+ "E-Mail: " + this.customer.getEmail();
						this.textarea_customer_info.setText(s);
					} else {
						JOptionPane.showMessageDialog(null, "Dieser Kunde existiert noch nicht.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Sie müssen einen Namen eingeben.");
				}
			}
		} else if (event.getSource() == this.button_contract_search_car) {
			if (this.textfield_plate_number.getText().equals("") || this.textfield_plate_number.getText().equals("Kennzeichen eintragen...")) {
				JOptionPane.showMessageDialog(null, "Sie müssen ein Kennzeichen eingeben.");
			} else {
				this.car = Main.getCarManager().searchCar(this.textfield_plate_number.getText());
				if (this.car != null) {
					this.car.loadContracts();
					String s = "Klasse: " + Main.getCarManager().getCarClassByID(this.car.getCarClass()).getClassName() + " - " + Main.getCarManager().getCarClassByID(this.car.getCarClass()).getPrice() + "€\n"
							+ "Automarke: " + this.car.getCarBrand().getName() + "\n"
							+ "Modell: " + this.car.getModel() + "\n"
							+ "Kraftstoff: " + this.car.getFuelType().toString() + "\n"
							+ "Leistung: " + this.car.getHorsepower() + "\n"
							+ "Drehmoment: " + this.car.getTorque() + "\n"
							+ "Kilometerstand: " + this.car.getMileage() + "\n"
							+ "Höchstgeschwindigkeit: " + this.car.getTopspeed();
					this.textarea_car_info.setText(s);
				} else {
					JOptionPane.showMessageDialog(null, "Dieses Fahrzeug existiert nicht.");
				}
			}
		} else if (event.getSource() == this.button_contract_create) {
			long contract_start = Util.convertStringToCal(textfield_contract_start.getText()).getTimeInMillis();
			long contract_end = Util.convertStringToCal(textfield_contract_end.getText()).getTimeInMillis();
			
			if (this.customer == null) {
				JOptionPane.showMessageDialog(null, "Sie haben keinen Kunden ausgewählt!");
				return;
			} 
			if (this.car == null) {
				JOptionPane.showMessageDialog(null, "Sie haben kein Fahrzeug ausgewählt!");
				return;
			}
			if (contract_start < System.currentTimeMillis() || contract_end < System.currentTimeMillis()) {
				JOptionPane.showMessageDialog(null, "Der Auftrag kann nicht zurückliegen!");
				return;
			}
			if (contract_start > contract_end) {
				JOptionPane.showMessageDialog(null, "Der Start kann nicht nach dem Ende sein!");
				return;
			}
			for (Contract c : car.getContracts()) {
				if (c.getContractEnd() > contract_end || c.getContractEnd() > contract_start) {
					try {
						JOptionPane.showMessageDialog(null, "Das Fahrzeug wurde in diesem Zeitraum reserviert!");
						throw new CarTakenException("Das Fahrzeug wurde in diesem Zeitraum reserviert!");
					} catch (CarTakenException e) {
						e.printStackTrace();
					}
					return;
				}
			}
			new Contract(Main.getMySQL().getUser().getUserID(), customer.getCustomerID(), car.getCarID(), contract_start, contract_end, car.getMileage());
			JOptionPane.showMessageDialog(null, "Der Auftrag wurde erstellt!");
			
			if (Main.getMySQL().getUser().hasPermission(Permission.ADMIN)) {
				Main.getFrameManager().openFrameByID(1);
			} else {
				Main.getFrameManager().openFrameByID(101);
			}
			this.resetTexts();
		} else if (event.getSource() == this.button_contract_price) {
			Calendar start = Util.convertStringToCal(this.textfield_contract_start.getText());
			Calendar end = Util.convertStringToCal(this.textfield_contract_end.getText());
		    	
		    int time = end.get(Calendar.DAY_OF_YEAR) - start.get(Calendar.DAY_OF_YEAR);
		    
		    if (this.car != null) {
		    	CarClass car_class = Main.getCarManager().getCarClassByID(this.car.getCarClass());
		    	
		    	this.button_contract_price.setText("Preis: " + (car_class.getPrice() * time) + "€");
		    } else {
		    	JOptionPane.showMessageDialog(null, "Sie müssen ein Fahrzeug auswählen!");
				return;
		   }
		} else if (event.getSource() == this.button_calendar) {
			
		}
	}
	
	public void resetTexts() {
		this.customer = null;
		this.car = null;
		this.textfield_customer_name.setText("Kundennamen eintragen...");
		this.textfield_plate_number.setText("Kennzeichen eintragen...");
		this.textarea_customer_info.setText("");
		this.textarea_car_info.setText("");
		this.textfield_contract_start.setText("01.01.2020");
		this.textfield_contract_end.setText("31.12.2020");
		this.button_contract_price.setText("Preis: 0€");
	}
}