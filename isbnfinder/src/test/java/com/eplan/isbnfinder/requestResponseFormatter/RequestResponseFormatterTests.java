package com.eplan.isbnfinder.requestResponseFormatter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.eplan.isbnfinder.requestResponse.formatter.RequestResponseFormatter;

@DisplayName("Request and Response Formatting Test Case")
class RequestResponseFormatterTests {

	private RequestResponseFormatter formatter_;
	
	@BeforeEach
	void setUp() {
		formatter_ = new RequestResponseFormatter();
	}
	
	@Test
	@DisplayName("Test Formatting a Good CSV Request")
	void testFormatGoodRequest() {
		// 200 is Okay, Because We're Only Testing the CSV Parsing Here, not ISBN Validity
		String requestString = "0-06-097329-3,9781621291657,978-1-119-84766-3,200";
		String[] isbnArr = formatter_.formatRequest(requestString);
		assertEquals(isbnArr.length, 4);
	}
	
	@Test
	@DisplayName("Test Formatting a Good CSV Request, but with Extra Whitespace")
	void testFormatWhitespaceRequest() {
		String requestString = "          0-06  -0 97329-3, 97 81 62          1291657, 978-1- 119-84 766 -3,      200               ";
		String[] isbnArr = formatter_.formatRequest(requestString);
		assertEquals(isbnArr.length, 4);
	}

	@Test
	@DisplayName("Test Formatting a Bad CSV Request That's All Commas")
	void testAllCommasRequest() {
		String requestString = ",,,,,,";
		String[] isbnArr = formatter_.formatRequest(requestString);
		assertEquals(isbnArr.length, 7);
	}
	
	@Test
	@DisplayName("Test Formatting a Bad CSV Request That's All Commas Except 1 ISBN in the Middle")
	void testAllCommasRequestButMiddle() {
		String requestString = ",,,978-1- 119-84 766 -3,,,";
		String[] isbnArr = formatter_.formatRequest(requestString);
		assertEquals(isbnArr.length, 7);
	}
	
	@Test
	@DisplayName("Test Formatting a Bad CSV Request That's All Commas Except 1 ISBN at the Start")
	void testAllCommasRequestButStart() {
		String requestString = "978-1- 119-84 766 -3,,,,,,";
		String[] isbnArr = formatter_.formatRequest(requestString);
		assertEquals(isbnArr.length, 7);
	}
	
	@Test
	@DisplayName("Test Formatting a Bad CSV Request That's All Commas Except 1 ISBN at the End")
	void testAllCommasRequestButEnd() {
		String requestString = ",,,,,,978-1- 119-84 766 -3";
		String[] isbnArr = formatter_.formatRequest(requestString);
		assertEquals(isbnArr.length, 7);
	}
	
	@Test
	@DisplayName("Test Formatting a Bad Request That Contains Random Junk")
	void testJunkRequest() {
		String requestString = "agikj2094t!!gjoajl;k";
		String[] isbnArr = formatter_.formatRequest(requestString);
		assertEquals(isbnArr.length, 1);
	}
	
	@Test
	@DisplayName("Test Formatting a Bad Request That's empty")
	void testFormatEmptyRequest() {
		String requestString = "";
		String[] isbnArr = formatter_.formatRequest(requestString);
		assertEquals(isbnArr.length, 1);
	}
	
	// TODO: TEST FORMATTING RESPONSES_________________________________________
}
