package de.hwrberlin.autovermietung.frames;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import de.hwrberlin.autovermietung.Main;

public abstract class MainFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 8255617560086790573L;

	private int id;
	
	private JMenuBar menubar_main;
	
	private JLabel label_info;
	
	public MainFrame(int id, String title, int width, int height) {
		this.id = id;
		
		this.setTitle(title);
		
		this.setVisible(false);
		this.setSize(width, height);
		
		this.setIconImage(new ImageIcon(this.getClass().getResource("/images/logo.png")).getImage());
		
		this.setResizable(false);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public int getID() {
		return this.id;
	}
	
	public Dimension getRealSize() {
		return new Dimension(this.getContentPane().getWidth(), this.getContentPane().getHeight());
	}
	
	public void setInfoLabel() {
		this.label_info = new JLabel("CarControl | Eingeloggt als: " + Main.getMySQL().getUser().getUserName());
		this.label_info.setBounds(this.getContentPane().getWidth() - 250, this.getContentPane().getHeight() - 25, 275, 25);
		
		this.getContentPane().add(this.label_info);
	}

	public void setMenuBar() {
		this.menubar_main = new JMenuBar();
		
		JMenu first_menu = new JMenu("Start");
		first_menu.setMnemonic(KeyEvent.VK_S);
		first_menu.getAccessibleContext().setAccessibleDescription("Text");
		
		JMenuItem menu_item_logout = new JMenuItem("Abmelden", KeyEvent.VK_A);
		menu_item_logout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
		menu_item_logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (event.getSource() == menu_item_logout) {
					Main.getFrameManager().setupFrames();
					Main.getFrameManager().openFrameByID(0);
				}
			}
		});
		
		first_menu.add(menu_item_logout);

		JMenuItem menu_item_close = new JMenuItem("Beenden", KeyEvent.VK_ESCAPE);
		menu_item_close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.ALT_MASK));
		menu_item_close.setMnemonic(KeyEvent.VK_B);
		menu_item_close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		first_menu.add(menu_item_close);
		
		this.menubar_main.add(first_menu);
		
		this.setJMenuBar(this.menubar_main);
	}
	
	@Override
	public abstract void actionPerformed(ActionEvent event);
}