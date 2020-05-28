package de.hwrberlin.autovermietung.frames;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class InputFrame extends MainFrame {

	private static final long serialVersionUID = 6046886206685122861L;

	private JButton button_save_and_quit;
	
	public InputFrame() {
		super(-1, "Eingabe", 400, 400);
		
		this.setAlwaysOnTop(true);
		
		this.button_save_and_quit = new JButton("Speichern & Verlassen");
		this.button_save_and_quit.addActionListener(this);
		
		this.add(this.button_save_and_quit);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
	}
}
