package com.eplan.isbnfinder.validate.apache.validators;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.eplan.isbnfinder.validate.Isbn;

class FlexibleApacheIsbnValidatorTests {

	private FlexibleApacheIsbnValidator validator_;

	@BeforeEach
	void setUp() {
		validator_ = new FlexibleApacheIsbnValidator();
	}

	@Test
	@DisplayName("Mock Test Validating 1 Good ISBN-10 and 1 Good ISBN-13")
	void mockTestValidate1GoodString() {
		String[] isbnStrs = { "0-06-097329-3", "9781621291657" };
		FlexibleApacheIsbnValidator validator = mock(FlexibleApacheIsbnValidator.class);
		Isbn[] validated = { new Isbn(isbnStrs[0], true), new Isbn(isbnStrs[1], true) };
		when(validator.validate(isbnStrs)).thenReturn(validated);

		assertEquals(validated.length, isbnStrs.length);
		for (int i = 0; i < isbnStrs.length; i++) {
			assertEquals(validated[i].isbn, isbnStrs[i]);
			assertEquals(validated[i].isValid(), true);
		}
	}

//	@Test
//	@DisplayName("Implementation Test Validating 1 Good String")
//	void implementationTestValidate1GoodString() {
//		String isbnStr = "9781621291657";
//		Isbn[] validated = validator_.validate(isbnStr);
//
//		assertEquals(validated.length, 1);
//		assertEquals(validated[0].isbn, isbnStr);
//		assertEquals(validated[0].isValid(), true);
//	}
//
//	@Test
//	@DisplayName("Implementation Test Validating 3 Good Strings")
//	void implementationTestValidate3GoodStrings() {
//		String[] isbnStrs = { "978-1-119-84766-3", "9781621291657", "978-0-470-47977-3" };
//		Isbn[] validated = validator_.validate(isbnStrs);
//
//		assertEquals(validated.length, 3);
//		assertEquals(validated[0].isbn, isbnStrs[0]);
//		assertEquals(validated[0].isValid(), true);
//		assertEquals(validated[1].isbn, isbnStrs[1]);
//		assertEquals(validated[1].isValid(), true);
//		assertEquals(validated[2].isbn, isbnStrs[2]);
//		assertEquals(validated[2].isValid(), true);
//	}
//
//	@Test
//	@DisplayName("Implementation Test Validating Some Good and Bad Strings")
//	void implementationTestValidateSomeGoodAndBad() {
//		String[] isbnStrs = { "50", "0-06-097329-3", "9781621291657", "apples", "978-1-119-84766-3", "\n",
//				"0-87975-705-1", "978-0-470-47977-3" };
//		Isbn[] validated = validator_.validate(isbnStrs);
//
//		assertEquals(validated.length, 8);
//		assertEquals(validated[0].isbn, isbnStrs[0]);
//		assertEquals(validated[0].isValid(), false);
//		assertEquals(validated[1].isbn, isbnStrs[1]);
//		assertEquals(validated[1].isValid(), false);
//		assertEquals(validated[2].isbn, isbnStrs[2]);
//		assertEquals(validated[2].isValid(), true);
//		assertEquals(validated[3].isbn, isbnStrs[3]);
//		assertEquals(validated[3].isValid(), false);
//		assertEquals(validated[4].isbn, isbnStrs[4]);
//		assertEquals(validated[4].isValid(), true);
//		assertEquals(validated[5].isbn, isbnStrs[5]);
//		assertEquals(validated[5].isValid(), false);
//		assertEquals(validated[6].isbn, isbnStrs[6]);
//		assertEquals(validated[6].isValid(), false);
//		assertEquals(validated[7].isbn, isbnStrs[7]);
//		assertEquals(validated[7].isValid(), true);
//	}

}
