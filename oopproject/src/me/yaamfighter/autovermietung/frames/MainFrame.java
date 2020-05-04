package me.yaamfighter.autovermietung.frames;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import me.yaamfighter.autovermietung.Main;

public abstract class MainFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 8255617560086790573L;

	private int id;
	
	private JButton button_goback;
	
	public MainFrame(int id, String title, int width, int height) {
		this.id = id;

		this.setTitle(title);
		
		this.setVisible(false);
		this.setSize(width, height);
		
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		if (this.id != 0) {
			this.button_goback = new JButton("Zurück zur vorherigen Seite");
			this.button_goback.setBounds(50, 50, 200, 50);
			
			this.button_goback.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					if (event.getSource() == button_goback) {
						Main.getFrameManager().openFrameByID(id - 1);
					}					
				}
			});
			
			this.add(this.button_goback);
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
