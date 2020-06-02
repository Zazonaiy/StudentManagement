package StudentSystem2.view.tool.layout;

import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;

public class CircleLayout implements LayoutManager {
	private int minWidth = 0;            //��С���
	private int minHeight = 0;			 //��С�߶�
	private int preferredWidth = 0;       //��ѡ���
	private int preferredHeight = 0;      //��ѡ�߶�
	private boolean sizesSet = false;     //�ɷ���д�С����
	private int maxComponentWidth = 0;   //����������������
	private int maxComponentHeight = 0;  //��������������߶�
	
	
	public void addLayoutComponent(String name, Component comp) {
		
	}
	
	public void removeLayoutComponent(Component comp) {
		
	}
	
	public void setSizes(Container parent) {
		if (sizesSet) return;
		int n = parent.getComponentCount();  // �������ж��ٸ���Ҫ���ֵĶ���
		
		preferredWidth = 0;
		preferredHeight = 0;
		minWidth = 0;
		minHeight = 0;
		maxComponentWidth = 0;
		maxComponentHeight = 0;
		//	�������Ԫ���Ŀ�Ⱥ͸߶�
		//	������ѡ��СΪ�����С֮��
		for (int i = 0; i < n; i++){
			Component c = parent.getComponent(i); //��ÿ��Ԫ����������
			if (c.isVisible()) {
				Dimension d = c.getPreferredSize(); //ԭ���ĳߴ���Ϣ
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
		
		// ����Բ��
		Insets insets = parent.getInsets();
		int containerWidth = parent.getSize().width - insets.left -insets.right;
		int containerHeight = parent.getSize().height - insets.top - insets.bottom;
		
		int xcenter = insets.left + containerWidth / 2;
		int ycenter = insets.top + containerHeight / 2;
		
		// ����뾶
		int xradius = (containerWidth - maxComponentWidth) / 2;
		int yradius = (containerHeight - maxComponentHeight) / 2;
		int radius = Math.min(xradius, yradius);
		
		// ��Ԫ�����ֳ�һ��԰
		int n =parent.getComponentCount();
		for (int i = 0; i < n; i++) {
			Component c = parent.getComponent(i);
			if (c.isVisible()) {
				double angle = 2 * Math.PI * i / n; //����Ƕ�
				// ȷ��ԭ��������
				int x = xcenter + (int) (Math.cos(angle) * radius);
				int y = ycenter + (int) (Math.sin(angle) * radius);
				System.out.println(x + " | " + y);
				// ��Ԫ�������ķŵ�Ŀ��������
				Dimension d = c.getPreferredSize();
				c.setBounds(x - d.width / 2, y - d.height, d.width, d.height);
			}
		}
	}
}
