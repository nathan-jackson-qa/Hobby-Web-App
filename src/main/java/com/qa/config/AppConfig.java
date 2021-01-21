package com.qa.config;

import java.time.LocalTime;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
	
	@Bean
	@Scope("prototype")
	public static String getTime() {
		return LocalTime.now().toString();
	}
	
	@Bean
	public ModelMapper Mapper() {
		return new ModelMapper();

	}

}