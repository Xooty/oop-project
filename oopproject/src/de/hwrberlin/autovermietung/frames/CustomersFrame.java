package de.hwrberlin.autovermietung.frames;

import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.customers.Customer;
import de.hwrberlin.autovermietung.users.Permission;

public class CustomersFrame extends MainFrame {

	private static final long serialVersionUID = -622964257621379860L;

	private JTextField textfield_customer_select;
	
	private JButton button_back, button_customer_select, button_customer_delete, button_customer_create;
	
	public CustomersFrame() {
		super(104, "Kunden verwalten", 600, 325);
		
		this.textfield_customer_select = new JTextField("Kundennamen eintragen...");
		this.textfield_customer_select.setBounds(25, 25, 250, 25);
		
		this.button_back = new JButton("Zurück");
		this.button_back.setBounds(175, 200, 250, 50);
		this.button_back.addActionListener(this);
		
		this.button_customer_select = new JButton("Kunden prüfen");
		this.button_customer_select.setBounds(25, 75, 250, 25);
		this.button_customer_select.addActionListener(this);
		
		this.button_customer_delete = new JButton("Kunden löschen");
		this.button_customer_delete.setBounds(25, 125, 250, 25);
		this.button_customer_delete.addActionListener(this);
		
		this.button_customer_create = new JButton("Kunden anlegen");
		this.button_customer_create.setBounds(350, 25, 225, 125);
		this.button_customer_create.addActionListener(this);
		
		this.getContentPane().add(this.textfield_customer_select);
		this.getContentPane().add(this.button_back);
		this.getContentPane().add(this.button_customer_select);
		this.getContentPane().add(this.button_customer_delete);
		this.getContentPane().add(this.button_customer_create);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.button_back) {
			if (Main.getMySQL().getUser().hasPermission(Permission.ADMIN)) {
				Main.getFrameManager().openFrameByID(1);
			} else {
				Main.getFrameManager().openFrameByID(101);
			}
			this.textfield_customer_select.setText("Kundennamen eintragen...");
		} else if (event.getSource() == this.button_customer_create) {
			this.openCustomerCreate();
		} else if (event.getSource() == this.button_customer_select) {
			if (this.textfield_customer_select.getText().equals("") || this.textfield_customer_select.getText().equals("Kundennamen eintragen...")) {
				JOptionPane.showMessageDialog(null, "Sie müssen einen Namen eingeben.");
			} else {
				String[] args = this.textfield_customer_select.getText().split(" ");
				if (args.length > 1) {
					if (Main.getCustomerManager().searchCustomer(args[0], args[1]) != null) {
						JOptionPane.showMessageDialog(null, "Dieser Kunde existiert bereits.");
					} else {
						JOptionPane.showMessageDialog(null, "Dieser Kunde existiert noch nicht.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Sie müssen einen Namen eingeben.");
				}
			}
		} else if (event.getSource() == this.button_customer_delete) {
			if (this.textfield_customer_select.getText().equals("") || this.textfield_customer_select.getText().equals("Kundennamen eintragen...")) {
				JOptionPane.showMessageDialog(null, "Sie müssen einen Namen eingeben.");
			} else {
				String[] args = this.textfield_customer_select.getText().split(" ");
				if (args.length > 1) {
					Customer customer = Main.getCustomerManager().searchCustomer(args[0], args[1]);
					if (customer == null) {
						JOptionPane.showMessageDialog(null, "Dieser Kunde existiert noch nicht.");
						return;
					}
					Main.getCustomerManager().deleteCustomer(customer);
					JOptionPane.showMessageDialog(null, "Der Kunde wurde erfolgreich gelöscht!");
				} else {
					JOptionPane.showMessageDialog(null, "Sie müssen einen Namen eingeben.");
				}
			}
		}
	}
	
	public void openCustomerCreate() {
		JComboBox<Object> salutation = new JComboBox<Object>(Arrays.asList("Herr", "Frau").toArray());
		JTextField first_name = new JTextField();
		JTextField surname = new JTextField();
		JTextField birth_date = new JTextField("01.01.2020");
		JTextField address = new JTextField();
		JTextField phone = new JTextField();
		JTextField email = new JTextField();
		Object[] message = { "Anrede", salutation, "Vorname", first_name, "Nachname", surname, "Geburtsdatum", birth_date, "Adresse", address, "Telefon", phone, "E-Mail", email };

        JOptionPane pane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        pane.createDialog(null, "Kunde hinzufügen").setVisible(true);
        
        if (Integer.valueOf(pane.getValue().toString()) == JOptionPane.CANCEL_OPTION || Integer.valueOf(pane.getValue().toString()) == JOptionPane.CLOSED_OPTION) {
        } else {
        	if (first_name.getText().equals("") || surname.getText().equals("") || birth_date.getText().equals("") || address.getText().equals("")) {
        		JOptionPane.showMessageDialog(null, "Sie müssen die Daten des Kunden eingeben!");
        		return;
        	}
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

			if (Calendar.getInstance().get(Calendar.YEAR) - cal.get(Calendar.YEAR) < 18) {
				JOptionPane.showMessageDialog(null, "Dieser Kunde hat noch nicht das 18. Lebensjahr erreicht.");
				return;
			}
			
			if (Main.getCustomerManager().searchCustomer(first_name.getText(), surname.getText()) != null) {
				JOptionPane.showMessageDialog(null, "Dieser Kunde existiert bereits.");
				return;
			}
			customer = new Customer(salutation.getSelectedItem().toString(), first_name.getText(), surname.getText(), birth_date.getText(), address.getText(), phone.getText(), email.getText());
			JOptionPane.showMessageDialog(null, salutation.getSelectedItem().toString() + " " + customer.getFullName() + " wurde hinzugefügt!");
        }
	}
}
