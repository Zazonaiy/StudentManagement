package StudentSystem2.view;

import javax.swing.JFrame;//基本框架
import java.awt.Toolkit; // 返回本地默认窗口设置
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension; // toolkit返回的包含存屏幕大小信息的对象
import java.awt.EventQueue;
import java.awt.Font; //字体
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import StudentSystem2.controller.Controller;
import StudentSystem2.model.Student;
import StudentSystem2.view.tool.PMessages;
import StudentSystem2.view.tool.PaintGIF;
import StudentSystem2.view.tool.PaintPicture;
import StudentSystem2.view.tool.PaintString;
import StudentSystem2.view.tool.layout.CircleLayout;

import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class Frame extends JFrame implements MyView{
	private int width;
	private int height;
	private Dimension screenSize;
	private Controller controller;
	private String backResult;


	public Frame() {
		backResult = "";
		setTitle("学生管理系统");
		
		// 根据本机系统设置初始化窗口大小
		Toolkit kit = Toolkit.getDefaultToolkit();
		screenSize = kit.getScreenSize();
		this.width = (int) (0.7*screenSize.width);
		this.height = (int) (0.7*screenSize.height);
		setSize(width, height);
		setLocation(new Double(0.14*screenSize.width).intValue(), 
				new Double(0.13*screenSize.height).intValue());
		

		// TODO 使用VMC时要把这儿删了
		//initContentPane();
		
		// 初始化客户端
	}
	
	public void initContentPane() {
		
		
		// 登录界面
		initOnloadPane();
		
		// 主界面
		//initHomePane();  
		
		// 学生列表界面
		//loadStudentListPane();
		
		// 查找学生信息界面
		//loadFindStudentPane();
		
		// 删除学生
		//loadDelStudentPane();
		
		// 更新名单
		//loadUpdatelistPane();
	}
	
	private void initBackgroundColor() {
		this.getContentPane().setBackground(Color.WHITE);
	}
	private void initBackgroundImg() {
		PaintPicture p = new PaintPicture(new Double(0.30*width).intValue(),
				new Double(0.25*height).intValue(),
				new ImageIcon("img/Java.jpg"));
		add(p, 0);
		setVisible(true);
	}
	private void initHellowords() {
		int[] textX = {new Double(0.26*screenSize.width).intValue(),
				new Double(0.32*screenSize.width).intValue(),
				new Double(0.54*screenSize.width).intValue()};
		int[] textY = {new Double(0.08*screenSize.height).intValue(),
				new Double(0.12*screenSize.height).intValue(),
				new Double(0.54*screenSize.height).intValue()};
		String[] textMess = {"欢迎使用学生管理系统", "请登录", "By  杨伟豪"};
		Font[] textFonts = {new Font("仿宋", Font.BOLD, 30),
				new Font("仿宋", Font.BOLD, 26),
				new Font("仿宋", Font.BOLD, 20)};
		add(new PaintString(new PMessages(textX, textY, textMess, textFonts)), 1);
		setVisible(true);
	}
	private void initOnload() {
		JPanel onload = new JPanel();
		
		JLabel userStr = new JLabel("用户名：");  
		JTextField userStrBox = new JTextField(12); 
		JPanel userName = new JPanel();
		userName.add(userStr);
		userName.add(userStrBox);
		userName.setOpaque(false); //透明
		onload.add(userName);
		
		JLabel passStr = new JLabel("密码：");
		JPasswordField passStrBox = new JPasswordField(12);
		JPanel passWord = new JPanel();
		passWord.add(passStr);
		passWord.add(passStrBox);
		passWord.setOpaque(false); //透明
		onload.add(passWord);
		
		JButton button = new JButton("登录");
		button.addActionListener(event -> {
			
			String commond = "loadSystem," + userStrBox.getText() + "," + new String(passStrBox.getPassword());
			controller.receiveCommond(commond);
			if (backResult.equals("success")) {
				loadHomePane();
				
			}else if (backResult.equals("missParams")){
				JOptionPane.showMessageDialog(getContentPane(), "账号或密码不能为空");
			}else {
				JOptionPane.showMessageDialog(getContentPane(), "登录失败");
			}
		});
		onload.add(button);
		onload.setOpaque(false);
		add(onload, 2);
	}
	
	private void initOnloadPane() {
		this.getContentPane().removeAll();
		//绘制背景颜色
		initBackgroundColor();
		// 绘制图片
		initBackgroundImg();	
		// 绘制字符串
		initHellowords();
		// 绘制登录文本框
		initOnload();
	}
	
	// 主界面
	private void initHomePane() {
		this.getContentPane().removeAll();
		
		PaintGIF p = new PaintGIF(new Double(0.44*width).intValue(),
				new Double(0.30*height).intValue(),
				"img/XMB.gif");
		p.setOpaque(false);
		this.getContentPane().add(p);
		
		setVisible(true);
		
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new CircleLayout());
		
		// 创建按钮并绑定事件
		JButton toShowMePane = new JButton("关于ME");
		toShowMePane.addActionListener(event -> loadShowMePane());
		JButton toAchiManagerPane = new JButton("成绩管理");
		toAchiManagerPane.addActionListener(event -> loadAchiManagerPane());
		JButton toCourseManagerPane = new JButton("课程管理");
		toCourseManagerPane.addActionListener(event -> loadCourseManagerPane());
		JButton toStudentManagerPane = new JButton("学生管理");
		toStudentManagerPane.addActionListener(event -> loadStudentManagerPane());
		JButton toChangeUserPane = new JButton("更换用户");
		toChangeUserPane.addActionListener(event -> loadOnloadPane());
		
		buttonPanel.add(toShowMePane);
		buttonPanel.add(toAchiManagerPane);
		buttonPanel.add(toCourseManagerPane);
		buttonPanel.add(toStudentManagerPane);
		buttonPanel.add(toChangeUserPane);
		buttonPanel.setOpaque(false);
		this.getContentPane().add(buttonPanel);
		
		//this.getContentPane().add(new JButton("test"));
	}
	
	private void loadOnloadPane() {
		initOnloadPane();
		repaint(); //重新绘制页面
		validate();  //重新验证所有组件（刷新）
	}
	
	private void loadHomePane() {
		initHomePane();
		repaint(); //重新绘制页面
		validate();  //重新验证所有组件（刷新）
	}
	
	private void loadShowMePane() {
		this.getContentPane().removeAll();
		
		//PaintGIF p = new PaintGIF(new Double(0.20*width).intValue(),
		//		new Double(0.30*height).intValue(),
		//		"img/Java.jpg");
		
		PaintGIF p = new PaintGIF(new Double(0.08*width).intValue(),
				new Double(0.11*height).intValue(),
				"img/rabbit.gif");
		p.setOpaque(false);
		this.getContentPane().add(p);
		
		setVisible(true);
		
		JTextArea showStudent = new JTextArea(8,50);
		showStudent.setLineWrap(true);  //每行超出的文本换行
		showStudent.setEditable(false); //只显示文本不输进行输入
		JScrollPane scrollPane = new JScrollPane(showStudent);
		
		JButton toHome = new JButton("返回");
		toHome.setOpaque(false);
			//给按钮绑定事件
		toHome.addActionListener(event -> loadHomePane());
		
		JPanel studentMessagePane = new JPanel();
		studentMessagePane.setOpaque(false);
		studentMessagePane.setLayout(null);
		studentMessagePane.add(scrollPane);
		scrollPane.setBounds(new Double(0.20*width).intValue(), new Double(0.11*height).intValue(), 
				new Double(0.67*width).intValue(), new Double(0.60*height).intValue());
		
		this.getContentPane().add(toHome, BorderLayout.WEST);
		this.getContentPane().add(studentMessagePane);
		
		repaint(); //重新绘制页面
		validate();  //重新验证所有组件（刷新）
	}
	
	private void loadAchiManagerPane() {
		this.getContentPane().removeAll();
		// 初始化各功能模块
		JPanel buttonPane = new JPanel(); //按钮选择模块
		JPanel selectStudentAchiPane = getAchiManagerSelectAchiByStudentPane(buttonPane);
		
		JPanel updateStudentAchiPane =getAchiManagerUpdateAchiPane(buttonPane);

		
		
		// 返回主菜单按钮
		JButton tohomeButton = new JButton("返回主菜单");
		tohomeButton.setOpaque(false);
		tohomeButton.addActionListener(event -> loadHomePane());
		// 图片
		PaintGIF p = new PaintGIF(new Double(0.20*width).intValue(),
			new Double(0.30*height).intValue(),
			"img/Java.jpg");
		p.setOpaque(false);
		this.getContentPane().add(p);
		setVisible(true);
		this.getContentPane().add(tohomeButton, BorderLayout.WEST);
		setVisible(true);
		
		// 按钮选择模块
		buttonPane.setLayout(null);
		buttonPane.setOpaque(false);

		JButton selectStudentAchi = new JButton("查询学生成绩");
		selectStudentAchi.setOpaque(false);
		JButton selectCourseAchi = new JButton("查询课程成绩");
		selectCourseAchi.setOpaque(false);
		JButton updateStudentAchi = new JButton("修改学生成绩");
		updateStudentAchi.setOpaque(false);
			//设置bounds
		selectStudentAchi.setBounds(new Double(0.61*width).intValue(), new Double(0.08*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		selectCourseAchi.setBounds(new Double(0.61*width).intValue(), new Double(0.36*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		updateStudentAchi.setBounds(new Double(0.61*width).intValue(), new Double(0.64*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		
			//添加按钮事件
		selectStudentAchi.addActionListener(event->{
			this.getContentPane().remove(buttonPane);
			this.getContentPane().add(selectStudentAchiPane);
			repaint(); 
			validate();
		});
		selectCourseAchi.addActionListener(event->{
			controller.receiveCommond("showAllCourses");
			String[] courseItems = this.backResult.split(",");
			JPanel selectCourseAchiPane = getAchiManagerSelectAchiByCoursePane(buttonPane, courseItems);
			this.getContentPane().remove(buttonPane);
			this.getContentPane().add(selectCourseAchiPane);
			repaint();
			validate();
		});
		updateStudentAchi.addActionListener(event->{
			this.getContentPane().remove(buttonPane);
			this.getContentPane().add(updateStudentAchiPane);
			repaint();validate();
		});
		
		
			//添加按钮组件入buttonPane
		buttonPane.add(selectStudentAchi);
		buttonPane.add(selectCourseAchi);
		buttonPane.add(updateStudentAchi);
		// 添加选择模块 至主Pane
		this.getContentPane().add(buttonPane);
		setVisible(true);
		
		
		
		//this.getContentPane().add(addStudentPane);
		
		repaint(); //重新绘制页面
		validate();  //重新验证所有组件（刷新）
	}
	private JPanel getAchiManagerSelectAchiByStudentPane(JPanel buttonPane){
		JPanel pane = new JPanel();
		// 返回框
		JTextArea backMessageBox = new JTextArea(8, 50);
		backMessageBox.setLineWrap(true);
		backMessageBox.setEditable(false);
		JScrollPane backMessagePane = new JScrollPane(backMessageBox);
		backMessagePane.setOpaque(false);
		// 输入学号 
		JTextField inputBox = new JTextField(14);
		inputBox.setOpaque(false);
		JLabel tips = new JLabel("查询依据：");
		tips.setOpaque(false);
		JComboBox<String> checkBox = new JComboBox<>();
		checkBox.addItem("学号");
		checkBox.addItem("姓名");
		checkBox.setOpaque(false);
		JComboBox<String> checkBox2 = new JComboBox<>();
		checkBox2.addItem("所有成绩");
		checkBox2.addItem("最优成绩");
		checkBox2.addItem("最低成绩");
		checkBox2.addItem("平均成绩");
		checkBox2.setOpaque(false);
		JButton confirmButton1 = new JButton("确认查询");
		confirmButton1.setOpaque(false);
		confirmButton1.addActionListener(event->{
			String selectWay = checkBox.getSelectedItem().toString();
			String selectWay2 = checkBox2.getSelectedItem().toString();
			if (selectWay.equals("学号")) {
				if (selectWay2.equals("所有成绩")) {
					controller.receiveCommond("getStudentAllAchiById," + inputBox.getText());
					backMessageBox.setText(this.backResult);
				}else if (selectWay2.equals("最优成绩")) {
					controller.receiveCommond("getStudentMaxAchievementById," + inputBox.getText());
					backMessageBox.setText(this.backResult);
				}else if (selectWay2.equals("最低成绩")) {
					controller.receiveCommond("getStudenMinAchievementById," + inputBox.getText());
					backMessageBox.setText(this.backResult);
				}else if (selectWay2.equals("平均成绩")){
					controller.receiveCommond("getStudentAvgAchievementById," + inputBox.getText());
					backMessageBox.setText(this.backResult);
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "error");
				}
			}else if (selectWay.equals("姓名")) {
				if (selectWay2.equals("所有成绩")) {
					controller.receiveCommond("getStudentAllAchiByName," + inputBox.getText());
					backMessageBox.setText(this.backResult);
				}else if (selectWay2.equals("最优成绩")) {
					controller.receiveCommond("getStudentMaxAchievementByName," + inputBox.getText());
					backMessageBox.setText(this.backResult);
				}else if (selectWay2.equals("最低成绩")) {
					controller.receiveCommond("getStudenMinAchievementByName," + inputBox.getText());
					backMessageBox.setText(this.backResult);
				}else if (selectWay2.equals("平均成绩")){
					controller.receiveCommond("getStudentAvgAchievementByName," + inputBox.getText());
					backMessageBox.setText(this.backResult);
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "error");
				}
			}else {
				JOptionPane.showMessageDialog(getContentPane(), "error");
			}
		});
				
		
		
		
		// 返回按钮
		ImageIcon icon = new ImageIcon("img/backButton.jpg");
		Image img = icon.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);  //缩放图片
		JButton backButton = new JButton(new ImageIcon(img));
			// 获取鼠标焦点时改变样式
		backButton.setBorder(null);// 去边框
		ImageIcon icon2 = new ImageIcon("img/backButton3.jpg");
		Image img2 = icon2.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);
		backButton.setRolloverIcon(new ImageIcon(img2));
		//backButton.setPressedIcon(new ImageIcon(img2));
		backButton.setOpaque(false);
		backButton.addActionListener(event->{
			this.getContentPane().remove(pane);
			this.getContentPane().add(buttonPane);
			repaint();
			validate();
		});
		/*
		
		*/		
		
		
		pane.setLayout(null);
		pane.setOpaque(false);
		pane.add(tips);
		pane.add(inputBox);
		pane.add(checkBox);
		pane.add(checkBox2);
		pane.add(backMessagePane);

		pane.add(confirmButton1);

		pane.add(backButton);
		
		tips.setBounds(new Double(0.61*width).intValue(), new Double(0.02*height).intValue(), 
			new Double(0.05*width).intValue(), new Double(0.03*height).intValue());
		checkBox.setBounds(new Double(0.67*width).intValue(), new Double(0.02*height).intValue(), 
			new Double(0.04*width).intValue(), new Double(0.03*height).intValue());
		checkBox2.setBounds(new Double(0.72*width).intValue(), new Double(0.02*height).intValue(), 
				new Double(0.06*width).intValue(), new Double(0.03*height).intValue());
		inputBox.setBounds(new Double(0.80*width).intValue(), new Double(0.02*height).intValue(), 
				new Double(0.07*width).intValue(), new Double(0.03*height).intValue());
		confirmButton1.setBounds(new Double(0.61*width).intValue(), new Double(0.08*height).intValue(), 
			new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
			
		backMessagePane.setBounds(new Double(0.61*width).intValue(), new Double(0.16*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.28*height).intValue());
		
		
		backButton.setBounds(new Double(0.70*width).intValue(), new Double(0.50*height).intValue(), 
				new Double(0.07*width).intValue(), new Double(0.07*width).intValue());
			
		return pane;
	}
	private JPanel getAchiManagerSelectAchiByCoursePane(JPanel buttonPane, String[] courseItems) {
		//TODO
		JPanel pane = new JPanel();
		pane.setLayout(null);
		pane.setOpaque(false);
		
		// 课程信息显示窗
		JTextArea backMessageBox = new JTextArea(8, 50);
		backMessageBox.setLineWrap(true);
		backMessageBox.setEditable(false);
		JScrollPane backMessagePane = new JScrollPane(backMessageBox);
		backMessagePane.setOpaque(false);
		// 课程选择下拉菜单
		JLabel tips = new JLabel("选择课程");
			//选择查询方式
		JComboBox<String> checkBox = new JComboBox<>();
		checkBox.addItem("所有成绩");
		checkBox.addItem("最优成绩");
		checkBox.addItem("最低成绩");
		checkBox.addItem("平均成绩");
		checkBox.setOpaque(false);
		
		JComboBox<String> checkCourseBox = new JComboBox<>();
		checkCourseBox.setOpaque(false);
		checkCourseBox.setOpaque(false);
		for (String item : courseItems) {
			checkCourseBox.addItem(item);
		}
		tips.setOpaque(false);
		// 确认按钮
		JButton confirmButton = new JButton("确认查询");
		confirmButton.setOpaque(false);
		confirmButton.addActionListener(event->{
			String selectWay = checkBox.getSelectedItem().toString();
			if (selectWay.equals("所有成绩")) {
				controller.receiveCommond("showCourseAllAchiById," + 
						checkCourseBox.getSelectedItem().toString().split(" ")[0]);
				backMessageBox.setText(this.backResult);
			}else if (selectWay.equals("最优成绩")) {
				controller.receiveCommond("showCourseMaxAchievementById," + 
						checkCourseBox.getSelectedItem().toString().split(" ")[0]);
				backMessageBox.setText(this.backResult);
			}else if (selectWay.equals("最低成绩")) {
				controller.receiveCommond("showCourseMinAchievementById," + 
						checkCourseBox.getSelectedItem().toString().split(" ")[0]);
				backMessageBox.setText(this.backResult);
			}else if (selectWay.equals("平均成绩")){
				controller.receiveCommond("showCourseAvgAchievementById," + 
						checkCourseBox.getSelectedItem().toString().split(" ")[0]);
				backMessageBox.setText(this.backResult);
			}else {
				JOptionPane.showMessageDialog(getContentPane(), "error");
			}
		});
		
		// 返回按钮
		ImageIcon icon = new ImageIcon("img/backButton.jpg");
		Image img = icon.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);  //缩放图片
		JButton backButton = new JButton(new ImageIcon(img));
			// 获取鼠标焦点时改变样式
		backButton.setBorder(null);// 去边框
		ImageIcon icon2 = new ImageIcon("img/backButton3.jpg");
		Image img2 = icon2.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);
		backButton.setRolloverIcon(new ImageIcon(img2));
		//backButton.setPressedIcon(new ImageIcon(img2));
		backButton.setOpaque(false);
		backButton.addActionListener(event->{
			this.getContentPane().remove(pane);
			this.getContentPane().add(buttonPane);
			repaint();
			validate();
		});
		
		pane.add(tips);
		pane.add(checkBox);
		pane.add(checkCourseBox);
		pane.add(confirmButton);
		pane.add(backMessagePane);
		pane.add(backButton);
		
		tips.setBounds(new Double(0.61*width).intValue(), new Double(0.02*height).intValue(), 
				new Double(0.05*width).intValue(), new Double(0.03*height).intValue());
		checkBox.setBounds(new Double(0.68*width).intValue(), new Double(0.02*height).intValue(), 
				new Double(0.06*width).intValue(), new Double(0.03*height).intValue());
		checkCourseBox.setBounds(new Double(0.75*width).intValue(), new Double(0.02*height).intValue(), 
				new Double(0.12*width).intValue(), new Double(0.03*height).intValue());
		confirmButton.setBounds(new Double(0.61*width).intValue(), new Double(0.08*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		backMessagePane.setBounds(new Double(0.61*width).intValue(), new Double(0.16*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.54*height).intValue());
		backButton.setBounds(new Double(0.70*width).intValue(), new Double(0.75*height).intValue(), 
				new Double(0.07*width).intValue(), new Double(0.07*width).intValue());
		
		return pane;
	}
	private JPanel getAchiManagerUpdateAchiPane(JPanel buttonPane) {
		JPanel pane = new JPanel();
		// 确认学生 
		JTextField inputStudentBox = new JTextField(14);
		inputStudentBox.setOpaque(false);
		JLabel tips1 = new JLabel("修改依据：");
		tips1.setOpaque(false);
		JComboBox<String> checkBox = new JComboBox<>();
		JComboBox<String> checkCourseBox = new JComboBox<>(); //选择课程下拉菜单
		checkBox.addItem("学号");
		checkBox.addItem("姓名");
		checkBox.setOpaque(false);
		checkCourseBox.setOpaque(false);
		
		String[] commond = new String[4];
		JButton confirmButton1 = new JButton("查找学生");  // TODO 添加按钮事件，用返回的课程信息列表填充下拉菜单
		confirmButton1.setOpaque(false);
		confirmButton1.addActionListener(event->{
			checkCourseBox.removeAllItems();
			String selectWay = checkBox.getSelectedItem().toString();
			if (selectWay.equals("学号")) {
				controller.receiveCommond("getStudentCourseById," + inputStudentBox.getText());
				if (this.backResult.equals("")) {
					JOptionPane.showMessageDialog(getContentPane(), "该学生没有选修任何课程");
				}else if (this.backResult.equals("notFoundStudent")) {
					JOptionPane.showMessageDialog(getContentPane(), "该学生不存在");
				}else if (this.backResult.equals("loseParams")) {
					JOptionPane.showMessageDialog(getContentPane(), "输入栏不能为空");
				}else {
					String[] boxElem = this.backResult.split(",");
					for (String elem : boxElem) {
						checkCourseBox.addItem(elem);
					}
					commond[0] = checkBox.getSelectedItem().toString();
					commond[1] = inputStudentBox.getText();
					inputStudentBox.setText(null);
				
				}
			}else if (selectWay.equals("姓名")) {
				controller.receiveCommond("getStudentCourseByName," + inputStudentBox.getText());
				if (this.backResult.equals("")) {
					JOptionPane.showMessageDialog(getContentPane(), "该学生没有选修任何课程");
				}else if (this.backResult.equals("notFoundStudent")) {
					JOptionPane.showMessageDialog(getContentPane(), "该学生不存在");
				}else if (this.backResult.equals("loseParams")) {
					JOptionPane.showMessageDialog(getContentPane(), "输入栏不能为空");
				}else {
					String[] boxElem = this.backResult.split(",");
					for (String elem : boxElem) {
						checkCourseBox.addItem(elem);
					}
					commond[0] = checkBox.getSelectedItem().toString();
					commond[1] = inputStudentBox.getText();
					inputStudentBox.setText(null);		
				}
			}else {
				JOptionPane.showMessageDialog(getContentPane(), "未知错误");
			}
		});
		
		// 确认修改
		JTextField inputAchiBox = new JTextField(14);
		inputAchiBox.setOpaque(false);
		JLabel tips2 = new JLabel("学生课程：");
		tips2.setOpaque(false);
		JLabel tips3 = new JLabel("设置分数：");
		tips3.setOpaque(false);
		JButton confirmButton2 = new JButton("确认修改");  // TODO 添加按钮事件，用返回的课程信息列表填充下拉菜单
		confirmButton2.setOpaque(false);
		confirmButton2.addActionListener(event->{
			try {
				if (commond[0].equals("学号")) {
					String courseId = checkCourseBox.getSelectedItem().toString().split(" ")[0];
					String grade = inputAchiBox.getText();
					controller.receiveCommond("setStudentAchievementById," + commond[1]
							+ "," + courseId + "," + grade); //学号课程号成绩
					if (this.backResult.equals("success")) {
						JOptionPane.showMessageDialog(getContentPane(), "修改成绩成功");
						inputAchiBox.setText(null);
					}else if (this.backResult.equals("faild")) {
						JOptionPane.showMessageDialog(getContentPane(), "修改成绩失败");
					}else {
						JOptionPane.showMessageDialog(getContentPane(), "未知错误");
					}
				}else if (commond[0].equals("姓名")) {
					controller.receiveCommond("getStudentIdByName," + commond[1]);//通过姓名找ID
					commond[1] = this.backResult;
					String courseId = checkCourseBox.getSelectedItem().toString().split(" ")[0];
					String grade = inputAchiBox.getText();
					controller.receiveCommond("setStudentAchievementById," + commond[1] 
							+ "," + courseId + "," + grade); //学号课程号成绩
					if (this.backResult.equals("success")) {
						JOptionPane.showMessageDialog(getContentPane(), "修改成绩成功");
						inputAchiBox.setText(null);
					}else if (this.backResult.equals("faild")) {
						JOptionPane.showMessageDialog(getContentPane(), "修改成绩失败");
					}else {
						JOptionPane.showMessageDialog(getContentPane(), "未知错误");
					}
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "未知错误");
				}
			}catch (NullPointerException e) {
				JOptionPane.showMessageDialog(getContentPane(), "未选择课程或未设置分数");
			}
			
		});
		
		
		// 返回按钮
		ImageIcon icon = new ImageIcon("img/backButton.jpg");
		Image img = icon.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);  //缩放图片
		JButton backButton = new JButton(new ImageIcon(img));
			// 获取鼠标焦点时改变样式
		backButton.setBorder(null);// 去边框
		ImageIcon icon2 = new ImageIcon("img/backButton3.jpg");
		Image img2 = icon2.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);
		backButton.setRolloverIcon(new ImageIcon(img2));
		//backButton.setPressedIcon(new ImageIcon(img2));
		backButton.setOpaque(false);
		backButton.addActionListener(event->{
			this.getContentPane().remove(pane);
			this.getContentPane().add(buttonPane);
			repaint();
			validate();
		});
		/*
		
		*/		
		
		
		pane.setLayout(null);
		pane.setOpaque(false);
		pane.add(inputStudentBox);
		pane.add(tips1);
		pane.add(checkBox);
		pane.add(checkCourseBox);
		pane.add(inputAchiBox);
		pane.add(tips2);
		pane.add(tips3);

		pane.add(confirmButton1);
		pane.add(confirmButton2);

		pane.add(backButton);
		
		tips1.setBounds(new Double(0.61*width).intValue(), new Double(0.02*height).intValue(), 
			new Double(0.05*width).intValue(), new Double(0.03*height).intValue());
		checkBox.setBounds(new Double(0.67*width).intValue(), new Double(0.02*height).intValue(), 
			new Double(0.04*width).intValue(), new Double(0.03*height).intValue());
		inputStudentBox.setBounds(new Double(0.72*width).intValue(), new Double(0.02*height).intValue(), 
				new Double(0.15*width).intValue(), new Double(0.03*height).intValue());
		confirmButton1.setBounds(new Double(0.61*width).intValue(), new Double(0.08*height).intValue(), 
			new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
			
		tips2.setBounds(new Double(0.61*width).intValue(), new Double(0.16*height).intValue(), 
				new Double(0.05*width).intValue(), new Double(0.03*height).intValue());
		checkCourseBox.setBounds(new Double(0.67*width).intValue(), new Double(0.16*height).intValue(), 
				new Double(0.04*width).intValue(), new Double(0.03*height).intValue());
		tips3.setBounds(new Double(0.72*width).intValue(), new Double(0.16*height).intValue(), 
				new Double(0.06*width).intValue(), new Double(0.03*height).intValue());
		inputAchiBox.setBounds(new Double(0.77*width).intValue(), new Double(0.16*height).intValue(), 
				new Double(0.10*width).intValue(), new Double(0.03*height).intValue());
		confirmButton2.setBounds(new Double(0.61*width).intValue(), new Double(0.21*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		
		backButton.setBounds(new Double(0.70*width).intValue(), new Double(0.50*height).intValue(), 
				new Double(0.07*width).intValue(), new Double(0.07*width).intValue());
			
		return pane;
	}
	
	private void loadStudentManagerPane() {  //TODO
		this.getContentPane().removeAll();
		// 初始化各功能模块
		JPanel buttonPane = new JPanel(); //按钮选择模块
		JPanel addStudentPane = getStudentManagerAddStudentPane();
		JPanel selectStudentPane = getStudentManagerSelectStudentPane(buttonPane);
		JPanel updateStudentPane = getStudentManagerUpdateStudentPane(buttonPane);
		JPanel deleteStudentPane = getStudentManagerDeleteStudentPane(buttonPane);
		
		
		// 返回主菜单按钮
		JButton tohomeButton = new JButton("返回主菜单");
		tohomeButton.setOpaque(false);
		tohomeButton.addActionListener(event -> loadHomePane());
		// 图片
		PaintGIF p = new PaintGIF(new Double(0.20*width).intValue(),
			new Double(0.30*height).intValue(),
			"img/Java.jpg");
		p.setOpaque(false);
		this.getContentPane().add(p);
		setVisible(true);
		this.getContentPane().add(tohomeButton, BorderLayout.WEST);
		setVisible(true);
		
		// 按钮选择模块
		buttonPane.setLayout(null);
		buttonPane.setOpaque(false);
		JButton addStudent = new JButton("添加学生");
		addStudent.setOpaque(false);
		JButton selectStudent = new JButton("查询学生信息");
		selectStudent.setOpaque(false);
		JButton updateStudent = new JButton("修改学生信息");
		updateStudent.setOpaque(false);
		JButton deleteStudent = new JButton("删除学生");
		deleteStudent.setOpaque(false);
			//设置bounds
		addStudent.setBounds(new Double(0.61*width).intValue(), new Double(0.08*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		selectStudent.setBounds(new Double(0.61*width).intValue(), new Double(0.27*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		updateStudent.setBounds(new Double(0.61*width).intValue(), new Double(0.46*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		deleteStudent.setBounds(new Double(0.61*width).intValue(), new Double(0.65*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
			//添加按钮事件
		addStudent.addActionListener(event->{
			this.getContentPane().remove(buttonPane);
			this.getContentPane().add(addStudentPane);
			repaint(); 
			validate();
		});
		selectStudent.addActionListener(event->{
			this.getContentPane().remove(buttonPane);
			this.getContentPane().add(selectStudentPane);
			repaint();
			validate();
		});
		updateStudent.addActionListener(event->{
			this.getContentPane().remove(buttonPane);
			this.getContentPane().add(updateStudentPane);
			repaint();validate();
		});
		deleteStudent.addActionListener(event->{
			this.getContentPane().remove(buttonPane);
			this.getContentPane().add(deleteStudentPane);
			repaint();
			validate();
		});
		
			//添加按钮组件入buttonPane
		buttonPane.add(addStudent);
		buttonPane.add(selectStudent);
		buttonPane.add(updateStudent);
		buttonPane.add(deleteStudent);
		// 添加选择模块 至主Pane
		this.getContentPane().add(buttonPane);
		setVisible(true);
		
		
		
		//this.getContentPane().add(addStudentPane);
		
		repaint(); //重新绘制页面
		validate();  //重新验证所有组件（刷新）
	}
	private JPanel getStudentManagerAddStudentPane() {
		// 于controller的指令
		String[] commond = new String[4];
		commond[0] = "addStudent";
		// 输入学号 
		JTextField inputId = new JTextField(14);
		inputId.setOpaque(false);
		JLabel id = new JLabel("请输入需要插入的学生的学号：");
		id.setOpaque(false);
		JButton confirmButton1 = new JButton("确认");
		confirmButton1.setOpaque(false);
		confirmButton1.addActionListener(event->{
			String input = inputId.getText();
			if(!input.equals("") && input != null) {
				commond[1] = input;
				inputId.setForeground(Color.GRAY);
			}else {
				JOptionPane.showMessageDialog(getContentPane(), "还未输入学号");
			}
		});
				
		// 输入名字 
		JTextField inputName = new JTextField(14);
		inputName.setOpaque(false);
		JLabel name = new JLabel("请输入需要插入的学生的姓名：");
		name.setOpaque(false);
		JButton confirmButton2 = new JButton("确认");
		confirmButton2.setOpaque(false);
		confirmButton2.addActionListener(event->{
			String input = inputName.getText();
			if(input != null && !input.equals("")) {
				commond[2] = input;
				inputName.setForeground(Color.GRAY);
			}else {
				JOptionPane.showMessageDialog(getContentPane(), "还未输入姓名");
			}
		});
				
		// 输入性别 
		JTextField inputSex = new JTextField(14);
		inputSex.setOpaque(false);
		JLabel sex = new JLabel("请输入需要插入的学生的性别：");
		sex.setOpaque(false);
		JButton confirmButton3 = new JButton("确认");
		confirmButton3.setOpaque(false);
		confirmButton3.addActionListener(event->{
			String input = inputSex.getText();
			if (input.equals("男")) {
				commond[3] = "0";
				inputSex.setForeground(Color.GRAY);
			}else if (input.equals("女")) {
				commond[3] = "1";
				inputSex.setForeground(Color.GRAY);
			}else {
				JOptionPane.showMessageDialog(getContentPane(), "请输入正确的性别");
			}
		});
		
		// 提交按钮
		ImageIcon icon3 = new ImageIcon("img/rightButton.jpg");
		Image img3 = icon3.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);
		JButton rightButton = new JButton(new ImageIcon(img3));
		rightButton.setBorder(null);
		ImageIcon icon4 = new ImageIcon("img/rightButton2.jpg");
		Image img4 = icon4.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);
		rightButton.setRolloverIcon(new ImageIcon(img4));
		rightButton.addActionListener(event->{
			try {
				// TODO
				System.out.println(commond[0] + commond[1] + commond[2] + commond[3]);
				controller.receiveCommond(commond);
				
				if (this.backResult.equals("输入错误")) {
					JOptionPane.showMessageDialog(getContentPane(), "请输入完整信息");
				}else if (this.backResult.equals("add faild")){
					JOptionPane.showMessageDialog(getContentPane(), "请勿重复添加");
				}else {
					JOptionPane.showMessageDialog(getContentPane(), this.backResult);
					inputId.setText(null);
					inputId.setForeground(Color.BLACK);
					inputName.setText(null);
					inputName.setForeground(Color.BLACK);
					inputSex.setText(null);
					inputSex.setForeground(Color.BLACK);
				}
			}catch(NullPointerException e) {
				JOptionPane.showMessageDialog(getContentPane(), "请输入完整信息");
			}finally{
				this.backResult = "";
			}
		});
		
		// 返回按钮
		ImageIcon icon = new ImageIcon("img/backButton.jpg");
		Image img = icon.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);  //缩放图片
		JButton backButton = new JButton(new ImageIcon(img));
			// 获取鼠标焦点时改变样式
		backButton.setBorder(null);// 去边框
		ImageIcon icon2 = new ImageIcon("img/backButton3.jpg");
		Image img2 = icon2.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);
		backButton.setRolloverIcon(new ImageIcon(img2));
		//backButton.setPressedIcon(new ImageIcon(img2));
		backButton.setOpaque(false);
		backButton.addActionListener(event->{
			loadStudentManagerPane();
		});
		/*
		JTextArea stuMessage = new JTextArea(8, 50);
		stuMessage.setLineWrap(true);
		//stuMessage.setEditable(false);
		//studentMessage.setOpaque(false);
		JScrollPane stuMessPane = new JScrollPane(stuMessage);
		stuMessPane.setOpaque(false);
		JButton addButton = new JButton("添加");
		addButton.setOpaque(false);
		*/		
		
		JPanel pane = new JPanel();
		pane.setLayout(null);
		pane.setOpaque(false);
		pane.add(id);
		pane.add(inputId);
		pane.add(name);
		pane.add(inputName);
		pane.add(sex);
		pane.add(inputSex);
		pane.add(confirmButton1);
		pane.add(confirmButton2);
		pane.add(confirmButton3);
		pane.add(rightButton);
		pane.add(backButton);
		//pane.add(stuMessPane);
		//pane.add(addButton);
		//TODO pane.add(p);
		
		id.setBounds(new Double(0.61*width).intValue(), new Double(0.02*height).intValue(), 
			new Double(0.15*width).intValue(), new Double(0.03*height).intValue());
		inputId.setBounds(new Double(0.75*width).intValue(), new Double(0.02*height).intValue(), 
			new Double(0.12*width).intValue(), new Double(0.03*height).intValue());
		confirmButton1.setBounds(new Double(0.61*width).intValue(), new Double(0.08*height).intValue(), 
			new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
			
		name.setBounds(new Double(0.61*width).intValue(), new Double(0.16*height).intValue(), 
			new Double(0.15*width).intValue(), new Double(0.03*height).intValue());
		inputName.setBounds(new Double(0.75*width).intValue(), new Double(0.16*height).intValue(), 
			new Double(0.12*width).intValue(), new Double(0.03*height).intValue());
		confirmButton2.setBounds(new Double(0.61*width).intValue(), new Double(0.22*height).intValue(), 
			new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
			
		sex.setBounds(new Double(0.61*width).intValue(), new Double(0.30*height).intValue(), 
			new Double(0.15*width).intValue(), new Double(0.03*height).intValue());
		inputSex.setBounds(new Double(0.75*width).intValue(), new Double(0.30*height).intValue(), 
			new Double(0.12*width).intValue(), new Double(0.03*height).intValue());
		confirmButton3.setBounds(new Double(0.61*width).intValue(), new Double(0.36*height).intValue(), 
			new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		
		rightButton.setBounds(new Double(0.70*width).intValue(), new Double(0.47*height).intValue(), 
				new Double(0.07*width).intValue(), new Double(0.07*width).intValue());
		backButton.setBounds(new Double(0.70*width).intValue(), new Double(0.65*height).intValue(), 
				new Double(0.07*width).intValue(), new Double(0.07*width).intValue());
		
			/*
			stuMessPane.setBounds(new Double(0.60*width).intValue(), new Double(0.16*height).intValue(), 
				new Double(0.27*width).intValue(), new Double(0.55*height).intValue());
			addButton.setBounds(new Double(0.60*width).intValue(), new Double(0.73*height).intValue(), 
				new Double(0.27*width).intValue(), new Double(0.06*height).intValue());
			*/
		return pane;
	}
	private JPanel getStudentManagerSelectStudentPane(JPanel buttonPane) { //查询学生信息
		JPanel pane = new JPanel();
		// 返回框
		JTextArea backMessageBox = new JTextArea(8, 50);
		backMessageBox.setLineWrap(true);
		backMessageBox.setEditable(false);
		JScrollPane backMessagePane = new JScrollPane(backMessageBox);
		backMessagePane.setOpaque(false);
		
		// 输入学号 
		JTextField inputBox = new JTextField(14);
		inputBox.setOpaque(false);
		JLabel tips = new JLabel("查询依据：");
		tips.setOpaque(false);
		JComboBox<String> checkBox = new JComboBox<>();
		checkBox.addItem("学号");
		checkBox.addItem("姓名");
		checkBox.setOpaque(false);
		JButton confirmButton1 = new JButton("确认查询");
		confirmButton1.setOpaque(false);
		confirmButton1.addActionListener(event->{
			String[] commond = new String[2];
			String adopt = checkBox.getSelectedItem().toString();
			if (adopt.equals("学号")) {
				commond[0] = "findStudentById";
			}else if(adopt.equals("姓名")) {
				commond[0] = "findStudentByName";
			}
			commond[1] = inputBox.getText();
			controller.receiveCommond(commond);
			if (backResult.equals("输入错误") || backResult.equals("[]")) {
				backMessageBox.setText("输入错误或查无此人");
			}else {
				backMessageBox.setText(this.backResult);
			}
			
		});
				
		
		
		
		// 返回按钮
		ImageIcon icon = new ImageIcon("img/backButton.jpg");
		Image img = icon.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), icon.getImage().SCALE_DEFAULT);  //缩放图片
		JButton backButton = new JButton(new ImageIcon(img));
			// 获取鼠标焦点时改变样式
		backButton.setBorder(null);// 去边框
		ImageIcon icon2 = new ImageIcon("img/backButton3.jpg");
		Image img2 = icon2.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);
		backButton.setRolloverIcon(new ImageIcon(img2));
		//backButton.setPressedIcon(new ImageIcon(img2));
		backButton.setOpaque(false);
		backButton.addActionListener(event->{
			inputBox.setText(null);
			backMessageBox.setText(null);
			this.getContentPane().remove(pane);
			this.getContentPane().add(buttonPane);
			repaint();
			validate();
		});
		/*
		
		*/		
		
		
		pane.setLayout(null);
		pane.setOpaque(false);
		pane.add(tips);
		pane.add(inputBox);
		pane.add(checkBox);
		pane.add(backMessagePane);

		pane.add(confirmButton1);

		pane.add(backButton);
		
		tips.setBounds(new Double(0.61*width).intValue(), new Double(0.02*height).intValue(), 
			new Double(0.05*width).intValue(), new Double(0.03*height).intValue());
		checkBox.setBounds(new Double(0.67*width).intValue(), new Double(0.02*height).intValue(), 
			new Double(0.04*width).intValue(), new Double(0.03*height).intValue());
		inputBox.setBounds(new Double(0.72*width).intValue(), new Double(0.02*height).intValue(), 
				new Double(0.15*width).intValue(), new Double(0.03*height).intValue());
		confirmButton1.setBounds(new Double(0.61*width).intValue(), new Double(0.08*height).intValue(), 
			new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
			
		backMessagePane.setBounds(new Double(0.61*width).intValue(), new Double(0.16*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.28*height).intValue());
		
		
		backButton.setBounds(new Double(0.70*width).intValue(), new Double(0.50*height).intValue(), 
				new Double(0.07*width).intValue(), new Double(0.07*width).intValue());
			
		return pane;
	}
	
	private JPanel getStudentManagerUpdateStudentPane(JPanel buttonPane) {
		JPanel pane = new JPanel();
		// TODO JPanel changeCoursePane = getStudentManagerUpdateStudentPaneChangeCourse(buttonPane, pane);
		// TODO JPanel changeAchievementPane = getStudentManagerUpdateStudentPaneChangeAchievement(buttonPane, pane);
		// 输入学号 
		JTextField inputId = new JTextField(14);
		inputId.setOpaque(false);
		JLabel id = new JLabel("请输入学生的学号：");
		id.setOpaque(false);
		JButton confirmButton1 = new JButton("确认");
		confirmButton1.setOpaque(false);
		confirmButton1.addActionListener(event->{
					// TODO
			this.backResult = inputId.getText();
			inputId.setForeground(Color.GRAY);
			
		});
		
		// TODO 暂存学号，用于接下来改课或改成绩用
				
		//  修改选课
		JButton confirmButton2 = new JButton("修改选课");
		confirmButton2.setOpaque(false);
		confirmButton2.addActionListener(event->{
			try {
				controller.receiveCommond("findStudentById," + inputId.getText());
				if (this.backResult != "输入错误") {
					//controller.receiveCommond("getStudentCourseById," + inputId.getText()); //TODO
					JPanel changeCoursePane = getStudentManagerUpdateStudentPaneChangeCourse(buttonPane, pane, 
							Integer.valueOf(inputId.getText()));
			
					this.getContentPane().remove(pane);
					this.getContentPane().add(changeCoursePane);
					repaint(); //重新绘制页面
					validate();  //重新验证所有组件（刷新）
					inputId.setText(null);
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "找不到该学生");
				}
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(getContentPane(), "请输入学生id");
			}
		});
				
		// TODO 修改成绩
		JButton confirmButton3 = new JButton("修改成绩");
		confirmButton3.setOpaque(false);
		confirmButton3.addActionListener(event->{
			try {
				controller.receiveCommond("findStudentById," + inputId.getText());
				if (!this.backResult.equals("输入错误")) {
					controller.receiveCommond("getStudentCourseById," + inputId.getText());
					JPanel changeAchievementPane = getStudentManagerUpdateStudentPaneChangeAchievement(buttonPane, pane, 
							Integer.valueOf(inputId.getText()));
					this.getContentPane().remove(pane);
					this.getContentPane().add(changeAchievementPane);
					repaint();
					validate();
					inputId.setText(null);
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "找不到该学生");
				}
			}catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(getContentPane(), "还未输入或确认学生学号");
			}
		});
		
		// 返回按钮
		ImageIcon icon = new ImageIcon("img/backButton.jpg");
		Image img = icon.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);  //缩放图片
		JButton backButton = new JButton(new ImageIcon(img));
			// 获取鼠标焦点时改变样式
		backButton.setBorder(null);// 去边框
		ImageIcon icon2 = new ImageIcon("img/backButton3.jpg");
		Image img2 = icon2.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);
		backButton.setRolloverIcon(new ImageIcon(img2));
		//backButton.setPressedIcon(new ImageIcon(img2));
		backButton.setOpaque(false);
		backButton.addActionListener(event->{
			this.getContentPane().remove(pane);
			this.getContentPane().add(buttonPane);
			repaint();
			validate();
		});
		/*
		JTextArea stuMessage = new JTextArea(8, 50);
		stuMessage.setLineWrap(true);
		//stuMessage.setEditable(false);
		//studentMessage.setOpaque(false);
		JScrollPane stuMessPane = new JScrollPane(stuMessage);
		stuMessPane.setOpaque(false);
		JButton addButton = new JButton("添加");
		addButton.setOpaque(false);
		*/		
		
		
		pane.setLayout(null);
		pane.setOpaque(false);
		pane.add(id);
		pane.add(inputId);
		pane.add(confirmButton1);
		pane.add(confirmButton2);
		pane.add(confirmButton3);
		pane.add(backButton);
		//pane.add(stuMessPane);
		//pane.add(addButton);
		//TODO pane.add(p);
		
		id.setBounds(new Double(0.61*width).intValue(), new Double(0.02*height).intValue(), 
			new Double(0.15*width).intValue(), new Double(0.03*height).intValue());
		inputId.setBounds(new Double(0.75*width).intValue(), new Double(0.02*height).intValue(), 
			new Double(0.12*width).intValue(), new Double(0.03*height).intValue());
		confirmButton1.setBounds(new Double(0.61*width).intValue(), new Double(0.08*height).intValue(), 
			new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
			
		confirmButton2.setBounds(new Double(0.61*width).intValue(), new Double(0.22*height).intValue(), 
			new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
			
		confirmButton3.setBounds(new Double(0.61*width).intValue(), new Double(0.36*height).intValue(), 
			new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		
		backButton.setBounds(new Double(0.70*width).intValue(), new Double(0.50*height).intValue(), 
				new Double(0.07*width).intValue(), new Double(0.07*width).intValue());
			/*
			stuMessPane.setBounds(new Double(0.60*width).intValue(), new Double(0.16*height).intValue(), 
				new Double(0.27*width).intValue(), new Double(0.55*height).intValue());
			addButton.setBounds(new Double(0.60*width).intValue(), new Double(0.73*height).intValue(), 
				new Double(0.27*width).intValue(), new Double(0.06*height).intValue());
			*/
		return pane;
	}
	private JPanel getStudentManagerUpdateStudentPaneChangeCourse(JPanel ButtonPane, JPanel lastPane, 
			Integer studentId) {
		JPanel pane = new JPanel();
		String[] commond = new String[3]; //TODO
		
		// 打印已选课程
		JLabel tips = new JLabel("该学生已选课程如下");
		tips.setOpaque(false);
		JTextArea displayBox = new JTextArea(8, 50);
		displayBox.setLineWrap(true);
		displayBox.setEditable(false);
		//displayBox.setOpaque(false);
		displayBox.setText(this.backResult);
		JScrollPane displayPane = new JScrollPane(displayBox);
		displayPane.setOpaque(false);
		displayBox.setText(this.backResult);
		
		// 添加课程
		JLabel addCourseTipes = new JLabel("请输入添加课程的id");
		addCourseTipes.setOpaque(false);
		JTextField addCourseBox = new JTextField(14);
		addCourseBox.setOpaque(false);
		JButton confirmButton1 = new JButton("确认添加");
		confirmButton1.addActionListener(event->{
			commond[0] = "addStudentToCourseById";
			commond[1] = String.valueOf(studentId);
			commond[2] = addCourseBox.getText();
			controller.receiveCommond(commond);
			if (this.backResult.equals("success")) {
				JOptionPane.showMessageDialog(getContentPane(), "添加课程成功");
			}else if (this.backResult.equals("faild")) {
				JOptionPane.showMessageDialog(getContentPane(), "没有该课课程");
			}else if (this.backResult.equals("lackParams")) {
				JOptionPane.showMessageDialog(getContentPane(), "请输入需要添加课程的id");
			}
			else {
				JOptionPane.showMessageDialog(getContentPane(), "添加出错");
			}
			addCourseBox.setText(null);
		});
		confirmButton1.setOpaque(false);
		JButton getOptionalCourse = new JButton("查看可选课程");
		getOptionalCourse.addActionListener(event->{
			String[] commond2 = new String[2];
			commond2[0] = "getOptionalCourseByStudentId";
			commond2[1] = String.valueOf(studentId);
			controller.receiveCommond(commond2);
			displayBox.setText(this.backResult);
		});
		
		//删除课程
		JLabel removeCourseTipes = new JLabel("请输入删除课程的id");
		removeCourseTipes.setOpaque(false);
		JTextField removeCourseBox = new JTextField(14);
		removeCourseBox.setOpaque(false);
		JButton confirmButton2 = new JButton("确认删除");    //TODO
		confirmButton2.setOpaque(false);
		confirmButton2.addActionListener(event->{
			commond[0] = "removeStudentFromCourseById";
			commond[1] = String.valueOf(studentId);
			commond[2] = removeCourseBox.getText();
			controller.receiveCommond(commond);
			if (this.backResult.equals("success")) {
				JOptionPane.showMessageDialog(getContentPane(), "退课成功");
			}else if (this.backResult.equals("faild")) {
				JOptionPane.showMessageDialog(getContentPane(), "该学生没有选修该课课程");
			}else if (this.backResult.equals("lackParams")) {
				JOptionPane.showMessageDialog(getContentPane(), "请输入需要取消课程的id");
			}
			else {
				JOptionPane.showMessageDialog(getContentPane(), "退课出错");
			}
			removeCourseBox.setText(null);
		});
		JButton getSelectedCourse = new JButton("查看已选课程");
		getSelectedCourse.addActionListener(event->{
			controller.receiveCommond("getStudentCourseById," + Integer.valueOf(studentId));
			if (this.backResult.equals("")) {
				displayBox.setText("null");
			}else {
				displayBox.setText(this.backResult);
			}
			
		});
		
		
		// 返回按钮
		ImageIcon icon = new ImageIcon("img/backButton.jpg");
		Image img = icon.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);  //缩放图片
		JButton backButton = new JButton(new ImageIcon(img));
			// 获取鼠标焦点时改变样式
		backButton.setBorder(null);// 去边框
		ImageIcon icon2 = new ImageIcon("img/backButton3.jpg");
		Image img2 = icon2.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);
		backButton.setRolloverIcon(new ImageIcon(img2));
		//backButton.setPressedIcon(new ImageIcon(img2));
		backButton.setOpaque(false);
		backButton.addActionListener(event->{
			this.getContentPane().remove(pane);
			this.getContentPane().add(lastPane);
			repaint(); //重新绘制页面
			validate();  //重新验证所有组件（刷新）
		});
		/*
		JTextArea stuMessage = new JTextArea(8, 50);
		stuMessage.setLineWrap(true);
		//stuMessage.setEditable(false);
		//studentMessage.setOpaque(false);
		JScrollPane stuMessPane = new JScrollPane(stuMessage);
		stuMessPane.setOpaque(false);
		JButton addButton = new JButton("添加");
		addButton.setOpaque(false);
		*/		
		
		
		pane.setLayout(null);
		pane.setOpaque(false);
		pane.add(tips);
		pane.add(displayPane);
		pane.add(addCourseTipes);
		pane.add(addCourseBox);
		pane.add(getOptionalCourse);
		pane.add(removeCourseTipes);
		pane.add(removeCourseBox);
		pane.add(getSelectedCourse);
		pane.add(confirmButton1);
		pane.add(confirmButton2);
		pane.add(backButton);
		//pane.add(stuMessPane);
		//pane.add(addButton);
		//TODO pane.add(p);
		
		tips.setBounds(new Double(0.61*width).intValue(), new Double(0.02*height).intValue(), 
			new Double(0.15*width).intValue(), new Double(0.03*height).intValue());
		displayPane.setBounds(new Double(0.61*width).intValue(), new Double(0.07*height).intValue(), 
			new Double(0.26*width).intValue(), new Double(0.28*height).intValue());
		
		addCourseTipes.setBounds(new Double(0.61*width).intValue(), new Double(0.38*height).intValue(), 
				new Double(0.15*width).intValue(), new Double(0.03*height).intValue());
		addCourseBox.setBounds(new Double(0.75*width).intValue(), new Double(0.38*height).intValue(), 
				new Double(0.12*width).intValue(), new Double(0.03*height).intValue());
		confirmButton1.setBounds(new Double(0.61*width).intValue(), new Double(0.43*height).intValue(), 
				new Double(0.12*width).intValue(), new Double(0.06*height).intValue());
		getOptionalCourse.setBounds(new Double(0.75*width).intValue(), new Double(0.43*height).intValue(), 
				new Double(0.12*width).intValue(), new Double(0.06*height).intValue());
		
		removeCourseTipes.setBounds(new Double(0.61*width).intValue(), new Double(0.55*height).intValue(), 
				new Double(0.15*width).intValue(), new Double(0.03*height).intValue());
		removeCourseBox.setBounds(new Double(0.75*width).intValue(), new Double(0.55*height).intValue(), 
				new Double(0.12*width).intValue(), new Double(0.03*height).intValue());
		confirmButton2.setBounds(new Double(0.61*width).intValue(), new Double(0.60*height).intValue(), 
			new Double(0.12*width).intValue(), new Double(0.06*height).intValue());
		getSelectedCourse.setBounds(new Double(0.75*width).intValue(), new Double(0.60*height).intValue(), 
				new Double(0.12*width).intValue(), new Double(0.06*height).intValue());
		
		backButton.setBounds(new Double(0.70*width).intValue(), new Double(0.73*height).intValue(), 
				new Double(0.07*width).intValue(), new Double(0.07*width).intValue());
			/*
			stuMessPane.setBounds(new Double(0.60*width).intValue(), new Double(0.16*height).intValue(), 
				new Double(0.27*width).intValue(), new Double(0.55*height).intValue());
			addButton.setBounds(new Double(0.60*width).intValue(), new Double(0.73*height).intValue(), 
				new Double(0.27*width).intValue(), new Double(0.06*height).intValue());
			*/
		return pane;
	}
	private JPanel getStudentManagerUpdateStudentPaneChangeAchievement(JPanel ButtonPane, JPanel lastPane, Integer studentId) {
		JPanel pane = new JPanel();
		// 确认学生 
		//JTextField inputStudentBox = new JTextField(14);
		//inputStudentBox.setOpaque(false);
		//JLabel tips1 = new JLabel("输入学生学号：");
		//tips1.setOpaque(false);
		//JComboBox<String> checkBox = new JComboBox<>();
		JComboBox<String> checkCourseBox = new JComboBox<>(); //选择课程下拉菜单
		//checkBox.addItem("学号");
		//checkBox.addItem("姓名");
		//checkBox.setOpaque(false);
		checkCourseBox.setOpaque(false);
		
		JButton confirmButton1 = new JButton("查找学生");  // TODO 添加按钮事件，用返回的课程信息列表填充下拉菜单
		confirmButton1.setOpaque(false);
		confirmButton1.addActionListener(event->{
			//checkCourseBox.removeAllItems();
			String[] commond = new String[2];
			commond[0] = "getStudentCourseById";
			commond[1] = String.valueOf(studentId);
			controller.receiveCommond(commond);
			if (this.backResult.equals("")) {
				JOptionPane.showMessageDialog(getContentPane(), "该学生没有选修任何课程");
			}else if(this.backResult.equals("notFoundStudent")) {
				JOptionPane.showMessageDialog(getContentPane(), "该学生不在系统中");
			}else if(this.backResult.equals("loseParams")){
				JOptionPane.showMessageDialog(getContentPane(), "输入栏为空");
			}
			else {
				String[] items = backResult.split(",");
				System.out.println(backResult);
				for (String item : items) {
					checkCourseBox.addItem(item);
				}
			}
			// TODO
		});
		
		// 确认修改
		JTextField inputAchiBox = new JTextField(14);
		inputAchiBox.setOpaque(false);
		JLabel tips2 = new JLabel("学生课程：");
		tips2.setOpaque(false);
		JLabel tips3 = new JLabel("设置分数：");
		tips3.setOpaque(false);
		JButton confirmButton2 = new JButton("确认修改");  // TODO 添加按钮事件，用返回的课程信息列表填充下拉菜单
		confirmButton2.setOpaque(false);
		confirmButton2.addActionListener(event->{
			boolean isCheckCourseBoxNull = false;
			if (checkCourseBox.getSelectedItem() == null) {
				isCheckCourseBoxNull = true;
			}
			if (!isCheckCourseBoxNull) {
				
				if (inputAchiBox.getText().replaceAll(" ", "").equals("")) {
					JOptionPane.showMessageDialog(getContentPane(), "还未输入修改的成绩值");
				}else {
					String[] commond = new String[4];
					commond[0] = "setStudentAchievementById";
					commond[1] = String.valueOf(studentId);
					commond[2] = checkCourseBox.getSelectedItem().toString().split(" ")[0];;
					commond[3] = inputAchiBox.getText().replaceAll(" ", "");
					controller.receiveCommond(commond);
					if (this.backResult.equals("success")) {
						JOptionPane.showMessageDialog(getContentPane(), "成绩修改成功");
						inputAchiBox.setText(null);
					}else if (this.backResult.equals("faild")) {
						JOptionPane.showMessageDialog(getContentPane(), "成绩修改失败");
					}else {
						JOptionPane.showMessageDialog(getContentPane(), "error");
					}
				}
			}else {
				JOptionPane.showMessageDialog(getContentPane(), "没有选择课程");
			}
		});
		
		// 返回按钮
		ImageIcon icon = new ImageIcon("img/backButton.jpg");
		Image img = icon.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);  //缩放图片
		JButton backButton = new JButton(new ImageIcon(img));
			// 获取鼠标焦点时改变样式
		backButton.setBorder(null);// 去边框
		ImageIcon icon2 = new ImageIcon("img/backButton3.jpg");
		Image img2 = icon2.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);
		backButton.setRolloverIcon(new ImageIcon(img2));
		//backButton.setPressedIcon(new ImageIcon(img2));
		backButton.setOpaque(false);
		backButton.addActionListener(event->{
			this.getContentPane().remove(pane);
			this.getContentPane().add(lastPane);
			repaint();
			validate();
		});
		/*
		
		*/		
		
		
		pane.setLayout(null);
		pane.setOpaque(false);
	//	pane.add(inputStudentBox);
	//	pane.add(tips1);
	//	pane.add(checkBox);
		pane.add(checkCourseBox);
		pane.add(inputAchiBox);
		pane.add(tips2);
		pane.add(tips3);

		pane.add(confirmButton1);
		pane.add(confirmButton2);

		pane.add(backButton);
		
	//	tips1.setBounds(new Double(0.61*width).intValue(), new Double(0.02*height).intValue(), 
	//		new Double(0.10*width).intValue(), new Double(0.03*height).intValue());
		//checkBox.setBounds(new Double(0.67*width).intValue(), new Double(0.02*height).intValue(), 
			//new Double(0.04*width).intValue(), new Double(0.03*height).intValue());
	//	inputStudentBox.setBounds(new Double(0.72*width).intValue(), new Double(0.02*height).intValue(), 
	//			new Double(0.15*width).intValue(), new Double(0.03*height).intValue());
		confirmButton1.setBounds(new Double(0.61*width).intValue(), new Double(0.02*height).intValue(), 
			new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
			
		tips2.setBounds(new Double(0.61*width).intValue(), new Double(0.16*height).intValue(), 
				new Double(0.05*width).intValue(), new Double(0.03*height).intValue());
		checkCourseBox.setBounds(new Double(0.67*width).intValue(), new Double(0.16*height).intValue(), 
				new Double(0.07*width).intValue(), new Double(0.03*height).intValue());
		tips3.setBounds(new Double(0.76*width).intValue(), new Double(0.16*height).intValue(), 
				new Double(0.05*width).intValue(), new Double(0.03*height).intValue());
		inputAchiBox.setBounds(new Double(0.82*width).intValue(), new Double(0.16*height).intValue(), 
				new Double(0.05*width).intValue(), new Double(0.03*height).intValue());
		confirmButton2.setBounds(new Double(0.61*width).intValue(), new Double(0.21*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		
		backButton.setBounds(new Double(0.70*width).intValue(), new Double(0.50*height).intValue(), 
				new Double(0.07*width).intValue(), new Double(0.07*width).intValue());
			
		return pane;
	}

	private JPanel getStudentManagerDeleteStudentPane(JPanel buttonPane) {
		JPanel pane = new JPanel();
		// 返回框
		JTextArea backMessageBox = new JTextArea(8, 50);
		backMessageBox.setLineWrap(true);
		backMessageBox.setEditable(false);
		JScrollPane backMessagePane = new JScrollPane(backMessageBox);
		backMessagePane.setOpaque(false);
		
		// 输入学号 
		JTextField inputBox = new JTextField(14);
		inputBox.setOpaque(false);
		JLabel tips = new JLabel("删除依据：");
		tips.setOpaque(false);
		JComboBox<String> checkBox = new JComboBox<>();
		checkBox.addItem("学号");
		checkBox.addItem("姓名");
		checkBox.setOpaque(false);
		JButton confirmButton1 = new JButton("确认删除");
		confirmButton1.setOpaque(false);
		confirmButton1.addActionListener(event->{
			if (inputBox.getText() == null) {
				JOptionPane.showMessageDialog(getContentPane(), "请输入需要删除的学生的id");
			}else {
				String[] commond = new String[2];
				if (checkBox.getSelectedItem().toString().equals("学号")) {
					commond[0] = "deleteStudentrById";
					commond[1] = inputBox.getText();
					controller.receiveCommond(commond);
					backMessageBox.setText(this.backResult);
				}else if (checkBox.getSelectedItem().toString().equals("姓名")) {
					commond[0] = "deleteStudentByName";
					commond[1] = inputBox.getText();
					controller.receiveCommond(commond);
					backMessageBox.setText(this.backResult);
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "checkBox error");
				}
				
			}
		});
				
		
		
		
		// 返回按钮
		ImageIcon icon = new ImageIcon("img/backButton.jpg");
		Image img = icon.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), icon.getImage().SCALE_DEFAULT);  //缩放图片
		JButton backButton = new JButton(new ImageIcon(img));
			// 获取鼠标焦点时改变样式
		backButton.setBorder(null);// 去边框
		ImageIcon icon2 = new ImageIcon("img/backButton3.jpg");
		Image img2 = icon2.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);
		backButton.setRolloverIcon(new ImageIcon(img2));
		//backButton.setPressedIcon(new ImageIcon(img2));
		backButton.setOpaque(false);
		backButton.addActionListener(event->{
			this.getContentPane().remove(pane);
			this.getContentPane().add(buttonPane);
			repaint();
			validate();
		});	
		
		
		pane.setLayout(null);
		pane.setOpaque(false);
		pane.add(tips);
		pane.add(inputBox);
		pane.add(checkBox);
		pane.add(backMessagePane);

		pane.add(confirmButton1);

		pane.add(backButton);
		
		tips.setBounds(new Double(0.61*width).intValue(), new Double(0.02*height).intValue(), 
			new Double(0.05*width).intValue(), new Double(0.03*height).intValue());
		checkBox.setBounds(new Double(0.67*width).intValue(), new Double(0.02*height).intValue(), 
			new Double(0.04*width).intValue(), new Double(0.03*height).intValue());
		inputBox.setBounds(new Double(0.72*width).intValue(), new Double(0.02*height).intValue(), 
				new Double(0.15*width).intValue(), new Double(0.03*height).intValue());
		confirmButton1.setBounds(new Double(0.61*width).intValue(), new Double(0.08*height).intValue(), 
			new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
			
		backMessagePane.setBounds(new Double(0.61*width).intValue(), new Double(0.16*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.28*height).intValue());
		
		
		backButton.setBounds(new Double(0.70*width).intValue(), new Double(0.50*height).intValue(), 
				new Double(0.07*width).intValue(), new Double(0.07*width).intValue());
			
		return pane;
	}

	
	private void loadCourseManagerPane() {  //TODO
		this.getContentPane().removeAll();
		// 初始化各功能模块
		JPanel buttonPane = new JPanel(); //按钮选择模块
		JPanel addCoursePane = getCourseManagerAddCoursePane(buttonPane);
		//JPanel selectCoursePane = getCourseManagerSelectCoursePane(buttonPane, courseItems);
		//JPanel updateCoursePane = getCourseManagerUpdateCoursePane(buttonPane, courseItems);
		JPanel deleteCoursePane = getCourseManagerDeleteCoursePane(buttonPane);
		
		
		// 返回主菜单按钮
		JButton tohomeButton = new JButton("返回主菜单");
		tohomeButton.setOpaque(false);
		tohomeButton.addActionListener(event -> loadHomePane());
		// 图片
		PaintGIF p = new PaintGIF(new Double(0.20*width).intValue(),
			new Double(0.30*height).intValue(),
			"img/Java.jpg");
		p.setOpaque(false);
		this.getContentPane().add(p);
		setVisible(true);
		this.getContentPane().add(tohomeButton, BorderLayout.WEST);
		setVisible(true);
		
		// 按钮选择模块
		buttonPane.setLayout(null);
		buttonPane.setOpaque(false);
		JButton addCourse = new JButton("添加课程");
		addCourse.setOpaque(false);
		JButton selectCourse = new JButton("查询课程信息");
		selectCourse.setOpaque(false);
		JButton updateCourse = new JButton("修改课程信息");
		updateCourse.setOpaque(false);
		JButton deleteCourse = new JButton("移除课程");
		deleteCourse.setOpaque(false);
			//设置bounds
		addCourse.setBounds(new Double(0.61*width).intValue(), new Double(0.08*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		selectCourse.setBounds(new Double(0.61*width).intValue(), new Double(0.27*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		updateCourse.setBounds(new Double(0.61*width).intValue(), new Double(0.46*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		deleteCourse.setBounds(new Double(0.61*width).intValue(), new Double(0.65*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
			//添加按钮事件
		addCourse.addActionListener(event->{
			this.getContentPane().remove(buttonPane);
			this.getContentPane().add(addCoursePane);
			repaint(); 
			validate();
		});
		selectCourse.addActionListener(event->{
			controller.receiveCommond("showAllCourses");
			String[] courseItems = this.backResult.split(",");
			JPanel selectCoursePane = getCourseManagerSelectCoursePane(buttonPane, courseItems);
			this.getContentPane().remove(buttonPane);
			this.getContentPane().add(selectCoursePane);
			repaint();
			validate();
		});
		updateCourse.addActionListener(event->{
			controller.receiveCommond("showAllCourses");
			String[] courseItems = this.backResult.split(",");
			JPanel updateCoursePane = getCourseManagerUpdateCoursePane(buttonPane, courseItems);
			this.getContentPane().remove(buttonPane);
			this.getContentPane().add(updateCoursePane);
			repaint();validate();
		});
		deleteCourse.addActionListener(event->{
			this.getContentPane().remove(buttonPane);
			this.getContentPane().add(deleteCoursePane);
			repaint();
			validate();
		});
		
			//添加按钮组件入buttonPane
		buttonPane.add(addCourse);
		buttonPane.add(selectCourse);
		buttonPane.add(updateCourse);
		buttonPane.add(deleteCourse);
		// 添加选择模块 至主Pane
		this.getContentPane().add(buttonPane);
		setVisible(true);
		
		
		
		//this.getContentPane().add(addStudentPane);
		
		repaint(); //重新绘制页面
		validate();  //重新验证所有组件（刷新）
	}
	public JPanel getCourseManagerAddCoursePane(JPanel buttonPane) {
		//TODO
		JPanel pane = new JPanel();
		String[] commond = new String[3];
		commond[0] = "addCourseToCourseMap";
		// 输入课程id
		JTextField inputId = new JTextField(14);
		inputId.setOpaque(false);
		JLabel id = new JLabel("请输入需要添加的课程的课程号：");
		id.setOpaque(false);
		JButton confirmButton1 = new JButton("确认");
		confirmButton1.setOpaque(false);
		confirmButton1.addActionListener(event->{
			commond[1] = inputId.getText();
			inputId.setForeground(Color.GRAY);
		});
				
		// 输入名字 
		JTextField inputName = new JTextField(14);
		inputName.setOpaque(false);
		JLabel name = new JLabel("请输入需要插入的课程的课程名：");
		name.setOpaque(false);
		JButton confirmButton2 = new JButton("确认");
		confirmButton2.setOpaque(false);
		confirmButton2.addActionListener(event->{
			commond[2] = inputName.getText();
			inputName.setForeground(Color.GRAY);
		});
				
		// 确认添加
		ImageIcon icon3 = new ImageIcon("img/rightButton.jpg");
		Image img3 = icon3.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);
		JButton confirmButton3 = new JButton(new ImageIcon(img3));
		confirmButton3.setBorder(null);
		ImageIcon icon4 = new ImageIcon("img/rightButton2.jpg");
		Image img4 = icon4.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);
		confirmButton3.setRolloverIcon(new ImageIcon(img4));
		confirmButton3.setOpaque(false);
		confirmButton3.addActionListener(event->{
			controller.receiveCommond(commond);
			if (this.backResult.equals("success")) {
				JOptionPane.showMessageDialog(getContentPane(), "课程添加成功");
				inputId.setText(null);
				inputId.setForeground(Color.BLACK);
				inputName.setText(null);
				inputName.setForeground(Color.BLACK);
			}else if (this.backResult.equals("faild")) {
				JOptionPane.showMessageDialog(getContentPane(), "课程添加失败");
			}else {
				JOptionPane.showMessageDialog(getContentPane(), "error");
			}
		});
		
		// 返回按钮
		ImageIcon icon = new ImageIcon("img/backButton.jpg");
		Image img = icon.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);  //缩放图片
		JButton backButton = new JButton(new ImageIcon(img));
			// 获取鼠标焦点时改变样式
		backButton.setBorder(null);// 去边框
		ImageIcon icon2 = new ImageIcon("img/backButton3.jpg");
		Image img2 = icon2.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);
		backButton.setRolloverIcon(new ImageIcon(img2));
		//backButton.setPressedIcon(new ImageIcon(img2));
		backButton.setOpaque(false);
		backButton.addActionListener(event->{
			this.getContentPane().remove(pane);
			this.getContentPane().add(buttonPane);
			repaint();
			validate();
		});
	
		
		
		pane.setLayout(null);
		pane.setOpaque(false);
		pane.add(id);
		pane.add(inputId);
		pane.add(name);
		pane.add(inputName);
		pane.add(confirmButton1);
		pane.add(confirmButton2);
		pane.add(confirmButton3);
		pane.add(backButton);
		//pane.add(stuMessPane);
		//pane.add(addButton);
		//TODO pane.add(p);
		
		id.setBounds(new Double(0.61*width).intValue(), new Double(0.02*height).intValue(), 
			new Double(0.15*width).intValue(), new Double(0.03*height).intValue());
		inputId.setBounds(new Double(0.75*width).intValue(), new Double(0.02*height).intValue(), 
			new Double(0.12*width).intValue(), new Double(0.03*height).intValue());
		confirmButton1.setBounds(new Double(0.61*width).intValue(), new Double(0.08*height).intValue(), 
			new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
			
		name.setBounds(new Double(0.61*width).intValue(), new Double(0.16*height).intValue(), 
			new Double(0.15*width).intValue(), new Double(0.03*height).intValue());
		inputName.setBounds(new Double(0.75*width).intValue(), new Double(0.16*height).intValue(), 
			new Double(0.12*width).intValue(), new Double(0.03*height).intValue());
		confirmButton2.setBounds(new Double(0.61*width).intValue(), new Double(0.22*height).intValue(), 
			new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		confirmButton3.setBounds(new Double(0.70*width).intValue(), new Double(0.33*height).intValue(), 
			new Double(0.07*width).intValue(), new Double(0.07*width).intValue());
		
		backButton.setBounds(new Double(0.70*width).intValue(), new Double(0.60*height).intValue(), 
				new Double(0.07*width).intValue(), new Double(0.07*width).intValue());
			/*
			stuMessPane.setBounds(new Double(0.60*width).intValue(), new Double(0.16*height).intValue(), 
				new Double(0.27*width).intValue(), new Double(0.55*height).intValue());
			addButton.setBounds(new Double(0.60*width).intValue(), new Double(0.73*height).intValue(), 
				new Double(0.27*width).intValue(), new Double(0.06*height).intValue());
			*/
		return pane;
	}
	public JPanel getCourseManagerSelectCoursePane(JPanel buttonPane, String[] courseItems) {
		//TODO
		JPanel pane = new JPanel();
		pane.setLayout(null);
		pane.setOpaque(false);
		// 课程选择下拉菜单
		JLabel tips = new JLabel("选择课程");
		JComboBox<String> checkCourseBox = new JComboBox<>();
		checkCourseBox.setOpaque(false);
		for (String item : courseItems) {
			checkCourseBox.addItem(item);
		}
		tips.setOpaque(false);
		// 课程信息显示窗
		JTextArea backMessageBox = new JTextArea(8, 50);
		backMessageBox.setLineWrap(true);
		backMessageBox.setEditable(false);
		JScrollPane backMessagePane = new JScrollPane(backMessageBox);
		backMessagePane.setOpaque(false);
		// 确认按钮
		JButton confirmButton = new JButton("确认查询");
		confirmButton.setOpaque(false);
		confirmButton.addActionListener(event->{
			String[] commond = new String[2];
			commond[0] = "findCourseById";
			String[] courseItem = checkCourseBox.getSelectedItem().toString().split(" ");
			commond[1] = courseItem[0]; // 课程id
			controller.receiveCommond(commond);
			if (this.backResult.equals("空")) {
				backMessageBox.setText("系统未查找到该课程");  //TODO 
			}else {
				backMessageBox.setText(this.backResult);
			}
		});
		
		// 返回按钮
		ImageIcon icon = new ImageIcon("img/backButton.jpg");
		Image img = icon.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);  //缩放图片
		JButton backButton = new JButton(new ImageIcon(img));
			// 获取鼠标焦点时改变样式
		backButton.setBorder(null);// 去边框
		ImageIcon icon2 = new ImageIcon("img/backButton3.jpg");
		Image img2 = icon2.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);
		backButton.setRolloverIcon(new ImageIcon(img2));
		//backButton.setPressedIcon(new ImageIcon(img2));
		backButton.setOpaque(false);
		backButton.addActionListener(event->{
			loadCourseManagerPane();
		});
		
		pane.add(tips);
		pane.add(checkCourseBox);
		pane.add(confirmButton);
		pane.add(backMessagePane);
		pane.add(backButton);
		
		tips.setBounds(new Double(0.61*width).intValue(), new Double(0.02*height).intValue(), 
				new Double(0.15*width).intValue(), new Double(0.03*height).intValue());
		checkCourseBox.setBounds(new Double(0.75*width).intValue(), new Double(0.02*height).intValue(), 
				new Double(0.12*width).intValue(), new Double(0.03*height).intValue());
		confirmButton.setBounds(new Double(0.61*width).intValue(), new Double(0.08*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		backMessagePane.setBounds(new Double(0.61*width).intValue(), new Double(0.16*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.54*height).intValue());
		backButton.setBounds(new Double(0.70*width).intValue(), new Double(0.75*height).intValue(), 
				new Double(0.07*width).intValue(), new Double(0.07*width).intValue());
		
		return pane;
	}
	public JPanel getCourseManagerUpdateCoursePane(JPanel buttonPane, String[] courseItems) {
		//TODO
		JPanel pane = new JPanel();
		pane.setLayout(null);
		pane.setOpaque(false);
		// 课程选择下拉菜单
		JLabel tips = new JLabel("选择课程");
		JComboBox<String> checkCourseBox = new JComboBox<>();
		checkCourseBox.setOpaque(false);
		for (String item : courseItems) {
			checkCourseBox.addItem(item);
		}
		tips.setOpaque(false);
		// 添加学生入课程
		JLabel tips2 = new JLabel("录入学生");
		tips2.setOpaque(false);
		JButton getStudentList = new JButton("获取学生名单");
		getStudentList.setOpaque(false);
		JComboBox<String> checkStudentBox = new JComboBox<>();
		checkStudentBox.setOpaque(false);
		getStudentList.addActionListener(event->{
			checkStudentBox.removeAllItems();
			String[] commond = new String[2];
			commond[0] = "getStudentListNotInCourseById";
			commond[1] = checkCourseBox.getSelectedItem().toString().split(" ")[0];		//课程号
			controller.receiveCommond(commond);
			String[] stuMessList = this.backResult.split(",");
			for (String stuMess : stuMessList) {
				checkStudentBox.addItem(stuMess);
			}
		});
			// 确认 录入 按钮
		JButton confirmButton1 = new JButton("录入");
		confirmButton1.setOpaque(false);
		confirmButton1.addActionListener(event->{
			try {
				String[] commond = new String[3];
				commond[0] = "addStudentToCourseById";
				commond[1] = checkStudentBox.getSelectedItem().toString().split(" ")[0];	//学号
			
				commond[2] = checkCourseBox.getSelectedItem().toString().split(" ")[0];		//课程号
				controller.receiveCommond(commond);
				if (this.backResult.equals("success")) {
					JOptionPane.showMessageDialog(getContentPane(), "选课成功");
					checkStudentBox.removeAllItems();	//清除上次的缓存
					// TODO 判断box2中是否需要加入stu
				}else if (this.backResult.equals("faild")) {
					JOptionPane.showMessageDialog(getContentPane(), "选课失败");
				}else if (this.backResult.equals("lackParams")) {
					JOptionPane.showMessageDialog(getContentPane(), "错误，缺少参数");
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "未知错误");
				}
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(getContentPane(), "请选择学生");
			}
		});
		// 从课程中移除学生
		JLabel tips3 = new JLabel("移除学生");
		tips3.setOpaque(false);
		JButton getStudentList2 = new JButton("获取学生名单");
		getStudentList2.setOpaque(false);
		JComboBox<String> checkStudentBox2 = new JComboBox<>();
		checkStudentBox2.setOpaque(false);
		getStudentList2.addActionListener(event->{
			checkStudentBox2.removeAllItems();
			String[] commond = new String[2];
			commond[0] = "getCourseStudentListById";
			commond[1] = checkCourseBox.getSelectedItem().toString().split(" ")[0];		//课程号
			controller.receiveCommond(commond);
			String[] stuMessList = this.backResult.split(",");
			for (String stuMess : stuMessList) {
				checkStudentBox2.addItem(stuMess);
			}
		});
			// 确认 移除 按钮
		JButton confirmButton2 = new JButton("移除");
		confirmButton2.setOpaque(false);
		confirmButton2.addActionListener(event->{
			try {
				String[] commond = new String[3];
				commond[0] = "removeStudentFromCourseById";
				commond[1] = checkStudentBox2.getSelectedItem().toString().split(" ")[0];	//学号
				commond[2] = checkCourseBox.getSelectedItem().toString().split(" ")[0];		//课程号
				controller.receiveCommond(commond);
				if (this.backResult.equals("success")) {
					JOptionPane.showMessageDialog(getContentPane(), "退课成功");
					checkStudentBox2.removeAllItems();	//清除上次缓存
					//TODO 判断box1中是否需要加入stu
				}else if(this.backResult.equals("faild")) {
					JOptionPane.showMessageDialog(getContentPane(), "退课失败");
				}else if(this.backResult.equals("lackParams")) {
					JOptionPane.showMessageDialog(getContentPane(), "参数错误");
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "未知错误");
				}
			}catch (NullPointerException e) {
				JOptionPane.showMessageDialog(getContentPane(), "请选择需要退课的学生");
			}
		});
		// 修课程名字
		JLabel tips4 = new JLabel("修改课程名：");
		tips4.setOpaque(false);
		JTextField inputBox = new JTextField();
		inputBox.setOpaque(false);
		JButton confirmButton3 = new JButton("确认修改");
		confirmButton3.addActionListener(event->{
			if (inputBox.getText() == null || inputBox.getText().replaceAll(" ", "").equals("")) {
				JOptionPane.showMessageDialog(getContentPane(), "需要修改的课程名为空 或不规范");
			}else {
				String[] commond = new String[3];
				commond[0] = "updateCourseNameById";
				commond[1] = checkCourseBox.getSelectedItem().toString().split(" ")[0];
				commond[2] = inputBox.getText().replaceAll(" ", "");
				controller.receiveCommond(commond);
				if (this.backResult.equals("success")) {
					JOptionPane.showMessageDialog(getContentPane(), "课程名修改成功");
					//刷新课程列表
					controller.receiveCommond("showAllCourses");
					String[] newCourseList = this.backResult.split(",");
					checkCourseBox.removeAllItems();
					for (String newCourse : newCourseList) {
						checkCourseBox.addItem(newCourse);
					}
					//重置输入栏
					inputBox.setText(null);
				}else if (this.backResult.equals("faild")) {
					JOptionPane.showMessageDialog(getContentPane(), "课程名修改失败");
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "未知错误");
				}
			}
			
		});
		
		// 返回按钮
		ImageIcon icon = new ImageIcon("img/backButton.jpg");
		Image img = icon.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);  //缩放图片
		JButton backButton = new JButton(new ImageIcon(img));
			// 获取鼠标焦点时改变样式
		backButton.setBorder(null);// 去边框
		ImageIcon icon2 = new ImageIcon("img/backButton3.jpg");
		Image img2 = icon2.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);
		backButton.setRolloverIcon(new ImageIcon(img2));
		//backButton.setPressedIcon(new ImageIcon(img2));
		backButton.setOpaque(false);
		backButton.addActionListener(event->{
			checkStudentBox.removeAllItems();
			checkStudentBox2.removeAllItems();
			loadCourseManagerPane();
		});
		
		pane.add(tips);
		pane.add(checkCourseBox);
		pane.add(tips2);
		pane.add(getStudentList);
		pane.add(checkStudentBox);
		pane.add(tips3);
		pane.add(getStudentList2);
		pane.add(checkStudentBox2);
		pane.add(tips4);
		pane.add(inputBox);
		
		pane.add(confirmButton1);
		pane.add(confirmButton2);
		pane.add(confirmButton3);
		pane.add(backButton);
		
		tips.setBounds(new Double(0.61*width).intValue(), new Double(0.02*height).intValue(), 
				new Double(0.15*width).intValue(), new Double(0.03*height).intValue());
		checkCourseBox.setBounds(new Double(0.67*width).intValue(), new Double(0.02*height).intValue(), 
				new Double(0.12*width).intValue(), new Double(0.03*height).intValue());
		tips2.setBounds(new Double(0.61*width).intValue(), new Double(0.08*height).intValue(), 
				new Double(0.05*width).intValue(), new Double(0.03*height).intValue());
		getStudentList.setBounds(new Double(0.67*width).intValue(), new Double(0.08*height).intValue(), 
				new Double(0.10*width).intValue(), new Double(0.03*height).intValue());
		checkStudentBox.setBounds(new Double(0.79*width).intValue(), new Double(0.08*height).intValue(), 
				new Double(0.09*width).intValue(), new Double(0.03*height).intValue());
		confirmButton1.setBounds(new Double(0.61*width).intValue(), new Double(0.13*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		tips3.setBounds(new Double(0.61*width).intValue(), new Double(0.22*height).intValue(), 
				new Double(0.05*width).intValue(), new Double(0.03*height).intValue());
		getStudentList2.setBounds(new Double(0.67*width).intValue(), new Double(0.22*height).intValue(), 
				new Double(0.10*width).intValue(), new Double(0.03*height).intValue());
		checkStudentBox2.setBounds(new Double(0.79*width).intValue(), new Double(0.22*height).intValue(), 
				new Double(0.09*width).intValue(), new Double(0.03*height).intValue());
		confirmButton2.setBounds(new Double(0.61*width).intValue(), new Double(0.27*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		tips4.setBounds(new Double(0.61*width).intValue(), new Double(0.36*height).intValue(), 
				new Double(0.07*width).intValue(), new Double(0.03*height).intValue());
		inputBox.setBounds(new Double(0.69*width).intValue(), new Double(0.36*height).intValue(), 
				new Double(0.10*width).intValue(), new Double(0.03*height).intValue());
		confirmButton3.setBounds(new Double(0.61*width).intValue(), new Double(0.41*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		
		backButton.setBounds(new Double(0.70*width).intValue(), new Double(0.50*height).intValue(), 
				new Double(0.07*width).intValue(), new Double(0.07*width).intValue());
		
		return pane;
	}
	public JPanel getCourseManagerDeleteCoursePane(JPanel buttonPane) {
		//TODO
		JPanel pane = new JPanel();
		pane.setOpaque(false);
		pane.setLayout(null);
		
		// 信息显示床
		JTextArea showMessageBox = new JTextArea(8, 50);
		showMessageBox.setLineWrap(true);
		showMessageBox.setEditable(false);
		//studentMessage.setOpaque(false);
		JScrollPane showMessPane = new JScrollPane(showMessageBox);
		showMessPane.setOpaque(false);
		// 显示目前可删除课程
		controller.receiveCommond("showAllCourses");
		String courseMess = this.backResult.replaceAll(",", "\n");
		showMessageBox.setText(courseMess);
		
		// 移除课程
		JLabel tips = new JLabel("请输入需要移除课程的id：");
		tips.setOpaque(false);
		JTextField inputBox = new JTextField();
		inputBox.setOpaque(false);
		JButton confirmButton = new JButton("确认移除");
		confirmButton.setOpaque(false);
		confirmButton.addActionListener(event->{
			String[] commond = new String[2];
			commond[0] = "removeCourseById";
			commond[1] = inputBox.getText().replaceAll(" ", "");
			controller.receiveCommond(commond);
			if (this.backResult.equals("success")) {
				JOptionPane.showMessageDialog(getContentPane(), "课程成功从系统中移除");
				//清空输入栏
				inputBox.setText(null);
				//刷新视窗显示的可删除课程
				controller.receiveCommond("showAllCourses");
				String courseMess2 = this.backResult.replaceAll(",", "\n");
				showMessageBox.setText(courseMess2);
			}else if (this.backResult.equals("faild")) {
				JOptionPane.showMessageDialog(getContentPane(), "课程移除操作失败");
			}else {
				JOptionPane.showMessageDialog(getContentPane(), "未知错误");
			}
		});
		
		//返回按钮
		ImageIcon icon = new ImageIcon("img/backButton.jpg");
		Image img = icon.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);  //缩放图片
		JButton backButton = new JButton(new ImageIcon(img));
			// 获取鼠标焦点时改变样式
		backButton.setBorder(null);// 去边框
		ImageIcon icon2 = new ImageIcon("img/backButton3.jpg");
		Image img2 = icon2.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);
		backButton.setRolloverIcon(new ImageIcon(img2));
		//backButton.setPressedIcon(new ImageIcon(img2));
		backButton.setOpaque(false);
		backButton.addActionListener(event->{
			loadCourseManagerPane();
		});
		
		pane.add(showMessPane);
		pane.add(tips);
		pane.add(inputBox);
		pane.add(confirmButton);
		pane.add(backButton);
		
		showMessPane.setBounds(new Double(0.61*width).intValue(), new Double(0.02*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.50*height).intValue());
		tips.setBounds(new Double(0.61*width).intValue(), new Double(0.54*height).intValue(), 
				new Double(0.12*width).intValue(), new Double(0.03*height).intValue());
		inputBox.setBounds(new Double(0.75*width).intValue(), new Double(0.54*height).intValue(), 
				new Double(0.12*width).intValue(), new Double(0.03*height).intValue());
		confirmButton.setBounds(new Double(0.61*width).intValue(), new Double(0.59*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		backButton.setBounds(new Double(0.70*width).intValue(), new Double(0.70*height).intValue(), 
				new Double(0.07*width).intValue(), new Double(0.07*width).intValue());
		
		return pane;
	}
	
	@Override
	public void index(Controller controller) {
		// TODO 
		this.initContentPane();
		EventQueue.invokeLater(() ->{
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置默认关闭操作为关闭
			this.setVisible(true);  //更具布尔参数选择是否隐藏窗口
			this.controller = controller;
		});
		
	}

	@Override
	public void showResult(String res) {
		// 获得返回结果
		this.resetBackResult();
		this.backResult = res;
		
	}
	
	public void resetBackResult() {
		this.backResult = "";
	}
}
