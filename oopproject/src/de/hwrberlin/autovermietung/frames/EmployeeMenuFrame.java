package de.hwrberlin.autovermietung.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.other.RoundedBorder;
import de.hwrberlin.autovermietung.users.Permission;

public class EmployeeMenuFrame extends MainFrame {

	private static final long serialVersionUID = -6463793055912864893L;

	private JButton button_customer, button_contracts, button_cars, button_calendar;
	
	private JLabel label_employee, label_border_main;
	
	public EmployeeMenuFrame() {
		super(101, Permission.USER, "Menü für Mitarbeiter", 675, 475);
		
		this.getContentPane().setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setBounds(200, 25, 250, 50);
		panel.setBackground(Color.WHITE);
		panel.setBorder(new RoundedBorder(new Color(33, 136, 143), 50));
		panel.setLayout(null);
		
		this.label_employee = new JLabel("Mitarbeiter");
		this.label_employee.setHorizontalAlignment(SwingConstants.CENTER);
		this.label_employee.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 18));
		this.label_employee.setBounds(0, 0, 250, 50);
		
		panel.add(this.label_employee);
		
		this.label_border_main = new JLabel();
		this.label_border_main.setBounds(45, 50, 575, 350);
		this.label_border_main.setBorder(new RoundedBorder(new Color(33, 136, 143), 20));
		this.label_border_main.setBackground(new Color(33, 136, 143));
		
		this.button_cars = new JButton("Fahrzeuge");
		this.button_cars.setBounds(70, 100, 250, 125);
		this.button_cars.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 18));
		this.button_cars.setIcon(new ImageIcon(this.getClass().getResource("/images/car64.png")));
		this.button_cars.setBorder(new RoundedBorder(new Color(33, 136, 143), 50));
		this.button_cars.setBackground(Color.WHITE);
		this.button_cars.setFocusable(false);
		this.button_cars.addActionListener(this);
		
		this.button_contracts = new JButton("Aufträge");
		this.button_contracts.setBounds(70, 255, 250, 125);
		this.button_contracts.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 18));
		this.button_contracts.setIcon(new ImageIcon(this.getClass().getResource("/images/contract64.png")));
		this.button_contracts.setBorder(new RoundedBorder(new Color(33, 136, 143), 50));
		this.button_contracts.setBackground(Color.WHITE);
		this.button_contracts.setFocusable(false);
		this.button_contracts.addActionListener(this);
		
		this.button_customer = new JButton("Kunden");
		this.button_customer.setBounds(345, 255, 250, 125);
		this.button_customer.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 18));
		this.button_customer.setIcon(new ImageIcon(this.getClass().getResource("/images/users64.png")));
		this.button_customer.setBorder(new RoundedBorder(new Color(33, 136, 143), 50));
		this.button_customer.setBackground(Color.WHITE);
		this.button_customer.setFocusable(false);
		this.button_customer.addActionListener(this);
		
		this.button_calendar = new JButton("Kalender");
		this.button_calendar.setBounds(345, 100, 250, 125);
		this.button_calendar.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 18));
		this.button_calendar.setIcon(new ImageIcon(this.getClass().getResource("/images/calendar64.png")));
		this.button_calendar.setBorder(new RoundedBorder(new Color(33, 136, 143), 50));
		this.button_calendar.setBackground(Color.WHITE);
		this.button_calendar.setFocusable(false);
		this.button_calendar.addActionListener(this);
		
		this.getContentPane().add(panel);
		this.getContentPane().add(this.label_border_main);
		this.getContentPane().add(this.button_cars);
		this.getContentPane().add(this.button_contracts);
		this.getContentPane().add(this.button_customer);
		this.getContentPane().add(this.button_calendar);
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
