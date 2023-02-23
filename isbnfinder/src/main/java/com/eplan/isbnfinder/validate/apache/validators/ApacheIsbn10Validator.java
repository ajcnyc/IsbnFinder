package com.eplan.isbnfinder.validate.apache.validators;

import org.apache.commons.validator.routines.ISBNValidator;

import com.eplan.isbnfinder.validate.Isbn;
import com.eplan.isbnfinder.validate.apache.ApacheIsbnValidator;

/**
 * Validator using Apache Commons to validate 10-digit ISBNs
 * 
 * @author Alex Cohen
 *
 */
public class ApacheIsbn10Validator implements ApacheIsbnValidator {

	private ISBNValidator apacheValidator_; // The Apache ISBNValidator to use
	
	/**
	 * Creates an ApacheIsbn10Validator
	 */
	public ApacheIsbn10Validator() {
		apacheValidator_ = new ISBNValidator();
	}

	
	@Override
	public Isbn[] validate(String... isbns) {
		// Get validity
		boolean validity = apacheValidator_.isValidISBN10(isbns[0]);
		
		// Construct Isbn Object
		Isbn isbn = new Isbn(isbns[0],validity);
		
		// Put it in an array and return
		Isbn[] ret = {isbn};
		return ret;
	}
 
}
