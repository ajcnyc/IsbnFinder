package com.eplan.isbnfinder.validate;

/**
 * Represents an ISBN Number Whose Validity is Known
 * 
 * @author Alex Cohen
 *
 */
public class Isbn {
	
	private String isbn_; // The ISBN Number
	private boolean validity_; // Whether the ISBN is valid or not
	
	/**
	 * Creates a new ISBN Number
	 * 
	 * @param isbn The ISBN number as a String
	 * @param validity Whether the ISBN is valid or not
	 */
	public Isbn(String isbn, boolean validity) {
		isbn_ = isbn;
		validity_ = validity;
	}

	/**
	 * Gets the ISBN number as a String
	 * 
	 * @return The ISBN number
	 */
	public String getIsbn() {
		return isbn_;
	}

	/**
	 * Sets the ISBN number
	 * 
	 * @param isbn The ISBN number to set
	 */
	public void setIsbn(String isbn) {
		isbn_ = isbn;
	}

	/**
	 * Checks if this ISBN is valid
	 * 
	 * @return true if valid, false if not
	 */
	public boolean isValid() {
		return validity_;
	}

	/**
	 * Sets whether this ISBN is valid
	 * 
	 * @param validity true if valid, false if not
	 */
	public void setValidity(boolean validity) {
		validity_ = validity;
	}
	
	
}
