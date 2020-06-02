package StudentSystem2.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class Student {
	private Integer studentId;						//ѧ����
	private String name;							//ѧ������
	private Integer sex;							//ѧ���Ա�, 0�У�1Ů
	private List<Achievement> achiList;				//��ѧ�����пγ̵ĳɼ�
	
	public Student(Integer studentId, String name, Integer sex) {
		this.studentId = studentId;
		this.name = name;
		this.sex = sex;
		achiList = new ArrayList<>();
		
	}
	public Student(Integer studentId) throws ParseException{
		this.studentId = Integer.valueOf(studentId);
	}
	
	public Integer getId() {
		return studentId;
	}
	
	public String getName() {
		return name;
	}
	public Integer getSex() {
		return sex;
	}
	public List<Achievement> getAchiList(){
		return achiList;
	}
	public void addAchievement(Achievement achi) {
		achiList.add(achi);
	}
	
	
	@Override
	public String toString() {
		StringBuffer achiBuffer = new StringBuffer();
		achiList.forEach(achi->achiBuffer.append(achi + ","));
		return studentId + ", " + name + ", " + sex + "\n�ɼ���Ϣ: " + achiBuffer + "\n" ;
	}
	
}
