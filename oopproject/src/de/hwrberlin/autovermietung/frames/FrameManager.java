package de.hwrberlin.autovermietung.frames;

import java.util.ArrayList;
import java.util.List;

public class FrameManager {

	private List<MainFrame> frames;
	
	private MainFrame current_frame;
	private MainFrame previous_frame;
	
	public FrameManager() {
		this.frames = new ArrayList<MainFrame>();
		
		this.setupFrames();
		
		this.current_frame = this.getFrames().get(0);
		this.current_frame.setVisible(true);
		
		this.previous_frame = null;
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
	
	public MainFrame getPreviousFrame() {
		return this.previous_frame;
	}
	
	public void setPreviousFrame(MainFrame frame) {
		this.previous_frame = frame;
	}
	
	public MainFrame openFrameByID(int id) {
		MainFrame frame = this.getFrameByID(id);
		if (frame != null) {
			this.setPreviousFrame(this.current_frame);
			this.current_frame.setVisible(false);
			this.setCurrentFrame(frame);
			frame.setVisible(true);
			if (id != 0) frame.setMenuBar();
			return frame;
		}
		return null;
	}
	
	public void setupFrames() {
		this.frames.clear();
		this.addFrame(new LoginFrame());
		this.addFrame(new MenuFrame());
		this.addFrame(new ContractFrame());
		this.addFrame(new CarFrame());
		this.addFrame(new CalendarFrame());
	}
}
