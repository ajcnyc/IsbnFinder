package com.eplan.isbnfinder.validate.validators.isbn10;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.eplan.isbnfinder.validate.Isbn;
import com.eplan.isbnfinder.validate.validators.isbn10.ApacheIsbn10ValidatorService;

class ApacheIsbn10ValidatorTests {
 
	private ApacheIsbn10ValidatorService validator_;
	
	@BeforeEach
	void setUp() {
		validator_ = new ApacheIsbn10ValidatorService();
	}
	
	@Test
	@DisplayName("Mock Test Validating 1 Good String")
	void mockTestValidate1GoodString() {
		String isbnStr = "0-06-097329-3";
		ApacheIsbn10ValidatorService validator = mock(ApacheIsbn10ValidatorService.class);
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
	
	@Test
	@DisplayName("Implementation Test Validating 3 Good Strings")
	void implementationTestValidate3GoodStrings() {
		String[] isbnStrs = {"0-06-097329-3","0-87975-705-1","0-471-77088-4"};
		Isbn[] validated = validator_.validate(isbnStrs);	
		
		assertEquals(validated.length, 3);
		assertEquals(validated[0].isbn,"0-06-097329-3");
		assertEquals(validated[0].isValid(),true);
		assertEquals(validated[1].isbn,"0-87975-705-1");
		assertEquals(validated[1].isValid(),true);
		assertEquals(validated[2].isbn,"0-471-77088-4");
		assertEquals(validated[2].isValid(),true);
	}
	
	@Test
	@DisplayName("Implementation Test Validating Some Good and Bad Strings")
	void implementationTestValidateSomeGoodAndBad() {
		String[] isbnStrs = {"50","0-06-097329-3","9781621291657","apples","978-1-119-84766-3","\n","0-87975-705-1"};
		Isbn[] validated = validator_.validate(isbnStrs);	
		
		assertEquals(validated.length, 7);
		assertEquals(validated[0].isbn,"50");
		assertEquals(validated[0].isValid(),false);
		assertEquals(validated[1].isbn,"0-06-097329-3");
		assertEquals(validated[1].isValid(),true);
		assertEquals(validated[2].isbn,"9781621291657");
		assertEquals(validated[2].isValid(),false);
		assertEquals(validated[3].isbn,"apples");
		assertEquals(validated[3].isValid(),false);
		assertEquals(validated[4].isbn,"978-1-119-84766-3");
		assertEquals(validated[4].isValid(),false);
		assertEquals(validated[5].isbn,"\n");
		assertEquals(validated[5].isValid(),false);
		assertEquals(validated[6].isbn,"0-87975-705-1");
		assertEquals(validated[6].isValid(),true);
	}
	
}
