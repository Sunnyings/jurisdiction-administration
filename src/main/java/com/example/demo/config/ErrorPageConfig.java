package com.example.demo.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ErrorPageConfig {
	@Bean
	public WebServerFactoryCustomizer<ConfigurableWebServerFactory> embeddedServletContainerCustomizer() {
			//定义错误页面,接收FORBIDDEN错误状态码，交给403请求处理
			return factory -> {
		            ErrorPage errorPage403 = new ErrorPage(HttpStatus.FORBIDDEN,
		                    "error/403");
		        };
	}
}
