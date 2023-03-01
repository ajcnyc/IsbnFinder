package com.eplan.isbnfinder.database;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Data Access Object for the ISBN database to track request made to the server
 * 
 * @author Alex Cohen
 *
 */
public class IsbnDAO {
	private JdbcTemplate jdbcTemplate_; // The JDBC template to use

	@Autowired
	public IsbnDAO(DataSource dataSource) {
		jdbcTemplate_ = new JdbcTemplate(dataSource);
	}

	/**
	 * Creates IsbnRecords from the given ISBN Strings and adds the records to the
	 * database
	 * 
	 * @param isbnRecords The ISBN Strings
	 */
	public void addIsbnRecords(String[] isbns) {
		IsbnRecord[] isbnObjRecords = new IsbnRecord[isbns.length];

		for (int i = 0; i < isbns.length; i++) {
			isbnObjRecords[i] = new IsbnRecord(isbns[i]);
		}

		addIsbnRecords(isbnObjRecords);
	}

	/**
	 * Adds the given IsbnRecords to the database
	 * 
	 * @param isbnRecords The IsbnRecords to add
	 */
	public void addIsbnRecords(IsbnRecord[] isbnRecords) {
		IsbnRecord[] cleanRecords = clean(isbnRecords);

		for (int i = 0; i < cleanRecords.length; i++) {
			addIsbnRecord(cleanRecords[i]);
		}
	}

	/**
	 * Adds a given IsbnRecords to the database
	 * 
	 * @param isbnRecords The IsbnRecord to add
	 */
	public void addIsbnRecord(IsbnRecord isbnRecord) {
		String sql = "INSERT INTO isbn_requests (isbn, times_searched)\r\n" + "VALUES (?, 1)\r\n"
				+ "ON DUPLICATE KEY UPDATE times_searched = times_searched + 1";
		int check = jdbcTemplate_.update(sql, isbnRecord.getIsbn());
		System.out.println("check: " + check);
	}

	/*
	 * Cleans each IsbnRecord's ISBN in the given array of any dashes and whitespace
	 */
	private IsbnRecord[] clean(IsbnRecord[] isbns) {
		for (int i = 0; i < isbns.length; i++) {
			IsbnRecord isbn = isbns[i];
			isbns[i].setIsbn(clean(isbn.getIsbn()));
		}
		return isbns;
	}

	/*
	 * Cleans the given isbn of any dashes and whitespace
	 */
	private String clean(String isbn) {
		return isbn.replace("-", "").replace("\\s+", "");
	}
}
