package StudentSystem2.exe;

import java.util.List;
import java.util.Map;

import StudentSystem2.model.Course;
import StudentSystem2.model.Student;
import StudentSystem2.server.MemoryManager;

public class CopyTest {
	public static void main(String[] args) {
		MemoryManager mema = new MemoryManager();
		mema.addStudent(new Student(1, "ÑîÎ°ºÀ", 0));
		mema.addStudent(new Student(2, "ÑîÌÎ", 0));
		
		mema.addCourseToCourseMap(new Course(0, "java"));
		mema.addCourseToCourseMap(new Course(1, "sql"));
		
		System.out.println(mema.showAll());
		mema.setStudentAchievementById(1, 0, 80.0);
		mema.setStudentAchievementById(2, 1, 90.0);
		
		Map<Course, List<Student>> mapCopy = mema.copyCourseMap(mema.getCourseMap());
		
	}
}
