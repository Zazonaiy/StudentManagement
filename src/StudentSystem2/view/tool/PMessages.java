package StudentSystem2.view.tool;

import java.awt.Font;

public class PMessages {
	private int[] x;
	private int[] y;
	private String[] pMessages;
	private Font[] fonts;
	private int size = 0;
	//用于判断重写的paintComponent是否需要 多次draw PMessages里的一串信息
	private boolean isAct = false; 
	
	PMessages(){
		isAct = false;
	}
	public PMessages(int[] x, int[] y, String[] messages, Font[] fonts){
		this.x = x;
		this.y = y;
		this.pMessages = messages;
		this.fonts = fonts;
		
		if (x.length == y.length && x.length == pMessages.length &&
				y.length == pMessages.length) {
			if(x.length == fonts.length) {
				this.isAct = true; //激活PMessages对象
				size = x.length;
			}else {
				System.out.println("PMessages is error!!!");
			}
		}else {
			System.out.println("PMessages is error!!!");
		}
		
	}
	
	public int getX(int index) {
		return x[index];
	}
	
	public int getY(int index) {
		return y[index];
	}
	
	public int getSize() {
		return size;
	}
	
	public String getString(int index) {
		return pMessages[index];
	}
	
	public Font getFonts(int index) {
		return fonts[index];
	}
	
	public boolean IsPMessageAct() {
		return isAct;
	}
}
