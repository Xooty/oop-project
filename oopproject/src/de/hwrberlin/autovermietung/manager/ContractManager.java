package de.hwrberlin.autovermietung.manager;

import java.util.ArrayList;
import java.util.List;

import de.hwrberlin.autovermietung.contract.Contract;

public class ContractManager {

	private List<Contract> contracts;
	
	public ContractManager() {
		this.contracts = new ArrayList<Contract>();
	}
	
	public List<Contract> getContracts() {
		return this.contracts;
	}
	
	public void addContract(Contract contract) {
		this.contracts.add(contract);
	}
	
	public void removeContract(Contract contract) {
		this.contracts.remove(contract);
	}
	
	public Contract getContractByID(int id) {
		for (Contract contract : this.contracts) {
			if (contract.getContractID() == id) return contract;
		}
		return null;
	}
}