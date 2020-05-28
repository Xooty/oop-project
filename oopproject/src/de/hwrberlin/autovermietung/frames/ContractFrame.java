package de.hwrberlin.autovermietung.frames;

import java.awt.event.ActionEvent;

import de.hwrberlin.autovermietung.users.Permission;

public class ContractFrame extends MainFrame {

	private static final long serialVersionUID = -344819132138234335L;

	public ContractFrame() {
		super(2, Permission.ADMIN, "Offene Angebote", 900, 900);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
	}
}
