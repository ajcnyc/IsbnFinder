package com.eplan.isbnfinder.validate.manual.validators;

import org.springframework.stereotype.Service;

import com.eplan.isbnfinder.validate.Isbn;
import com.eplan.isbnfinder.validate.manual.ManualIsbnValidatorService;

/**
 * Validator to manually validate 13-digit ISBNs
 * 
 * @author Alex Cohen
 *
 */
@Service
public class ManualIsbn13ValidatorService implements ManualIsbnValidatorService {

	@Override
	public Isbn[] validate(String... isbns) {
		// The array of validated Isbn objects to return
		Isbn[] validated = new Isbn[isbns.length];

		// Validate each ISBN String and add its Isbn object to the array
		for (int i = 0; i < isbns.length; i++) {
			validated[i] = validate(isbns[i]);
		}

		// Return the array
		return validated;
	}

	/*
	 * Validates a single String ISBN
	 */
	private Isbn validate(String isbn) {
		return new Isbn(isbn, isValid(isbn));
	}

	/*
	 * Checks if the given Strin ISBN is valid or not
	 */
	private boolean isValid(String isbn) {
		// The isbn cleaned of dashes and whitespace
		String cleanIsbn = clean(isbn);
		
		int sumOfProducts = 0; // The sum of the products

		// For each character in cleanIsbn
		for (int i = 0; i < cleanIsbn.length(); i++) {
			char curChar = cleanIsbn.charAt(i);
			
			// If the character is not a digit, the isbn is invalid
			// Note: We do not have to check for X, since X is only allowed
			// for 10 in ISBN-10s, not ISBN-13s
			if(Character.isDigit(curChar) == false) {
				return false;
			}
			
			// Get the product (current digit * weight)
			int digit = Integer.parseInt("" + curChar);
			int weight = getWeight(i);
			int product = digit * weight;
			
			// Add the product to the sum of products
			sumOfProducts = sumOfProducts + product;
		}

		boolean validity = false; // Whether isbn is valid
		
		// If & only if sum of products is divisible by 10, the isbn is valid
		if (sumOfProducts % 10 == 0) {
			validity = true;
		}
		
		return validity;
	}
	
	/*
	 * Cleans the given isbn of any dashes and whitespace
	 */
	private String clean(String isbn) {
		return isbn.replace("-", "").replace("\\s+", "");
	}

	/*
	 * Gets the weight for the given index
	 * Weight is 1 for even indices and 3 for odd
	 */
	private int getWeight(int index) {
		if (index % 2 == 0) {
			return 1;
		} else {
			return 3;
		}
	}

}
