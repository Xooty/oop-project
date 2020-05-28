package de.hwrberlin.autovermietung.frames;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.customers.Customer;
import de.hwrberlin.autovermietung.users.Permission;

public class WorkerMenuFrame extends MainFrame {

	private static final long serialVersionUID = -6463793055912864893L;

	private JButton button_customer_add;
	private JButton button_car_back;
	
	public WorkerMenuFrame() {
		super(101, Permission.USER, "Menü für Mitarbeiter", 600, 600);
		
		this.button_customer_add = new JButton("Kunde hinzufügen");
		this.button_customer_add.setBounds(10, 10, 200, 25);
		this.button_customer_add.addActionListener(this);
		
		this.button_car_back = new JButton("Auto zurück");
		this.button_car_back.setBounds(350, 10, 200, 25);
		this.button_car_back.addActionListener(this);
		
		this.add(this.button_customer_add);
		this.add(this.button_car_back);
		this.add(new JLabel());
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.button_customer_add) {
			JTextField first_name = new JTextField();
			JTextField surname = new JTextField();
			JTextField age = new JTextField();
			JTextField id = new JTextField();
			Object[] message = { "Vorname", first_name, "Nachname", surname, "Alter", age, "ID", id };

	        JOptionPane pane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
	        pane.createDialog(null, "Kunde hinzufügen").setVisible(true);
	        
	        if (Integer.valueOf(pane.getValue().toString()) != JOptionPane.CANCEL_OPTION) {
	        	Customer customer = new Customer(first_name.getText(), surname.getText(), Integer.parseInt(age.getText()), id.getText());
		        
		        System.out.println(customer.getFullName() + " wurde hinzugefügt.");
	        }
		} else if (event.getSource() == this.button_car_back) {
			
		}
	}
}
