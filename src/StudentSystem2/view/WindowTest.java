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
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置默认关闭操作为关闭
			frame.setVisible(true);  //更具布尔参数选择是否隐藏窗口
			
		});
		
	}*/
	
	public static void main(String[] args) {
		Frame frame = new Frame();
		EventQueue.invokeLater(() ->{
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置默认关闭操作为关闭
			frame.setVisible(true);  //更具布尔参数选择是否隐藏窗口
			
		});
		
	}
}
