package com.eplan.isbnfinder.database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

class IsbnDAOTests {
	private Connection connection;
	private IsbnDAO isbnDAO;

	@BeforeEach
	public void setUp() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/isbn_validator";
		String user = "share";
		String password = "NOTSECURE";
		connection = DriverManager.getConnection(url, user, password);
		isbnDAO = new IsbnDAO(new DriverManagerDataSource(url, user, password));
	}

	@Test
	public void testAddIsbnRecord() throws SQLException {
		String isbn = "1234567890";
		String sql = "SELECT * FROM isbn_requests WHERE isbn = '"+isbn+"'";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		int initialTimesSearched = 0;
		
		if (resultSet.next()) {
	        initialTimesSearched = resultSet.getInt("times_searched");
	    }
	    
	    assert(initialTimesSearched >= 0);
	    
	    // Add a record to the database
	    IsbnRecord isbnRecord = new IsbnRecord(isbn);
	    isbnDAO.addIsbnRecord(isbnRecord);

	    resultSet = statement.executeQuery(sql);
	    // Verify that the record was added correctly
	    assertTrue(resultSet.next());
	    assertEquals(isbn, resultSet.getString("isbn"));
	    assertEquals(initialTimesSearched+1, resultSet.getInt("times_searched"));
	}

}
