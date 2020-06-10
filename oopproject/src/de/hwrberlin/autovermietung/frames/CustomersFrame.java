package de.hwrberlin.autovermietung.frames;

import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import de.hwrberlin.autovermietung.customers.Customer;
import de.hwrberlin.autovermietung.users.Permission;

public class CustomersFrame extends MainFrame {

	private static final long serialVersionUID = -622964257621379860L;

	public CustomersFrame() {
		super(104, Permission.USER, "Kunden", 600, 400);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JTextField first_name = new JTextField();
		JTextField surname = new JTextField();
		JTextField birth_date = new JTextField("01.01.2020");
		Object[] message = { "Vorname", first_name, "Nachname", surname, "Geburtsdatum", birth_date };

        JOptionPane pane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        pane.createDialog(null, "Kunde hinzufügen").setVisible(true);
        
        if (Integer.valueOf(pane.getValue().toString()) != JOptionPane.CANCEL_OPTION || Integer.valueOf(pane.getValue().toString()) != JOptionPane.CLOSED_OPTION) {
        	Customer customer = null;
        	
        	SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        	format.format(new Date());
        	Date date = null;
        	try {
				date = format.parse(birth_date.getText());
			} catch (ParseException e) {
				e.printStackTrace();
			}
        	
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			customer = new Customer(0, first_name.getText(), surname.getText(), cal);
	        
	        System.out.println(customer.getFullName() + " wurde hinzugefügt. Der Kunde ist momentan " + (Calendar.getInstance().get(Calendar.YEAR) - customer.getBirthDate().get(Calendar.YEAR)) + " Jahre alt!");
        }
	}
}
