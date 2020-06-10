package de.hwrberlin.autovermietung.frames;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.users.Permission;

public class InputFrame extends MainFrame {

	private static final long serialVersionUID = 6046886206685122861L;

	private JButton button_save_and_quit;
	
	public InputFrame() {
		super(103, Permission.USER, "Test", 400, 400);
		
		this.button_save_and_quit = new JButton("Speichern & Verlassen");
		this.button_save_and_quit.addActionListener(this);
		
		this.add(this.button_save_and_quit);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.button_save_and_quit) {
			
			Main.getFrameManager().openFrameByID(101);
		}
	}
}
