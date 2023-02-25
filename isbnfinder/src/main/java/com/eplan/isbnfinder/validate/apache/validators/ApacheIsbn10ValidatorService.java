package com.eplan.isbnfinder.validate.apache.validators;

import org.apache.commons.validator.routines.ISBNValidator;
import org.springframework.stereotype.Service;

import com.eplan.isbnfinder.validate.Isbn;
import com.eplan.isbnfinder.validate.apache.ApacheIsbnValidatorService;

/**
 * Validator using Apache Commons to validate 10-digit ISBNs
 * 
 * @author Alex Cohen
 *
 */
@Service
public class ApacheIsbn10ValidatorService implements ApacheIsbnValidatorService {

	private ISBNValidator apacheValidator_; // The Apache ISBNValidator to use

	/**
	 * Creates an ApacheIsbn10Validator
	 */
	public ApacheIsbn10ValidatorService() {
		apacheValidator_ = new ISBNValidator();
	}

	@Override
	public Isbn[] validate(String... isbns) {

		Isbn[] ret = new Isbn[isbns.length]; // The ISBN array to return
		
		// For each isbn
		for (int i = 0; i < isbns.length; i++) {
			// Get validity
			boolean validity = validateSingle(isbns[i]);

			// Construct Isbn Object
			Isbn isbn = new Isbn(isbns[i], validity);

			// Put it in an array and return
			ret[i] = isbn;
		}
		return ret;
	}
	
	/**
	 * Validates an ISBN-10
	 * 
	 * @param isbn The ISBN-10 to validate
	 * @return true if valid, false if invalid
	 */
	boolean validateSingle(String isbn) {
		return apacheValidator_.isValidISBN10(isbn);
	}

}
