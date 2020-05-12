package de.hwrberlin.autovermietung.frames;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.users.User;

public class LoginFrame extends MainFrame {

	private static final long serialVersionUID = 4444358342846683048L;

	private JPanel panel_login;
	
	private JButton button_login;
	
	private JLabel label_login_user, label_login_password;
	private JTextField textfield_login_user;
	private JPasswordField passwordfield_login_password;
	
	public LoginFrame() {
		super(0, "Login", 300, 350);
		
		this.panel_login = this.getLoginPanel(0, 0, 300, 350);

		this.getContentPane().add(this.panel_login);
		
		this.add(new JLabel());
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.button_login) {
			char[] password = this.passwordfield_login_password.getPassword();
			StringBuilder sb = new StringBuilder();
			
			for (char c : password) {
				sb.append(c);
			}
			
			User user = this.login(this.textfield_login_user.getText(), sb.toString());
			
			if (user != null) {
				JOptionPane.showMessageDialog(null, "Sie haben sich erfolgreich als " + user.getUserName() + " angemeldet. Berechtigungsstufe: " + user.getPermission());
				Main.getFrameManager().openFrameByID(1);
			} else {
				JOptionPane.showMessageDialog(null, "Dieser Benutzer existiert nicht. Bitte überprüfen Sie die Schreibweise.");
			}
		}
	}
	
	public JPanel getLoginPanel(int x, int y, int width, int height) {
		
		this.button_login = new JButton("Anmelden");
		
		this.label_login_user = new JLabel("User");
		this.label_login_password = new JLabel("Passwort");
		
		this.textfield_login_user = new JTextField();
		this.passwordfield_login_password = new JPasswordField();

		/////////////////////////////////////////////////////
		
		this.button_login.addActionListener(this);
		
		/////////////////////////////////////////////////////
		
		JPanel panel = new JPanel();
		
		panel.setBounds(x, y, width, height);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 100, 100));
		
		panel.add(this.label_login_user);
		panel.add(Box.createRigidArea(new Dimension(0, 0)));
		panel.add(this.textfield_login_user);
		panel.add(Box.createRigidArea(new Dimension(0, 50)));
		
		panel.add(this.label_login_password);
		panel.add(Box.createRigidArea(new Dimension(0, 0)));
		panel.add(this.passwordfield_login_password);
		panel.add(Box.createRigidArea(new Dimension(0, 50)));
		
		panel.add(this.button_login);
		
		return panel;
	}
	
	public User login(String user_name, String user_password) {
		for (User user : Main.getUserManager().getUsers()) {
			if (user.getUserName().equals(user_name) && user.getUserPassword().equals(user_password)) return user;
		}
		return null;
	}
}
