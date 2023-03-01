package com.eplan.isbnfinder;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eplan.isbnfinder.database.IsbnDAO;
import com.eplan.isbnfinder.requestResponse.formatter.RequestResponseFormatter;
import com.eplan.isbnfinder.validate.Isbn;
import com.eplan.isbnfinder.validate.IsbnValidatorService;

@RestController
@RequestMapping("/api")
public class IsbnRestController {
	
	@Autowired
	private IsbnValidatorService validator_;
	
	@Autowired
	private DataSource dataSource_;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/validate-with-get")
    public ResponseEntity<String> testValidateWithGet(@RequestParam(value = "csv", defaultValue = "") String csv) {
		try {
			return ResponseEntity.ok(handleRequest(csv));
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred on the server.");
		}
    }
	
	@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/validate")
    public ResponseEntity<String> validateIsbns( @RequestBody String csv ) {
		try {
			return ResponseEntity.ok(handleRequest(csv));
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred on the server.");
		}
    }
    
    public String handleRequest(String csvRequest) {
    	// Format request
    	RequestResponseFormatter formatter = new RequestResponseFormatter();
    	String[] strIsbns = formatter.formatRequest(csvRequest);
    	
    	// Add to database
    	IsbnDAO  dao = new IsbnDAO(dataSource_);
    	dao.addIsbnRecords(strIsbns);
    	
    	// Validate
    	Isbn[] isbns = validator_.validate(strIsbns);
    	
    	// Format validated
    	String response = formatter.formatResponse(isbns);
    	
    	// Return validated
    	return response;
    }
}
