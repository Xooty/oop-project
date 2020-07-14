package de.hwrberlin.autovermietung.other;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JLabel;

public class RoundedLabel extends JLabel {

	private static final long serialVersionUID = -3975245638542056037L;

	private final Color color;
	private int gap;
	
	public RoundedLabel(Color c, int g) {
		color = c;
		gap = g;
	}

	public void paint(Graphics g) {
		Dimension d = getSize();
		int h = d.height;
		int w = d.width;
		
		Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.draw(new RoundRectangle2D.Double(0, 0, w - 1, h - 1, gap, gap));
        g2d.fillRoundRect(0, 0, w - 1, h - 1, gap, gap);
        g2d.dispose();
	}
}
