package com.eplan.isbnfinder.validate.apache.validators;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.eplan.isbnfinder.validate.Isbn;

class ApacheIsbn10ValidatorTests {
 
	private ApacheIsbn10Validator validator_;
	
	@BeforeEach
	void setUp() {
		validator_ = new ApacheIsbn10Validator();
	}
	
	@Test
	@DisplayName("Mock Test Validating 1 Good String")
	void mockTestValidate1GoodString() {
		String isbnStr = "0-06-097329-3";
		ApacheIsbn10Validator validator = mock(ApacheIsbn10Validator.class);
		Isbn[] validated = {new Isbn("0-06-097329-3", true)};
		when(validator.validate(isbnStr)).thenReturn(validated);		
		
		assertEquals(validated.length, 1);
		assertEquals(validated[0].isbn,"0-06-097329-3");
		assertEquals(validated[0].isValid(),true);
	}

	@Test
	@DisplayName("Implementation Test Validating 1 Good String")
	void implementationTestValidate1GoodString() {
		String isbnStr = "0-06-097329-3";
		Isbn[] validated = validator_.validate(isbnStr);	
		
		assertEquals(validated.length, 1);
		assertEquals(validated[0].isbn,"0-06-097329-3");
		assertEquals(validated[0].isValid(),true);
	}
	
}
