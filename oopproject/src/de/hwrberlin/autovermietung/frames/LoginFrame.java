package de.hwrberlin.autovermietung.frames;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.mysql.MySQL;
import de.hwrberlin.autovermietung.users.Permission;
import de.hwrberlin.autovermietung.users.User;

public class LoginFrame extends MainFrame {

	private static final long serialVersionUID = 4444358342846683048L;

	private JButton button_login, button_cancel;
	
	private JLabel label_login_user, label_login_password;
	private JTextField textfield_login_user;
	private JPasswordField passwordfield_login_password;
	
	public LoginFrame() {
		super(0, Permission.USER, "Login", 300, 350);
		
		this.setContentPane(new ImagePanel());
		
		this.button_login = new JButton("Anmelden");
		this.button_login.setBounds(25, 225, 100, 25);
		this.button_login.addActionListener(this);
		
		this.button_cancel = new JButton("Abbrechen");
		this.button_cancel.setBounds(175, 225, 100, 25);
		this.button_cancel.addActionListener(this);
		
		this.label_login_user = new JLabel("User");
		this.label_login_user.setBounds(100, 30, 100, 40);
		this.label_login_user.setIcon(new ImageIcon("res/avatar.png"));
		
		this.label_login_password = new JLabel("Passwort");
		this.label_login_password.setBounds(100, 110, 100, 40);
		this.label_login_password.setIcon(new ImageIcon("res/password.png"));
		
		this.textfield_login_user = new JTextField();
		this.textfield_login_user.setBounds(100, 75, 100, 25);

		this.passwordfield_login_password = new JPasswordField();
		this.passwordfield_login_password.setBounds(100, 150, 100, 25);
		
		
		this.add(this.button_login);
		this.add(this.button_cancel);
		this.add(this.label_login_user);
		this.add(this.label_login_password);
		this.add(this.textfield_login_user);
		this.add(this.passwordfield_login_password);
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
				return mysql.setUser(new User(rs.getInt("user_id")));
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
			} else {
				JOptionPane.showMessageDialog(null, "Der Benutzername oder das Passwort stimmen nicht. Bitte überprüfen Sie die Schreibweise.");
			}
		} else if (event.getSource() == this.button_cancel) {
			System.exit(0);
		}
	}
	
	class ImagePanel extends JComponent {

		private static final long serialVersionUID = -7108340505337167138L;
		
		private Image image;
		
	    public ImagePanel() {
	        try {
				this.image = ImageIO.read(new File("res/background_login.png"));
			} catch (IOException e) {
				e.printStackTrace();
			};
	    }
	    
	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(image, 20, 30, this.getWidth() - 30, this.getHeight() - 60, this);
	    }
	}
}