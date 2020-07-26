package de.hwrberlin.autovermietung;

import de.hwrberlin.autovermietung.manager.CarManager;
import de.hwrberlin.autovermietung.manager.ContractManager;
import de.hwrberlin.autovermietung.manager.CustomerManager;
import de.hwrberlin.autovermietung.manager.FrameManager;
import de.hwrberlin.autovermietung.mysql.MySQL;

public class Main {

	public static CarManager car_manager;
	
	public static FrameManager frame_manager;
	
	public static CustomerManager customer_manager;
	
	public static ContractManager contract_manager;
	
	public static MySQL mysql;
	
	public static void main(String[] args) {
		mysql = new MySQL(System.getProperty("user.home") + "/Desktop/Autovermietung/");
		
		car_manager = new CarManager();
		
		frame_manager = new FrameManager();
		
		customer_manager = new CustomerManager();
		
		contract_manager = new ContractManager();
	}
	
	public static CarManager getCarManager() {
		return car_manager;
	}
	
	public static FrameManager getFrameManager() {
		return frame_manager;
	}
	
	public static CustomerManager getCustomerManager() {
		return customer_manager;
	}
	
	public static ContractManager getContractManager() {
		return contract_manager;
	}
	
	public static MySQL getMySQL() {
		return mysql;
	}
}
