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

		setTitle("Zooooo");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setBounds(100, 100, 484, 324);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		txtLocalhost = new JTextField();
		txtLocalhost.setText("localhost");
		txtLocalhost.setBounds(146, 10, 147, 21);
		panel.add(txtLocalhost);
		txtLocalhost.setColumns(10);

		JLabel lblIp = new JLabel("IP:");
		lblIp.setBounds(80, 13, 30, 15);
		panel.add(lblIp);

		JLabel label = new JLabel("数据库:");
		label.setBounds(80, 42, 54, 15);
		panel.add(label);

		textField = new JTextField();
		textField.setBounds(146, 39, 147, 21);
		panel.add(textField);
		textField.setColumns(10);

		JLabel label_1 = new JLabel("表名:");
		label_1.setBounds(80, 127, 54, 15);
		panel.add(label_1);

		textField_1 = new JTextField();
		textField_1.setBounds(146, 124, 147, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel label_2 = new JLabel("包名:");
		label_2.setBounds(79, 156, 54, 15);
		panel.add(label_2);

		txtComyourcom = new JTextField();
		txtComyourcom.setText("com.buxiu.example");
		txtComyourcom.setBounds(146, 155, 147, 21);
		panel.add(txtComyourcom);
		txtComyourcom.setColumns(10);

		JLabel lblNewLabel = new JLabel("输出目录：");
		lblNewLabel.setBounds(80, 190, 65, 15);
		panel.add(lblNewLabel);

		textField_3 = new JTextField();
		textField_3.setBounds(146, 186, 147, 21);
		panel.add(textField_3);
		textField_3.setColumns(10);

		checkBox = new JCheckBox("生成包结构目录");
		checkBox.setSelected(true);
		checkBox.setBounds(145, 213, 147, 23);
		panel.add(checkBox);

		JLabel lblNewLabel_1 = new JLabel("可以指定表名，也可以不指定");
		lblNewLabel_1.setBounds(303, 127, 176, 15);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("* 数据库名");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(303, 42, 66, 15);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("* 包结构");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setBounds(303, 158, 79, 15);
		panel.add(lblNewLabel_3);

		JButton button = new JButton("执行");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				go();
			}
		});
		button.setBounds(145, 242, 93, 23);
		panel.add(button);

		textField_4 = new JTextField();
		textField_4.setText("123456");
		textField_4.setBounds(145, 93, 147, 21);
		panel.add(textField_4);
		textField_4.setColumns(10);

		txtRoot = new JTextField();
		txtRoot.setText("root");
		txtRoot.setBounds(145, 66, 148, 21);
		panel.add(txtRoot);
		txtRoot.setColumns(10);

		JLabel label_3 = new JLabel("用户名:");
		label_3.setBounds(80, 69, 54, 15);
		panel.add(label_3);

		JLabel label_4 = new JLabel("密码:");
		label_4.setBounds(80, 96, 54, 15);
		panel.add(label_4);

		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setBounds(248, 242, 204, 23);
		panel.add(lblNewLabel_4);

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
	private JTextField txtLocalhost;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtComyourcom;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField txtRoot;

	

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
		txtLocalhost.setText(p.getProperty("host", "127.0.0.1"));
		textField.setText(p.getProperty("database", "bubuxiu"));
		txtRoot.setText(p.getProperty("user", "root"));
		textField_4.setText(p.getProperty("pass", "root"));
		txtComyourcom.setText(p.getProperty("packname", "org.buxiu.bugonline"));
		textField_3.setText(p.getProperty("dirstr", "/Users/bubuxiu/git/bootexample/src"));
		textField_1.setText(p.getProperty("tablename", ""));
	}

	private void saveconfig() {
		String host = txtLocalhost.getText();
		String database = textField.getText();
		String user = txtRoot.getText();
		String pass = textField_4.getText();
		String packname = txtComyourcom.getText();
		String dirstr = textField_3.getText();// 空表示当前目录
		String tablename = textField_1.getText();

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
		String host = txtLocalhost.getText();
		String database = textField.getText();

		if (database.length() == 0) {
			setTips("数据库名必填");
			return;
		}

		String user = txtRoot.getText();
		String pass = textField_4.getText();
		String packname = txtComyourcom.getText();
		String dirstr = textField_3.getText();// 空表示当前目录
		String tablename = textField_1.getText();
		boolean createPackage = checkBox.getSelectedObjects() != null;
		System.out.println(createPackage);
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
