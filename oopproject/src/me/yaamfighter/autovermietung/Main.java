package me.yaamfighter.autovermietung;

import me.yaamfighter.autovermietung.cars.CarManager;
import me.yaamfighter.autovermietung.frames.FrameManager;
import me.yaamfighter.autovermietung.mysql.DBController;

public class Main {

	public static CarManager car_manager;
	
	public static FrameManager frame_manager;
	
	public static DBController dbc;
	
	public static void main(String[] args) {
		car_manager = new CarManager();
		
		frame_manager = new FrameManager();
		
		dbc = DBController.getInstance();
        dbc.openConnection();
        dbc.initTables();
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
