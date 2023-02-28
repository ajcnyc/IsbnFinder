package com.eplan.isbnfinder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.eplan.isbnfinder.config.Config;
import com.eplan.isbnfinder.validate.Isbn;
import com.eplan.isbnfinder.validate.IsbnValidatorService;
import com.eplan.isbnfinder.validate.validators.flexible.FlexibleIsbnValidatorService;
import com.google.gson.Gson;

@ExtendWith(SpringExtension.class)
@WebMvcTest(IsbnRestController.class)
@ContextConfiguration(classes = Config.class)
class IsbnRestControllerTests {

	@Autowired
	private MockMvc mockMvc_;
	
//	@Autowired
//	private IsbnValidatorService validator_;
	
//	@BeforeEach
//	void setUp() {
//		given(isbnValidatorService.validateIsbn(isbn))
//	}

	@Test
	@DisplayName("GET 1 Good ISBN")
	void get1GoodIsbn() throws Exception {
		MvcResult mvcResult = mockMvc_.perform(MockMvcRequestBuilders.get("/api/validate-with-get?csv=0-06-097329-3"))
				.andExpect(status().isOk()).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String responseStr = mvcResult.getResponse().getContentAsString();
		Gson gson = new Gson();
		Isbn[] isbns = gson.fromJson(responseStr, Isbn[].class);
		assertTrue(isbns.length == 1);
		assertTrue(isbns[0].isbn.equals("0-06-097329-3"));
	}

	@Test
	@DisplayName("GET Mixed Good & Bad ISBNs")
	void getMixedGoodAndBadIsbns() throws Exception {
		// The isbns to test
		String requestStr = "0-06-097329-3,9781621291657,5,a,,hi,1-1-1";
		String[] requestArr = requestStr.split(",");
		
		// The correct validities
		boolean[] validityCheck = {true,true,false,false,false,false,false};
		
		MvcResult mvcResult = mockMvc_.perform(MockMvcRequestBuilders.get("/api/validate-with-get?csv=" + requestStr))
				.andExpect(status().isOk()).andReturn();
		
		// assert successful request
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		// Assert the correct length, isbn strings, and validities
		String responseStr = mvcResult.getResponse().getContentAsString();
		Gson gson = new Gson();
		Isbn[] isbns = gson.fromJson(responseStr, Isbn[].class);
		assertTrue(isbns.length == requestArr.length);
		for (int i = 0; i < isbns.length; i++) {
			assertEquals(isbns[i].getIsbn(), requestArr[i]);
			System.out.println(isbns[i].getIsbn()+","+isbns[i].isValid());
			assertEquals(isbns[i].isValid(), validityCheck[i]);
		}
	}

	@Test
	@DisplayName("GET Junk")
	void getJunk() throws Exception {
		MvcResult mvcResult = mockMvc_.perform(MockMvcRequestBuilders.get("/api/validate-with-get?csv=junk"))
				.andExpect(status().isOk()).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String responseStr = mvcResult.getResponse().getContentAsString();
		Gson gson = new Gson();
		Isbn[] isbns = gson.fromJson(responseStr, Isbn[].class);
		assertTrue(isbns.length == 1);
		assertTrue(isbns[0].isbn.equals("junk"));
		assert (isbns[0].isValid() == false);
	}
	
	@Test
	@DisplayName("GET Blank")
	void getBlank() throws Exception {
		MvcResult mvcResult = mockMvc_.perform(MockMvcRequestBuilders.get("/api/validate-with-get?csv="))
				.andExpect(status().isOk()).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String responseStr = mvcResult.getResponse().getContentAsString();
		Gson gson = new Gson();
		Isbn[] isbns = gson.fromJson(responseStr, Isbn[].class);
		assertTrue(isbns.length == 1);
		assertTrue(isbns[0].isbn.equals(""));
		assert (isbns[0].isValid() == false);
	}
	
	@Test
	@DisplayName("GET Nothing")
	void getNothing() throws Exception {
		MvcResult mvcResult = mockMvc_.perform(MockMvcRequestBuilders.get("/api/validate-with-get"))
				.andExpect(status().isOk()).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String responseStr = mvcResult.getResponse().getContentAsString();
		Gson gson = new Gson();
		Isbn[] isbns = gson.fromJson(responseStr, Isbn[].class);
		assertTrue(isbns.length == 1);
		assertTrue(isbns[0].isbn.equals(""));
		assert (isbns[0].isValid() == false);
	}

	@Test
	@DisplayName("GET Malformed 1")
	void getMalformed1() throws Exception {
		MvcResult mvcResult = mockMvc_.perform(MockMvcRequestBuilders.get("/api/validate-with-get?"))
				.andExpect(status().isOk()).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String responseStr = mvcResult.getResponse().getContentAsString();
		Gson gson = new Gson();
		Isbn[] isbns = gson.fromJson(responseStr, Isbn[].class);
		assertTrue(isbns.length == 1);
		assertTrue(isbns[0].isbn.equals(""));
		assert (isbns[0].isValid() == false);
	}
	
	@Test
	@DisplayName("GET Malformed 2")
	void getMalformed2() throws Exception {
		MvcResult mvcResult = mockMvc_.perform(MockMvcRequestBuilders.get("/api/validate-with-get?c"))
				.andExpect(status().isOk()).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String responseStr = mvcResult.getResponse().getContentAsString();
		Gson gson = new Gson();
		Isbn[] isbns = gson.fromJson(responseStr, Isbn[].class);
		assertTrue(isbns.length == 1);
		assertTrue(isbns[0].isbn.equals(""));
		assert (isbns[0].isValid() == false);
	}
}
