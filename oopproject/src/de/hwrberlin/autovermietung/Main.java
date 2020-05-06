package de.hwrberlin.autovermietung;

import de.hwrberlin.autovermietung.cars.CarManager;
import de.hwrberlin.autovermietung.frames.FrameManager;
import de.hwrberlin.autovermietung.mysql.DBController;

public class Main {

	public static CarManager car_manager;
	
	public static FrameManager frame_manager;
	
	public static DBController dbc;
	
	public static void main(String[] args) {
		car_manager = new CarManager();
		
		frame_manager = new FrameManager();
		
//		dbc = DBController.getInstance();
//        dbc.openConnection();
//        dbc.initTables();
	}
	
	public static CarManager getCarManager() {
		return car_manager;
	}
	
	public static FrameManager getFrameManager() {
		return frame_manager;
	}
	
	public static DBController getDBController() {
		return dbc;
	}
}
