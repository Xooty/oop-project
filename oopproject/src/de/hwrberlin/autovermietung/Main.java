package de.hwrberlin.autovermietung;

import de.hwrberlin.autovermietung.manager.CarManager;
import de.hwrberlin.autovermietung.manager.FrameManager;
import de.hwrberlin.autovermietung.mysql.MySQL;

public class Main {

	public static CarManager car_manager;
	
	public static FrameManager frame_manager;
	
	public static MySQL mysql;
	
	public static void main(String[] args) {
		car_manager = new CarManager();
		
		frame_manager = new FrameManager();
		
		mysql = new MySQL(System.getProperty("user.home") + "/Desktop/Autovermietung/");
	}
	
	public static CarManager getCarManager() {
		return car_manager;
	}
	
	public static FrameManager getFrameManager() {
		return frame_manager;
	}
	
	public static MySQL getMySQL() {
		return mysql;
	}
}
