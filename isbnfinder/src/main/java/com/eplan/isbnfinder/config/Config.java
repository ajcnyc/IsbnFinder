package com.eplan.isbnfinder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.eplan.isbnfinder.validate.IsbnValidatorService;
import com.eplan.isbnfinder.validate.apache.validators.FlexibleApacheIsbnValidatorService;

@Configuration
@ComponentScan(basePackages = {"com.eplan.isbnfinder"})
public class Config {
	
	@Bean
	@Primary
	public IsbnValidatorService isbnValidatorService() {
		return new FlexibleApacheIsbnValidatorService();
	}

}
