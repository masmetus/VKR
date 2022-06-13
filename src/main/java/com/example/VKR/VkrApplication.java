package com.example.VKR;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.VKR.service.excel.mapper" )
public class VkrApplication {

	public static void main(String[] args) {
		SpringApplication.run(VkrApplication.class, args);
	}

}
