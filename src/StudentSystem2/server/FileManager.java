package StudentSystem2.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import StudentSystem2.model.Achievement;
import StudentSystem2.model.Course;
import StudentSystem2.model.Student;
import StudentSystem2.model.User;

public class FileManager implements Manager{
	private List<Student> studentList;				//学生对象列表
	private Map<Course, List<Student>> courseMap ;    //内含所有课程、每门课程的所有学生、学生又内含其所有成绩
	private List<User> userList;				//用户(登录)
	private User loggedUser;
	private static final File STU_FILE = new File("data/student.txt");
	private static final File COU_FILE = new File("data/course.txt");
	private static final File ACHI_FILE = new File("data/achievement.txt");
	private static final File USER_FILE = new File("data/user.txt");
	
	public FileManager() {
		studentList = new ArrayList<Student>();
		courseMap = new HashMap<Course, List<Student>>();
		userList = new ArrayList<User>();
		//文件读取到内存
		checkFileAndDir();
		readFileToMemory();
		//目前系统中没有登录的用户
		loggedUser = null;
	}
	private void checkFileAndDir() {
		// 查看路径下有没有目标文件 没有就创建一个
		try {
			File stuFileFolder = STU_FILE.getParentFile(); //返回父目录
			if (!stuFileFolder.exists()) {
				stuFileFolder.mkdirs();
			}
			if (!STU_FILE.exists()) {
				STU_FILE.createNewFile();
			}
			File couFileFolder = COU_FILE.getParentFile(); //返回父目录
			if (!couFileFolder.exists()) {
				couFileFolder.mkdirs();
			}
			if (!COU_FILE.exists()) {
				COU_FILE.createNewFile();
			}
			File achiFileFolder = ACHI_FILE.getParentFile(); //返回父目录
			if (!achiFileFolder.exists()) {
				achiFileFolder.mkdirs();
			}
			if (!ACHI_FILE.exists()) {
				ACHI_FILE.createNewFile();
			}
			File userFileFolder = USER_FILE.getParentFile(); //返回父目录
			if (!userFileFolder.exists()) {
				userFileFolder.mkdirs();
			}
			if (!USER_FILE.exists()) {
				USER_FILE.createNewFile();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void readFileToMemory() {
		// 将学生信息从文件读取至内存中
		try (BufferedReader studentReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(STU_FILE), "GBK"))){
			String line = studentReader.readLine();
			while (line != null && line.length()>0) {
				System.out.println("读取 + "+line);
				//userList.add(newUserByStr(line));
				studentList.add(newStudentByFileLine(line));
				line = studentReader.readLine();
			}
		}catch (IOException | NumberFormatException e) {
			e.printStackTrace();
		}
		// 将课程信息从文件读取至内存中
		try (BufferedReader courseReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(COU_FILE), "GBK"))){
			String line = courseReader.readLine();
			while (line != null && line.length()>0) {
				System.out.println("读取 + "+line);
				addMapKeyValueByFileLine(line);	//往映射表里加键值对
				line = courseReader.readLine();
			}
		}catch (IOException | NumberFormatException e) {
			e.printStackTrace();
		}
		// 将成绩信息从文件读取至内存中
		try (BufferedReader achiReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(ACHI_FILE), "GBK"))){
			String line = achiReader.readLine();
			while (line != null && line.length()>0) {
				System.out.println("读取 + "+line);
				newAchiAddToStudentByFileLine(line); //往学生对象的成绩列表里加成绩对象
				line = achiReader.readLine();
			}
		}catch (IOException | NumberFormatException e) {
			e.printStackTrace();
		}
		// 将用户信息从文件读取至内存
		try (BufferedReader userReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(USER_FILE), "GBK"))){
			String line = userReader.readLine();
			while (line != null && line.length()>0) {
				System.out.println("读取 + "+line);
				newUserAddToUserListByFileLine(line);
				line = userReader.readLine();
			}
		}catch (IOException | NumberFormatException e) {
			e.printStackTrace();
		}		
	}
	private Student newStudentByFileLine(String line) {
		String[] params = line.replaceAll(" ", "").split(",");
		return new Student(Integer.valueOf(params[0]),	//id
				params[1],								//name
				Integer.valueOf(params[2]));			//sex
	}
	private void addMapKeyValueByFileLine (String line){
		String[] fileLine = new String[2];
		String courseData = null;
		String stuIdData = null;
		try {
			fileLine = line.replaceAll(" ", "").split("@");
			courseData = fileLine[0];
			stuIdData = fileLine[1];
		}catch (ArrayIndexOutOfBoundsException e) {
			courseData = line.replaceAll("@", "");
		}
		
		// 课程
		Course course = new Course(Integer.valueOf(courseData.replaceAll(" ", "").split(",")[0]),	//id
				courseData.replaceAll(" ", "").split(",")[1]);										//姓名
		// 学生列表
		List<Student> courseStuList = new ArrayList<>();
		if (stuIdData != null) {
			for (String stuId : stuIdData.replaceAll(" ", "").split(",")) {		//遍历课程文件里读出的所有学生的id
				for (Student student: this.studentList) {
					if (Integer.valueOf(stuId).equals(student.getId())) {		//在内存中找到该学生
						courseStuList.add(student);								//加入课程学生列表
						break;
					}
				}
			}
		}
		
		// 添加至映射表
		this.courseMap.put(course, courseStuList);
	}
	private void newAchiAddToStudentByFileLine (String line) {
		String[] achiData = line.replaceAll(" ", "").split(",");
		Integer courseId = Integer.valueOf(achiData[0]);
		Integer studentId = Integer.valueOf(achiData[1]);
		Double grade = Double.valueOf(achiData[2]);
		
		// 在courseMap里找到对映的course
		Course course = null;
		for (Course co : courseMap.keySet()) {
			if (courseId.equals(co.getCourseId())) {
				course = co;
				break;
			}
		}
		// 在courseMap里找对映的student
		Achievement achi = null; 
		if (course == null) {
			System.out.println("错误，没有找到该成绩的课程；该课程或已不再系统文件中");
		}else {
			achi = new Achievement(course, grade);	//构建成绩对象
			for (Student stu : courseMap.get(course)) {	
				if (stu.getId().equals(studentId)) {
					stu.getAchiList().add(achi);	// 往课程映射表中的学生的achilist中加achi，
					break;							//这里映射图和studentList的引用指向的是同一对象
				}
			}
		}
	}
	private void newUserAddToUserListByFileLine(String line) {
		String[] userData = line.replaceAll(" ", "").split(",");
		String userName = userData[0];
		String userPass = userData[1];
		
		userList.add(new User(userName, userPass));
	}
	
	private void stuSave() {
		try (BufferedWriter bufferedWriter = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(STU_FILE), "GBK"))){
			
			StringBuffer buffer = new StringBuffer();
			for (Student student : studentList) {
				buffer.append(String.valueOf(student.getId()) + "," 
						+ student.getName() + ","
						+ String.valueOf(student.getSex()) + "," + "\n");
			}

			bufferedWriter.write(buffer.toString());
			bufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void couSave() {
		try (BufferedWriter bufferedWriter = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(COU_FILE), "GBK"))){
			
			StringBuffer buffer = new StringBuffer();
			for (Course course : courseMap.keySet()) {
				buffer.append(String.valueOf(course.getCourseId()) + "," 
						+ course.getCourseName() + "@");
				if (courseMap.get(course) != null && courseMap.get(course).size() != 0) {
					for (Student student : courseMap.get(course)) {
						buffer.append(String.valueOf(student.getId()) + ",");
					}
					
				}
				buffer.append("\n");
			}

			bufferedWriter.write(buffer.toString());
			bufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void achiSave() {
		try (BufferedWriter bufferedWriter = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(ACHI_FILE), "GBK"))){
			
			StringBuffer buffer = new StringBuffer();
			for (Student student : studentList) {
				if (student.getAchiList() != null && student.getAchiList().size() != 0) {
					for (Achievement achi : student.getAchiList()) {
						buffer.append(String.valueOf(achi.getCourse().getCourseId()) + ","
								+ String.valueOf(student.getId()) + ","
								+ String.valueOf(achi.getGrade()) + "\n");
					}
				}
			}

			bufferedWriter.write(buffer.toString());
			bufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void save() {
		stuSave();		//TODO 
		couSave();
		achiSave();
	}
	
	@Override
	public Student getStudentById(Integer id) {
		Student result = studentList.stream().filter(user -> user.getId().equals(id))
				.findFirst().orElse(null);
		
		return result;
	}
	@Override
	public Integer getStudentIdByName(String name) {
		return studentList.stream()
				.filter(user->user.getName().contains(name))
				.findFirst().orElse(null).getId();
	}	
	@Override
	public boolean addStudent(Student user) {
		Student exist = getStudentById(user.getId());
		if(exist == null) {
			studentList.add(user);
			save();		//TODO
			return true;
		}
		return false;
	}
	@Override
	public boolean deleteStudentrById(Integer id) {
		// 
		try {
			Iterator<Student> it = studentList.iterator();
			while(it.hasNext()) {
				Student user = it.next();
				if (user.getId().equals(id)) {
					studentList.remove(user);
					save();		//TODO 
					break;
				}
			}
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	@Override
	public boolean deleteStudentByName(String name) {
		try {
			List<Student> result = studentList.stream()
					.filter(user->user.getName().contains(name))
					.collect(Collectors.toList());
			studentList.removeAll(result);
			save();			//TODO
			return true;
		}catch (Exception e) {
			return false;
		}
	}	
	@Override
	public String findStudentById(Integer id) {
		return getStudentById(id).toString();
	}
	@Override
	public String findStudentByName(String name) {
		// 
		List<Student> result = studentList.stream()
				.filter(user->user.getName().contains(name))
				.collect(Collectors.toList());
		
		return result.toString();
	}
	@Override
	public List<Achievement> getAchievementListByName(String stuName){
		Student student = studentList.stream()
				.filter(user->user.getName().contains(stuName))
				.findFirst().orElse(null);
		try {
			return student.getAchiList();
		}catch (NullPointerException e) {
			return null;
		}
	}
	@Override
	public List<Achievement> getAchievementListById(Integer id){
		return getAchievementList(getStudentById(id));
	}
	@Override
	public List<Achievement> getAchievementList(Student student) {
		// 返回某个学生的成绩单
		try {
			return student.getAchiList();
		}catch (NullPointerException e) {
			return null;
		}
	}
	@Override
	public boolean setStudentAchievementById(Integer studentId, Integer courseId, Double grade) {
		for (Student student : studentList) {
			if (student.getId().equals(studentId)) {
				for (Achievement achi : student.getAchiList()) {
					if (achi.getCourse().getCourseId().equals(courseId)) {
						achi.setGrade(grade);
						save();		//TODO
						return true;
					}
				}
			}
		}
		return false;
	}
	@Override
	public String showAll() {
		//  逐个打出每个学生每门课程的分数
		StringBuffer buffer = new StringBuffer();
		studentList.forEach(student->{
			buffer.append(student + "\n");		//给缓冲池添加学生信息
			student.getAchiList().forEach(achi ->{
				buffer.append(achi + "\n");
			});
		});
		
		return buffer.toString();
	}
	@Override
	public boolean removeAllStudent() {
		// 
		try {
			studentList.removeAll(studentList);
			save();
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	@Override
	public Set<Course> getStudentCourseListById(Integer studentId) {
		Student student = getStudentById(studentId);
		Set<Course> chosedCourse = new HashSet<>();
		if (student == null)
			return null;
		for (Course course : courseMap.keySet()) {
			for (Student stu2 : courseMap.get(course)) {
				if (stu2.getId().equals(student.getId())) {
					for (Achievement achi : student.getAchiList()) {
						if (achi.getCourse().getCourseId().equals(course.getCourseId())) {
							chosedCourse.add(course);
							break;
						}
					}
					break;
				}
			}
		}
		return chosedCourse;
	}
	@Override
	public String studentListToString(List<Student> studentList) {
		// 输出所有学生基本信息
		StringBuffer studentBuffer = new StringBuffer();
		studentList.forEach(student->studentBuffer.append(student + "\n"));
	
		return studentBuffer.toString();
	}
	@Override
	public List<Student> getAllStudentList(){
		return this.studentList;
	}
	public List<Student> getAllStudentListCopy(){
		return new ArrayList<Student> (this.studentList);
	}
	@Override
	public boolean loadSystem(String userName, String passWord) {
		// 系统登录
		boolean res = false;
		try {
			for (User user : userList) {
				if (user.getUserName().equals(userName)) {
					if (user.getPassWord().equals(passWord)) {
						loggedUser = user;
						res = true;
					}
				}
			}
		}catch (NullPointerException e) {
			return false;
		}
		return res;
	}
	@Override
	public boolean changeUser(String userName, String passWord) {
		// 更换用户
		boolean res = loadSystem(userName, passWord);
		return res;
	}
	@Override
	public List<Course> getCourseList() {
		// TODO 自动生成的方法存根
		List<Course> cl= new ArrayList<>();
		for(Course course : courseMap.keySet()) {
			cl.add(course);
		}
		return cl;
	}
	@Override
	public String showAllCourses() {
		StringBuffer buffer = new StringBuffer();
		for (Course course : courseMap.keySet()) {
			buffer.append(course.getCourseId() + " ");
			buffer.append(course.getCourseName() + ",");
		}
		return buffer+"";
	}
	@Override
	public boolean removeCourseById(Integer courseId) {
		boolean isSuccess = true;
		for (Course course : courseMap.keySet()) {
			if (course.getCourseId().equals(courseId)) {
				courseMap.remove(course);
				break;
			}
		}
		for (Student student : studentList) {
			Iterator<Achievement> item = student.getAchiList().iterator();
			while (item.hasNext()) {
				Achievement achi = item.next();
				if (achi.getCourse().getCourseId().equals(courseId)) {
					item.remove();
					break;
				}
			}
		}
		save();		//TODO
		return true;
	}
	@Override
	public Course getCourseById(Integer id) {
		// 根据课程号返回课程对象
		for (Course course : courseMap.keySet()) {
			if (course.getCourseId().equals(id)) {
				return course;
			}
		}
		// 没查找到目标课程
		return null;
	}
	@Override
	public boolean addCourseToCourseMap(Course course) {
		boolean isSuccess = true;
		if (courseMap.containsKey(course)) {
			isSuccess = false;
		}else {
			courseMap.put(course, new ArrayList<Student>());
		}
		
		save();		//TODO
		return isSuccess;
	}
	@Override
	public List<Student> getCourseStudentListById(Integer courseId) {
		// 返回选修了该课程的所有学生
		for (Course c : courseMap.keySet()) {
			if (c.getCourseId().equals(courseId)) {
				return new ArrayList<Student> (courseMap.get(c));		//TODO
			}
		}
		return null;
	}
	@Override
	public String addStudentToCourseById(Integer studentId, Integer courseId) {
		String success = "faild";
		for (Course course : courseMap.keySet()) {
			if (course.getCourseId().equals(courseId)) {
				for (Student student : studentList) {
					if (student.getId().equals(studentId)) {
						boolean ifNewAchi = true;
						for (Achievement achi : student.getAchiList()) {
							if (achi.getCourse().getCourseId().equals(courseId)){
								ifNewAchi = false;
							}
						}
						if (ifNewAchi) {
							student.getAchiList().add(new Achievement(course, new Double(0)));
						}
						courseMap.get(course).add(student);
						success = "success";
					}
				}
			}
		}
		save();		//TODO
		return success;
	}
	@Override
	public Set<Course> getOptionalCourseByStudentId(Integer studentId){
		Set<Course> chosedCourse = new HashSet<>();	//已选课程
		Set<Course> allCourse = copyCourseMap(this.courseMap).keySet();	//所有课程
		Student student = getStudentById(studentId);
		if (student == null) {
			System.out.println("没找到该学生 id: " + studentId);
			return null;
		}
		for (Achievement achi : student.getAchiList()) {
			Course itCourse = achi.getCourse();
			for (Course cs : allCourse) {
				if (cs.getCourseId().equals(itCourse.getCourseId())) {
					chosedCourse.add(cs);
					System.out.println(cs.getCourseName());
				}
			}
		}
		allCourse.removeAll(chosedCourse);   //所有课程 - 已选课程 = 可选课程
		return allCourse;
		
	}
	@Override
	public String removeStudentFromCourseById(Integer studentId, Integer courseId) {
		String success = "faild";
		boolean act = false;
		
		//从课程映射中删除学生
		for (Course course : courseMap.keySet()) {				
			if (course.getCourseId().equals(courseId)) {		
				System.out.println(studentId + " @ " + courseId);
				Iterator<Student> studentIterator = courseMap.get(course).iterator();
				while (studentIterator.hasNext()) {
					Student student = studentIterator.next();
					if (student.getId().equals(studentId)) {
						studentIterator.remove();
						act = true;
					}
				}
			}
		}
		//移除学生该门课程的成绩记录
		if(act) {
			for (Student student : studentList) {
				if (student.getId().equals(studentId)) {
					Iterator<Achievement> achiIterator = student.getAchiList().iterator();
					while (achiIterator.hasNext()) {
						Achievement achi = achiIterator.next();
						if (achi.getCourse().getCourseId().equals(courseId)) {
							achiIterator.remove();
							success = "success";
						}
					}
				}
			}
		}
		
		save();		//TODO
		return success;
	}
	@Override
	public List<Achievement> getCourseAchievementListById(Integer courseId) {
		// 返回课程所有成绩
		//根据id找课程
		Course course = null;
		for (Course c : courseMap.keySet()) {
			if (c.getCourseId().equals(courseId)) {
				course = c;
			}else {
				return null;
			}
		}
		//根据课程找成绩表
		List<Achievement> achiList = new ArrayList<>();
		for (Student student : courseMap.get(course)) {
			for (Achievement achi : student.getAchiList()) {
				if (achi.getCourse().getCourseId().equals(course.getCourseId())) {
					achiList.add(achi);
				}
			}
		}
		return achiList;
	}
	public String toString() {
		//
		return studentListToString(studentList);
	}
	@Override
	public Achievement getStudentMaxAchievementById(Integer studentId) {
		Student student = getStudentById(studentId);
		Achievement maxAchi = new Achievement();
		
		if (student == null) {
			return null; 	//没找到该学生
		}else if(student.getAchiList() == null){
			return null;	//该学生目前没有选任何课程
		}else {
			for (Achievement achi : student.getAchiList()) {
				if (achi.getGrade() > maxAchi.getGrade()) {
					maxAchi = achi;
				}
			}
			return maxAchi;
		}
		
	}
	@Override
	public Achievement getStudentMaxAchievementByName(String stuName) {
		Achievement maxAchi = new Achievement();
		Student student = studentList.stream()
				.filter(user->user.getName().contains(stuName))
				.findFirst().orElse(null);
		if (student == null) {
			return null;
		}else if(student.getAchiList() == null) {
			return null;
		}else {
			for (Achievement achi : student.getAchiList()) {
				if (achi.getGrade() > maxAchi.getGrade()) {
					maxAchi = achi;
				}
			}
			return maxAchi;
		}
	}
	@Override
	public Achievement getStudenMinAchievementById(Integer studentId) {
		Student student = getStudentById(studentId);
		Achievement minAchi = new Achievement();
		minAchi.setGrade(100.0);
		
		if (student == null) {
			return null; 	//没找到该学生
		}else if(student.getAchiList() == null){
			return null;	//该学生目前没有选任何课程
		}else {
			for (Achievement achi : student.getAchiList()) {
				if (achi.getGrade() < minAchi.getGrade()) {
					minAchi = achi;
				}
			}
			return minAchi;
		}
	}
	@Override
	public Achievement getStudentMinAchievementByName(String stuName) {
		Achievement minAchi = new Achievement();
		minAchi.setGrade(100.0);
		Student student = studentList.stream()
				.filter(user->user.getName().contains(stuName))
				.findFirst().orElse(null);
		if (student == null) {
			return null;
		}else if(student.getAchiList() == null) {
			return null;
		}else {
			for (Achievement achi : student.getAchiList()) {
				if (achi.getGrade() < minAchi.getGrade()) {
					minAchi = achi;
				}
			}
			return minAchi;
		}
	}
	@Override
	public Double getStudentAvgAchievementById(Integer studentId) {
		Student student = getStudentById(studentId);
		Double grade = 0.0;	//总成绩
		
		if (student == null) {
			return null; 	//没找到该学生
		}else if(student.getAchiList() == null || student.getAchiList().size() == 0){
			return null;	//该学生目前没有选任何课程
		}else {
			for (Achievement achi : student.getAchiList()) {
				grade += achi.getGrade();
			}
			return grade / student.getAchiList().size();
		}
	}
	@Override
	public Double getStudentAvgAchievementByName(String stuName) {
		Student student = studentList.stream()
				.filter(user->user.getName().contains(stuName))
				.findFirst().orElse(null);
		Double grade = 0.0;
		
		if (student == null) {
			return null;
		}else if(student.getAchiList() == null || student.getAchiList().size() == 0){
			return null;	//该学生目前没有选任何课程
		}else {
			for (Achievement achi : student.getAchiList()) {
				grade += achi.getGrade();
			}
			return grade / student.getAchiList().size();
		}
	}
	@Override
	public String showCourseMaxAchievementById(Integer courseId) {
		Course course = null;
		String stuName = null;
		Achievement maxAchi = new Achievement();
		for (Course co : courseMap.keySet()) {
			if (co.getCourseId().equals(courseId)) {
				course = co;
			}
		}
		
		if (course == null) {
			return null;	//没找到课程
		}else if (courseMap.get(course) == null || courseMap.get(course).size() == 0) {
			return null;	//课程下的选课学生数为0
		}else {
			for (Student student : courseMap.get(course)) {			//遍历课程中的选课学生
				for (Achievement stuAchi : student.getAchiList()) {		//遍历每个课程中学生的成绩列表
					if (stuAchi.getCourse().getCourseId().equals(courseId)) {		//找到该门课程
						if (stuAchi.getGrade() > maxAchi.getGrade()) {		//比较判断
							maxAchi.setGrade(stuAchi.getGrade());
							stuName = student.getName();
						}
						break;
					}
				}
			}
			if (stuName == null) {
				return null;
			}else {
				return stuName + " " + maxAchi.getGrade();
			}
		}
	}
	@Override
	public String showCourseMinAchievementById(Integer courseId) {
		Course course = null;
		String stuName = null;
		Double minGrade = 100.0;
		
		for (Course co : courseMap.keySet()) {
			if (co.getCourseId().equals(courseId)) {
				course = co;
			}
		}
		
		if (course == null) {
			return null;	//没找到课程
		}else if (courseMap.get(course) == null || courseMap.get(course).size() == 0) {
			return null;	//课程下的选课学生数为0
		}else {
			for (Student student : courseMap.get(course)) {			//遍历课程中的选课学生
				for (Achievement stuAchi : student.getAchiList()) {		//遍历每个课程中学生的成绩列表
					if (stuAchi.getCourse().getCourseId().equals(courseId)) {		//找到该门课程
						if (stuAchi.getGrade() < minGrade) {		//比较判断
							minGrade = stuAchi.getGrade();
							stuName = student.getName();
						}
						break;
					}
				}
			}
			if (stuName == null) {
				return null;
			}else {
				return stuName + " " + minGrade;
			}
			
		}
	}
	@Override
	public Double showCourseAvgAchievementById(Integer courseId) {
		Course course = null;
		Double grade = new Double(0.0);
		for (Course co : courseMap.keySet()) {
			if (co.getCourseId().equals(courseId)) {
				course = co;
			}
		}
		
		if (course == null) {
			return null;	//没找到课程
		}else if (courseMap.get(course) == null || courseMap.get(course).size() == 0) {
			return null;	//课程下的选课学生数为0
		}else {
			for (Student student : courseMap.get(course)) {			//遍历课程中的选课学生
				for (Achievement stuAchi : student.getAchiList()) {		//遍历每个课程中学生的成绩列表
					if (stuAchi.getCourse().getCourseId().equals(courseId)) {		//找到该门课程
						grade += stuAchi.getGrade();
						break;
					}
				}
			}
			return grade / courseMap.get(course).size();
		}
	}
	@Override
	public String showCourseAllAchiById(Integer courseId){
		Course course = null;
		StringBuffer buffer = new StringBuffer();
		for (Course co : courseMap.keySet()) {
			if (co.getCourseId().equals(courseId)) {
				course = co;
				break;
			}
		}
		if (course == null) {	//没有该课程
			System.out.println("没有该课程");
			return null;
		}else {
			if (courseMap.get(course) == null || courseMap.get(course).size() == 0) {	//该课程没有选择的学生	
				return null;
			}else {
				buffer.append(course.getCourseName() + "成绩如下: " + "\n");
				for (Student student : courseMap.get(course)) {
					if (student.getAchiList() == null || student.getAchiList().size() == 0) {	//该学生没有成绩记录
						System.out.println("该学生没有成绩记录");
						return null;
					}else {
						for (Achievement achi : student.getAchiList()) {
							if (achi.getCourse().getCourseId().equals(courseId)) {
								buffer.append(student.getName() + " " + achi.getGrade() + ",");
							}
						}
					}
					buffer.append("\n");
				}
			}
			return buffer.toString();
		}
	}
	//	深拷贝
	public static Map<Course, List<Student>> copyCourseMap(Map<Course, List<Student>> courseMap){
		Map<Course, List<Student>> mapCopy = courseMap.entrySet().stream()
				.collect(Collectors.toMap(e->e.getKey(),  e -> new ArrayList<Student>(e.getValue())));
		return mapCopy;
	}
	public Map<Course, List<Student>> getCourseMap(){
		return this.courseMap;
	}
	
}
