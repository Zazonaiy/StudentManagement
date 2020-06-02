package StudentSystem2.view;
import java.awt.EventQueue;

import javax.swing.JFrame;

import StudentSystem2.controller.Controller;
import StudentSystem2.server.MemoryManager;

public class WindowTest {
	/*
	public static void main(String[] args) {
		Frame frame = new Frame();
		Controller controller = new Controller(frame, new MemoryStudentManager());
		EventQueue.invokeLater(() ->{
			frame.index(controller);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����Ĭ�Ϲرղ���Ϊ�ر�
			frame.setVisible(true);  //���߲�������ѡ���Ƿ����ش���
			
		});
		
	}*/
	
	public static void main(String[] args) {
		Frame frame = new Frame();
		EventQueue.invokeLater(() ->{
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����Ĭ�Ϲرղ���Ϊ�ر�
			frame.setVisible(true);  //���߲�������ѡ���Ƿ����ش���
			
		});
		
	}
}
