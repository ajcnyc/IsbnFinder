package com.eplan.isbnfinder.validate.apache.validators;

import com.eplan.isbnfinder.validate.Isbn;
import com.eplan.isbnfinder.validate.apache.ApacheIsbnValidator;

/**
 * Validator using Apache Commons to validate 10-digit ISBNs
 * 
 * @author Alex Cohen
 *
 */
public class ApacheIsbn10Validator implements ApacheIsbnValidator {

	@Override
	public Isbn[] validate(String... isbns) {
		return null;
	}
 
}
