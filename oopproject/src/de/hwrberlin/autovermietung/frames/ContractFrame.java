package de.hwrberlin.autovermietung.frames;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.users.Permission;

public class ContractFrame extends MainFrame {

	private static final long serialVersionUID = -344819132138234335L;

	private JButton button_back, button_contract_search, button_contract_create, button_car_return;
	
	public ContractFrame() {
		super(105, "Aufträge verwalten", 700, 300);
		
		this.button_back = new JButton("Zurück");
		this.button_back.setBounds(375, 150, 250, 50);
		this.button_back.addActionListener(this);
		
		this.button_contract_search = new JButton("Auftrag suchen");
		this.button_contract_search.setBounds(50, 50, 250, 50);
		this.button_contract_search.addActionListener(this);
		
		this.button_contract_create = new JButton("Auftrag anlegen");
		this.button_contract_create.setBounds(375, 50, 250, 50);
		this.button_contract_create.addActionListener(this);
		
		this.button_car_return = new JButton("Fahrzeugrücknahme");
		this.button_car_return.setBounds(50, 150, 250, 50);
		this.button_car_return.addActionListener(this);
		
		this.getContentPane().add(this.button_back);
		this.getContentPane().add(this.button_contract_search);
		this.getContentPane().add(this.button_contract_create);
		this.getContentPane().add(this.button_car_return);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.button_back) {
			if (Main.getMySQL().getUser().hasPermission(Permission.ADMIN)) {
				Main.getFrameManager().openFrameByID(1);
			} else {
				Main.getFrameManager().openFrameByID(101);
			}
		} else if (event.getSource() == this.button_contract_search) {
			Main.getFrameManager().openFrameByID(103);
		} else if (event.getSource() == this.button_contract_create) {
			Main.getFrameManager().openFrameByID(108);
		} else if (event.getSource() == this.button_car_return) {
			Main.getFrameManager().openFrameByID(109);
		}
	}
}
