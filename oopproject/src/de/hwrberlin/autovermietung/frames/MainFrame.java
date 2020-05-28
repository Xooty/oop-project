package de.hwrberlin.autovermietung.frames;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import de.hwrberlin.autovermietung.Main;
import de.hwrberlin.autovermietung.listeners.MenuActionListener;
import de.hwrberlin.autovermietung.users.Permission;

public abstract class MainFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 8255617560086790573L;

	private int id;
	
	private Permission permission;

	private JMenuBar menubar_main;
	
	public MainFrame(int id, Permission permission, String title, int width, int height) {
		this.id = id;
		
		this.permission = permission;

		this.setTitle(title);
		
		this.setVisible(false);
		this.setSize(width, height);
		
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.add(new JLabel());
	}
	
	public int getID() {
		return this.id;
	}
	
	public Permission getPermission() {
		return this.permission;
	}
	
	public Dimension getRealSize() {
		return new Dimension(this.getContentPane().getWidth(), this.getContentPane().getHeight());
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
		menu_item_logout.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		
		first_menu.add(menu_item_logout);

		JMenuItem menu_item_close = new JMenuItem("Beenden", KeyEvent.VK_ESCAPE);
		menu_item_close.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menu_item_close.setMnemonic(KeyEvent.VK_B);
		menu_item_close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
//				for (MainFrame frame : Main.getFrameManager().getFrames()) {
//					frame.dispose();
//				}
				System.exit(0);
			}
		});
		first_menu.add(menu_item_close);
		
		this.menubar_main.add(first_menu);
		
		JMenu menu = new JMenu("Navigation");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription("Text");
		
		for (MainFrame frame : Main.getFrameManager().getFrames()) {
			if (frame.getID() == 0 || frame.getID() == this.id || frame.getID() == 102) continue;
			if (Main.getMySQL().getUser().hasPermission(frame.getPermission())) {
				JMenuItem item = new JMenuItem(frame.getTitle() + " anzeigen");
				item.addActionListener(new MenuActionListener());
				menu.add(item);
			}
		}
		
//		JMenuItem item_contract = new JMenuItem("Aufträge anzeigen");
//		menu.add(item_contract);
		
		this.menubar_main.add(menu);
		
		this.setJMenuBar(this.menubar_main);
	}
	
	@Override
	public abstract void actionPerformed(ActionEvent event);
}