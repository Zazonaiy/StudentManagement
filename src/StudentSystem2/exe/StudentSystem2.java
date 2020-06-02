package StudentSystem2.exe;

import StudentSystem2.controller.Controller;
import StudentSystem2.server.FileManager;
import StudentSystem2.server.Manager;
import StudentSystem2.server.MemoryManager;
import StudentSystem2.view.Frame;
import StudentSystem2.view.MyView;

public class StudentSystem2 {
	public static void main(String[] args) {
		MyView view = new Frame();
		//Manager server = new MemoryManager();
		Manager server = new FileManager();
		Controller controller = new Controller(view, server);
	}
}
