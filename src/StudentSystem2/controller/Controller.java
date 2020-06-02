package StudentSystem2.controller;

import java.util.List;
import java.util.Set;

import StudentSystem2.model.Achievement;
import StudentSystem2.model.Course;
import StudentSystem2.model.Student;
import StudentSystem2.server.Manager;
import StudentSystem2.view.MyView;


public class Controller {
	private MyView view;
	private Manager server;
	
	public Controller(MyView view, Manager server) {
		this.view = view;
		this.server = server;
		this.start();
	}
	
	private void start() {
		view.index(this);
		
	}
	
	public void receiveCommond(String commond) {
		
		exeCommond(getParams(commond));
	}
	public void receiveCommond(String[] commond) {
		exeCommond(commond);
	}
	
	public void exeCommond(String[] params) {
		String res = "命令未输入或未成功执行";
		
		//选择方法并执行
		switch (params[0]) {
			
			case "addStudent":{
				try {
					boolean isSuccess = server.addStudent(new Student(Integer.valueOf(params[1]),  
							params[2], 		//id,姓名,性别
							Integer.valueOf(params[3])));
					if(isSuccess) {
						res = "add success";
						break;
					}
					res = "add faild";
					break;
				}catch (Exception e) {
					res = "输入错误";
					break;
				}
			}
			case "deleteStudentrById":{
				try {
					if (server.deleteStudentrById(Integer.valueOf(params[1]))) {
						res = "delete " + params[1];
					}
					break;
				}catch (Exception e) {
					res = "输入错误";
					break;
				}
			}
			case "deleteStudentByName":{
				try {
					if(server.deleteStudentByName(params[1])) {
						res = "delete " + params[1];
					}
					break;
				}catch (Exception e) {
					res = "输入错误";
					break;
				}
			}
			case "findStudentById":{
				try {
					Integer id = Integer.valueOf(params[1]);
					res = server.findStudentById(id);
					break;
				}catch(Exception e) {
					res = "输入错误";
					break;
				}
				
			}
			case "findStudentByName":{
				try {
					res = server.findStudentByName(params[1]);
					break;
				}catch(Exception e) {
					res = "输入错误";
					break;
				}
			}
			case "addStudentToCourseById":{
				try {
					res = server.addStudentToCourseById(Integer.valueOf(params[1]), Integer.valueOf(params[2]));
					break;
				}catch(NumberFormatException e) {
					res = "lackParams";
					break;
				}
			}
			case "removeStudentFromCourseById":{
				try {
					res = server.removeStudentFromCourseById(Integer.valueOf(params[1]), Integer.valueOf(params[2]));
					break;
				}catch(NumberFormatException e) {
					res = "lackParams";
					break;
				}
			}
			case "getOptionalCourseByStudentId":{
				
				Set<Course> optionalCourse = server.getOptionalCourseByStudentId(Integer.valueOf(params[1]));
				if (optionalCourse == null) {
					res = "null";
					break;
				}
				StringBuffer buffer = new StringBuffer();
				for (Course course : optionalCourse) {
					buffer.append(course+", ");
				}
				res = buffer.toString();
				break;
			}
			case "getAchievementListById":{
				res = server.getAchievementListById(Integer.valueOf(params[1])) + "";
				break;
			}
			case "setStudentAchievementById":{
				boolean isSuccess = server.setStudentAchievementById(Integer.valueOf(params[1]), 
						Integer.valueOf(params[2]), Double.valueOf(params[3]));
				if (isSuccess) {
					res = "success";
				}else {
					res = "faild";
				}
				break;
				// TODO
			}
			case "getStudentIdByName" :{
				res = String.valueOf(server.getStudentIdByName(params[1]));
			}
			case "getStudentCourseById":{
				boolean isNull = false;
				try {
					List<Achievement> achiList = server.getAchievementListById(Integer.valueOf(params[1]));
					if (achiList == null || achiList.size() == 0) {
						isNull = true;
					}
					try {
						StringBuffer buffer = new StringBuffer();
						achiList.forEach(achi->buffer.append(achi.getCourse().getCourseId() + " " +
								achi.getCourse().getCourseName() + ","));

						if (isNull) {
							res = "";
						}else {
							res = buffer.toString();
						}
				
						break;
					}catch (NullPointerException e) {
						res = "notFoundStudent";
						break;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					res = "loseParams";
					break;
				}
			}case "getStudentCourseByName" : {
				boolean isNull = false;
				List<Achievement> achiList = server.getAchievementListByName(params[1]);
				if (achiList == null || achiList.size() == 0) {
					isNull = true;
				}
				try {
					StringBuffer buffer = new StringBuffer();
					achiList.forEach(achi->buffer.append(achi.getCourse().getCourseId() + " " +
							achi.getCourse().getCourseName() + ","));

					if (isNull) {
						res = "";
					}else {
						res = buffer.toString();
					}
				
					break;
				}catch (NullPointerException e) {
					res = "notFoundStudent";
					break;
				}
			}
			case "showAll":{
				try {
					res = server.showAll();
					if (res.equals("")) res = "用户列表是空的";
					break;
				}catch (Exception e) {
					res = "error";
					break;
				}
			}
			case "removeAllStudent":{
				try {
					if(server.removeAllStudent()) {
						res = "学生列表已清空";
					}
					break;
				}catch (Exception e) {
					res = "error";
					break;
				}
			}
			case "loadSystem":{
				res = "error";
				try {
					if (server.loadSystem(params[1], params[2])) {
						res = "success";
					}
					break;
				}catch (Exception e) {
					res = "missParams";
					break;
				}
			}
			case "changeUser":{
				try {
					res = "error";
					if (server.changeUser(params[1], params[2])) {
						res = "success";
					}
					break;
				}catch (Exception e) {
					res = "missParams";
					break;
				}
			}
			case "getCourseList":{
				try {
					res = server.getCourseList() + "";
					break;
				}catch (Exception e) {
					res = "error";
					break;
				}
			}
			case "getCourseById":{
				try {
					Course course = server.getCourseById(Integer.valueOf(params[1]));
					if (course == null) {
						res = "空";
					}else {
						res = course + "";
					}
					break;
				}catch (Exception e) {
					res = "error";
					break;
				}
			}
			case "updateCourseNameById":{
				try {
					Course course = server.getCourseById(Integer.valueOf(params[1]));		//根据课程id找课程
					if (course == null) {
						res = "faild";
					}else {
						//修改courseMap中的课程名
						course.setCourseName(params[2]);				//找到课程改名字
						//修改学生成绩信息里的课程名
						for (Student student : server.getAllStudentList()) {
							for (Achievement achi : student.getAchiList()) {
								if (achi.getCourse().getCourseId().equals(params[1])) {
									achi.getCourse().setCourseName(params[2]);
									break;
								}
							}
						}
						res = "success";
						
					}
					break;
				}catch (Exception e) {
					res = "error";
					break;
				}
				
			}
			case "findCourseById":{
				try {
					Course course = server.getCourseById(Integer.valueOf(params[1]));
					if (course == null) {
						res = "空";
					}else {
						res = "课程号: " + course.getCourseId() + ",课程名: " + course.getCourseName() + "\n" + "选课学生: ";
						List<Student> studentList= server.getCourseStudentListById(Integer.valueOf(params[1]));
						if (studentList != null && studentList.size() != 0) {
							for (Student student : studentList) {
								res += student.getId() + "-" + student.getName() + ", ";
							}
						}else {
							res += "null";
						}
					}
					break;
				}catch (Exception e) {
					res = "error";
					break;
				}
			}
			case "removeCourseById":{
				boolean isSuccess = server.removeCourseById(Integer.valueOf(params[1]));
				if (isSuccess) {
					res = "success";
				}else {
					res = "faild";
				}
				break;
			}
			case "showAllCourses":{
				res = server.showAllCourses();
				break;
			}
			case "getCourseStudentListById":{
				List<Student> stuList = server.getCourseStudentListById(Integer.valueOf(params[1]));
				if (stuList != null) { //
					StringBuffer buffer = new StringBuffer();
					for (Student student : stuList) {
						buffer.append(student.getId() + " " + student.getName() + ",");
					}
					res =  buffer.toString();
				}else {
					res = "error";
				}
				break;
			}
			case "getStudentListNotInCourseById":{
				res = "error";
				List<Student> inCourseStuList = server.getCourseStudentListById(Integer.valueOf(params[1]));
				List<Student> stuList = server.getAllStudentListCopy();
				stuList.removeAll(inCourseStuList);
				StringBuffer buffer = new StringBuffer();
				if (stuList != null){
					for (Student student : stuList) {
						buffer.append(student.getId() + " " + student.getName() + ",");
					}
					res = buffer.toString();
				}
				
				break;
			}
			case "getCourseAchievementListById":{
				if (server.getCourseAchievementListById(Integer.valueOf(params[1])) != null) {
					res = server.getCourseAchievementListById(Integer.valueOf(params[1])) + "";
				}else {
					res = "error";
				}
				break;
			}
			case "addCourseToCourseMap":{
				boolean isSuccess = server.addCourseToCourseMap(new Course(Integer.valueOf(params[1]), params[2]));
				if (isSuccess) {
					res = "success";
				}else {
					res = "faild";
				}
				break;
			}
			case "getStudentAllAchiById":{
				try {
					StringBuffer buffer = new StringBuffer();
					List<Achievement> achiList = server.getAchievementListById(Integer.valueOf(params[1]));
					if (achiList == null || achiList.size() == 0) {
						res = "该学生没有选修的课程";
					}else {
						for (Achievement achi : achiList) {
							buffer.append(achi.getCourse().getCourseName() + " " + achi.getGrade() + "," + "\n");
						}
						res = buffer.toString();
					}
					break;
				}catch (NumberFormatException e) {
					res = "通过学号查找，你这输入的是学号吗?";
					break;
				}
			}case "getStudentMaxAchievementById":{
				try {
					Achievement achi = server.getStudentMaxAchievementById(Integer.valueOf(params[1]));
					if (achi == null) {
						res = "没有找到该学生或该学生的选课为空";
					}else {
						res = achi.getCourse().getCourseName() + " " + achi.getGrade() + "\n";
					}
					break;
				}catch (NumberFormatException e) {
					res = "通过学号查找，你这输入的是学号吗?";
					break;
				}
			}case "getStudenMinAchievementById":{
				try {
					Achievement achi = server.getStudenMinAchievementById(Integer.valueOf(params[1]));
					if (achi == null) {
						res = "没有找到该学生或该学生的选课为空";
					}else {
						res = achi.getCourse().getCourseName() + " " + achi.getGrade() + "\n";
					}
					break;
				}catch (NumberFormatException e) {
					res = "通过学号查找，你这输入的是学号吗?";
					break;
				}
			}case "getStudentAvgAchievementById":{
				try {
					Double avgGrade = server.getStudentAvgAchievementById(Integer.valueOf(params[1]));
					if (avgGrade == null) {
						res = "没有找到该学生或该学生的选课为空";
					}else {
						res = "平均成绩： " + avgGrade;
					}
					break;
				}catch (NumberFormatException e) {
					res = "通过学号查找，你这输入的是学号吗?";
					break;
				}
				
			}case "getStudentAllAchiByName":{
				StringBuffer buffer = new StringBuffer();
				List<Achievement> achiList = server.getAchievementListByName(params[1]);
				if (achiList == null || achiList.size() == 0) {
					res = "该学生没有选修的课程";
				}else {
					for (Achievement achi : achiList) {
						buffer.append(achi.getCourse().getCourseName() + " " + achi.getGrade() + "," + "\n");
					}
					res = buffer.toString();
				}
				break;
			}case "getStudentMaxAchievementByName":{
				Achievement achi = server.getStudentMaxAchievementByName(params[1]);
				if (achi == null) {
					res = "没有找到该学生或该学生的选课为空";
				}else {
					res = achi.getCourse().getCourseName() + " " + achi.getGrade() + "\n";
				}
				break;
			}case "getStudenMinAchievementByName":{
				Achievement achi = server.getStudentMinAchievementByName(params[1]);
				if (achi == null) {
					res = "没有找到该学生或该学生的选课为空";
				}else {
					res = achi.getCourse().getCourseName() + " " + achi.getGrade() + "\n";
				}
				break;
			}case "getStudentAvgAchievementByName":{
				Double avgGrade = server.getStudentAvgAchievementByName(params[1]);
				if (avgGrade == null) {
					res = "没有找到该学生或该学生的选课为空";
				}else {
					res = "平均成绩： " + avgGrade;
				}
				break;
			}case "showCourseAllAchiById":{
				res = server.showCourseAllAchiById(Integer.valueOf(params[1]));
				break;
			}case "showCourseMaxAchievementById":{
				String maxGrade = server.showCourseMaxAchievementById(Integer.valueOf(params[1]));
				if (maxGrade == null) {
					res = "系统中没有该课程或该课程没有选课的学生";
				}else {
					res = "最优成绩: " + maxGrade; 
				}
				break;
			}case "showCourseMinAchievementById":{
				String minGrade = server.showCourseMinAchievementById(Integer.valueOf(params[1]));
				if (minGrade == null) {
					res = "系统中没有该课程或该课程没有选课的学生";
				}else {
					res = "最低成绩: " + minGrade; 
				}
				break;
			}case "showCourseAvgAchievementById":{
				Double maxGrade = server.showCourseAvgAchievementById(Integer.valueOf(params[1]));
				if (maxGrade == null) {
					res = "系统中没有该课程或该课程没有选课的学生";
				}else {
					res = "平均成绩: " + maxGrade; 
				}
				break;
			}
		}
			
		
		view.showResult(res);
	}
	
	public String[] getParams(String commond) {
		String[] res = commond.replaceAll(" ","").split(",");
		for (String s : res) {
			System.out.println(s); //TODO 待测试完毕删除
		}
		return res;
	}
}
