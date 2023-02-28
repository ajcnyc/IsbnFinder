package com.eplan.isbnfinder.validate.validators.flexible;

import org.springframework.stereotype.Service;

import com.eplan.isbnfinder.validate.Isbn;
import com.eplan.isbnfinder.validate.validators.isbn10.ApacheIsbn10ValidatorService;
import com.eplan.isbnfinder.validate.validators.isbn10.Isbn10ValidatorService;
import com.eplan.isbnfinder.validate.validators.isbn13.ApacheIsbn13ValidatorService;
import com.eplan.isbnfinder.validate.validators.isbn13.Isbn13ValidatorService;

/**
 * 
 * Validator using Apache Commons to validate 10 and 13-digit ISBNs
 * 
 * @author Alex Cohen
 *
 */
@Service
public class FlexibleIsbnValidatorService extends Isbn10ValidatorService {
	
	private Isbn10ValidatorService isbn10Validator_; // The IsbnValidator for ISBN-10's
	private Isbn13ValidatorService isbn13Validator_; // The IsbnValidator for ISBN-13's

	/**
	 * Creates a FlexibleApacheIsbnValidator
	 * 
	 * @param isbn10Validator The ISBN-10 Validator to use for ISBN-10s
	 * @param isbn13Validator The ISBN-13 Validator to use for ISBN-13s
	 */
	public FlexibleIsbnValidatorService(Isbn10ValidatorService isbn10Validator, Isbn13ValidatorService isbn13Validator) {
		isbn10Validator_ = isbn10Validator;
		isbn13Validator_ = isbn13Validator;
	}
	
	@Override
	public Isbn[] validate(String... isbns) {
		Isbn[] ret = new Isbn[isbns.length]; // The ISBN array to return
		
		// For each isbn
		for (int i = 0; i < isbns.length; i++) {
			// Validate
			boolean validity = validateSingle(isbns[i]);

			// Construct Isbn Object
			Isbn isbn = new Isbn(isbns[i], validity);

			// Put it in an array and return
			ret[i] = isbn;
		}
		return ret;
	}

	@Override
	public boolean validateSingle(String isbn) {
		boolean validity = false; // Whether the ISBN is valid
		
		// Check if valid ISBN-10
		boolean valid10 = isbn10Validator_.validateSingle(isbn);
		if(valid10) {
			validity = true;
		} else { // If not valid ISBN-10, check if valid ISBN-13
			boolean valid13 = isbn13Validator_.validateSingle(isbn);
			if(valid13) {
				validity = true;
			}
		}
		
		return validity;
	}

}
