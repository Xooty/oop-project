package de.hwrberlin.autovermietung.manager;

import java.util.ArrayList;
import java.util.List;

import de.hwrberlin.autovermietung.frames.CalendarFrame;
import de.hwrberlin.autovermietung.frames.CarFrame;
import de.hwrberlin.autovermietung.frames.CarsFrame;
import de.hwrberlin.autovermietung.frames.ContractFrame;
import de.hwrberlin.autovermietung.frames.CustomersFrame;
import de.hwrberlin.autovermietung.frames.EmployeeMenuFrame;
import de.hwrberlin.autovermietung.frames.InputFrame;
import de.hwrberlin.autovermietung.frames.LoginFrame;
import de.hwrberlin.autovermietung.frames.MainFrame;
import de.hwrberlin.autovermietung.frames.MenuFrame;
import de.hwrberlin.autovermietung.frames.SearchUserFrame;
import de.hwrberlin.autovermietung.frames.UsersFrame;

public class FrameManager {

	private List<MainFrame> frames;
	
	private MainFrame current_frame;
	
	public FrameManager() {
		this.frames = new ArrayList<MainFrame>();
		
		this.setupFrames();
		
		this.current_frame = this.getFrames().get(0);
		this.current_frame.setVisible(true);
	}
	
	public List<MainFrame> getFrames() {
		return this.frames;
	}
	
	public MainFrame getFrameByID(int id) {
		for (MainFrame frame : this.frames) {
			if (frame.getID() == id) return frame;
		}
		return null;
	}
	
	public void addFrame(MainFrame frame) {
		this.frames.add(frame);
	}
	
	public MainFrame getCurrentFrame() {
		return this.current_frame;
	}
	
	public void setCurrentFrame(MainFrame frame) {
		this.current_frame = frame;
	}
	
	public MainFrame openFrameByID(int id) {
		MainFrame frame = this.getFrameByID(id);
		if (frame != null) {
			this.current_frame.setVisible(false);
			this.setCurrentFrame(frame);
			frame.setVisible(true);
			if (id != 0) frame.setMenuBar();
			return frame;
		}
		return null;
	}
	
	public void setupFrames() {
		this.frames.clear();						// ID
		this.addFrame(new LoginFrame());			// 0
		this.addFrame(new MenuFrame());				// 1
		this.addFrame(new UsersFrame());			// 2
		this.addFrame(new SearchUserFrame());		// 3
		
		this.addFrame(new EmployeeMenuFrame());		// 101
		this.addFrame(new CarFrame());				// 102
		this.addFrame(new InputFrame());			// 103
		this.addFrame(new CustomersFrame());		// 104
		this.addFrame(new ContractFrame());			// 105
		this.addFrame(new CalendarFrame()); 		// 106
		this.addFrame(new CarsFrame());				// 107
	}
}
