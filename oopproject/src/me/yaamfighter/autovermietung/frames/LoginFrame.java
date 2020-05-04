package me.yaamfighter.autovermietung.frames;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

import me.yaamfighter.autovermietung.Main;

public class LoginFrame extends MainFrame {

	private static final long serialVersionUID = 4444358342846683048L;

	private JButton button_test;
	
	public LoginFrame() {
		super(0, "Autovermietung Seite 1", 900, 900);
		
		this.button_test = new JButton("Test");
		this.button_test.setBounds(200, 200, 100, 50);
		
		this.button_test.addActionListener(this);
		
		this.add(this.button_test);
		
		this.add(new JLabel());
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.button_test) {
			Main.getFrameManager().openFrameByID(1);
		}
	}
}
