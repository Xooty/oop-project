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

public class MenuAdminFrame extends MainFrame {

	private static final long serialVersionUID = -1617781986656844268L;

	private JButton button_users, button_customers, button_contracts, button_cars, button_calendar;
	
	private JLabel label_admin, label_border_main;
	
	public MenuAdminFrame() {
		super(1, "Hauptmenü", 1000, 600);
		
		this.getContentPane().setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setBounds(350, 11, 250, 50);
		panel.setBackground(Color.WHITE);
		panel.setBorder(new RoundedBorder(new Color(33, 136, 143), 50));
		panel.setLayout(null);
		
		this.label_admin = new JLabel("Admin");
		this.label_admin.setHorizontalAlignment(SwingConstants.CENTER);
		this.label_admin.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 18));
		this.label_admin.setBounds(0, 0, 250, 50);
		
		panel.add(this.label_admin);
		
		this.label_border_main = new JLabel();
		this.label_border_main.setBounds(34, 36, 897, 462);
		this.label_border_main.setBorder(new RoundedBorder(new Color(33, 136, 143), 20));
		this.label_border_main.setBackground(new Color(33, 136, 143));
		
		this.button_users = new JButton("Benutzer");
		this.button_users.setBounds(50, 75, 250, 100);
		this.button_users.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 18));
		this.button_users.setIcon(new ImageIcon(this.getClass().getResource("/images/users64.png")));
		this.button_users.setBorder(new RoundedBorder(new Color(33, 136, 143), 50));
		this.button_users.setBackground(Color.WHITE);
		this.button_users.setFocusable(false);
		this.button_users.addActionListener(this);
		
		this.button_customers = new JButton("Kunden");
		this.button_customers.setBounds(650, 75, 250, 100);
		this.button_customers.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 18));
		this.button_customers.setIcon(new ImageIcon(this.getClass().getResource("/images/users64.png")));
		this.button_customers.setBorder(new RoundedBorder(new Color(33, 136, 143), 50));
		this.button_customers.setBackground(Color.WHITE);
		this.button_customers.setFocusable(false);
		this.button_customers.addActionListener(this);
		
		this.button_contracts = new JButton("Aufträge");
		this.button_contracts.setBounds(50, 350, 250, 100);
		this.button_contracts.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 18));
		this.button_contracts.setIcon(new ImageIcon(this.getClass().getResource("/images/contract64.png")));
		this.button_contracts.setBorder(new RoundedBorder(new Color(33, 136, 143), 50));
		this.button_contracts.setBackground(Color.WHITE);
		this.button_contracts.setFocusable(false);
		this.button_contracts.addActionListener(this);
		
		this.button_cars = new JButton("Fahrzeuge");
		this.button_cars.setBounds(350, 200, 250, 100);
		this.button_cars.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 18));
		this.button_cars.setIcon(new ImageIcon(this.getClass().getResource("/images/car64.png")));
		this.button_cars.setBorder(new RoundedBorder(new Color(33, 136, 143), 50));
		this.button_cars.setBackground(Color.WHITE);
		this.button_cars.setFocusable(false);
		this.button_cars.addActionListener(this);
		
		this.button_calendar = new JButton("Kalender");
		this.button_calendar.setBounds(650, 350, 250, 100);
		this.button_calendar.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 18));
		this.button_calendar.setIcon(new ImageIcon(this.getClass().getResource("/images/calendar64.png")));
		this.button_calendar.setBorder(new RoundedBorder(new Color(33, 136, 143), 50));
		this.button_calendar.setBackground(Color.WHITE);
		this.button_calendar.setFocusable(false);
		this.button_calendar.addActionListener(this);
		
		this.getContentPane().add(panel);
		this.getContentPane().add(this.label_border_main);
		this.getContentPane().add(this.button_users);
		this.getContentPane().add(this.button_customers);
		this.getContentPane().add(this.button_contracts);
		this.getContentPane().add(this.button_cars);
		this.getContentPane().add(this.button_calendar);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.button_users) {
			Main.getFrameManager().openFrameByID(2);
		} else if (event.getSource() == this.button_customers) {
			Main.getFrameManager().openFrameByID(104);
		} else if (event.getSource() == this.button_contracts) {
			Main.getFrameManager().openFrameByID(105);
		}  else if (event.getSource() == this.button_cars) {
			Main.getFrameManager().openFrameByID(4);
		}  else if (event.getSource() == this.button_calendar) {
			Main.getFrameManager().openFrameByID(106);
		}
	}
}
