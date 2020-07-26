package de.hwrberlin.autovermietung.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.mysql.MySQL;
import de.hwrberlin.autovermietung.other.RoundedBorder;
import de.hwrberlin.autovermietung.users.Permission;
import de.hwrberlin.autovermietung.users.User;

public class LoginFrame extends MainFrame {

	private static final long serialVersionUID = 4444358342846683048L;

	private JButton button_login;
	
	private JLabel label_carcontrol;
	
	private JLabel label_login_user, label_login_password;
	private JTextField textfield_login_user;
	private JPasswordField passwordfield_login_password;
	
	private JLabel label_main_image;
	
	private JLabel label_border_main, label_border_user, label_border_password;
	
	public LoginFrame() {
		super(0, "Login", 375, 600);
		
		this.getContentPane().setBackground(Color.WHITE);

		this.label_main_image = new JLabel(new ImageIcon(this.getClass().getResource("/images/logo.png")));
		this.label_main_image.setBounds(90, 10, 200, 175);
		
		JPanel panel = new JPanel();
		panel.setBounds(60, 190, 250, 50);
		panel.setBackground(Color.WHITE);
		panel.setBorder(new RoundedBorder(new Color(33, 136, 143), 50));
		panel.setLayout(null);
		
		this.label_carcontrol = new JLabel("CarControl");
		this.label_carcontrol.setHorizontalAlignment(SwingConstants.CENTER);
		this.label_carcontrol.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 18));
		this.label_carcontrol.setBounds(0, 0, 250, 50);
		
		panel.add(this.label_carcontrol);
		
		this.label_border_main = new JLabel();
		this.label_border_main.setBounds(20, 210, 325, 240);
		this.label_border_main.setBorder(new RoundedBorder(new Color(33, 136, 143), 20));
		
		this.button_login = new JButton("Anmelden");
		this.button_login.setBounds(75, 465, 200, 60);	
		this.button_login.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 18));
		this.button_login.setBorder(new RoundedBorder(new Color(33, 136, 143), 50));
		this.button_login.setBackground(Color.WHITE);
		this.button_login.addActionListener(this);
		
		this.label_login_user = new JLabel();
		this.label_login_user.setBounds(50, 270, 100, 40);
		this.label_login_user.setIcon(new ImageIcon(this.getClass().getResource("/images/avatar.png")));
		
		this.label_login_password = new JLabel();
		this.label_login_password.setBounds(50, 360, 100, 40);
	    this.label_login_password.setIcon(new ImageIcon(this.getClass().getResource("/images/password.png")));
		
		this.textfield_login_user = new JTextField("Benutzername");
		this.textfield_login_user.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 18));
		this.textfield_login_user.setBounds(90, 265, 240, 55);
		this.textfield_login_user.setBorder(null);

		this.passwordfield_login_password = new JPasswordField("Passwort");
		this.passwordfield_login_password.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 18));
		this.passwordfield_login_password.setBounds(90, 355, 240, 55);
		this.passwordfield_login_password.setBorder(null);

		this.label_border_user = new JLabel();
		this.label_border_user.setBounds(40, 260, 295, 65);
		this.label_border_user.setBorder(new RoundedBorder(new Color(33, 136, 143), 20));
		
		this.label_border_password = new JLabel();
		this.label_border_password.setBounds(40, 350, 295, 65);
		this.label_border_password.setBorder(new RoundedBorder(new Color(33, 136, 143), 20));
		
		this.getContentPane().add(this.label_main_image);
		this.getContentPane().add(panel);
		this.getContentPane().add(this.label_border_main);
		this.getContentPane().add(this.label_login_user);
		this.getContentPane().add(this.label_login_password);
		this.getContentPane().add(this.textfield_login_user);
		this.getContentPane().add(this.passwordfield_login_password);
		this.getContentPane().add(this.label_border_user);
		this.getContentPane().add(this.label_border_password);
		this.getContentPane().add(this.button_login);
	}
	
	public User login(String user_name, String user_password) {
		MySQL mysql = Main.getMySQL();
		Connection connection = mysql.openConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("SELECT * FROM users WHERE user_name = ? AND user_password = ?");
			st.setString(1, user_name);
			st.setString(2, user_password);
			
			rs = st.executeQuery();
			
			if (rs.first()) {
				return mysql.setUser(new User(rs.getInt("user_id"), connection));
			} else {
				return null;
			}
		} catch (NullPointerException e) {
			System.err.println("Der User konnte nicht geladen werden.");
			mysql.closeRessources(rs, st, connection);
			return null;
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			mysql.closeRessources(rs, st, connection);
		}
		return null;
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
				if (user.hasPermission(Permission.ADMIN)) {
					Main.getFrameManager().openFrameByID(1);
				} else {
					Main.getFrameManager().openFrameByID(101);
				}
				Main.getFrameManager().setupInfoLabels();
			} else {
				JOptionPane.showMessageDialog(null, "Der Benutzername oder das Passwort stimmen nicht. Bitte überprüfen Sie die Schreibweise.");
			}
		}
	}
}