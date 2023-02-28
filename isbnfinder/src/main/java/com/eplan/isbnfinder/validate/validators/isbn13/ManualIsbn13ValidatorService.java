package com.eplan.isbnfinder.validate.validators.isbn13;

import org.springframework.stereotype.Service;

import com.eplan.isbnfinder.validate.Isbn;

/**
 * Validator to manually validate 13-digit ISBNs
 * 
 * @author Alex Cohen
 *
 */
@Service
public class ManualIsbn13ValidatorService extends Isbn13ValidatorService {

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
		return new Isbn(isbn, validateSingle(isbn));
	}

	@Override
	public boolean validateSingle(String isbn) {
		// The isbn cleaned of dashes and whitespace
		String cleanIsbn = clean(isbn);
		
		// If blank, return false
		if(cleanIsbn.equals("")) {
			return false;
		}
		
		int sumOfProducts = 0; // The sum of the products

		// For each character in cleanIsbn
		for (int i = 0; i < cleanIsbn.length(); i++) {
			char curChar = cleanIsbn.charAt(i);
			
			// If the character is not a digit, the isbn is invalid
			// Note: We do not have to check for X, since X is only allowed
			// for 10 in ISBN-10s, not ISBN-13s
			if(isDigit(curChar) == false) {
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
	
	/*
	 * Checks if char is a digit 0-9
	 */
	private boolean isDigit(char ch) {
		switch(ch) {
			case '0': return true;
			case '1': return true;
			case '2': return true;
			case '3': return true;
			case '4': return true;
			case '5': return true;
			case '6': return true;
			case '7': return true;
			case '8': return true;
			case '9': return true;
			default: return false;
		}
	}

}
