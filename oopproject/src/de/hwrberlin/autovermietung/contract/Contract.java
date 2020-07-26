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
	
	private boolean car_returned;
	
	public Contract(int employee_id, int customer_id, int car_id, long contract_start, long contract_end, int mileage_start) {
		
		this.employee_id = employee_id;
		this.customer_id = customer_id;
		this.car_id = car_id;
		this.contract_start = contract_start;
		this.contract_end = contract_end;
		this.mileage_start = mileage_start;
		this.car_returned = false;
		
		MySQL sql = Main.getMySQL();
		Connection connection = sql.openConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("INSERT INTO contracts (employee_id, customer_id, car_id, contract_start, contract_end, mileage_start, car_returned) VALUES (?, ?, ?, ?, ?, ?, ?)");
			st.setInt(1, this.employee_id);
			st.setInt(2, this.customer_id);
			st.setInt(3, this.car_id);
			st.setLong(4, this.contract_start);
			st.setLong(5, this.contract_end);
			st.setInt(6, this.mileage_start);
			st.setBoolean(7, this.car_returned);
			
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.closeRessources(rs, st, connection);
		}
	}
	
	public Contract(int contract_id, Connection connection) {
		this.contract_id = contract_id;
		
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
			
			this.car_returned = rs.getBoolean("car_returned");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getContractID() {
		return this.contract_id;
	}
	
	public int getEmployeeID() {
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
	
	public boolean isCarReturned() {
		return this.car_returned;
	}
	
	public void setCarReturned(int mileage, boolean car_returned) {
		this.mileage_end = mileage;
		this.car_returned = car_returned;
		
		MySQL sql = Main.getMySQL();
		Connection connection = sql.openConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("UPDATE contracts SET mileage_end = ?, car_returned = ? WHERE car_id = ? AND contract_start = ? AND contract_end = ?");
			st.setInt(1, this.mileage_end);
			st.setBoolean(2, this.car_returned);
			st.setInt(3, this.car_id);
			st.setLong(4, this.contract_start);
			st.setLong(5, this.contract_end);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.closeRessources(rs, st, connection);
		}
	}
}