package de.hwrberlin.autovermietung.frames;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.cars.Car;
import de.hwrberlin.autovermietung.contract.Contract;
import de.hwrberlin.autovermietung.customers.Customer;
import de.hwrberlin.autovermietung.other.Util;

public class ContractSearchFrame extends MainFrame {

	private static final long serialVersionUID = -344819132138234335L;

	private JTextField textfield_customer_name, textfield_plate_number;

	private JButton button_back, button_contract_search_customer, button_contract_search_car;
	
	private JTextArea textarea_contract_info;

	public ContractSearchFrame() {
		super(103, "Auftrag suchen", 700, 400);

		this.textfield_customer_name = new JTextField("Kundennamen eintragen...");
		this.textfield_customer_name.setBounds(50, 50, 250, 25);
		
		this.textfield_plate_number = new JTextField("Kennzeichen eintragen...");
		this.textfield_plate_number.setBounds(400, 50, 250, 25);

		this.button_back = new JButton("Zurück");
		this.button_back.setBounds(225, 285, 250, 50);
		this.button_back.addActionListener(this);

		this.button_contract_search_customer = new JButton("Auftrag durch Kunden suchen");
		this.button_contract_search_customer.setBounds(50, 75, 250, 50);
		this.button_contract_search_customer.addActionListener(this);
		
		this.button_contract_search_car = new JButton("Auftrag durch Fahrzeug suchen");
		this.button_contract_search_car.setBounds(400, 75, 250, 50);
		this.button_contract_search_car.addActionListener(this);
		
		this.textarea_contract_info = new JTextArea();
		this.textarea_contract_info.setBounds(50, 150, 600, 100);
		
		JScrollPane scroll_pane = new JScrollPane(this.textarea_contract_info);
		scroll_pane.setBounds(50, 150, 600, 100);

		this.getContentPane().add(this.textfield_customer_name);
		this.getContentPane().add(this.textfield_plate_number);
		this.getContentPane().add(this.button_back);
		this.getContentPane().add(this.button_contract_search_customer);
		this.getContentPane().add(this.button_contract_search_car);
//		this.getContentPane().add(this.textarea_contract_info);
		this.getContentPane().add(scroll_pane);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.button_back) {
			Main.getFrameManager().openFrameByID(105);
			this.resetTexts();
		} else if (event.getSource() == this.button_contract_search_customer) {
			if (this.textfield_customer_name.getText().equals("") || this.textfield_customer_name.getText().equals("Kundennamen eintragen...")) {
				JOptionPane.showMessageDialog(null, "Sie müssen einen Kundennamen eingeben.");
			} else {
				String[] args = this.textfield_customer_name.getText().split(" ");
				if (args.length > 1) {
					Customer customer = Main.getCustomerManager().searchCustomer(args[0], args[1]);
					if (customer == null) {
						JOptionPane.showMessageDialog(null, "Dieser Kunde existiert nicht.");
						return;
					}
					customer.loadContracts();
					
					if (customer.getContracts().size() == 0) {
						this.textarea_contract_info.setText("Keine Aufträge für diesen Kunden vorhanden!");
						return;
					}
					
					StringBuilder sb = new StringBuilder();
					
					int contracts = customer.getContracts().size();
					
					for (Contract contract : customer.getContracts()) {
						sb.append("Auftrags-ID: " + contract.getContractID() + "\n"
								+ "Kunde: " + customer.getSalutation() + " " + customer.getFullName() + "\n"
								+ "Mitarbeiter: " + contract.getEmployeeID() + "\n"
								+ "Auto: " + Main.getCarManager().getCarByID(contract.getCarID()).getCarBrand().getName() + " " + Main.getCarManager().getCarByID(contract.getCarID()).getModel() + "\n"
								+ "Auftragsstart: " + Util.convertDateLongToString(contract.getContractStart()) + "\n"
								+ "Auftragsende: " + Util.convertDateLongToString(contract.getContractEnd()) + (contracts > 1 ? "\n"
								+ "\n" : ""));
						contracts--;
					}
					this.textarea_contract_info.setText(sb.toString().trim());
				} else {
					JOptionPane.showMessageDialog(null, "Sie müssen einen Kundennamen eingeben.");
				}
			}
		} else if (event.getSource() == this.button_contract_search_car) {
			if (this.textfield_plate_number.getText().equals("") || this.textfield_plate_number.getText().equals("Kennzeichen eintragen...")) {
				JOptionPane.showMessageDialog(null, "Sie müssen ein Kennzeichen eingeben.");
			} else {
				Car car = Main.getCarManager().searchCar(this.textfield_plate_number.getText());
				if (car == null) {
					JOptionPane.showMessageDialog(null, "Dieses Fahrzeug existiert nicht.");
					return;
				}
				car.loadContracts();
				
				if (car.getContracts().size() == 0) {
					this.textarea_contract_info.setText("Keine Aufträge für dieses Fahrzeug vorhanden!");
					return;
				}
				
				StringBuilder sb = new StringBuilder();
				
				int contracts = car.getContracts().size();
				
				for (Contract contract : car.getContracts()) {
					Customer customer = Main.getCustomerManager().searchCustomer(contract.getCustomerID());
					sb.append("Auftrags-ID: " + contract.getContractID() + "\n"
							+ "Kunde: " + customer.getSalutation() + " " + customer.getFullName() + "\n"
							+ "Mitarbeiter: " + contract.getEmployeeID() + "\n"
							+ "Auto: " + car.getCarBrand().getName() + " " + car.getModel() + "\n"
							+ "Auftragsstart: " + Util.convertDateLongToString(contract.getContractStart()) + "\n"
							+ "Auftragsende: " + Util.convertDateLongToString(contract.getContractEnd()) + (contracts > 1 ? "\n"
							+ "\n" : ""));
					contracts--;
				}
				this.textarea_contract_info.setText(sb.toString().trim());
			}
		}
	}
	
	public void resetTexts() {
		this.textfield_customer_name.setText("Kundennamen eintragen...");
		this.textfield_plate_number.setText("Kennzeichen eintragen...");
		this.textarea_contract_info.setText("");
	}
}
