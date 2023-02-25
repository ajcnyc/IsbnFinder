package com.eplan.isbnfinder.validate;

import org.springframework.stereotype.Service;

/**
 * Validator interface to check if Strings are valid ISBNs
 * 
 * @author Alex Cohen
 *
 */
@Service
public interface IsbnValidatorService {

	/**
	 * Checks whether the given strings are valid ISBN numbers or not. All ISBNs
	 * passed in are returned as Isbn objects, and each ISBN object has a boolean
	 * for validity.
	 * 
	 * @param isbns The ISBN numbers to check
	 * @return The ISBNs that were passed in, returned as Isbn objects.Each ISBN
	 *         object has a boolean for validity.
	 */
	public Isbn[] validate(String... isbns);

}