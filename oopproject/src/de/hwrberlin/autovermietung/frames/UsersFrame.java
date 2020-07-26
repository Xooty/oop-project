package de.hwrberlin.autovermietung.frames;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.mysql.MySQL;
import de.hwrberlin.autovermietung.users.Permission;

public class UsersFrame extends MainFrame {

	private static final long serialVersionUID = -3723162624844422083L;

	private JButton button_search, button_create, button_back;
	
	public UsersFrame() {
		super(2, "Benutzer verwalten", 700, 300);
		
		this.button_search = new JButton("Bearbeiten & Suchen");
		this.button_search.setBounds(50, 50, 250, 100);
		this.button_search.addActionListener(this);
		
		this.button_create = new JButton("Anlegen");
		this.button_create.setBounds(400, 50, 250, 100);
		this.button_create.addActionListener(this);
		
		this.button_back = new JButton("Zurück");
		this.button_back.setBounds(250, 175, 200, 50);
		this.button_back.addActionListener(this);
		
		this.getContentPane().add(this.button_search);
		this.getContentPane().add(this.button_create);
		this.getContentPane().add(this.button_back);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.button_search) {
			Main.getFrameManager().openFrameByID(3);
		} else if (event.getSource() == this.button_create) {
			this.openUserCreate();
		} else if (event.getSource() == this.button_back) {
			Main.getFrameManager().openFrameByID(1);
		}
	}
	
	public void openUserCreate() {
		JTextField user_name = new JTextField();
		JTextField password = new JTextField();
		JComboBox<Object> permission = new JComboBox<Object>(Permission.values());
		Object[] objects = { "Benutzername", user_name, "Passwort", password, "Berechtigung", permission };

        JOptionPane pane = new JOptionPane(objects, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        pane.createDialog(null, "Benutzer anlegen").setVisible(true);
        
        if (Integer.valueOf(pane.getValue().toString()) == JOptionPane.CANCEL_OPTION || Integer.valueOf(pane.getValue().toString()) == JOptionPane.CLOSED_OPTION) {
        } else {
        	MySQL sql = Main.getMySQL();
        	
        	if (user_name.getText().equals("") || password.getText().equals("")) {
        		JOptionPane.showMessageDialog(null, "Sie müssen einen Benutzernamen und ein Passwort eingeben!");
        		return;
        	}
        	
        	if (sql.createUser(user_name.getText(), password.getText(), permission.getSelectedItem().toString())) {
        		JOptionPane.showMessageDialog(null, "Der User " + user_name.getText() + " wurde erfolgreich angelegt.");
        	} else {
        		JOptionPane.showMessageDialog(null, "Der User " + user_name.getText() + " existiert bereits.");
        	}
        }
	}
}
