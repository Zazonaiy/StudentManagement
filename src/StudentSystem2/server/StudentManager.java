package StudentSystem2.server;



import java.util.List;
import java.util.Set;

import StudentSystem2.model.Achievement;
import StudentSystem2.model.Course;
import StudentSystem2.model.Student;




public interface StudentManager {
	//根据ID获取学生对象
	public Student getStudentById(Integer id);
	public Integer getStudentIdByName(String name);
	//添加学生
	public boolean addStudent(Student user);
	//根据ID删除学生
	public boolean deleteStudentrById(Integer id) ;
	//根据名字删除学生
	public boolean deleteStudentByName(String name);
	//根据ID查找学生
	public String findStudentById(Integer id);
	//根据名字查找学生，模糊查询
	public String findStudentByName(String name) ;
	//返回所有信息
	public String showAll();
	//返回某个学生的课表
	public Set<Course> getStudentCourseListById(Integer studentId);
	//public Set<Course> getStudentCourseListByName(String stuName);
	//返回学生成绩单
	public List<Achievement> getAchievementList(Student student);
	public List<Achievement> getAchievementListByName(String stuName);
	public List<Achievement> getAchievementListById(Integer id);
	//得到学生列表（所有学生）
	public List<Student> getAllStudentList();
	//移除系统中的所有学生，慎用
	public boolean removeAllStudent();
	public List<Student> getAllStudentListCopy();
	
	//查看学生可选课程
	public Set<Course> getOptionalCourseByStudentId(Integer id);
	
	//设置学生的成绩
	public boolean setStudentAchievementById(Integer studentId, Integer courseId, Double grade);
	//查找最高成绩
	public Achievement getStudentMaxAchievementById(Integer studentId);
	public Achievement getStudentMaxAchievementByName(String stuName);
	//查找最低成绩
	public Achievement getStudenMinAchievementById(Integer studentId);
	public Achievement getStudentMinAchievementByName(String stuName);
	//查成平均成绩
	public Double getStudentAvgAchievementById(Integer studentId);
	public Double getStudentAvgAchievementByName(String stuName);
	
	
	//把学生列表转换为字符串
	public String studentListToString(List<Student> userList) ;
	//把系统中所有学生的信息转换为字符串
	public String toString() ;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
