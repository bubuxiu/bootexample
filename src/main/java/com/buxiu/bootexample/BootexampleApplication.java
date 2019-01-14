package com.buxiu.bootexample;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication; 

@SpringBootApplication
@MapperScan("com.buxiu.bootexample.mapper")

//@ImportResource("classpath:transaction.xml")
 
public class BootexampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootexampleApplication.class, args);
	}
}
