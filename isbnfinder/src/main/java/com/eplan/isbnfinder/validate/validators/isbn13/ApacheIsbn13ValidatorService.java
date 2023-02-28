package com.eplan.isbnfinder.validate.validators.isbn13;

import org.apache.commons.validator.routines.ISBNValidator;
import org.springframework.stereotype.Service;

import com.eplan.isbnfinder.validate.Isbn;
import com.eplan.isbnfinder.validate.validators.isbn10.Isbn10ValidatorService;

/**
 * Validator using Apache Commons to validate 13-digit ISBNs
 * 
 * @author Alex Cohen
 *
 */
@Service
public class ApacheIsbn13ValidatorService extends Isbn10ValidatorService {

	private ISBNValidator apacheValidator_; // The Apache ISBNValidator to use

	/**
	 * Creates an ApacheIsbn13Validator
	 */
	public ApacheIsbn13ValidatorService() {
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
	 * Validates an ISBN-13
	 * 
	 * @param isbn The ISBN-13 to validate
	 * @return true if valid, false if invalid
	 */
	public boolean validateSingle(String isbn) {
		return apacheValidator_.isValidISBN13(isbn);
	}

}
