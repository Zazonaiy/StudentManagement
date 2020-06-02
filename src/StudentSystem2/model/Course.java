package StudentSystem2.model;



public class Course {
	private Integer courseId;							//¿Î³ÌID
	private String courseName;							//¿Î³ÌÃû×Ö
	
	public Course(Integer id, String name) {
		this.courseId = id;
		this.courseName = name;
	}
	

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	
	public String toString() {
		return courseName ;
	}
}
