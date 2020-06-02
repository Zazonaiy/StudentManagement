package StudentSystem2.model;

public class Achievement {
	private Course course;							//����ɼ����ڵĿγ̵�ID
	private Double grade;								//�ɼ���ֵ
	
	public Achievement() {
		this.course = null;
		this.grade = 0.0;
	}
	public Achievement(Course course, Double grade) {
		this.course = course;
		this.grade = grade;
	}

	public Course getCourse() {
		return course;
	}


	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}
	
	public String toString() {
		return course.toString() + grade.toString();
	}
	
	
}
