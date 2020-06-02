package StudentSystem2.view.tool;

import javax.swing.JComponent;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Color;

public class PaintPicture extends JComponent {
	private int x;
	private int y;
	private Image img;
	
	public PaintPicture(int x, int y, ImageIcon img){
		this.x = x;
		this.y = y;
		this.img = img.getImage();
		
	}
	
	public void paintComponent(Graphics g) {
		if (img == null) return;
		super.paintComponent(g);
		g.drawImage(img, x, y, null);

	}
}
