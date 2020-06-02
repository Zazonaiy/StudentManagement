package StudentSystem2.view.tool;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Font;

public class PaintString extends JComponent{
	private int x;
	private int y;
	private String message;
	private PMessages pMess;  //������ʾ�����ַ���Ϣ�Ĵ���
	private Font font;
	private boolean fontIsDefault = true; //��������Ƿ���Ĭ�����壬������������drawString
	
	PaintString(int x, int y, String message){
		this.x = x;
		this.y = y;
		this.message = message;
	}
	
	PaintString(int x, int y, String message, Font font){
		this.x = x;
		this.y = y;
		this.message = message;
		this.font = font;
		fontIsDefault = false;
	}
	
	public PaintString(PMessages pMess){
		this.pMess = pMess;
		fontIsDefault = false;
	}
	
	
	public void paintComponent(Graphics g) {
		boolean isPMess = pMess.IsPMessageAct();
		if (fontIsDefault) {
			g.drawString(message, x, y);
		}else {
			if(isPMess) {
				for (int i = 0; i < pMess.getSize(); i++) {
					g.setFont(pMess.getFonts(i));
					g.drawString(pMess.getString(i),
							pMess.getX(i), pMess.getY(i));
				}
			}else {
				g.setFont(font);
				g.drawString(message, x, y);
			}
		}
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
