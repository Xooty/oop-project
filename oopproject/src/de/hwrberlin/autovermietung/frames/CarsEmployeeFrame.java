package de.hwrberlin.autovermietung.frames;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.cars.Car;
import de.hwrberlin.autovermietung.mysql.MySQL;

public class CarsEmployeeFrame extends MainFrame {

	private static final long serialVersionUID = -7857686873009312944L;

	private JComboBox<Object> combobox_cars;
	
	private JButton button_back, button_car_detail_view;
	
//	private JTextArea textarea_list_contracts;
	
	public CarsEmployeeFrame() {
		super(107, "Fahrzeuge", 400, 350);
		
		List<String> cars = new ArrayList<String>();
		
		cars.add("Fahrzeug auswählen...");
		for (Car car : Main.getCarManager().getCars()) {
			cars.add(car.getCarBrand().getName() + " " + car.getModel());
		}
		
		this.combobox_cars = new JComboBox<Object>(cars.toArray());
		this.combobox_cars.setSelectedIndex(0);
		this.combobox_cars.setBounds(75, 50, 250, 25);
		
		this.button_back = new JButton("Zurück");
		this.button_back.setBounds(75, 200, 250, 50);
		this.button_back.addActionListener(this);
		
		this.button_car_detail_view = new JButton("Detailansicht");
		this.button_car_detail_view.setBounds(75, 100, 250, 25);
		this.button_car_detail_view.addActionListener(this);
		
		this.getContentPane().add(this.combobox_cars);
		this.getContentPane().add(this.button_back);
		this.getContentPane().add(this.button_car_detail_view);	
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.button_back) {
			Main.getFrameManager().openFrameByID(101);
		} else if (event.getSource() == this.button_car_detail_view) {
			if (this.combobox_cars.getSelectedIndex() == 0) return;
			CarFrame frame = (CarFrame) Main.getFrameManager().openFrameByID(102);
			frame.setCar(Main.getCarManager().getCarByName(this.combobox_cars.getSelectedItem().toString()));
		}
	}

	public Car searchCar(String plate_number) {
		MySQL sql = Main.getMySQL();
		Connection connection = sql.openConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("SELECT * FROM cars WHERE plate_number = ?");
			st.setString(1, plate_number);
			rs = st.executeQuery();
			
			if (rs.first()) {
				return new Car(rs.getInt("car_id"), connection);
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.closeRessources(rs, st, connection);
		}
		return null;
	}
}
