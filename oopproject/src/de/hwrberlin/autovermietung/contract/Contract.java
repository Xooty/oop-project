package de.hwrberlin.autovermietung.contract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.mysql.MySQL;

public class Contract {

	private int contract_id, employee_id, customer_id, car_id;
	
	private long contract_start, contract_end;
	
	private int mileage_start, mileage_end;
	
	public Contract(int contract_id) {
		this.contract_id = contract_id;
		
		MySQL mysql = Main.getMySQL();
		Connection connection = mysql.openConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("SELECT * FROM contracts WHERE contract_id = ?");
			st.setInt(1, this.contract_id);
			
			rs = st.executeQuery();
			
			rs.first();
			
			this.employee_id = rs.getInt("employee_id");
			this.customer_id = rs.getInt("customer_id");
			this.car_id = rs.getInt("car_id");
			
			this.contract_start = rs.getLong("contract_start");
			this.contract_end = rs.getLong("contract_end");
				
			this.mileage_start = rs.getInt("mileage_start");
			this.mileage_end = rs.getInt("mileage_end");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysql.closeRessources(rs, st, connection);
		}
	}
	
	public int getContractID() {
		return this.contract_id;
	}
	
	public int getWorkerID() {
		return this.employee_id;
	}
	
	public int getCustomerID() {
		return this.customer_id;
	}
	
	public int getCarID() {
		return this.car_id;
	}
	
	public long getContractStart() {
		return this.contract_start;
	}
	
	public void setContractStart(long contract_start) {
		this.contract_start = contract_start;
	}
	
	public long getContractEnd() {
		return this.contract_end;
	}
	
	public void setContractEnd(long contract_end) {
		this.contract_end = contract_end;
	}
	
	public long getTimeLeft() {
		return this.contract_end - this.contract_start;
	}
	
	public int getMileageStart() {
		return this.mileage_start;
	}
	
	public void setMileageStart(int mileage_start) {
		this.mileage_start = mileage_start;
	}
	
	public void addMileageStart(int mileage_start) {
		this.mileage_start += mileage_start;
	}
	
	public int getMileageEnd() {
		return this.mileage_end;
	}
	
	public void setMileageEnd(int mileage_end) {
		this.mileage_end = mileage_end;
	}
	
	public void addMileageEnd(int mileage_end) {
		this.mileage_end += mileage_end;
	}
}