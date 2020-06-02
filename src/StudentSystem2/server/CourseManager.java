package StudentSystem2.server;

import java.util.List;
import java.util.Set;

import StudentSystem2.model.Achievement;
import StudentSystem2.model.Course;
import StudentSystem2.model.Student;

public interface CourseManager {
	public Course getCourseById(Integer id);
	
	//选修了本门课程的学生名单
	public List<Student> getCourseStudentListById(Integer id);
	//本门课程的成绩情况
	public List<Achievement> getCourseAchievementListById(Integer id);
	//向本门课程里添加学生
	public String addStudentToCourseById(Integer studentId, Integer courseId);
	//移除本门课程里的某个学生
	public String removeStudentFromCourseById(Integer student, Integer courseId);
	//添加课程至课程映射表
	public boolean addCourseToCourseMap(Course course);
	//以固定格式返回所有课程
	public String showAllCourses();
	
	//把某门课程从系统中移除
	public boolean removeCourseById(Integer courseId);
	
	//查本门课程所有成绩信息
	public String showCourseAllAchiById(Integer courseId);
	//查本门课程最高成绩
	public String showCourseMaxAchievementById(Integer courseId);
	//查本门课程最低成绩
	public String showCourseMinAchievementById(Integer courseId);
	//查本门课程平均成绩
	public Double showCourseAvgAchievementById(Integer courseId);
	
	//返回学生课表
	public List<Course> getCourseList();
		
	public String toString() ;
}
