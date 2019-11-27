package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@MapperScan("com.example.demo.mapper")
@ImportResource(locations = {"classpath:kaptcha.xml"})
public class JurisdictionAdministrationApplication {
	public static void main(String[] args) {
		SpringApplication.run(JurisdictionAdministrationApplication.class, args);
	}
}
