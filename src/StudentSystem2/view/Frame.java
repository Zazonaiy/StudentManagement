package StudentSystem2.view;

import javax.swing.JFrame;//�������
import java.awt.Toolkit; // ���ر���Ĭ�ϴ�������
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension; // toolkit���صİ�������Ļ��С��Ϣ�Ķ���
import java.awt.EventQueue;
import java.awt.Font; //����
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
		setTitle("ѧ������ϵͳ");
		
		// ���ݱ���ϵͳ���ó�ʼ�����ڴ�С
		Toolkit kit = Toolkit.getDefaultToolkit();
		screenSize = kit.getScreenSize();
		this.width = (int) (0.7*screenSize.width);
		this.height = (int) (0.7*screenSize.height);
		setSize(width, height);
		setLocation(new Double(0.14*screenSize.width).intValue(), 
				new Double(0.13*screenSize.height).intValue());
		

		// TODO ʹ��VMCʱҪ�����ɾ��
		//initContentPane();
		
		// ��ʼ���ͻ���
	}
	
	public void initContentPane() {
		
		
		// ��¼����
		initOnloadPane();
		
		// ������
		//initHomePane();  
		
		// ѧ���б����
		//loadStudentListPane();
		
		// ����ѧ����Ϣ����
		//loadFindStudentPane();
		
		// ɾ��ѧ��
		//loadDelStudentPane();
		
		// ��������
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
		String[] textMess = {"��ӭʹ��ѧ������ϵͳ", "���¼", "By  ��ΰ��"};
		Font[] textFonts = {new Font("����", Font.BOLD, 30),
				new Font("����", Font.BOLD, 26),
				new Font("����", Font.BOLD, 20)};
		add(new PaintString(new PMessages(textX, textY, textMess, textFonts)), 1);
		setVisible(true);
	}
	private void initOnload() {
		JPanel onload = new JPanel();
		
		JLabel userStr = new JLabel("�û�����");  
		JTextField userStrBox = new JTextField(12); 
		JPanel userName = new JPanel();
		userName.add(userStr);
		userName.add(userStrBox);
		userName.setOpaque(false); //͸��
		onload.add(userName);
		
		JLabel passStr = new JLabel("���룺");
		JPasswordField passStrBox = new JPasswordField(12);
		JPanel passWord = new JPanel();
		passWord.add(passStr);
		passWord.add(passStrBox);
		passWord.setOpaque(false); //͸��
		onload.add(passWord);
		
		JButton button = new JButton("��¼");
		button.addActionListener(event -> {
			
			String commond = "loadSystem," + userStrBox.getText() + "," + new String(passStrBox.getPassword());
			controller.receiveCommond(commond);
			if (backResult.equals("success")) {
				loadHomePane();
				
			}else if (backResult.equals("missParams")){
				JOptionPane.showMessageDialog(getContentPane(), "�˺Ż����벻��Ϊ��");
			}else {
				JOptionPane.showMessageDialog(getContentPane(), "��¼ʧ��");
			}
		});
		onload.add(button);
		onload.setOpaque(false);
		add(onload, 2);
	}
	
	private void initOnloadPane() {
		this.getContentPane().removeAll();
		//���Ʊ�����ɫ
		initBackgroundColor();
		// ����ͼƬ
		initBackgroundImg();	
		// �����ַ���
		initHellowords();
		// ���Ƶ�¼�ı���
		initOnload();
	}
	
	// ������
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
		
		// ������ť�����¼�
		JButton toShowMePane = new JButton("����ME");
		toShowMePane.addActionListener(event -> loadShowMePane());
		JButton toAchiManagerPane = new JButton("�ɼ�����");
		toAchiManagerPane.addActionListener(event -> loadAchiManagerPane());
		JButton toCourseManagerPane = new JButton("�γ̹���");
		toCourseManagerPane.addActionListener(event -> loadCourseManagerPane());
		JButton toStudentManagerPane = new JButton("ѧ������");
		toStudentManagerPane.addActionListener(event -> loadStudentManagerPane());
		JButton toChangeUserPane = new JButton("�����û�");
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
		repaint(); //���»���ҳ��
		validate();  //������֤���������ˢ�£�
	}
	
	private void loadHomePane() {
		initHomePane();
		repaint(); //���»���ҳ��
		validate();  //������֤���������ˢ�£�
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
		showStudent.setLineWrap(true);  //ÿ�г������ı�����
		showStudent.setEditable(false); //ֻ��ʾ�ı������������
		JScrollPane scrollPane = new JScrollPane(showStudent);
		
		JButton toHome = new JButton("����");
		toHome.setOpaque(false);
			//����ť���¼�
		toHome.addActionListener(event -> loadHomePane());
		
		JPanel studentMessagePane = new JPanel();
		studentMessagePane.setOpaque(false);
		studentMessagePane.setLayout(null);
		studentMessagePane.add(scrollPane);
		scrollPane.setBounds(new Double(0.20*width).intValue(), new Double(0.11*height).intValue(), 
				new Double(0.67*width).intValue(), new Double(0.60*height).intValue());
		
		this.getContentPane().add(toHome, BorderLayout.WEST);
		this.getContentPane().add(studentMessagePane);
		
		repaint(); //���»���ҳ��
		validate();  //������֤���������ˢ�£�
	}
	
	private void loadAchiManagerPane() {
		this.getContentPane().removeAll();
		// ��ʼ��������ģ��
		JPanel buttonPane = new JPanel(); //��ťѡ��ģ��
		JPanel selectStudentAchiPane = getAchiManagerSelectAchiByStudentPane(buttonPane);
		
		JPanel updateStudentAchiPane =getAchiManagerUpdateAchiPane(buttonPane);

		
		
		// �������˵���ť
		JButton tohomeButton = new JButton("�������˵�");
		tohomeButton.setOpaque(false);
		tohomeButton.addActionListener(event -> loadHomePane());
		// ͼƬ
		PaintGIF p = new PaintGIF(new Double(0.20*width).intValue(),
			new Double(0.30*height).intValue(),
			"img/Java.jpg");
		p.setOpaque(false);
		this.getContentPane().add(p);
		setVisible(true);
		this.getContentPane().add(tohomeButton, BorderLayout.WEST);
		setVisible(true);
		
		// ��ťѡ��ģ��
		buttonPane.setLayout(null);
		buttonPane.setOpaque(false);

		JButton selectStudentAchi = new JButton("��ѯѧ���ɼ�");
		selectStudentAchi.setOpaque(false);
		JButton selectCourseAchi = new JButton("��ѯ�γ̳ɼ�");
		selectCourseAchi.setOpaque(false);
		JButton updateStudentAchi = new JButton("�޸�ѧ���ɼ�");
		updateStudentAchi.setOpaque(false);
			//����bounds
		selectStudentAchi.setBounds(new Double(0.61*width).intValue(), new Double(0.08*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		selectCourseAchi.setBounds(new Double(0.61*width).intValue(), new Double(0.36*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		updateStudentAchi.setBounds(new Double(0.61*width).intValue(), new Double(0.64*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		
			//��Ӱ�ť�¼�
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
		
		
			//��Ӱ�ť�����buttonPane
		buttonPane.add(selectStudentAchi);
		buttonPane.add(selectCourseAchi);
		buttonPane.add(updateStudentAchi);
		// ���ѡ��ģ�� ����Pane
		this.getContentPane().add(buttonPane);
		setVisible(true);
		
		
		
		//this.getContentPane().add(addStudentPane);
		
		repaint(); //���»���ҳ��
		validate();  //������֤���������ˢ�£�
	}
	private JPanel getAchiManagerSelectAchiByStudentPane(JPanel buttonPane){
		JPanel pane = new JPanel();
		// ���ؿ�
		JTextArea backMessageBox = new JTextArea(8, 50);
		backMessageBox.setLineWrap(true);
		backMessageBox.setEditable(false);
		JScrollPane backMessagePane = new JScrollPane(backMessageBox);
		backMessagePane.setOpaque(false);
		// ����ѧ�� 
		JTextField inputBox = new JTextField(14);
		inputBox.setOpaque(false);
		JLabel tips = new JLabel("��ѯ���ݣ�");
		tips.setOpaque(false);
		JComboBox<String> checkBox = new JComboBox<>();
		checkBox.addItem("ѧ��");
		checkBox.addItem("����");
		checkBox.setOpaque(false);
		JComboBox<String> checkBox2 = new JComboBox<>();
		checkBox2.addItem("���гɼ�");
		checkBox2.addItem("���ųɼ�");
		checkBox2.addItem("��ͳɼ�");
		checkBox2.addItem("ƽ���ɼ�");
		checkBox2.setOpaque(false);
		JButton confirmButton1 = new JButton("ȷ�ϲ�ѯ");
		confirmButton1.setOpaque(false);
		confirmButton1.addActionListener(event->{
			String selectWay = checkBox.getSelectedItem().toString();
			String selectWay2 = checkBox2.getSelectedItem().toString();
			if (selectWay.equals("ѧ��")) {
				if (selectWay2.equals("���гɼ�")) {
					controller.receiveCommond("getStudentAllAchiById," + inputBox.getText());
					backMessageBox.setText(this.backResult);
				}else if (selectWay2.equals("���ųɼ�")) {
					controller.receiveCommond("getStudentMaxAchievementById," + inputBox.getText());
					backMessageBox.setText(this.backResult);
				}else if (selectWay2.equals("��ͳɼ�")) {
					controller.receiveCommond("getStudenMinAchievementById," + inputBox.getText());
					backMessageBox.setText(this.backResult);
				}else if (selectWay2.equals("ƽ���ɼ�")){
					controller.receiveCommond("getStudentAvgAchievementById," + inputBox.getText());
					backMessageBox.setText(this.backResult);
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "error");
				}
			}else if (selectWay.equals("����")) {
				if (selectWay2.equals("���гɼ�")) {
					controller.receiveCommond("getStudentAllAchiByName," + inputBox.getText());
					backMessageBox.setText(this.backResult);
				}else if (selectWay2.equals("���ųɼ�")) {
					controller.receiveCommond("getStudentMaxAchievementByName," + inputBox.getText());
					backMessageBox.setText(this.backResult);
				}else if (selectWay2.equals("��ͳɼ�")) {
					controller.receiveCommond("getStudenMinAchievementByName," + inputBox.getText());
					backMessageBox.setText(this.backResult);
				}else if (selectWay2.equals("ƽ���ɼ�")){
					controller.receiveCommond("getStudentAvgAchievementByName," + inputBox.getText());
					backMessageBox.setText(this.backResult);
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "error");
				}
			}else {
				JOptionPane.showMessageDialog(getContentPane(), "error");
			}
		});
				
		
		
		
		// ���ذ�ť
		ImageIcon icon = new ImageIcon("img/backButton.jpg");
		Image img = icon.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);  //����ͼƬ
		JButton backButton = new JButton(new ImageIcon(img));
			// ��ȡ��꽹��ʱ�ı���ʽ
		backButton.setBorder(null);// ȥ�߿�
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
		
		// �γ���Ϣ��ʾ��
		JTextArea backMessageBox = new JTextArea(8, 50);
		backMessageBox.setLineWrap(true);
		backMessageBox.setEditable(false);
		JScrollPane backMessagePane = new JScrollPane(backMessageBox);
		backMessagePane.setOpaque(false);
		// �γ�ѡ�������˵�
		JLabel tips = new JLabel("ѡ��γ�");
			//ѡ���ѯ��ʽ
		JComboBox<String> checkBox = new JComboBox<>();
		checkBox.addItem("���гɼ�");
		checkBox.addItem("���ųɼ�");
		checkBox.addItem("��ͳɼ�");
		checkBox.addItem("ƽ���ɼ�");
		checkBox.setOpaque(false);
		
		JComboBox<String> checkCourseBox = new JComboBox<>();
		checkCourseBox.setOpaque(false);
		checkCourseBox.setOpaque(false);
		for (String item : courseItems) {
			checkCourseBox.addItem(item);
		}
		tips.setOpaque(false);
		// ȷ�ϰ�ť
		JButton confirmButton = new JButton("ȷ�ϲ�ѯ");
		confirmButton.setOpaque(false);
		confirmButton.addActionListener(event->{
			String selectWay = checkBox.getSelectedItem().toString();
			if (selectWay.equals("���гɼ�")) {
				controller.receiveCommond("showCourseAllAchiById," + 
						checkCourseBox.getSelectedItem().toString().split(" ")[0]);
				backMessageBox.setText(this.backResult);
			}else if (selectWay.equals("���ųɼ�")) {
				controller.receiveCommond("showCourseMaxAchievementById," + 
						checkCourseBox.getSelectedItem().toString().split(" ")[0]);
				backMessageBox.setText(this.backResult);
			}else if (selectWay.equals("��ͳɼ�")) {
				controller.receiveCommond("showCourseMinAchievementById," + 
						checkCourseBox.getSelectedItem().toString().split(" ")[0]);
				backMessageBox.setText(this.backResult);
			}else if (selectWay.equals("ƽ���ɼ�")){
				controller.receiveCommond("showCourseAvgAchievementById," + 
						checkCourseBox.getSelectedItem().toString().split(" ")[0]);
				backMessageBox.setText(this.backResult);
			}else {
				JOptionPane.showMessageDialog(getContentPane(), "error");
			}
		});
		
		// ���ذ�ť
		ImageIcon icon = new ImageIcon("img/backButton.jpg");
		Image img = icon.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);  //����ͼƬ
		JButton backButton = new JButton(new ImageIcon(img));
			// ��ȡ��꽹��ʱ�ı���ʽ
		backButton.setBorder(null);// ȥ�߿�
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
		// ȷ��ѧ�� 
		JTextField inputStudentBox = new JTextField(14);
		inputStudentBox.setOpaque(false);
		JLabel tips1 = new JLabel("�޸����ݣ�");
		tips1.setOpaque(false);
		JComboBox<String> checkBox = new JComboBox<>();
		JComboBox<String> checkCourseBox = new JComboBox<>(); //ѡ��γ������˵�
		checkBox.addItem("ѧ��");
		checkBox.addItem("����");
		checkBox.setOpaque(false);
		checkCourseBox.setOpaque(false);
		
		String[] commond = new String[4];
		JButton confirmButton1 = new JButton("����ѧ��");  // TODO ��Ӱ�ť�¼����÷��صĿγ���Ϣ�б���������˵�
		confirmButton1.setOpaque(false);
		confirmButton1.addActionListener(event->{
			checkCourseBox.removeAllItems();
			String selectWay = checkBox.getSelectedItem().toString();
			if (selectWay.equals("ѧ��")) {
				controller.receiveCommond("getStudentCourseById," + inputStudentBox.getText());
				if (this.backResult.equals("")) {
					JOptionPane.showMessageDialog(getContentPane(), "��ѧ��û��ѡ���κογ�");
				}else if (this.backResult.equals("notFoundStudent")) {
					JOptionPane.showMessageDialog(getContentPane(), "��ѧ��������");
				}else if (this.backResult.equals("loseParams")) {
					JOptionPane.showMessageDialog(getContentPane(), "����������Ϊ��");
				}else {
					String[] boxElem = this.backResult.split(",");
					for (String elem : boxElem) {
						checkCourseBox.addItem(elem);
					}
					commond[0] = checkBox.getSelectedItem().toString();
					commond[1] = inputStudentBox.getText();
					inputStudentBox.setText(null);
				
				}
			}else if (selectWay.equals("����")) {
				controller.receiveCommond("getStudentCourseByName," + inputStudentBox.getText());
				if (this.backResult.equals("")) {
					JOptionPane.showMessageDialog(getContentPane(), "��ѧ��û��ѡ���κογ�");
				}else if (this.backResult.equals("notFoundStudent")) {
					JOptionPane.showMessageDialog(getContentPane(), "��ѧ��������");
				}else if (this.backResult.equals("loseParams")) {
					JOptionPane.showMessageDialog(getContentPane(), "����������Ϊ��");
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
				JOptionPane.showMessageDialog(getContentPane(), "δ֪����");
			}
		});
		
		// ȷ���޸�
		JTextField inputAchiBox = new JTextField(14);
		inputAchiBox.setOpaque(false);
		JLabel tips2 = new JLabel("ѧ���γ̣�");
		tips2.setOpaque(false);
		JLabel tips3 = new JLabel("���÷�����");
		tips3.setOpaque(false);
		JButton confirmButton2 = new JButton("ȷ���޸�");  // TODO ��Ӱ�ť�¼����÷��صĿγ���Ϣ�б���������˵�
		confirmButton2.setOpaque(false);
		confirmButton2.addActionListener(event->{
			try {
				if (commond[0].equals("ѧ��")) {
					String courseId = checkCourseBox.getSelectedItem().toString().split(" ")[0];
					String grade = inputAchiBox.getText();
					controller.receiveCommond("setStudentAchievementById," + commond[1]
							+ "," + courseId + "," + grade); //ѧ�ſγ̺ųɼ�
					if (this.backResult.equals("success")) {
						JOptionPane.showMessageDialog(getContentPane(), "�޸ĳɼ��ɹ�");
						inputAchiBox.setText(null);
					}else if (this.backResult.equals("faild")) {
						JOptionPane.showMessageDialog(getContentPane(), "�޸ĳɼ�ʧ��");
					}else {
						JOptionPane.showMessageDialog(getContentPane(), "δ֪����");
					}
				}else if (commond[0].equals("����")) {
					controller.receiveCommond("getStudentIdByName," + commond[1]);//ͨ��������ID
					commond[1] = this.backResult;
					String courseId = checkCourseBox.getSelectedItem().toString().split(" ")[0];
					String grade = inputAchiBox.getText();
					controller.receiveCommond("setStudentAchievementById," + commond[1] 
							+ "," + courseId + "," + grade); //ѧ�ſγ̺ųɼ�
					if (this.backResult.equals("success")) {
						JOptionPane.showMessageDialog(getContentPane(), "�޸ĳɼ��ɹ�");
						inputAchiBox.setText(null);
					}else if (this.backResult.equals("faild")) {
						JOptionPane.showMessageDialog(getContentPane(), "�޸ĳɼ�ʧ��");
					}else {
						JOptionPane.showMessageDialog(getContentPane(), "δ֪����");
					}
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "δ֪����");
				}
			}catch (NullPointerException e) {
				JOptionPane.showMessageDialog(getContentPane(), "δѡ��γ̻�δ���÷���");
			}
			
		});
		
		
		// ���ذ�ť
		ImageIcon icon = new ImageIcon("img/backButton.jpg");
		Image img = icon.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);  //����ͼƬ
		JButton backButton = new JButton(new ImageIcon(img));
			// ��ȡ��꽹��ʱ�ı���ʽ
		backButton.setBorder(null);// ȥ�߿�
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
		// ��ʼ��������ģ��
		JPanel buttonPane = new JPanel(); //��ťѡ��ģ��
		JPanel addStudentPane = getStudentManagerAddStudentPane();
		JPanel selectStudentPane = getStudentManagerSelectStudentPane(buttonPane);
		JPanel updateStudentPane = getStudentManagerUpdateStudentPane(buttonPane);
		JPanel deleteStudentPane = getStudentManagerDeleteStudentPane(buttonPane);
		
		
		// �������˵���ť
		JButton tohomeButton = new JButton("�������˵�");
		tohomeButton.setOpaque(false);
		tohomeButton.addActionListener(event -> loadHomePane());
		// ͼƬ
		PaintGIF p = new PaintGIF(new Double(0.20*width).intValue(),
			new Double(0.30*height).intValue(),
			"img/Java.jpg");
		p.setOpaque(false);
		this.getContentPane().add(p);
		setVisible(true);
		this.getContentPane().add(tohomeButton, BorderLayout.WEST);
		setVisible(true);
		
		// ��ťѡ��ģ��
		buttonPane.setLayout(null);
		buttonPane.setOpaque(false);
		JButton addStudent = new JButton("���ѧ��");
		addStudent.setOpaque(false);
		JButton selectStudent = new JButton("��ѯѧ����Ϣ");
		selectStudent.setOpaque(false);
		JButton updateStudent = new JButton("�޸�ѧ����Ϣ");
		updateStudent.setOpaque(false);
		JButton deleteStudent = new JButton("ɾ��ѧ��");
		deleteStudent.setOpaque(false);
			//����bounds
		addStudent.setBounds(new Double(0.61*width).intValue(), new Double(0.08*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		selectStudent.setBounds(new Double(0.61*width).intValue(), new Double(0.27*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		updateStudent.setBounds(new Double(0.61*width).intValue(), new Double(0.46*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		deleteStudent.setBounds(new Double(0.61*width).intValue(), new Double(0.65*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
			//��Ӱ�ť�¼�
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
		
			//��Ӱ�ť�����buttonPane
		buttonPane.add(addStudent);
		buttonPane.add(selectStudent);
		buttonPane.add(updateStudent);
		buttonPane.add(deleteStudent);
		// ���ѡ��ģ�� ����Pane
		this.getContentPane().add(buttonPane);
		setVisible(true);
		
		
		
		//this.getContentPane().add(addStudentPane);
		
		repaint(); //���»���ҳ��
		validate();  //������֤���������ˢ�£�
	}
	private JPanel getStudentManagerAddStudentPane() {
		// ��controller��ָ��
		String[] commond = new String[4];
		commond[0] = "addStudent";
		// ����ѧ�� 
		JTextField inputId = new JTextField(14);
		inputId.setOpaque(false);
		JLabel id = new JLabel("��������Ҫ�����ѧ����ѧ�ţ�");
		id.setOpaque(false);
		JButton confirmButton1 = new JButton("ȷ��");
		confirmButton1.setOpaque(false);
		confirmButton1.addActionListener(event->{
			String input = inputId.getText();
			if(!input.equals("") && input != null) {
				commond[1] = input;
				inputId.setForeground(Color.GRAY);
			}else {
				JOptionPane.showMessageDialog(getContentPane(), "��δ����ѧ��");
			}
		});
				
		// �������� 
		JTextField inputName = new JTextField(14);
		inputName.setOpaque(false);
		JLabel name = new JLabel("��������Ҫ�����ѧ����������");
		name.setOpaque(false);
		JButton confirmButton2 = new JButton("ȷ��");
		confirmButton2.setOpaque(false);
		confirmButton2.addActionListener(event->{
			String input = inputName.getText();
			if(input != null && !input.equals("")) {
				commond[2] = input;
				inputName.setForeground(Color.GRAY);
			}else {
				JOptionPane.showMessageDialog(getContentPane(), "��δ��������");
			}
		});
				
		// �����Ա� 
		JTextField inputSex = new JTextField(14);
		inputSex.setOpaque(false);
		JLabel sex = new JLabel("��������Ҫ�����ѧ�����Ա�");
		sex.setOpaque(false);
		JButton confirmButton3 = new JButton("ȷ��");
		confirmButton3.setOpaque(false);
		confirmButton3.addActionListener(event->{
			String input = inputSex.getText();
			if (input.equals("��")) {
				commond[3] = "0";
				inputSex.setForeground(Color.GRAY);
			}else if (input.equals("Ů")) {
				commond[3] = "1";
				inputSex.setForeground(Color.GRAY);
			}else {
				JOptionPane.showMessageDialog(getContentPane(), "��������ȷ���Ա�");
			}
		});
		
		// �ύ��ť
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
				
				if (this.backResult.equals("�������")) {
					JOptionPane.showMessageDialog(getContentPane(), "������������Ϣ");
				}else if (this.backResult.equals("add faild")){
					JOptionPane.showMessageDialog(getContentPane(), "�����ظ����");
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
				JOptionPane.showMessageDialog(getContentPane(), "������������Ϣ");
			}finally{
				this.backResult = "";
			}
		});
		
		// ���ذ�ť
		ImageIcon icon = new ImageIcon("img/backButton.jpg");
		Image img = icon.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);  //����ͼƬ
		JButton backButton = new JButton(new ImageIcon(img));
			// ��ȡ��꽹��ʱ�ı���ʽ
		backButton.setBorder(null);// ȥ�߿�
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
		JButton addButton = new JButton("���");
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
	private JPanel getStudentManagerSelectStudentPane(JPanel buttonPane) { //��ѯѧ����Ϣ
		JPanel pane = new JPanel();
		// ���ؿ�
		JTextArea backMessageBox = new JTextArea(8, 50);
		backMessageBox.setLineWrap(true);
		backMessageBox.setEditable(false);
		JScrollPane backMessagePane = new JScrollPane(backMessageBox);
		backMessagePane.setOpaque(false);
		
		// ����ѧ�� 
		JTextField inputBox = new JTextField(14);
		inputBox.setOpaque(false);
		JLabel tips = new JLabel("��ѯ���ݣ�");
		tips.setOpaque(false);
		JComboBox<String> checkBox = new JComboBox<>();
		checkBox.addItem("ѧ��");
		checkBox.addItem("����");
		checkBox.setOpaque(false);
		JButton confirmButton1 = new JButton("ȷ�ϲ�ѯ");
		confirmButton1.setOpaque(false);
		confirmButton1.addActionListener(event->{
			String[] commond = new String[2];
			String adopt = checkBox.getSelectedItem().toString();
			if (adopt.equals("ѧ��")) {
				commond[0] = "findStudentById";
			}else if(adopt.equals("����")) {
				commond[0] = "findStudentByName";
			}
			commond[1] = inputBox.getText();
			controller.receiveCommond(commond);
			if (backResult.equals("�������") || backResult.equals("[]")) {
				backMessageBox.setText("����������޴���");
			}else {
				backMessageBox.setText(this.backResult);
			}
			
		});
				
		
		
		
		// ���ذ�ť
		ImageIcon icon = new ImageIcon("img/backButton.jpg");
		Image img = icon.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), icon.getImage().SCALE_DEFAULT);  //����ͼƬ
		JButton backButton = new JButton(new ImageIcon(img));
			// ��ȡ��꽹��ʱ�ı���ʽ
		backButton.setBorder(null);// ȥ�߿�
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
		// ����ѧ�� 
		JTextField inputId = new JTextField(14);
		inputId.setOpaque(false);
		JLabel id = new JLabel("������ѧ����ѧ�ţ�");
		id.setOpaque(false);
		JButton confirmButton1 = new JButton("ȷ��");
		confirmButton1.setOpaque(false);
		confirmButton1.addActionListener(event->{
					// TODO
			this.backResult = inputId.getText();
			inputId.setForeground(Color.GRAY);
			
		});
		
		// TODO �ݴ�ѧ�ţ����ڽ������Ŀλ�ĳɼ���
				
		//  �޸�ѡ��
		JButton confirmButton2 = new JButton("�޸�ѡ��");
		confirmButton2.setOpaque(false);
		confirmButton2.addActionListener(event->{
			try {
				controller.receiveCommond("findStudentById," + inputId.getText());
				if (this.backResult != "�������") {
					//controller.receiveCommond("getStudentCourseById," + inputId.getText()); //TODO
					JPanel changeCoursePane = getStudentManagerUpdateStudentPaneChangeCourse(buttonPane, pane, 
							Integer.valueOf(inputId.getText()));
			
					this.getContentPane().remove(pane);
					this.getContentPane().add(changeCoursePane);
					repaint(); //���»���ҳ��
					validate();  //������֤���������ˢ�£�
					inputId.setText(null);
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "�Ҳ�����ѧ��");
				}
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(getContentPane(), "������ѧ��id");
			}
		});
				
		// TODO �޸ĳɼ�
		JButton confirmButton3 = new JButton("�޸ĳɼ�");
		confirmButton3.setOpaque(false);
		confirmButton3.addActionListener(event->{
			try {
				controller.receiveCommond("findStudentById," + inputId.getText());
				if (!this.backResult.equals("�������")) {
					controller.receiveCommond("getStudentCourseById," + inputId.getText());
					JPanel changeAchievementPane = getStudentManagerUpdateStudentPaneChangeAchievement(buttonPane, pane, 
							Integer.valueOf(inputId.getText()));
					this.getContentPane().remove(pane);
					this.getContentPane().add(changeAchievementPane);
					repaint();
					validate();
					inputId.setText(null);
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "�Ҳ�����ѧ��");
				}
			}catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(getContentPane(), "��δ�����ȷ��ѧ��ѧ��");
			}
		});
		
		// ���ذ�ť
		ImageIcon icon = new ImageIcon("img/backButton.jpg");
		Image img = icon.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);  //����ͼƬ
		JButton backButton = new JButton(new ImageIcon(img));
			// ��ȡ��꽹��ʱ�ı���ʽ
		backButton.setBorder(null);// ȥ�߿�
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
		JButton addButton = new JButton("���");
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
		
		// ��ӡ��ѡ�γ�
		JLabel tips = new JLabel("��ѧ����ѡ�γ�����");
		tips.setOpaque(false);
		JTextArea displayBox = new JTextArea(8, 50);
		displayBox.setLineWrap(true);
		displayBox.setEditable(false);
		//displayBox.setOpaque(false);
		displayBox.setText(this.backResult);
		JScrollPane displayPane = new JScrollPane(displayBox);
		displayPane.setOpaque(false);
		displayBox.setText(this.backResult);
		
		// ��ӿγ�
		JLabel addCourseTipes = new JLabel("��������ӿγ̵�id");
		addCourseTipes.setOpaque(false);
		JTextField addCourseBox = new JTextField(14);
		addCourseBox.setOpaque(false);
		JButton confirmButton1 = new JButton("ȷ�����");
		confirmButton1.addActionListener(event->{
			commond[0] = "addStudentToCourseById";
			commond[1] = String.valueOf(studentId);
			commond[2] = addCourseBox.getText();
			controller.receiveCommond(commond);
			if (this.backResult.equals("success")) {
				JOptionPane.showMessageDialog(getContentPane(), "��ӿγ̳ɹ�");
			}else if (this.backResult.equals("faild")) {
				JOptionPane.showMessageDialog(getContentPane(), "û�иÿογ�");
			}else if (this.backResult.equals("lackParams")) {
				JOptionPane.showMessageDialog(getContentPane(), "��������Ҫ��ӿγ̵�id");
			}
			else {
				JOptionPane.showMessageDialog(getContentPane(), "��ӳ���");
			}
			addCourseBox.setText(null);
		});
		confirmButton1.setOpaque(false);
		JButton getOptionalCourse = new JButton("�鿴��ѡ�γ�");
		getOptionalCourse.addActionListener(event->{
			String[] commond2 = new String[2];
			commond2[0] = "getOptionalCourseByStudentId";
			commond2[1] = String.valueOf(studentId);
			controller.receiveCommond(commond2);
			displayBox.setText(this.backResult);
		});
		
		//ɾ���γ�
		JLabel removeCourseTipes = new JLabel("������ɾ���γ̵�id");
		removeCourseTipes.setOpaque(false);
		JTextField removeCourseBox = new JTextField(14);
		removeCourseBox.setOpaque(false);
		JButton confirmButton2 = new JButton("ȷ��ɾ��");    //TODO
		confirmButton2.setOpaque(false);
		confirmButton2.addActionListener(event->{
			commond[0] = "removeStudentFromCourseById";
			commond[1] = String.valueOf(studentId);
			commond[2] = removeCourseBox.getText();
			controller.receiveCommond(commond);
			if (this.backResult.equals("success")) {
				JOptionPane.showMessageDialog(getContentPane(), "�˿γɹ�");
			}else if (this.backResult.equals("faild")) {
				JOptionPane.showMessageDialog(getContentPane(), "��ѧ��û��ѡ�޸ÿογ�");
			}else if (this.backResult.equals("lackParams")) {
				JOptionPane.showMessageDialog(getContentPane(), "��������Ҫȡ���γ̵�id");
			}
			else {
				JOptionPane.showMessageDialog(getContentPane(), "�˿γ���");
			}
			removeCourseBox.setText(null);
		});
		JButton getSelectedCourse = new JButton("�鿴��ѡ�γ�");
		getSelectedCourse.addActionListener(event->{
			controller.receiveCommond("getStudentCourseById," + Integer.valueOf(studentId));
			if (this.backResult.equals("")) {
				displayBox.setText("null");
			}else {
				displayBox.setText(this.backResult);
			}
			
		});
		
		
		// ���ذ�ť
		ImageIcon icon = new ImageIcon("img/backButton.jpg");
		Image img = icon.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);  //����ͼƬ
		JButton backButton = new JButton(new ImageIcon(img));
			// ��ȡ��꽹��ʱ�ı���ʽ
		backButton.setBorder(null);// ȥ�߿�
		ImageIcon icon2 = new ImageIcon("img/backButton3.jpg");
		Image img2 = icon2.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);
		backButton.setRolloverIcon(new ImageIcon(img2));
		//backButton.setPressedIcon(new ImageIcon(img2));
		backButton.setOpaque(false);
		backButton.addActionListener(event->{
			this.getContentPane().remove(pane);
			this.getContentPane().add(lastPane);
			repaint(); //���»���ҳ��
			validate();  //������֤���������ˢ�£�
		});
		/*
		JTextArea stuMessage = new JTextArea(8, 50);
		stuMessage.setLineWrap(true);
		//stuMessage.setEditable(false);
		//studentMessage.setOpaque(false);
		JScrollPane stuMessPane = new JScrollPane(stuMessage);
		stuMessPane.setOpaque(false);
		JButton addButton = new JButton("���");
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
		// ȷ��ѧ�� 
		//JTextField inputStudentBox = new JTextField(14);
		//inputStudentBox.setOpaque(false);
		//JLabel tips1 = new JLabel("����ѧ��ѧ�ţ�");
		//tips1.setOpaque(false);
		//JComboBox<String> checkBox = new JComboBox<>();
		JComboBox<String> checkCourseBox = new JComboBox<>(); //ѡ��γ������˵�
		//checkBox.addItem("ѧ��");
		//checkBox.addItem("����");
		//checkBox.setOpaque(false);
		checkCourseBox.setOpaque(false);
		
		JButton confirmButton1 = new JButton("����ѧ��");  // TODO ��Ӱ�ť�¼����÷��صĿγ���Ϣ�б���������˵�
		confirmButton1.setOpaque(false);
		confirmButton1.addActionListener(event->{
			//checkCourseBox.removeAllItems();
			String[] commond = new String[2];
			commond[0] = "getStudentCourseById";
			commond[1] = String.valueOf(studentId);
			controller.receiveCommond(commond);
			if (this.backResult.equals("")) {
				JOptionPane.showMessageDialog(getContentPane(), "��ѧ��û��ѡ���κογ�");
			}else if(this.backResult.equals("notFoundStudent")) {
				JOptionPane.showMessageDialog(getContentPane(), "��ѧ������ϵͳ��");
			}else if(this.backResult.equals("loseParams")){
				JOptionPane.showMessageDialog(getContentPane(), "������Ϊ��");
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
		
		// ȷ���޸�
		JTextField inputAchiBox = new JTextField(14);
		inputAchiBox.setOpaque(false);
		JLabel tips2 = new JLabel("ѧ���γ̣�");
		tips2.setOpaque(false);
		JLabel tips3 = new JLabel("���÷�����");
		tips3.setOpaque(false);
		JButton confirmButton2 = new JButton("ȷ���޸�");  // TODO ��Ӱ�ť�¼����÷��صĿγ���Ϣ�б���������˵�
		confirmButton2.setOpaque(false);
		confirmButton2.addActionListener(event->{
			boolean isCheckCourseBoxNull = false;
			if (checkCourseBox.getSelectedItem() == null) {
				isCheckCourseBoxNull = true;
			}
			if (!isCheckCourseBoxNull) {
				
				if (inputAchiBox.getText().replaceAll(" ", "").equals("")) {
					JOptionPane.showMessageDialog(getContentPane(), "��δ�����޸ĵĳɼ�ֵ");
				}else {
					String[] commond = new String[4];
					commond[0] = "setStudentAchievementById";
					commond[1] = String.valueOf(studentId);
					commond[2] = checkCourseBox.getSelectedItem().toString().split(" ")[0];;
					commond[3] = inputAchiBox.getText().replaceAll(" ", "");
					controller.receiveCommond(commond);
					if (this.backResult.equals("success")) {
						JOptionPane.showMessageDialog(getContentPane(), "�ɼ��޸ĳɹ�");
						inputAchiBox.setText(null);
					}else if (this.backResult.equals("faild")) {
						JOptionPane.showMessageDialog(getContentPane(), "�ɼ��޸�ʧ��");
					}else {
						JOptionPane.showMessageDialog(getContentPane(), "error");
					}
				}
			}else {
				JOptionPane.showMessageDialog(getContentPane(), "û��ѡ��γ�");
			}
		});
		
		// ���ذ�ť
		ImageIcon icon = new ImageIcon("img/backButton.jpg");
		Image img = icon.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);  //����ͼƬ
		JButton backButton = new JButton(new ImageIcon(img));
			// ��ȡ��꽹��ʱ�ı���ʽ
		backButton.setBorder(null);// ȥ�߿�
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
		// ���ؿ�
		JTextArea backMessageBox = new JTextArea(8, 50);
		backMessageBox.setLineWrap(true);
		backMessageBox.setEditable(false);
		JScrollPane backMessagePane = new JScrollPane(backMessageBox);
		backMessagePane.setOpaque(false);
		
		// ����ѧ�� 
		JTextField inputBox = new JTextField(14);
		inputBox.setOpaque(false);
		JLabel tips = new JLabel("ɾ�����ݣ�");
		tips.setOpaque(false);
		JComboBox<String> checkBox = new JComboBox<>();
		checkBox.addItem("ѧ��");
		checkBox.addItem("����");
		checkBox.setOpaque(false);
		JButton confirmButton1 = new JButton("ȷ��ɾ��");
		confirmButton1.setOpaque(false);
		confirmButton1.addActionListener(event->{
			if (inputBox.getText() == null) {
				JOptionPane.showMessageDialog(getContentPane(), "��������Ҫɾ����ѧ����id");
			}else {
				String[] commond = new String[2];
				if (checkBox.getSelectedItem().toString().equals("ѧ��")) {
					commond[0] = "deleteStudentrById";
					commond[1] = inputBox.getText();
					controller.receiveCommond(commond);
					backMessageBox.setText(this.backResult);
				}else if (checkBox.getSelectedItem().toString().equals("����")) {
					commond[0] = "deleteStudentByName";
					commond[1] = inputBox.getText();
					controller.receiveCommond(commond);
					backMessageBox.setText(this.backResult);
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "checkBox error");
				}
				
			}
		});
				
		
		
		
		// ���ذ�ť
		ImageIcon icon = new ImageIcon("img/backButton.jpg");
		Image img = icon.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), icon.getImage().SCALE_DEFAULT);  //����ͼƬ
		JButton backButton = new JButton(new ImageIcon(img));
			// ��ȡ��꽹��ʱ�ı���ʽ
		backButton.setBorder(null);// ȥ�߿�
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
		// ��ʼ��������ģ��
		JPanel buttonPane = new JPanel(); //��ťѡ��ģ��
		JPanel addCoursePane = getCourseManagerAddCoursePane(buttonPane);
		//JPanel selectCoursePane = getCourseManagerSelectCoursePane(buttonPane, courseItems);
		//JPanel updateCoursePane = getCourseManagerUpdateCoursePane(buttonPane, courseItems);
		JPanel deleteCoursePane = getCourseManagerDeleteCoursePane(buttonPane);
		
		
		// �������˵���ť
		JButton tohomeButton = new JButton("�������˵�");
		tohomeButton.setOpaque(false);
		tohomeButton.addActionListener(event -> loadHomePane());
		// ͼƬ
		PaintGIF p = new PaintGIF(new Double(0.20*width).intValue(),
			new Double(0.30*height).intValue(),
			"img/Java.jpg");
		p.setOpaque(false);
		this.getContentPane().add(p);
		setVisible(true);
		this.getContentPane().add(tohomeButton, BorderLayout.WEST);
		setVisible(true);
		
		// ��ťѡ��ģ��
		buttonPane.setLayout(null);
		buttonPane.setOpaque(false);
		JButton addCourse = new JButton("��ӿγ�");
		addCourse.setOpaque(false);
		JButton selectCourse = new JButton("��ѯ�γ���Ϣ");
		selectCourse.setOpaque(false);
		JButton updateCourse = new JButton("�޸Ŀγ���Ϣ");
		updateCourse.setOpaque(false);
		JButton deleteCourse = new JButton("�Ƴ��γ�");
		deleteCourse.setOpaque(false);
			//����bounds
		addCourse.setBounds(new Double(0.61*width).intValue(), new Double(0.08*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		selectCourse.setBounds(new Double(0.61*width).intValue(), new Double(0.27*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		updateCourse.setBounds(new Double(0.61*width).intValue(), new Double(0.46*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
		deleteCourse.setBounds(new Double(0.61*width).intValue(), new Double(0.65*height).intValue(), 
				new Double(0.26*width).intValue(), new Double(0.06*height).intValue());
			//��Ӱ�ť�¼�
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
		
			//��Ӱ�ť�����buttonPane
		buttonPane.add(addCourse);
		buttonPane.add(selectCourse);
		buttonPane.add(updateCourse);
		buttonPane.add(deleteCourse);
		// ���ѡ��ģ�� ����Pane
		this.getContentPane().add(buttonPane);
		setVisible(true);
		
		
		
		//this.getContentPane().add(addStudentPane);
		
		repaint(); //���»���ҳ��
		validate();  //������֤���������ˢ�£�
	}
	public JPanel getCourseManagerAddCoursePane(JPanel buttonPane) {
		//TODO
		JPanel pane = new JPanel();
		String[] commond = new String[3];
		commond[0] = "addCourseToCourseMap";
		// ����γ�id
		JTextField inputId = new JTextField(14);
		inputId.setOpaque(false);
		JLabel id = new JLabel("��������Ҫ��ӵĿγ̵Ŀγ̺ţ�");
		id.setOpaque(false);
		JButton confirmButton1 = new JButton("ȷ��");
		confirmButton1.setOpaque(false);
		confirmButton1.addActionListener(event->{
			commond[1] = inputId.getText();
			inputId.setForeground(Color.GRAY);
		});
				
		// �������� 
		JTextField inputName = new JTextField(14);
		inputName.setOpaque(false);
		JLabel name = new JLabel("��������Ҫ����Ŀγ̵Ŀγ�����");
		name.setOpaque(false);
		JButton confirmButton2 = new JButton("ȷ��");
		confirmButton2.setOpaque(false);
		confirmButton2.addActionListener(event->{
			commond[2] = inputName.getText();
			inputName.setForeground(Color.GRAY);
		});
				
		// ȷ�����
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
				JOptionPane.showMessageDialog(getContentPane(), "�γ���ӳɹ�");
				inputId.setText(null);
				inputId.setForeground(Color.BLACK);
				inputName.setText(null);
				inputName.setForeground(Color.BLACK);
			}else if (this.backResult.equals("faild")) {
				JOptionPane.showMessageDialog(getContentPane(), "�γ����ʧ��");
			}else {
				JOptionPane.showMessageDialog(getContentPane(), "error");
			}
		});
		
		// ���ذ�ť
		ImageIcon icon = new ImageIcon("img/backButton.jpg");
		Image img = icon.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);  //����ͼƬ
		JButton backButton = new JButton(new ImageIcon(img));
			// ��ȡ��꽹��ʱ�ı���ʽ
		backButton.setBorder(null);// ȥ�߿�
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
		// �γ�ѡ�������˵�
		JLabel tips = new JLabel("ѡ��γ�");
		JComboBox<String> checkCourseBox = new JComboBox<>();
		checkCourseBox.setOpaque(false);
		for (String item : courseItems) {
			checkCourseBox.addItem(item);
		}
		tips.setOpaque(false);
		// �γ���Ϣ��ʾ��
		JTextArea backMessageBox = new JTextArea(8, 50);
		backMessageBox.setLineWrap(true);
		backMessageBox.setEditable(false);
		JScrollPane backMessagePane = new JScrollPane(backMessageBox);
		backMessagePane.setOpaque(false);
		// ȷ�ϰ�ť
		JButton confirmButton = new JButton("ȷ�ϲ�ѯ");
		confirmButton.setOpaque(false);
		confirmButton.addActionListener(event->{
			String[] commond = new String[2];
			commond[0] = "findCourseById";
			String[] courseItem = checkCourseBox.getSelectedItem().toString().split(" ");
			commond[1] = courseItem[0]; // �γ�id
			controller.receiveCommond(commond);
			if (this.backResult.equals("��")) {
				backMessageBox.setText("ϵͳδ���ҵ��ÿγ�");  //TODO 
			}else {
				backMessageBox.setText(this.backResult);
			}
		});
		
		// ���ذ�ť
		ImageIcon icon = new ImageIcon("img/backButton.jpg");
		Image img = icon.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);  //����ͼƬ
		JButton backButton = new JButton(new ImageIcon(img));
			// ��ȡ��꽹��ʱ�ı���ʽ
		backButton.setBorder(null);// ȥ�߿�
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
		// �γ�ѡ�������˵�
		JLabel tips = new JLabel("ѡ��γ�");
		JComboBox<String> checkCourseBox = new JComboBox<>();
		checkCourseBox.setOpaque(false);
		for (String item : courseItems) {
			checkCourseBox.addItem(item);
		}
		tips.setOpaque(false);
		// ���ѧ����γ�
		JLabel tips2 = new JLabel("¼��ѧ��");
		tips2.setOpaque(false);
		JButton getStudentList = new JButton("��ȡѧ������");
		getStudentList.setOpaque(false);
		JComboBox<String> checkStudentBox = new JComboBox<>();
		checkStudentBox.setOpaque(false);
		getStudentList.addActionListener(event->{
			checkStudentBox.removeAllItems();
			String[] commond = new String[2];
			commond[0] = "getStudentListNotInCourseById";
			commond[1] = checkCourseBox.getSelectedItem().toString().split(" ")[0];		//�γ̺�
			controller.receiveCommond(commond);
			String[] stuMessList = this.backResult.split(",");
			for (String stuMess : stuMessList) {
				checkStudentBox.addItem(stuMess);
			}
		});
			// ȷ�� ¼�� ��ť
		JButton confirmButton1 = new JButton("¼��");
		confirmButton1.setOpaque(false);
		confirmButton1.addActionListener(event->{
			try {
				String[] commond = new String[3];
				commond[0] = "addStudentToCourseById";
				commond[1] = checkStudentBox.getSelectedItem().toString().split(" ")[0];	//ѧ��
			
				commond[2] = checkCourseBox.getSelectedItem().toString().split(" ")[0];		//�γ̺�
				controller.receiveCommond(commond);
				if (this.backResult.equals("success")) {
					JOptionPane.showMessageDialog(getContentPane(), "ѡ�γɹ�");
					checkStudentBox.removeAllItems();	//����ϴεĻ���
					// TODO �ж�box2���Ƿ���Ҫ����stu
				}else if (this.backResult.equals("faild")) {
					JOptionPane.showMessageDialog(getContentPane(), "ѡ��ʧ��");
				}else if (this.backResult.equals("lackParams")) {
					JOptionPane.showMessageDialog(getContentPane(), "����ȱ�ٲ���");
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "δ֪����");
				}
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(getContentPane(), "��ѡ��ѧ��");
			}
		});
		// �ӿγ����Ƴ�ѧ��
		JLabel tips3 = new JLabel("�Ƴ�ѧ��");
		tips3.setOpaque(false);
		JButton getStudentList2 = new JButton("��ȡѧ������");
		getStudentList2.setOpaque(false);
		JComboBox<String> checkStudentBox2 = new JComboBox<>();
		checkStudentBox2.setOpaque(false);
		getStudentList2.addActionListener(event->{
			checkStudentBox2.removeAllItems();
			String[] commond = new String[2];
			commond[0] = "getCourseStudentListById";
			commond[1] = checkCourseBox.getSelectedItem().toString().split(" ")[0];		//�γ̺�
			controller.receiveCommond(commond);
			String[] stuMessList = this.backResult.split(",");
			for (String stuMess : stuMessList) {
				checkStudentBox2.addItem(stuMess);
			}
		});
			// ȷ�� �Ƴ� ��ť
		JButton confirmButton2 = new JButton("�Ƴ�");
		confirmButton2.setOpaque(false);
		confirmButton2.addActionListener(event->{
			try {
				String[] commond = new String[3];
				commond[0] = "removeStudentFromCourseById";
				commond[1] = checkStudentBox2.getSelectedItem().toString().split(" ")[0];	//ѧ��
				commond[2] = checkCourseBox.getSelectedItem().toString().split(" ")[0];		//�γ̺�
				controller.receiveCommond(commond);
				if (this.backResult.equals("success")) {
					JOptionPane.showMessageDialog(getContentPane(), "�˿γɹ�");
					checkStudentBox2.removeAllItems();	//����ϴλ���
					//TODO �ж�box1���Ƿ���Ҫ����stu
				}else if(this.backResult.equals("faild")) {
					JOptionPane.showMessageDialog(getContentPane(), "�˿�ʧ��");
				}else if(this.backResult.equals("lackParams")) {
					JOptionPane.showMessageDialog(getContentPane(), "��������");
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "δ֪����");
				}
			}catch (NullPointerException e) {
				JOptionPane.showMessageDialog(getContentPane(), "��ѡ����Ҫ�˿ε�ѧ��");
			}
		});
		// �޿γ�����
		JLabel tips4 = new JLabel("�޸Ŀγ�����");
		tips4.setOpaque(false);
		JTextField inputBox = new JTextField();
		inputBox.setOpaque(false);
		JButton confirmButton3 = new JButton("ȷ���޸�");
		confirmButton3.addActionListener(event->{
			if (inputBox.getText() == null || inputBox.getText().replaceAll(" ", "").equals("")) {
				JOptionPane.showMessageDialog(getContentPane(), "��Ҫ�޸ĵĿγ���Ϊ�� �򲻹淶");
			}else {
				String[] commond = new String[3];
				commond[0] = "updateCourseNameById";
				commond[1] = checkCourseBox.getSelectedItem().toString().split(" ")[0];
				commond[2] = inputBox.getText().replaceAll(" ", "");
				controller.receiveCommond(commond);
				if (this.backResult.equals("success")) {
					JOptionPane.showMessageDialog(getContentPane(), "�γ����޸ĳɹ�");
					//ˢ�¿γ��б�
					controller.receiveCommond("showAllCourses");
					String[] newCourseList = this.backResult.split(",");
					checkCourseBox.removeAllItems();
					for (String newCourse : newCourseList) {
						checkCourseBox.addItem(newCourse);
					}
					//����������
					inputBox.setText(null);
				}else if (this.backResult.equals("faild")) {
					JOptionPane.showMessageDialog(getContentPane(), "�γ����޸�ʧ��");
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "δ֪����");
				}
			}
			
		});
		
		// ���ذ�ť
		ImageIcon icon = new ImageIcon("img/backButton.jpg");
		Image img = icon.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);  //����ͼƬ
		JButton backButton = new JButton(new ImageIcon(img));
			// ��ȡ��꽹��ʱ�ı���ʽ
		backButton.setBorder(null);// ȥ�߿�
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
		
		// ��Ϣ��ʾ��
		JTextArea showMessageBox = new JTextArea(8, 50);
		showMessageBox.setLineWrap(true);
		showMessageBox.setEditable(false);
		//studentMessage.setOpaque(false);
		JScrollPane showMessPane = new JScrollPane(showMessageBox);
		showMessPane.setOpaque(false);
		// ��ʾĿǰ��ɾ���γ�
		controller.receiveCommond("showAllCourses");
		String courseMess = this.backResult.replaceAll(",", "\n");
		showMessageBox.setText(courseMess);
		
		// �Ƴ��γ�
		JLabel tips = new JLabel("��������Ҫ�Ƴ��γ̵�id��");
		tips.setOpaque(false);
		JTextField inputBox = new JTextField();
		inputBox.setOpaque(false);
		JButton confirmButton = new JButton("ȷ���Ƴ�");
		confirmButton.setOpaque(false);
		confirmButton.addActionListener(event->{
			String[] commond = new String[2];
			commond[0] = "removeCourseById";
			commond[1] = inputBox.getText().replaceAll(" ", "");
			controller.receiveCommond(commond);
			if (this.backResult.equals("success")) {
				JOptionPane.showMessageDialog(getContentPane(), "�γ̳ɹ���ϵͳ���Ƴ�");
				//���������
				inputBox.setText(null);
				//ˢ���Ӵ���ʾ�Ŀ�ɾ���γ�
				controller.receiveCommond("showAllCourses");
				String courseMess2 = this.backResult.replaceAll(",", "\n");
				showMessageBox.setText(courseMess2);
			}else if (this.backResult.equals("faild")) {
				JOptionPane.showMessageDialog(getContentPane(), "�γ��Ƴ�����ʧ��");
			}else {
				JOptionPane.showMessageDialog(getContentPane(), "δ֪����");
			}
		});
		
		//���ذ�ť
		ImageIcon icon = new ImageIcon("img/backButton.jpg");
		Image img = icon.getImage().getScaledInstance(new Double(0.07*width).intValue(), 
				new Double(0.07*width).intValue(), Image.SCALE_DEFAULT);  //����ͼƬ
		JButton backButton = new JButton(new ImageIcon(img));
			// ��ȡ��꽹��ʱ�ı���ʽ
		backButton.setBorder(null);// ȥ�߿�
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
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����Ĭ�Ϲرղ���Ϊ�ر�
			this.setVisible(true);  //���߲�������ѡ���Ƿ����ش���
			this.controller = controller;
		});
		
	}

	@Override
	public void showResult(String res) {
		// ��÷��ؽ��
		this.resetBackResult();
		this.backResult = res;
		
	}
	
	public void resetBackResult() {
		this.backResult = "";
	}
}
