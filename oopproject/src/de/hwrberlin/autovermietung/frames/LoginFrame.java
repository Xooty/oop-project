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
			
//			Main.getFrameManager().openFrameByID(1);
		}
	}
	
	public JPanel getLoginPanel(int x, int y, int width, int height) {
		
		this.button_login = new JButton("Login");
		
		this.label_login_user = new JLabel("User");
		this.label_login_password = new JLabel("Passwort");
		
		this.textfield_login_user = new JTextField();
		this.textfield_login_user.setSize(200, 25);
		this.passwordfield_login_password = new JPasswordField();
		this.passwordfield_login_password.setSize(200, 25);

		/////////////////////////////////////////////////////
		
		this.button_login.addActionListener(this);
		
		/////////////////////////////////////////////////////
		
		JPanel panel = new JPanel();
		
		panel.setSize(width, height);
		panel.setLocation(x, y);
		
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
}
