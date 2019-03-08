package com.buxiu.bootexample.zoo;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.UIManager;

import com.buxiu.hellodev.zoo.springboot.mysql.DBManager;
import com.buxiu.hellodev.zoo.springboot.mysql.TableInfo;
import com.buxiu.hellodev.zoo.springboot.mysql.TableParser;
import com.buxiu.hellodev.zoo.springboot.SpringZoo;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
*
*  Srpingboot 代码生成工具 UI版
*
*  @Author bubuxiu@gmail.com
*
**/
public class SpringUI extends JFrame {  
	private static final long serialVersionUID = 1L;
	
	private JCheckBox checkBox;
	Properties p = new Properties();
	String configFile = "/Users/bubuxiu/workspace/eclipse/config.ini";
	private JLabel lblNewLabel_4;

	public SpringUI() {

		setResizable(false);

		setTitle("Hellodev Generator");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setBounds(100, 100, 800, 600);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		// 布局变量
		int x_lable_start = 150; // x轴起始位置
		int x_input_start = 220; // x轴起始位置
		int y_start = 30; // y轴起始位置
		int y_step = 50; // y轴行间距
		int input_height = 36;
		int line_number = 0;
		
		JLabel lableTitle = new JLabel("Hellodev Code Generator");
		lableTitle.setBounds(x_lable_start+100, 20, 500, input_height); 
		lableTitle.setFont(new Font("Serif", Font.BOLD, 24)); 
		panel.add(lableTitle);
		
		// line 1
		JLabel lblIp = new JLabel("IP：");
		lblIp.setBounds(x_lable_start, y_start+ y_step*(line_number+1), 60, input_height);
		panel.add(lblIp);
		
		textFieldDBhost = new JTextField();
		textFieldDBhost.setText("localhost"); 
		textFieldDBhost.setBounds(x_input_start, y_start+ y_step*(line_number+1), 300, input_height);
		panel.add(textFieldDBhost);
		textFieldDBhost.setColumns(10);  
		JLabel lblIp_2 = new JLabel("* 数据库地址");
		lblIp_2.setForeground(Color.RED);
		lblIp_2.setBounds(x_input_start+330, y_start + y_step*(line_number+1), 300, input_height);
		panel.add(lblIp_2);
		
		// line 2
		JLabel label = new JLabel("数据库：");
		label.setBounds(x_lable_start, y_start + y_step*(line_number+2), 60, input_height);
		panel.add(label);

		textFieldDBname = new JTextField();
		textFieldDBname.setBounds(x_input_start, y_start + y_step*(line_number+2), 300, input_height);
		panel.add(textFieldDBname);
		textFieldDBname.setColumns(10); 
		JLabel lblNewLabel_2 = new JLabel("* 数据库名");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(x_input_start+330, y_start + y_step*(line_number+2), 300, input_height);
		panel.add(lblNewLabel_2);
		
		// line 3
		JLabel label_3 = new JLabel("用户名：");
		label_3.setBounds(x_lable_start, y_start + y_step*(line_number+3), 60, input_height);
		panel.add(label_3);

		textFieldUsername = new JTextField();
		textFieldUsername.setText("root");
		textFieldUsername.setBounds(x_input_start, y_start + y_step*(line_number+3), 300, input_height);
		panel.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		
		JLabel lblUsername_2 = new JLabel("* 数据库连接用户名");
		lblUsername_2.setForeground(Color.RED);
		lblUsername_2.setBounds(x_input_start+330, y_start + y_step*(line_number+3), 300, input_height);
		panel.add(lblUsername_2);
		
		// line 4 
		JLabel labelPasswd = new JLabel("密码：");
		labelPasswd.setBounds(x_lable_start, y_start + y_step*(line_number+4), 60, input_height);
		panel.add(labelPasswd);

		textFieldPasswd = new JTextField();
		textFieldPasswd.setText("123456");
		textFieldPasswd.setBounds(x_input_start, y_start + y_step*(line_number+4), 300, input_height);
		panel.add(textFieldPasswd);
		textFieldPasswd.setColumns(10);   
		JLabel labelPasswd_2 = new JLabel("* 数据库连接密码");
		labelPasswd_2.setForeground(Color.RED);
		labelPasswd_2.setBounds(x_input_start+330, y_start + y_step*(line_number+4), 300, input_height);
		panel.add(labelPasswd_2);
		
		// line 5
		JLabel label_1 = new JLabel("表名：");
		label_1.setBounds(x_lable_start, y_start + y_step*(line_number+5), 60, input_height);
		panel.add(label_1);

		textFieldTablename = new JTextField();
		textFieldTablename.setBounds(x_input_start, y_start + y_step*(line_number+5), 300, input_height);
		panel.add(textFieldTablename);
		textFieldTablename.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("* 指定表名");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(x_input_start+330, y_start + y_step*(line_number+5), 150, input_height);
		panel.add(lblNewLabel_1);

		// line 6
		JLabel label_2 = new JLabel("包名：");
		label_2.setBounds(x_lable_start, y_start + y_step*(line_number+6), 60, input_height);
		panel.add(label_2);

		textFieldPackagename = new JTextField();
		textFieldPackagename.setText("com.buxiu.example");
		textFieldPackagename.setBounds(x_input_start, y_start + y_step*(line_number+6), 300, input_height);
		panel.add(textFieldPackagename);
		textFieldPackagename.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("* 包结构");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setBounds(x_input_start+330, y_start + y_step*(line_number+6), 300, input_height);
		panel.add(lblNewLabel_3);
		
		// line 7
		JLabel lblNewLabel = new JLabel("输出目录：");
		lblNewLabel.setBounds(x_lable_start, y_start + y_step*(line_number+7), 70, input_height);
		panel.add(lblNewLabel);

		textFieldDestdir = new JTextField();
		textFieldDestdir.setBounds(x_input_start, y_start + y_step*(line_number+7), 500, input_height);
		panel.add(textFieldDestdir);
		textFieldDestdir.setColumns(10);
		
		// line 8
		checkBox = new JCheckBox("生成包结构目录");
		checkBox.setSelected(true);
		checkBox.setBounds(x_lable_start, y_start + y_step*(line_number+8), 160, input_height);
		panel.add(checkBox);
		
		// line 9
		JButton button = new JButton("开始生成代码");
		button.setBounds(x_lable_start+200, y_start + y_step*(line_number+9), 150, input_height);
		button.setFont(new Font("Serif", Font.BOLD, 15)); 
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				go();
			}
		}); 
		panel.add(button);

		 
		JLabel lableBottom = new JLabel("如有意见，请反馈给我~ @bubuxiu@gmail.com");
		lableBottom.setBounds(x_lable_start+350, y_start+y_step*(line_number+10), 500, input_height); 
		panel.add(lableBottom);
		
//		lblNewLabel_4 = new JLabel("");
//		lblNewLabel_4.setForeground(Color.RED);
//		lblNewLabel_4.setBounds(248, 242, 204, 23);
//		panel.add(lblNewLabel_4);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				saveconfig();
				System.exit(0);
			}

		});

		loadconfig();
	}

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private JTextField textFieldDBhost;
	private JTextField textFieldDBname;
	private JTextField textFieldTablename;
	private JTextField textFieldPackagename;
	private JTextField textFieldDestdir;
	private JTextField textFieldPasswd;
	private JTextField textFieldUsername;

	

	private void loadconfig() {
		File config = new File(configFile);
		if (config.exists()) {
			try {
				InputStream is = new FileInputStream(config);
				p.load(is);
				is.close();
				setUIVal();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				config.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void setUIVal() {
		textFieldDBhost.setText(p.getProperty("host", "127.0.0.1"));
		textFieldDBname.setText(p.getProperty("database", "bubuxiu"));
		textFieldUsername.setText(p.getProperty("user", "root"));
		textFieldPasswd.setText(p.getProperty("pass", "root"));
		textFieldPackagename.setText(p.getProperty("packname", "org.buxiu.bugonline"));
		textFieldDestdir.setText(p.getProperty("dirstr", "/Users/bubuxiu/git/bootexample/src"));
		textFieldTablename.setText(p.getProperty("tablename", ""));
	}

	private void saveconfig() {
		String host = textFieldDBhost.getText();
		String database = textFieldDBname.getText();
		String user = textFieldUsername.getText();
		String pass = textFieldPasswd.getText();
		String packname = textFieldPackagename.getText();
		String dirstr = textFieldDestdir.getText();// 空表示当前目录
		String tablename = textFieldTablename.getText();

		p.setProperty("host", host);
		p.setProperty("database", database);
		p.setProperty("user", user);
		p.setProperty("pass", pass);
		p.setProperty("packname", packname);
		p.setProperty("dirstr", dirstr);
		p.setProperty("tablename", tablename);

		try {
//			if(!dirstr.equals("")) {
//				configFile = dirstr + "/" + configFile;
//			}
			OutputStream out = new FileOutputStream(configFile);
			p.store(out, "退出保存文件," + sdf.format(new Date()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void setTips(String msg) {
		lblNewLabel_4.setText(msg);
	}

	public void go() {
		String host = textFieldDBhost.getText();
		String database = textFieldDBname.getText();

		if (database.length() == 0) {
			setTips("数据库名必填");
			return;
		}

		String user = textFieldUsername.getText();
		String pass = textFieldPasswd.getText();
		String packname = textFieldPackagename.getText();
		String dirstr = textFieldDestdir.getText();// 空表示当前目录
		String tablename = textFieldTablename.getText();
		boolean createPackage = checkBox.getSelectedObjects() != null;
//		System.out.println(createPackage);
		if (dirstr != null && !dirstr.isEmpty()) {
			if (!dirstr.endsWith("/")) {
				dirstr += "/";
			}
		}
		File dir = new File(dirstr);

		if (createPackage) {
			checkdir(dirstr + packname.replaceAll("\\.", "/")); 
			dir = new File(dirstr + packname.replaceAll("\\.", "/"));
		}
		String outputdir = dir.getAbsolutePath();// bean的生成目录

		String podir = outputdir + "/po";
		checkdir(podir);
		
		String mapperdir = outputdir + "/mapper";
		checkdir(mapperdir);
		
		String serviceifdir = outputdir + "/service"; 
		String servicedir = outputdir + "/service/impl";
		checkdir(servicedir);

		String controllerdir = outputdir + "/controller";
		checkdir(controllerdir);
		
		
		Connection conn = null;
		try {
			conn = DBManager.mysql(host, database, user, pass);

			TableInfo table = TableParser.parseOneTable(conn, tablename); 
			SpringZoo.setPackageName(packname);
			if(!table.getTablecomment().contains("NG")) {// 表注释包含NG则不生成代码
				writefile(podir, table.getSimplename()+"Po.java", SpringZoo.generatePoBean(table));
				writefile(mapperdir, table.getSimplename()+"Mapper.xml", SpringZoo.generateMapperXml(table));
				writefile(mapperdir, table.getSimplename()+"Mapper.java", SpringZoo.generateMapperJava(table));
				writefile(serviceifdir, "I"+table.getSimplename()+"Service.java", SpringZoo.generateServiceInterface(table));
				writefile(servicedir, table.getSimplename()+"ServiceImpl.java", SpringZoo.generateServiceImpl(table));
	
				writefile(controllerdir, table.getSimplename()+"Controller.java", SpringZoo.generateController(table));
			}else {
				System.out.println("表注释包含NG字符串，不生成此表的代码");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
	private void writefile(String outdir, String filename, String content) {
		File file = new File(outdir, filename);  
        System.out.println(file.getAbsolutePath());  
        try {  
            FileWriter fw = new FileWriter(file);  
            fw.write(content);  
            fw.flush();  
            fw.close();  
        } catch (IOException e) {   
            e.printStackTrace();  
        }  
	}
	
	private void checkdir(String dir) {
		File f = new File(dir); 
 		if (!f.exists()) {
			f.mkdirs();
		} 
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpringUI frame = new SpringUI();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
