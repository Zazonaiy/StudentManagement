package StudentSystem2.server;



import java.util.List;
import java.util.Set;

import StudentSystem2.model.Achievement;
import StudentSystem2.model.Course;
import StudentSystem2.model.Student;




public interface StudentManager {
	//����ID��ȡѧ������
	public Student getStudentById(Integer id);
	public Integer getStudentIdByName(String name);
	//���ѧ��
	public boolean addStudent(Student user);
	//����IDɾ��ѧ��
	public boolean deleteStudentrById(Integer id) ;
	//��������ɾ��ѧ��
	public boolean deleteStudentByName(String name);
	//����ID����ѧ��
	public String findStudentById(Integer id);
	//�������ֲ���ѧ����ģ����ѯ
	public String findStudentByName(String name) ;
	//����������Ϣ
	public String showAll();
	//����ĳ��ѧ���Ŀα�
	public Set<Course> getStudentCourseListById(Integer studentId);
	//public Set<Course> getStudentCourseListByName(String stuName);
	//����ѧ���ɼ���
	public List<Achievement> getAchievementList(Student student);
	public List<Achievement> getAchievementListByName(String stuName);
	public List<Achievement> getAchievementListById(Integer id);
	//�õ�ѧ���б�����ѧ����
	public List<Student> getAllStudentList();
	//�Ƴ�ϵͳ�е�����ѧ��������
	public boolean removeAllStudent();
	public List<Student> getAllStudentListCopy();
	
	//�鿴ѧ����ѡ�γ�
	public Set<Course> getOptionalCourseByStudentId(Integer id);
	
	//����ѧ���ĳɼ�
	public boolean setStudentAchievementById(Integer studentId, Integer courseId, Double grade);
	//������߳ɼ�
	public Achievement getStudentMaxAchievementById(Integer studentId);
	public Achievement getStudentMaxAchievementByName(String stuName);
	//������ͳɼ�
	public Achievement getStudenMinAchievementById(Integer studentId);
	public Achievement getStudentMinAchievementByName(String stuName);
	//���ƽ���ɼ�
	public Double getStudentAvgAchievementById(Integer studentId);
	public Double getStudentAvgAchievementByName(String stuName);
	
	
	//��ѧ���б�ת��Ϊ�ַ���
	public String studentListToString(List<Student> userList) ;
	//��ϵͳ������ѧ������Ϣת��Ϊ�ַ���
	public String toString() ;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
