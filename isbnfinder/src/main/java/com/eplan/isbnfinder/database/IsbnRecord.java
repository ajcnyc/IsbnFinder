package com.eplan.isbnfinder.database;

/**
 * A record of an ISBN Number (could be ISBN-10 or ISBN-13) and the total number
 * of times it has been searched for
 * 
 * @author Alex Cohen
 *
 */
public class IsbnRecord {

	private int id_; // The id automatically set in SQL
	private String isbn_; // The ISBN number
	private int timesSearched_; // The total number of times it has been searched for automatically incremented
								// in JDBC query

	/**
	 * Creates an ISBN Record with the given ISBN
	 * 
	 * @param isbn The ISBN number
	 */
	public IsbnRecord(String isbn) {
		isbn_ = isbn;
	}

	/**
	 * Gets the SQL ID of this record
	 * 
	 * @return The SQL ID
	 */
	public int getId() {
		return id_;
	}

	/**
	 * Sets the SQL ID of this record
	 * 
	 * @param The SQL ID
	 */
	public void setId(int id) {
		id_ = id;
	}

	/**
	 * Gets the ISBN number of this record
	 * 
	 * @return The ISBN number
	 */
	public String getIsbn() {
		return isbn_;
	}

	/**
	 * Sets the ISBN number of this record
	 * 
	 * @param The ISBN number
	 */
	public void setIsbn(String isbn) {
		isbn_ = isbn;
	}

	/**
	 * Gets the number of times the ISBN number was searched for
	 * 
	 * @return The number of times the ISBN number was searched for
	 */
	public int getTimesSearched() {
		return timesSearched_;
	}

	/**
	 * Sets the number of times the ISBN number was searched for
	 * 
	 * @param The number of times the ISBN number was searched for
	 */
	public void setTimesSearched(int timesSearched) {
		timesSearched_ = timesSearched;
	}

}
