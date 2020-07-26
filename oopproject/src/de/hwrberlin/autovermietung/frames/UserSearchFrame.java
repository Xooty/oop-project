package de.hwrberlin.autovermietung.frames;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.users.Permission;
import de.hwrberlin.autovermietung.users.User;

public class UserSearchFrame extends MainFrame {

	private static final long serialVersionUID = 3149582527679213002L;

	private JLabel label_user_name, label_user_id, label_new_user_name, label_new_password, label_user_permission;
	
	private JTextField textfield_user_name, textfield_new_user_name, textfield_new_password;
	
	private JComboBox<Object> combobox_permission;
	
	private JButton button_back, button_search, button_delete, button_save;
	
	private User user;
	
	public UserSearchFrame() {
		super(3, "Benutzer bearbeiten & suchen", 350, 600);
		
		this.label_user_name = new JLabel("Benutzername:");
		this.label_user_name.setBounds(25, 50, 150, 25);
		
		this.textfield_user_name = new JTextField();
		this.textfield_user_name.setBounds(150, 50, 175, 25);
		
		this.button_search = new JButton("Suchen");
		this.button_search.setBounds(25, 100, 300, 50);
		this.button_search.addActionListener(this);
		
		this.label_user_id = new JLabel("Benutzer-ID: Keine Suche gestartet");
		this.label_user_id.setBounds(25, 175, 250, 25);
		
		this.label_new_user_name = new JLabel("Neuer Benutzername:");
		this.label_new_user_name.setBounds(25, 225, 125, 25);
		
		this.textfield_new_user_name = new JTextField("Keine Suche gestartet");
		this.textfield_new_user_name.setBounds(175, 225, 150, 25);
		
		this.label_new_password = new JLabel("Neues Passwort:");
		this.label_new_password.setBounds(25, 275, 125, 25);
		
		this.textfield_new_password = new JTextField("Keine Suche gestartet");
		this.textfield_new_password.setBounds(175, 275, 150, 25);
		
		this.label_user_permission = new JLabel("Berechtigung:");
		this.label_user_permission.setBounds(25, 325, 125, 25);
		
		this.combobox_permission = new JComboBox<Object>(Permission.values());
		this.combobox_permission.setBounds(175, 325, 150, 25);
		this.combobox_permission.setSelectedIndex(0);
		
		this.button_delete = new JButton("Löschen");
		this.button_delete.setBounds(25, 400, 100, 50);
		this.button_delete.addActionListener(this);
		
		this.button_save = new JButton("Speichern");
		this.button_save.setBounds(225, 400, 100, 50);
		this.button_save.addActionListener(this);
		
		this.button_back = new JButton("Zurück");
		this.button_back.setBounds(25, 475, 300, 50);
		this.button_back.addActionListener(this);
		
		this.getContentPane().add(this.label_user_name);
		this.getContentPane().add(this.textfield_user_name);
		this.getContentPane().add(this.button_search);
		this.getContentPane().add(this.label_user_id);
		this.getContentPane().add(this.label_new_user_name);
		this.getContentPane().add(this.textfield_new_user_name);
		this.getContentPane().add(this.label_new_password);
		this.getContentPane().add(this.textfield_new_password);
		this.getContentPane().add(this.label_user_permission);
		this.getContentPane().add(this.combobox_permission);
		this.getContentPane().add(this.button_delete);
		this.getContentPane().add(this.button_save);
		this.getContentPane().add(this.button_back);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.button_search) {
			if (this.textfield_user_name.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Sie müssen einen Benutzernamen eingeben.");
			} else {
				this.user = Main.getMySQL().searchUser(this.textfield_user_name.getText());
				if (this.user != null) {
					this.label_user_id.setText("Benutzer-ID: " + this.user.getUserID());
					this.textfield_new_user_name.setText(this.user.getUserName());
					this.textfield_new_password.setText(this.user.getUserPassword());
					this.combobox_permission.setSelectedItem(this.user.getPermission());
				}
			}
		} else if (event.getSource() == this.button_delete) {
			if (this.user == null) {
				JOptionPane.showMessageDialog(null, "Sie haben noch keinen Benutzer gesucht.");
			} else {
				Main.getMySQL().deleteUser(this.user);
				JOptionPane.showMessageDialog(null, "Sie haben den Benutzer erfolgreich gelöscht.");
			}
		} else if (event.getSource() == this.button_save) {
			if (this.user != null) {
				if (this.textfield_new_user_name.getText().equals("") || this.textfield_new_password.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Sie haben kein Passwort oder keinen Benutzer angegeben.");
				}
				this.user.updateUser(this.textfield_new_user_name.getText(), this.textfield_new_password.getText(), Permission.valueOf(this.combobox_permission.getSelectedItem().toString()));
				JOptionPane.showMessageDialog(null, "Sie haben den Benutzer erfolgreich bearbeitet.");
			}
		} else if (event.getSource() == this.button_back) {
			Main.getFrameManager().openFrameByID(2);
			this.resetTexts();
		}
	}
	
	public void resetTexts() {
		this.textfield_user_name.setText("");
		this.label_user_id.setText("Benutzer-ID: Keine Suche gestartet");
		this.textfield_new_user_name.setText("Keine Suche gestartet");
		this.textfield_new_password.setText("Keine Suche gestartet");
		this.combobox_permission.setSelectedIndex(0);
	}
}
