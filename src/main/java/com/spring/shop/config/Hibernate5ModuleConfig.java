package com.spring.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@Configuration
public class Hibernate5ModuleConfig {
	
	@Bean
	Hibernate5Module hibernate5Module() {
		Hibernate5Module hibernate5Module = new Hibernate5Module();
		return hibernate5Module;
	}

}
