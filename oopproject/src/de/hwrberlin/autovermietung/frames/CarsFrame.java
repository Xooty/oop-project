package de.hwrberlin.autovermietung.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.cars.Car;
import de.hwrberlin.autovermietung.other.RoundedBorder;
import de.hwrberlin.autovermietung.users.Permission;

public class CarsFrame extends MainFrame {

	private static final long serialVersionUID = -7857686873009312944L;

	private JComboBox<Object> combobox_cars;
	
	private JButton button_detail_view;
	
//	private JTextArea textarea_list_contracts;
	
	private JLabel label_border_main, label_cars;
	
	public CarsFrame() {
		super(107, Permission.USER, "Fahrzeuge", 600, 600);
		
		this.getContentPane().setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setBounds(174, 53, 250, 50);
		panel.setBackground(Color.WHITE);
		panel.setBorder(new RoundedBorder(new Color(33, 136, 143), 50));
		panel.setLayout(null);
		
		this.label_cars = new JLabel("Fahrzeuge");
		this.label_cars.setHorizontalAlignment(SwingConstants.CENTER);
		this.label_cars.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 18));
		this.label_cars.setBounds(0, 0, 250, 50);
		
		panel.add(this.label_cars);
		
		this.label_border_main = new JLabel();
		this.label_border_main.setBounds(58, 78, 475, 415);
		this.label_border_main.setBorder(new RoundedBorder(new Color(33, 136, 143), 20));
		
		this.combobox_cars = new JComboBox<Object>();
//		this.combobox_cars.setSelectedIndex(0);
		this.combobox_cars.setBounds(185, 154, 250, 25);
		
		this.button_detail_view = new JButton("Detailansicht");
		this.button_detail_view.setBounds(185, 190, 250, 25);
		
		this.getContentPane().add(panel);
		this.getContentPane().add(this.label_border_main);
		this.getContentPane().add(this.combobox_cars);
		this.getContentPane().add(this.button_detail_view);
	}
	
	public void setup() {
		if (Main.getMySQL().getUser().hasPermission(Permission.ADMIN)) {
			
		} else {
			List<String> cars = new ArrayList<String>();
			
			cars.add("Fahrzeug auswählen...");
			for (Car car : Main.getCarManager().getCars()) {
				cars.add(car.getCarBrand().getName() + " " + car.getModel());
			}
			
			this.combobox_cars = new JComboBox<Object>(cars.toArray());
			this.combobox_cars.setSelectedIndex(0);
			this.combobox_cars.setBounds(25, 25, 250, 25);
			
			this.button_detail_view = new JButton("Detailansicht");
			this.setBounds(100, 50, 250, 25);
			
			this.getContentPane().add(this.combobox_cars);
			this.getContentPane().add(this.button_detail_view);
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
	}
}
