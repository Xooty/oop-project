package de.hwrberlin.autovermietung.frames;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import com.toedter.calendar.JCalendar;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.users.Permission;

public class CalendarFrame extends MainFrame {

	private static final long serialVersionUID = -6981272971991070467L;

	private JButton button_back;
	
	public CalendarFrame() {
		super(106, "Kalender", 800, 800);
		
		JCalendar cal = new JCalendar();
		cal.setBounds(25, 25, 750, 550);
		
		this.button_back = new JButton("Zurück");
		this.button_back.setBounds(25, 600, 300, 75);
		this.button_back.addActionListener(this);
		
		getContentPane().add(cal);
		this.getContentPane().add(this.button_back);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.button_back) {
			if (Main.getMySQL().getUser().hasPermission(Permission.ADMIN)) {
				Main.getFrameManager().openFrameByID(1);
			} else {
				Main.getFrameManager().openFrameByID(101);
			}
		}
	}
}
