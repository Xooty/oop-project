package de.hwrberlin.autovermietung.frames;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.mysql.MySQL;
import de.hwrberlin.autovermietung.users.Permission;
import de.hwrberlin.autovermietung.users.User;

public class SearchUserFrame extends MainFrame {

	private static final long serialVersionUID = 3149582527679213002L;

	private JLabel label_user_name, label_user_id, label_new_user_name, label_new_password, label_user_permission;
	
	private JTextField textfield_user_name, textfield_new_user_name, textfield_new_password;
	
	private JComboBox<Object> combobox_permission;
	
	private JButton button_search, button_delete, button_save;
	
	private User user;
	
	public SearchUserFrame() {
		super(3, Permission.ADMIN, "Benutzer verwalten", 350, 550);
		
		this.label_user_name = new JLabel("Benutzername:");
		this.label_user_name.setBounds(25, 50, 150, 25);
		
		this.textfield_user_name = new JTextField();
		this.textfield_user_name.setBounds(150, 50, 175, 25);
		
		this.button_search = new JButton("Suchen");
		this.button_search.setBounds(25, 100, 298, 50);
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
		this.button_save.setBounds(200, 400, 100, 50);
		this.button_save.addActionListener(this);
		
		this.getContentPane().add(this.label_user_name);
		this.getContentPane().add(this.textfield_user_name);
		this.getContentPane().add(this.button_search);
		this.getContentPane().add(this.label_user_id);
		this.getContentPane().add(this.label_new_user_name);
		this.getContentPane().add(this.textfield_new_user_name);
		this.getContentPane().add(this.label_new_user_name);
		this.getContentPane().add(this.textfield_new_user_name);
		this.getContentPane().add(this.label_new_password);
		this.getContentPane().add(this.textfield_new_password);
		this.getContentPane().add(this.label_user_permission);
		this.getContentPane().add(this.combobox_permission);
		this.getContentPane().add(this.button_delete);
		this.getContentPane().add(this.button_save);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.button_search) {
			if (this.textfield_user_name.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Sie müssen einen Benutzernamen eingeben.");
			} else {
				this.user = this.searchUser(this.textfield_user_name.getText());
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
				this.deleteUser(this.user);
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
		}
	}
	
	public User searchUser(String user_name) {
		MySQL sql = Main.getMySQL();
		Connection connection = sql.openConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("SELECT * FROM users WHERE user_name = ?");
			st.setString(1, user_name);
			rs = st.executeQuery();
			
			if (rs.first()) {
				return new User(rs.getInt("user_id"));
			} else {
				JOptionPane.showMessageDialog(null, "Dieser Benutzername existiert nicht.");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.closeRessources(rs, st, connection);
		}
		return null;
	}
	
	public void deleteUser(User user) {
		MySQL sql = Main.getMySQL();
		Connection connection = sql.openConnection();
		PreparedStatement st = null;
		
		try {
			st = connection.prepareStatement("DELETE FROM users WHERE user_id = ? AND user_name = ?");
			st.setInt(1, user.getUserID());
			st.setString(2, user.getUserName());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.closeRessources(null, st, connection);
		}
	}
}
