package com.eplan.isbnfinder.validate;

import org.springframework.stereotype.Service;

/**
 * Validator interface to check if Strings are valid ISBNs
 * 
 * @author Alex Cohen
 *
 */
@Service
public abstract class IsbnValidatorService {

	/**
	 * Checks whether the given strings are valid ISBN numbers or not. All ISBNs
	 * passed in are returned as Isbn objects, and each ISBN object has a boolean
	 * for validity.
	 * 
	 * @param isbns The ISBN numbers to check
	 * @return The ISBNs that were passed in, returned as Isbn objects.Each ISBN
	 *         object has a boolean for validity.
	 */
	public abstract Isbn[] validate(String... isbns);

	/**
	 * Checks whether the given string is a valid ISBN number or not
	 * 
	 * @param isbns The ISBN number to check
	 * @return true if valid, false if not
	 */
	public abstract boolean validateSingle(String isbn);
}
