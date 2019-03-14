package com.buxiu.bootexample.zoo;

import java.util.List;

import com.buxiu.hellodev.zoo.apidoc.Jdk8Apidoc;
 
/**
*
*  Apidoc 接口文档自动生成工具 命令行版
*
*  @Author bubuxiu@gmail.com
*
**/
public class ApidocCmd {
	 
	public static void main(String[] args)  {
		String packagename = "com.buxiu.bootexample";
		String sourcedir = "/Users/bubuxiu/git/bootexample/src/main/java";
		
		// 生成特定的controller接口文档， null表示生成所有接口
		String specialname = "UserController";
		
		int r = Jdk8Apidoc.init(packagename, sourcedir, "bugonlineappkey"); // bugonlineappkey需要到www.bugonline.cn上创建项目获取
		if(r != 0) { 
			return ;
		}
		
		List<String> controllerlist = Jdk8Apidoc.getContollerList();
		
		for(String controller:controllerlist) {
			if(specialname != null) {
				if(!specialname.equals(controller)) {
					continue;
				}
			} 
			// 生成的文档默认上传到阿里云服务器，可以通过访问www.bugonline.cn进行在线访问和修改
			Jdk8Apidoc.addOneApi(controller);
		}
	}
}