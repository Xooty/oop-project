package de.hwrberlin.autovermietung.frames;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.users.Permission;

public class WorkerMenuFrame extends MainFrame {

	private static final long serialVersionUID = -6463793055912864893L;

	private JButton button_customer, button_contracts, button_cars, button_calendar;
	
	public WorkerMenuFrame() {
		super(101, Permission.USER, "Menü für Mitarbeiter", 600, 400);
		
		this.button_cars = new JButton("Fahrzeuge");
		this.button_cars.setBounds(25, 25, 250, 125);
		this.button_cars.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 16));
		this.button_cars.setIcon(new ImageIcon("res/car64.png"));
		this.button_cars.addActionListener(this);
		
		this.button_contracts = new JButton("Aufträge");
		this.button_contracts.setBounds(25, 175, 250, 125);
		this.button_contracts.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 16));
		this.button_contracts.setIcon(new ImageIcon("res/contract64.png"));
		this.button_contracts.addActionListener(this);
		
		this.button_customer = new JButton("Kunden");
		this.button_customer.setBounds(300, 175, 250, 125);
		this.button_customer.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 16));
		this.button_customer.setIcon(new ImageIcon("res/users64.png"));
		this.button_customer.addActionListener(this);
		
		this.button_calendar = new JButton("Kalender");
		this.button_calendar.setBounds(300, 25, 250, 125);
		this.button_calendar.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 16));
		this.button_calendar.setIcon(new ImageIcon("res/calendar64.png"));
		this.button_calendar.addActionListener(this);
		
		this.add(this.button_cars);
		this.add(this.button_contracts);
		this.add(this.button_customer);
		this.add(this.button_calendar);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.button_cars) {
			CarsFrame cars_frame = (CarsFrame) Main.getFrameManager().openFrameByID(107);
			cars_frame.setup();
		} else if (event.getSource() == this.button_customer) {
			Main.getFrameManager().openFrameByID(104);
		} else if (event.getSource() == this.button_contracts) {
			Main.getFrameManager().openFrameByID(105);
		} else if (event.getSource() == this.button_calendar) {
			Main.getFrameManager().openFrameByID(106);
		}
	}
}
