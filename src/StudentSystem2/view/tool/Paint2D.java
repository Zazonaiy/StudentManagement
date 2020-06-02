package StudentSystem2.view.tool;

import java.awt.Color;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

//用于绘制2D图形于填充2D图形的颜色
public class Paint2D extends JComponent{
	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	
	Paint2D(int width, int height, Color color){
		this.x = 0;
		this.y = 0;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	Paint2D(int x, int y, int width, int height, Color color){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		//绘制矩形区域
		Rectangle2D rect = new Rectangle2D.Double(x, y, width, height);//默认起始位置0,0
		g2.setBackground(color);
		g2.draw(rect);
	}
}
