package de.hwrberlin.autovermietung.frames;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.cars.Car;
import de.hwrberlin.autovermietung.contract.Contract;
import de.hwrberlin.autovermietung.users.Permission;

public class CarReturnFrame extends MainFrame {

	private static final long serialVersionUID = -344819132138234335L;

	private JTextField textfield_plate_number, textfield_mileage_end;
	
	private JTextArea textarea_damages;
	
	private JButton button_back, button_car_return;
	
	public CarReturnFrame() {
		super(109, "Aufträge", 700, 300);

		this.textfield_plate_number = new JTextField("Kennzeichen eintragen...");
		this.textfield_plate_number.setBounds(50, 50, 250, 25);
		
		this.textfield_mileage_end = new JTextField("Kilometerstand eintragen...");
		this.textfield_mileage_end.setBounds(50, 100, 250, 25);
		
		this.textarea_damages = new JTextArea("Schäden notieren...");
		this.textarea_damages.setBounds(347, 11, 278, 118);
		
		this.button_back = new JButton("Zurück");
		this.button_back.setBounds(375, 150, 250, 50);
		this.button_back.addActionListener(this);
		
		this.button_car_return = new JButton("Auftrag abschließen");
		this.button_car_return.setBounds(50, 150, 250, 50);
		this.button_car_return.addActionListener(this);
		
		this.getContentPane().add(this.textfield_plate_number);
		this.getContentPane().add(this.textfield_mileage_end);
		this.getContentPane().add(this.textarea_damages);
		this.getContentPane().add(this.button_back);
		this.getContentPane().add(this.button_car_return);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.button_back) {
			Main.getFrameManager().openFrameByID(105);			
		} else if (event.getSource() == this.button_car_return) {
			if (this.textfield_plate_number.getText().equals("") || this.textfield_plate_number.getText().equals("Kennzeichen eintragen...")) {
				JOptionPane.showMessageDialog(null, "Sie müssen ein Kennzeichen eingeben.");
			} else if (this.textfield_mileage_end.getText().equals("") || this.textfield_mileage_end.getText().equals("Kilometerstand eintragen...")) {
				JOptionPane.showMessageDialog(null, "Sie müssen einen Kilometerstand eingeben.");
			} else {
				Car car = Main.getCarManager().searchCar(textfield_plate_number.getText());
				if (car != null) {
					int mileage = 0;
					try {
						mileage = Integer.valueOf(this.textfield_mileage_end.getText());
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
					
					if (car.getMileage() > mileage) {
						JOptionPane.showMessageDialog(null, "Sie müssen einen Kilometerstand eingeben, der größer als der bisherige ist.");
						return;
					}
					car.loadContracts();
					
					Contract contract = null;
					
					for (Contract c : car.getContracts()) {
						if (!c.isCarReturned() && System.currentTimeMillis() < c.getContractStart()) {
							contract = c;
							break;
						}
					}
					if (contract == null) {
						JOptionPane.showMessageDialog(null, "Der Auftrag hat noch nicht begonnen oder existiert nicht!");
						return;
					}
					contract.setCarReturned(mileage, true);
					car.setMileage(mileage);
					JOptionPane.showMessageDialog(null, "Das Fahrzeug wurde erfolgreich wieder zurückgegeben!");
					
					if (Main.getMySQL().getUser().hasPermission(Permission.ADMIN)) {
						Main.getFrameManager().openFrameByID(1);
					} else {
						Main.getFrameManager().openFrameByID(101);
					}
					this.resetTexts();
				} else {
					JOptionPane.showMessageDialog(null, "Dieses Fahrzeug existiert nicht!");
				}
			}
		}
	}
	
	public void resetTexts() {
		this.textfield_plate_number.setText("Kennzeichen eintragen...");
		this.textfield_mileage_end.setText("Kilometerstand eintragen...");
		this.textarea_damages.setText("Schäden notieren...");
	}
}
