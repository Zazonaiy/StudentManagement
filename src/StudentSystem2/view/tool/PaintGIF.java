package StudentSystem2.view.tool;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;

public class PaintGIF extends JPanel{
	private int x;
	private int y;
	private ImageIcon img;
	private String url;
	
	public PaintGIF(int x, int y, String url){
		this.x = x;
		this.y = y;
		this.img = new ImageIcon(url);
		
	}
	
	public void paint(Graphics g) {
		if (img == null) return;
		super.paint(g);
		g.drawImage(img.getImage(), x, y, this);

	}
	
	public Dimension getDimension() {
		Dimension d = new Dimension();
		d.setSize(img.getIconWidth(), img.getIconHeight());
		return d;
	}
}
