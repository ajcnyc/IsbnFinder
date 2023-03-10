package com.eplan.isbnfinder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.eplan.isbnfinder.validate.IsbnValidatorService;
import com.eplan.isbnfinder.validate.validators.flexible.FlexibleIsbnValidatorService;
import com.eplan.isbnfinder.validate.validators.isbn10.ApacheIsbn10ValidatorService;
import com.eplan.isbnfinder.validate.validators.isbn13.ManualIsbn13ValidatorService;

/**
 * The primary Spring configuration class for the application
 */
@Configuration
@ComponentScan(basePackages = { "com.eplan.isbnfinder" })
public class Config {

	/**
	 * A Bean method that creates a new instance of some concrete class that is a
	 * descendant of IsbnValidatorService so that the backend can use it to validate ISBN
	 * numbers
	 * 
	 * @return A new instance of IsbnValidatorService
	 */
	@Bean
	@Primary
	public IsbnValidatorService isbnValidatorService() {
		return new FlexibleIsbnValidatorService(new ApacheIsbn10ValidatorService(), new ManualIsbn13ValidatorService());
	}

}
