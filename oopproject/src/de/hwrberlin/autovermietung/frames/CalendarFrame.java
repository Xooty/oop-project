package de.hwrberlin.autovermietung.frames;

import java.awt.event.ActionEvent;

import com.toedter.calendar.JCalendar;

public class CalendarFrame extends MainFrame {

	private static final long serialVersionUID = -6981272971991070467L;

	public CalendarFrame() {
		super(4, "Kalendar", 800, 800);
		
		JCalendar cal = new JCalendar();
		
		this.add(cal);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
	}
}
