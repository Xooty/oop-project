package de.hwrberlin.autovermietung.frames;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.users.Permission;

public class MenuFrame extends MainFrame {

	private static final long serialVersionUID = -1617781986656844268L;

	private JButton button_users, button_customers, button_contracts, button_cars, button_calendar;
	
	public MenuFrame() {
		super(1, Permission.ADMIN, "Hauptmenü", 1000, 600);
		
		this.button_users = new JButton("Benutzer");
		this.button_users.setBounds(50, 50, 250, 100);
		this.button_users.addActionListener(this);
		
		this.button_customers = new JButton("Kunden");
		this.button_customers.setBounds(650, 50, 250, 100);
		this.button_customers.addActionListener(this);
		
		this.button_contracts = new JButton("Aufträge");
		this.button_contracts.setBounds(50, 350, 250, 100);
		this.button_contracts.addActionListener(this);
		
		this.button_cars = new JButton("Fahrzeuge");
		this.button_cars.setBounds(350, 200, 250, 100);
		this.button_cars.addActionListener(this);
		
		this.button_calendar = new JButton("Kalender");
		this.button_calendar.setBounds(650, 350, 250, 100);
		this.button_calendar.addActionListener(this);
		
		getContentPane().add(this.button_users);
		getContentPane().add(this.button_customers);
		getContentPane().add(this.button_contracts);
		getContentPane().add(this.button_cars);
		getContentPane().add(this.button_calendar);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.button_users) {
			Main.getFrameManager().openFrameByID(2);
		} else if (event.getSource() == this.button_customers) {
			Main.getFrameManager().openFrameByID(-1);
		} else if (event.getSource() == this.button_contracts) {
			Main.getFrameManager().openFrameByID(-1);
		}  else if (event.getSource() == this.button_cars) {
			Main.getFrameManager().openFrameByID(107);
		}  else if (event.getSource() == this.button_calendar) {
			Main.getFrameManager().openFrameByID(3);
		}
	}
}
