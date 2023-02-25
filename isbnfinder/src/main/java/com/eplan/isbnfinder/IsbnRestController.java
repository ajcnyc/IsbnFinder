package com.eplan.isbnfinder;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api")
public class IsbnRestController {
	
	@Autowired
	private IsbnValidatorService validator_;
	
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
    	
    	Isbn[] isbns = validator_.validate(strIsbns);
    	
    	String response = formatter.formatResponse(isbns);
    	
    	return response;
    }
}
