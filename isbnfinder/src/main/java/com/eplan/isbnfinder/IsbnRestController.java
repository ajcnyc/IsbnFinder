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

/**
 * The @RestController for the ISBN Finder Spring Boot Application. Requests map
 * to /api
 * 
 * @author Alex Cohen
 *
 */
@RestController
@RequestMapping("/api")
public class IsbnRestController {

	@Autowired
	private IsbnValidatorService validator_; // The ISBN Validator to use

	@Autowired
	private DataSource dataSource_; // The data source for the IsbnDAO

	/**
	 * Maps GET requests to /api/validate-with-get, and only allows CORS from
	 * http://localhost:4200 (the Angular frontend)
	 * 
	 * @param csv The CSV String of ISBN numbers to validate
	 * @return A ResponseEntity with a JSON String of validated ISBN objects
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/validate-with-get")
	public ResponseEntity<String> testValidateWithGet(@RequestParam(value = "csv", defaultValue = "") String csv) {
		try {
			return ResponseEntity.ok(handleRequest(csv));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred on the server.");
		}
	}

	/**
	 * Maps POST requests to /api/validate, and only allows CORS from
	 * http://localhost:4200 (the Angular frontend)
	 * 
	 * @param csv The CSV String of ISBN numbers to validate
	 * @return A ResponseEntity with a JSON String of validated ISBN objects
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/validate")
	public ResponseEntity<String> validateIsbns(@RequestBody String csv) {
		try {
			return ResponseEntity.ok(handleRequest(csv));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred on the server.");
		}
	}

	/**
	 * Takes a CSV request String, processes it, records it in the database, and
	 * returns a JSON String of validated ISBNS
	 * 
	 * @param csvRequest The CSV request String
	 * @return A JSON String of validated ISBNS
	 */
	private String handleRequest(String csvRequest) {
		// Format request
		RequestResponseFormatter formatter = new RequestResponseFormatter();
		String[] strIsbns = formatter.formatRequest(csvRequest);

		// Add to database COMMENT OUT IF NEED TO TEST WITHOUT DATABASE________
		IsbnDAO dao = new IsbnDAO(dataSource_);
		dao.addIsbnRecords(strIsbns);
		// _____________________________________________________________________
		// Validate
		Isbn[] isbns = validator_.validate(strIsbns);

		// Format validated
		String response = formatter.formatResponse(isbns);

		// Return validated
		return response;
	}
}
