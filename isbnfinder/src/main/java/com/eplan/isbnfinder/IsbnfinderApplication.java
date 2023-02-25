package com.eplan.isbnfinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eplan.isbnfinder.requestResponse.formatter.RequestResponseFormatter;
import com.eplan.isbnfinder.validate.Isbn;
import com.eplan.isbnfinder.validate.apache.ApacheIsbnValidatorService;
import com.eplan.isbnfinder.validate.apache.validators.FlexibleApacheIsbnValidatorService;
 
@SpringBootApplication
public class IsbnfinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsbnfinderApplication.class, args);
	}
	
}
