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

public abstract class MainFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 8255617560086790573L;

	private int id;
	
//	private JButton button_goback;

	private JMenuBar menubar_main;
	
	public MainFrame(int id, String title, int width, int height) {
		this.id = id;

		this.setTitle(title);
		
		this.setVisible(false);
		this.setSize(width, height);
		
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		if (this.id != 0) {
			
			this.menubar_main = new JMenuBar();
			
			JMenu first_menu = new JMenu("Start");
			first_menu.setMnemonic(KeyEvent.VK_S);
			first_menu.getAccessibleContext().setAccessibleDescription("Text");
			
			JMenuItem menu_item = new JMenuItem("Abmelden", KeyEvent.VK_A);
			menu_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
			menu_item.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
			
			first_menu.add(menu_item);

			menu_item = new JMenuItem("Beenden", KeyEvent.VK_ESCAPE);
			menu_item.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
			menu_item.setMnemonic(KeyEvent.VK_B);
			first_menu.add(menu_item);
			
			this.menubar_main.add(first_menu);
			
			JMenu menu = new JMenu("Navigation");
			menu.setMnemonic(KeyEvent.VK_N);
			menu.getAccessibleContext().setAccessibleDescription("Text");
			
//			for (MainFrame frame : Main.getFrameManager().getFrames()) {
//				JMenuItem item = new JMenuItem(frame.getName());
//				menu.add(item);
//			}
			
			JMenuItem item_contract = new JMenuItem("Aufträge anzeigen");
			menu.add(item_contract);
			
			this.menubar_main.add(menu);
			
			this.setJMenuBar(this.menubar_main);
			
//			this.button_goback = new JButton("Zurück zur vorherigen Seite");
//			this.button_goback.setBounds(50, 50, 200, 50);
//			
//			this.button_goback.addActionListener(new ActionListener() {
//				@Override
//				public void actionPerformed(ActionEvent event) {
//					if (event.getSource() == button_goback) {
//						Main.getFrameManager().openFrameByID(id - 1);
//					}					
//				}
//			});
//			
//			this.add(this.button_goback);
		}
		
		this.add(new JLabel());
	}
	
	public int getID() {
		return this.id;
	}
	
	public Dimension getRealSize() {
		return new Dimension(this.getContentPane().getWidth(), this.getContentPane().getHeight());
	}
	
	@Override
	public abstract void actionPerformed(ActionEvent event);
}