package com.buxiu.bootexample.zoo;

import java.sql.Connection;

import com.buxiu.hellodev.zoo.springboot.mysql.DBManager;
import com.buxiu.hellodev.zoo.springboot.mysql.TableInfo;
import com.buxiu.hellodev.zoo.springboot.mysql.TableParser;
import com.buxiu.hellodev.zoo.springboot.SpringZoo;
 
/**
*
*  Srpingboot 代码生成工具 命令行版
*
*  @Author bubuxiu@gmail.com
*
**/
public class SpringCmd {
	 
	public static void main(String[] args)  {
		
		Connection conn = null;
		try {
			conn = DBManager.mysql("127.0.0.1", "bubuxiu", "root", "root"); 
			//DBManager.getTables(conn);
			TableInfo table = TableParser.parseOneTable(conn, "buxiu_user"); 
//			
			SpringZoo.setPackageName("com.buxiu.bootexample");
			
			//System.out.println(table.toString());
			//
			SpringZoo.generatePoBean(table);
			SpringZoo.generateMapperXml(table);
//			SpringZoo.generateMapperJava(table);
//			SpringZoo.generateServiceInterface(table);
//			System.out.println(SpringZoo.generateServiceImpl(table));
//			System.out.println(SpringZoo.generateController(table));
//
//			System.out.println(SpringZoo.generateMapperXml(table));
//			System.out.println(SpringZoo.generateMapperJava(table));
//			System.out.println(SpringZoo.generateServiceInterface(table));
			//System.out.println(SpringZoo.generateServiceImpl(table));
			//System.out.println(SpringZoo.generateController(table));
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}