package StudentSystem2.server;

import java.util.List;
import java.util.Set;

import StudentSystem2.model.Achievement;
import StudentSystem2.model.Course;
import StudentSystem2.model.Student;

public interface CourseManager {
	public Course getCourseById(Integer id);
	
	//ѡ���˱��ſγ̵�ѧ������
	public List<Student> getCourseStudentListById(Integer id);
	//���ſγ̵ĳɼ����
	public List<Achievement> getCourseAchievementListById(Integer id);
	//���ſγ������ѧ��
	public String addStudentToCourseById(Integer studentId, Integer courseId);
	//�Ƴ����ſγ����ĳ��ѧ��
	public String removeStudentFromCourseById(Integer student, Integer courseId);
	//��ӿγ����γ�ӳ���
	public boolean addCourseToCourseMap(Course course);
	//�Թ̶���ʽ�������пγ�
	public String showAllCourses();
	
	//��ĳ�ſγ̴�ϵͳ���Ƴ�
	public boolean removeCourseById(Integer courseId);
	
	//�鱾�ſγ����гɼ���Ϣ
	public String showCourseAllAchiById(Integer courseId);
	//�鱾�ſγ���߳ɼ�
	public String showCourseMaxAchievementById(Integer courseId);
	//�鱾�ſγ���ͳɼ�
	public String showCourseMinAchievementById(Integer courseId);
	//�鱾�ſγ�ƽ���ɼ�
	public Double showCourseAvgAchievementById(Integer courseId);
	
	//����ѧ���α�
	public List<Course> getCourseList();
		
	public String toString() ;
}
