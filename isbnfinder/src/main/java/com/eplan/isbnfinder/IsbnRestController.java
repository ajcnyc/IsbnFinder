package com.eplan.isbnfinder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eplan.isbnfinder.requestResponse.formatter.RequestResponseFormatter;
import com.eplan.isbnfinder.validate.Isbn;
import com.eplan.isbnfinder.validate.IsbnValidatorService;
import com.eplan.isbnfinder.validate.apache.validators.FlexibleApacheIsbnValidatorService;

@RestController
@RequestMapping("/api")
public class IsbnRestController {
	
	@GetMapping("/validate-with-get")
    public String testValidateWithGet(@RequestParam(value = "csv", defaultValue = "") String csv) {
    	return handleRequest(csv);
    }
    
    @PostMapping("/validate")
    public @ResponseBody String validateIsbns( @RequestBody String csv ) {
    	return handleRequest(csv);
    }
    
    public String handleRequest(String csvRequest) {
    	RequestResponseFormatter formatter = new RequestResponseFormatter();
    	String[] strIsbns = formatter.formatRequest(csvRequest);
    	
    	IsbnValidatorService validator = new FlexibleApacheIsbnValidatorService();
    	Isbn[] isbns = validator.validate(strIsbns);
    	
    	String response = formatter.formatResponse(isbns);
    	
    	return response;
    }
}
