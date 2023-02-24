package com.eplan.isbnfinder.validate.apache.validators;

import org.apache.commons.validator.routines.ISBNValidator;

import com.eplan.isbnfinder.validate.Isbn;
import com.eplan.isbnfinder.validate.apache.ApacheIsbnValidator;

/**
 * 
 * Validator using Apache Commons to validate 10 and 13-digit ISBNs
 * 
 * @author Alex Cohen
 *
 */
public class FlexibleApacheIsbnValidator implements ApacheIsbnValidator {
	
	private ApacheIsbn10Validator isbn10Validator_; // The IsbnValidator for ISBN-10's
	private ApacheIsbn13Validator isbn13Validator_; // The IsbnValidator for ISBN-13's

	/**
	 * Creates a FlexibleApacheIsbnValidator
	 */
	public FlexibleApacheIsbnValidator() {
		isbn10Validator_ = new ApacheIsbn10Validator();
		isbn13Validator_ = new ApacheIsbn13Validator();
	}
	
	@Override
	public Isbn[] validate(String... isbns) {
		Isbn[] ret = new Isbn[isbns.length]; // The ISBN array to return
		
		// For each isbn
		for (int i = 0; i < isbns.length; i++) {
			boolean validity = false; // Whether the ISBN is valid
			
			// Check if valid ISBN-10
			boolean valid10 = isbn10Validator_.validateSingle(isbns[i]);
			if(valid10) {
				validity = true;
			} else { // If not valid ISBN-10, check if valid ISBN-13
				boolean valid13 = isbn13Validator_.validateSingle(isbns[i]);
				if(valid13) {
					validity = true;
				}
			}

			// Construct Isbn Object
			Isbn isbn = new Isbn(isbns[i], validity);

			// Put it in an array and return
			ret[i] = isbn;
		}
		return ret;
	}

}
