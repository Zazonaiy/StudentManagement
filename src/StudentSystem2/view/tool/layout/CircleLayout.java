package StudentSystem2.view.tool.layout;

import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;

public class CircleLayout implements LayoutManager {
	private int minWidth = 0;            //最小宽度
	private int minHeight = 0;			 //最小高度
	private int preferredWidth = 0;       //首选宽度
	private int preferredHeight = 0;      //首选高度
	private boolean sizesSet = false;     //可否进行大小设置
	private int maxComponentWidth = 0;   //布局内组件的最大宽度
	private int maxComponentHeight = 0;  //布局内组件的最大高度
	
	
	public void addLayoutComponent(String name, Component comp) {
		
	}
	
	public void removeLayoutComponent(Component comp) {
		
	}
	
	public void setSizes(Container parent) {
		if (sizesSet) return;
		int n = parent.getComponentCount();  // 布局内有多少个需要布局的对象
		
		preferredWidth = 0;
		preferredHeight = 0;
		minWidth = 0;
		minHeight = 0;
		maxComponentWidth = 0;
		maxComponentHeight = 0;
		//	计算最大元件的宽度和高度
		//	设置首选大小为组件大小之和
		for (int i = 0; i < n; i++){
			Component c = parent.getComponent(i); //对每个元件进行设置
			if (c.isVisible()) {
				Dimension d = c.getPreferredSize(); //原件的尺寸信息
				maxComponentWidth = Math.max(maxComponentWidth, d.width);
				maxComponentHeight = Math.max(maxComponentHeight, d.height);
				preferredWidth += d.width;
				preferredHeight += d.height;
			}
		}
		minWidth = preferredWidth / 2;
		minHeight = preferredHeight / 2;
		sizesSet = true;
	}
	
	public Dimension preferredLayoutSize(Container parent) {
		setSizes(parent);
		Insets insets = parent.getInsets();
		int width = preferredWidth + insets.left + insets.right;
		int height = preferredHeight + insets.top + insets.bottom;
		
		return new Dimension(width, height);
	}
	
	public Dimension minimumLayoutSize(Container parent) {
		setSizes(parent);
		Insets insets = parent.getInsets();
		int width = minWidth + insets.left + insets.right;
		int height = minHeight + insets.top + insets.bottom;
		
		return new Dimension(width, height);
	}
	
	public void layoutContainer(Container parent) {
		setSizes(parent);
		
		// 计算圆心
		Insets insets = parent.getInsets();
		int containerWidth = parent.getSize().width - insets.left -insets.right;
		int containerHeight = parent.getSize().height - insets.top - insets.bottom;
		
		int xcenter = insets.left + containerWidth / 2;
		int ycenter = insets.top + containerHeight / 2;
		
		// 计算半径
		int xradius = (containerWidth - maxComponentWidth) / 2;
		int yradius = (containerHeight - maxComponentHeight) / 2;
		int radius = Math.min(xradius, yradius);
		
		// 把元件布局成一个园
		int n =parent.getComponentCount();
		for (int i = 0; i < n; i++) {
			Component c = parent.getComponent(i);
			if (c.isVisible()) {
				double angle = 2 * Math.PI * i / n; //计算角度
				// 确定原件的坐标
				int x = xcenter + (int) (Math.cos(angle) * radius);
				int y = ycenter + (int) (Math.sin(angle) * radius);
				System.out.println(x + " | " + y);
				// 把元件的中心放到目标坐标上
				Dimension d = c.getPreferredSize();
				c.setBounds(x - d.width / 2, y - d.height, d.width, d.height);
			}
		}
	}
}
